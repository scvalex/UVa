import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX_N  = 300;
    private static final int MAX_SUM  = MAX_N;
    private static final int MAX_COIN = MAX_N;
    private static final int MAX_WAYS = MAX_N;

    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        long C[][] = new long[MAX_N + 1][MAX_N + 1];
        long S[][] = new long[MAX_N + 1][MAX_N + 1];
        long T[]   = new long[MAX_N + 1];

        C[0][0] = 1;

        for (int c = 1; c<=MAX_COIN; c++) {
            for (int n=1;n<=MAX_SUM;n++) 
                for (int k=0;k<MAX_WAYS;k++) {
                    if ( n - c >= 0 )
                        C[n][k + 1] += C[n - c][k];
                }
        }
        
        long total;
        for (int n=0; n<=MAX_SUM; n++) {
            total = 0;
            for (int k=0; k<=MAX_WAYS; k++) {
                total += C[n][k];
                S[n][k] = total;
            }
            T[n] = total;
        }

        boolean first = true;

        while ( sc.hasNext() ) {
            String nums[] = sc.nextLine().split(" ");
            int n = Integer.parseInt(nums[0]);
            if ( nums.length == 1 ) {
                System.out.print(T[n]);
            } else {
                int l1 = Integer.parseInt(nums[1]);
                l1 = Math.min(MAX_SUM, l1);
                if ( nums.length == 2) {
                    System.out.print(S[n][l1]);
                } else {
                    int l2 = Integer.parseInt(nums[2]);
                    l2 = Math.min(MAX_SUM, l2);
                    System.out.print(S[n][l2] - S[n][l1] + C[n][l1]);
                }
            }

            System.out.println();
            
        }

        sc.close();
    }
}
