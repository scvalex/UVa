import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.math.BigInteger;



public class Main {

	static final BigInteger ONE = new BigInteger("1");
	static final BigInteger TWO = new BigInteger("2");

	static final Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) {
		
		sc.nextInt();
	
		while (sc.hasNext() ) {
			System.out.println(hanoi(sc.nextInt()));
		}		
		
		sc.close();
	}
	
	public static BigInteger hanoi(int n) {
		if ( n ==  1 )
			return ONE;
		else 
			return TWO.multiply( hanoi(n-1) ).add(ONE);
	}
}
