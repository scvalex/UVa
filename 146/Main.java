import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		char [] line;
		String lines;
		while (sc.hasNext()) {
			lines = sc.nextLine();
			
			if ( lines.equals("#") ) break;
			
			line = lines.toCharArray();
			
			String np = nextPermutation(line);
			if ( np == null ) {
				System.out.println("No Successor");
			} else {
				System.out.println(new String(line));
			}
			 
		}
		
		sc.close();
	}
	
	private static String nextPermutation(char[] line) {
		int pos;
		int k = -1, l = -1;
		for (int i=line.length - 1; i >= 1; i--) {
			if (line[i] > line[i-1] ) {
				k = i - 1;
				break;
			}
		}
		
//		System.out.println("k: " + k);
		
		if ( k == -1 ) return null;
		
		
		for (int i=line.length - 1; i >= 0; i--) {
			if ( line[i] > line[k] ) {
				l = i;
				break;
			}
		}
		
		swap(line, k, l);
		
//		System.out.println("Before swap: " + Arrays.toString(line));
		for ( int i=k+1; i<k+1+(line.length - k)/2; i++) {
//			System.out.println("Swapping");
			swap(line, i, line.length - (i - k));
		}
//		System.out.println("After swap: " + Arrays.toString(line));
		
		return "";
	}
	
	private static void swap(char[] a, int i, int j) {
		char t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
}
