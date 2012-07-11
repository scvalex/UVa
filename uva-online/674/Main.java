import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    /*
      C[0] = 1;
      C[i] = C[i-1] + C[i-5]  + C[i - 10] + C[i - 25] + C[i - 50];
    */
    
    private static long [] C;
    private static final int [] coins = {1, 5, 10, 25, 50};
    private static final int MAX_SIZE = 7489;

    public static void main(String[] args) {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        boolean first = true;
            
        C = new long[MAX_SIZE  + 1];
        C[0] = 1;

        for (int c=0; c < coins.length; c++)  {
            for (int i=0;i<=MAX_SIZE; i++) {
                int pos = i - coins[c];
                if ( pos >= 0 ) {
                    C[i] += C[pos];
                }
            }
        }
        
        while (sc.hasNext()) {
            
            int n = sc.nextInt();

            System.out.println(C[n]);

        }

        sc.close();
    }
}
