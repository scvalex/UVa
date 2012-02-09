import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static final Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) {

		long [][][] sol;
		
		int [] numbers;
		int nTests = 1;
		
		int n,q,d,m;
		
		while (sc.hasNext()) {
			n = sc.nextInt();
			q = sc.nextInt();
			
			if ( n == 0 && q == 0 )
				break;
			
			numbers = new int[n + 1];
			
			for (int i=1;i<=n;i++) {
				numbers[i] = sc.nextInt();
			}

			System.out.println("SET " + (nTests++) + ":");
			for (int qq=1;qq<=q;qq++) {
				d = sc.nextInt();
				m = sc.nextInt();
			
				sol = new long[n + 1][m + 1][d + 1];
				
				for (int i=1;i<=n;i++) {
						for (int k=0;k<d;k++) {
								sol[i][1][k] = sol[i-1][1][k];
						}
						sol[i][1][mod(numbers[i],d)]++;
				}
				
				int r;
				for (int i=1;i<=n;i++) {
					r = mod (numbers[i], d);
					for (int j=2; j<=m; j++){
						for (int k=0; k<d; k++) {
							sol[i][j][ (k + r) % d] = sol[i-1][j][(k + r) % d] + sol[i-1][j-1][k];
						}
					}
				}
				
				System.out.println("QUERY " + qq + ": " + sol[n][m][0]);
			}
		}
		
		sc.close();
	}

	public static int mod(int a, int b) {
		int c = a % b;
		if ( a < 0 )
			c += b;
		return c % b;
	}
	
}
