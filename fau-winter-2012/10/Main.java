import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	
	static final int MAX_SIZE = 4000;

	public static void main(String[] args) {
	
		int nTests = sc.nextInt();
		int totalLength = 0;
		int dist, n;
		int maxLength = 0, maxSize;
		
		int [] ds;
		int [][] C;
		int [] length;
		int [] cost;
		int [][] Sol;
		
		for (int kk=0;kk<nTests;kk++) {
		
			totalLength = 0;
			maxLength   = 0;
					
			dist = sc.nextInt();
			n = sc.nextInt();
			
			cost = new int[MAX_SIZE];
			length = new int[MAX_SIZE];
			
			
			for ( int i=1;i<=n;i++) {
				length[i] = sc.nextInt();
				cost[i] = -sc.nextInt();
				
				maxLength = Math.max(maxLength, length[i]);
				
				totalLength += length[i];
			}
			
			if ( totalLength < dist ) {
				System.out.println("IMPOSSIBLE");
			} else {
				
				maxSize = dist + maxLength + 100;
				
				C = new int [maxSize + 1][maxSize + 1];
				Sol = new int [maxSize + 1][maxSize + 1];
				
				for (int i=0; i<n; i++) 
					C[i][0] = 0;
				for (int w=1; w<=maxSize; w++)
					C[0][w] = -400000000;
				
				for (int i=1; i <= n; i++ )
					for (int d=1; d <= maxSize; d++) {
						if ( length[i] > d ) {
							C[i][d] = C[i-1][d];
							Sol[i][d] = 0;
						}
						else {
							if ( C[i-1][d] <= C[i-1][d-length[i]] + cost[i] ) {
								C[i][d]  = C[i-1][d-length[i]] + cost[i];
								Sol[i][d] = 1;
							} else {
								C[i][d] = C[i-1][d];
							}
						}
					}
				
				
				int res = Integer.MAX_VALUE;
				
				int pos = 0;
				
				for (int i=dist;i<=maxSize;i++)
					if ( res != -400000000 && res > -C[n][i]) {
						res = -C[n][i];
						pos = i;
					}
				
				int posn = n;
				
				StringBuilder sb = new StringBuilder();
				 
				while ( posn > 0 && pos >= 0 ) {

					sb.append(Integer.toString(Sol[posn][pos]));			
							
					if ( Sol[posn][pos] == 1 ) 
						pos -= length[posn];

					posn--;
				}
				
				System.out.println(res + " " + sb.reverse().toString());
			}
		}
		sc.close();
	}
}
