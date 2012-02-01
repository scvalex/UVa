import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt();
		
		
		for (int kk=1;kk<=nTests;kk++) {


			int besti = -1, bestj = 0, bestSum = 0;
			int curi = 0, curj = 0, curSum = 0;
			int newSum = 0;
			int i, n, r;

			r = sc.nextInt() - 1;
						
			for ( i=0;i<r;i++) {

				newSum = curSum + sc.nextInt();
				
				if ( newSum > bestSum || ( newSum == bestSum &&  (bestj - besti < curj  - curi ) ) ) {
					besti = curi;
					bestj = curj;
					bestSum = newSum;
				}

				curj++;
				
				curSum = newSum;
				
				if ( curSum  < 0 ) {
					curi = i + 1;
					curj = i + 1;
					curSum = 0;
				}
		}
			
			if ( bestSum > 0 )
			System.out.println("The nicest part of route " + kk + " is between stops " + (besti + 1) + " and " + (bestj + 2));
			else
				System.out.println("Route " + kk + " has no nice parts");
		}
		
		sc.close();
	}
}
