// Solution to 10018 - Reverse and Add; STATUS: 
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static long pal;
	static int it;
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n = sc.nextInt();
		long num;
		for (int i = 0; i < n; i++) {
			num = sc.nextInt();
			getPalindrome(num);
			System.out.println(it  + " " + pal);
		}
	}
	
	static void getPalindrome(long num) {
		it = 0;
		pal = num;
		while ( !palindrome(pal) ) {
			pal += reverse(pal);
			it++;
		}
	}
	
	static boolean palindrome(long num) { 
		return (num == reverse(num));
	}
	
	static long reverse(long num) {
		long rev = 0;
		while (num > 0) {
			rev = (num % 10) + rev * 10; 
			num /= 10;
		}
		return rev;
	}
}
