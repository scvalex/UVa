import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        while (sc.hasNext()) {
            int n = sc.nextInt();
            BigInteger prod = new BigInteger("1");
            for (int i=1; i<=n; i++) {
                prod = prod.multiply(new BigInteger(Integer.toString(i)));
            }

            int sumDigits = 0;
            for (char c : prod.toString().toCharArray()) {
                sumDigits += (c - 48);
            }

            System.out.println(sumDigits);
        }
        sc.close();
    }
}
