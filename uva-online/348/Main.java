import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

        static final int MAX_N = 20;
        static final int [] cols = new int[MAX_N];
        static final int [] rows = new int[MAX_N];      
        static final int [][] best = new int [MAX_N][MAX_N];
        static final int [][] pos = new int [MAX_N][MAX_N];

        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
                int n, size, k, i, j, bst, a, b, val;
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
                        sb.append("Case " + nTests + ": " );
                        showSolution(0, n-1);
                        sb.append("\n");
        
                        for (i=0;i<MAX_N;i++)   
                                for (j=0;j<MAX_N;j++)
                                        best[i][j] = pos[i][j] = 0;
                }
                
                System.out.print(sb.toString());
                
                sc.close();
        }

        static int num;
        static StringBuilder sb = new StringBuilder("");

        static  void showSolution(int a, int b) {
                if (a==b) {sb.append("A" + (num++)); return; }
                sb.append("(");
                showSolution(a, pos[a][b]);
                sb.append(" x ");
                showSolution(pos[a][b] + 1,  b);
                sb.append(")");
        }
}
