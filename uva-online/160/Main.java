import java.util.*;
import java.io.*;

public class Main {

    static final int primes[] = {2 , 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                                 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n;

        while ( (n = sc.nextInt()) != 0) {
        
            int printed = 0;
            System.out.format("%3d! =", n);

            for (int i : primes) {

                if ( i > n)
                    break;

                int tn = n, occ = 0;

                while (tn != 0) {
                    tn /= i;
                    occ += tn;
                }

                if (printed == 15)
                    System.out.format("\n%9d", occ);
                else 
                    System.out.format("%3d" , occ);

                printed++;
            }
                
            System.out.println();
        }

        sc.close();
    }
    
    static void generatePrimes() {
        System.out.print("{2 ");
        for (int i=3;i<100;i++)
            if (prime(i))
                System.out.print(", " + i);
        System.out.print("}");
    }

    static boolean prime(int n) {
        for (int i = 2; i <= n / 2;i++) 
            if ( n % i == 0 )
                return false;
        return true;
    }


}
