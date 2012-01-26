import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = 1, length,n, pos;
		
		List<Integer> a = new ArrayList<Integer>();
		
		boolean notFirst = false;
		
		do {
		
			n = -sc.nextInt();
			
			if ( n == 1) 
				break;
				
			a = new ArrayList<Integer>();
			
			a.add(n);
				
			while ( true ) {

				n = -sc.nextInt();
				
				if ( n == 1 ) {
					break;
				}
				
				if ( a.get(0) > n ) {
					a.set(0, n);	
				} else {
					pos = Collections.binarySearch(a, n);
					if ( pos < 0 ) {
						pos = - ( pos + 1);
					} 
					
					if ( pos < a.size() ) {
						if (a.get(pos) > n) {
							a.set(pos, n);
						}
					} else {
						a.add(n);
					}
				}	
			}
			
			if (notFirst)
				System.out.println();
			else 
				notFirst = true;	
			System.out.println("Test #" + nTests + ":");
			System.out.println("  maximum possible interceptions: " + a.size());
			nTests++;
				
		} while (true);
		
		sc.close();
	}
	
}
