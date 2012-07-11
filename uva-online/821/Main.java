import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.*;

public class Main {

        static final int MAX_SIZE = 101;

        public static void main(String[] args) {
        
        
                DecimalFormat df = new DecimalFormat("0.000");
        
                int maxP, length, sum, a, b, nCase = 1;
                int cost[][];
                Set<Integer> s = new HashSet<Integer>();
                
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
                
                while (true) {
                        
                        s.clear();
                        
                        a = sc.nextInt();
                        b = sc.nextInt();
                        
                        if ( a == b && b == 0 )
                                break;
                                
                        cost = new int [MAX_SIZE][MAX_SIZE];
                        maxP = 0;
                        length = 0;
                        
                        
                        while (true) {
                        
                                cost[a][b] = 1;
                                
                                s.add(a);
                                s.add(b);
                                
                                maxP = Math.max(maxP, a);
                                maxP = Math.max(maxP, b);

                                a = sc.nextInt();
                                b = sc.nextInt();
                                
                                if ( a == b && b == 0 ) break;
                                
                                length++;
                        }

                        for (int k=1;k<=maxP;k++){                      
                                for (int i=1;i<=maxP; i++) {
                                        for (int j=1;j<=maxP;j++) {
                                                if ( i != j && cost[i][k] != 0 && cost[k][j] != 0 ) {
                                                        if ( cost[i][j] != 0 ) {
                                                                cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                                                        } else {
                                                                cost[i][j] = cost[i][k] + cost[k][j];
                                                        }
                                                }
                                        }
                                }
                        }
                        
                        sum = 0;
                        
                        for (int i=1;i<=maxP;i++) 
                                for (int j=1;j<=maxP;j++)
                                        sum += cost[i][j];
                        
                        String res = df.format((double)sum/numPairs(s.size()));
                        System.out.println("Case " + (nCase++) + ": average length between pages = " + res + " clicks");
                        
                }       
                sc.close();
        }
        
        static double numPairs(int n) {
                return n * (n - 1);
        }
}
