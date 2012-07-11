import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int l, n;

        List<Integer> cuts = new ArrayList<Integer>();

        while ( true ) {
            l = sc.nextInt();
            if ( l == 0)
                break;

            n = sc.nextInt();

            cuts.clear();
            cuts.add(0);
            
            for (int i=0;i<n;i++) {
                cuts.add(sc.nextInt());
            }

            cuts.add(l);
            
            int res = findBestCut(cuts, n, l);

            System.out.println("The minimum cutting is " + res + ".");

        }

        sc.close();
    }

    private static int findBestCut(List<Integer> cuts, int n, int l ) {
        int res = 0;

        Collections.sort(cuts);

        int C[][] = new int[n + 2][n + 2];

        int bestC, end;

        for (int pbSize = 1; pbSize <= n; pbSize++) {
            for (int start=0; start <= n - pbSize; start++) {
                bestC = Integer.MAX_VALUE;
                end = start + pbSize + 1;
                for (int k = start + 1; k < end; k ++ ) {
                    bestC = Math.min(C[start][k] + C[k][end], bestC);
                }
                C[start][end] = bestC + (cuts.get(end) - cuts.get(start));
            }
        }

        return C[0][n+1];
    }
        
}
