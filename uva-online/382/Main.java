import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static final String PERFECT = "PERFECT";
    static final String DEFICIENT = "DEFICIENT";
    static final String ABUNDANT = "ABUNDANT";

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        System.out.println("PERFECTION OUTPUT");
        String perfection;

        int sum;

        while (true) {
            int n = sc.nextInt();

            if ( n == 0 )
                break;

            sum = ( n == 1 ? 0 : 1);

            for(int i=2; i<=n/2; i++) {
                if ( n % i == 0 )
                    sum+=i;
            }

            perfection = ABUNDANT;
            if (sum == n) {
                perfection = PERFECT;
            } else if ( sum < n ) {
                perfection = DEFICIENT;
            }
            
            String nString = Integer.toString(n);

            int l = 5 - nString.length();

            for (int i=0;i<l;i++)
                nString = " " + nString;


            System.out.println(nString + "  " + perfection);
        }
        
        // WA without newline
        System.out.println("END OF OUTPUT");
        
        sc.close();
    }
}
