import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int [][] best;
	static int s,n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt();
		
		int [] coinsT;
		int [] coinsV;
		int ss, posi, posj;
		
		for (int kk = 1; kk <= nTests; kk++ ) {
			n = sc.nextInt();
			s = sc.nextInt();
			ss = s *s;
			
			best = new int[s+1][s+1];
			coinsV = new int[n];
			coinsT = new int[n];
			
			for (int i=0;i<n;i++) {
				coinsV[i] = sc.nextInt();
				coinsT[i] = sc.nextInt();
				best[coinsV[i]][coinsT[i]] = 1;
			}
			
			//printBest();
			int bestCount = Integer.MAX_VALUE;
			
			for (int i=0;i<=s;i++) {
				for (int j=0;j<=s;j++)
					if ( best[i][j] != 0 ) {
						for (int c=0;c<coinsV.length;c++) {
							posi = i + coinsV[c];
							posj = j + coinsT[c];
							if ( posi <= s && posj <= s) {
								if ( best[posi][posj] == 0 )
									best[posi][posj] = best[i][j] + 1;		
								else
									best[posi][posj] = Math.min(best[posi][posj], best[i][j] + 1);		
								if (posi*posi + posj*posj == ss && best[posi][posj] < bestCount) 
									bestCount = best[posi][posj];
							}				
						}
					}
			}
			
			if ( bestCount == Integer.MAX_VALUE )
				System.out.println("not possible");
			else 
				System.out.println(bestCount);

		}
		sc.close();
	}
	
	static void printBest() {
		for (int i=0;i<=s;i++) {
			for (int j=0;j<=s;j++)
				System.out.print(best[i][j] + " ");
			System.out.println();
		}
	}
}
