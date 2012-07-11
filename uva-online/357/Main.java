import java.util.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

        static final int MAX_N = 30000 + 1;
        static final BigInteger ONE = new BigInteger("1");
        static final BigInteger ZERO = new BigInteger("0");
        

        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
                
                int n;
                
                BigInteger sols;
                BigInteger [] sums = fill();

                while (sc.hasNext()) {
                        n = sc.nextInt();
                        sols = sums[n];
                        if ( sols.equals(ONE) ) {
                                System.out.println("There is only 1 way to produce " + n + " cents change.");                   
                        } else {
                                System.out.println("There are " + sols + " ways to produce " + n + " cents change.");                   
                        }
                }               
                sc.close();
        }
        
        static BigInteger [] fill () {
                BigInteger [] ways = new BigInteger[MAX_N];
                
                int [] coins = {1,5,10,25,50};

                int i, val,j,c;
                
                ways[0] = ONE;
                
                for (j=0; j < coins.length; j++ ) {
                                c = coins[j];
                                for (i = c; i < MAX_N; i ++ ) {
                                        if ( ways[i] == null ) 
                                                ways[i] = ZERO;
                                        ways[i] = ways[i].add(ways[i-c]);
                                }
                }

                return ways;
        }
}
