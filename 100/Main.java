// Solution to 100 - 3n+1 problem; STATUS: SOLVED

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		while (sc.hasNext()) {
			long first = sc.nextLong();
			long snd   = sc.nextLong();
			
			if (first < snd) 
				System.out.printf("%d %d %d\n", first, snd,	maxCycleLength(first, snd));
			else 
				System.out.printf("%d %d %d\n", first, snd,	maxCycleLength(snd, first));
		}
		
		sc.close();
	}
	
	private static long maxCycleLength(long i, long j) {
		long max = 0;
		for (long n = i; n <= j; n++) {
			max = Math.max(max, cycleLength(n));
		}
		
		return max;
	}
	
	private static long cycleLength(long n) {
		long len = 1;
		
		while (n > 1) {
				if ( n % 2 == 1 ) 
					n = n * 3 + 1;
				else 
					n = n / 2;
				len++;
		}
		
		return len;
	}
  
}
