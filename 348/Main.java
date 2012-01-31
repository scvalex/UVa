import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int MAX_N = 20;

	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n, size, k, i, j, bst, a, b, val;
		int [] cols = new int[MAX_N];
		int [] rows = new int[MAX_N];	
		int [][] best = new int [MAX_N][MAX_N];
		int [][] pos = new int [MAX_N][MAX_N];
		int nTests = 0;
		while (sc.hasNext()) {
			nTests++;
			n = sc.nextInt();
			if ( n == 0 ) break;
			for (i=0;i<n;i++) {
				rows[i] = sc.nextInt();
				cols[i] = sc.nextInt();
			}
			
			for (size = 1; size < n; size++) {
				for (i=0; i<n; i++) {
					j = i + size;
					if ( j < n ) {
						bst = Integer.MAX_VALUE;
						for (k=i; k<j; k++) {
							val = best[i][k] + best[k+1][j] + rows[i]*cols[k]*cols[j];
							if ( bst > val ) {	
								bst = val;
								pos[i][j] = k;
							}
								
						}
						best[i][j] = bst;
					}
				}
		

			}
			num = 1;
			System.out.print("Case " + nTests + ": (" );
			showSolution(pos, 0, n-1);
			System.out.println(")");
	
			for (i=0;i<MAX_N;i++)	
				for (j=0;j<MAX_N;j++)
					best[i][j] = pos[i][j] = 0;
		}
		sc.close();
	}

	static int num;

	static void showSolution(int [][] pos, int a, int b) {
		if (a==b) {System.out.print("A" + (num++)); return; }
		if (a != pos[a][b] ) { 
			System.out.print("(");
			showSolution(pos, a, pos[a][b]);
			System.out.print(")");
		} else {
			showSolution(pos, a, pos[a][b]);
		}
		System.out.print(" x ");
		if ( b != pos[a][b] + 1) {
			System.out.print("(");
			showSolution(pos, pos[a][b] + 1,  b);
			System.out.print(")");
		} else { 
			showSolution(pos, pos[a][b] + 1,  b);
		}
	}
}
