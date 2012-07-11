import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

                int n = sc.nextInt();
                int nCars, kk, kkk;

/*(
                for ( int i =0;i<2000; i++ ) {
                        System.out.println(i);
                }
*/

                int sum = 0;

                for ( int i=0;i<n;i++)
                        for (int j = 0; j<n; j++)
                                sum += i * j;
                System.out.println(sum);                
/*              for ( kk=1; kk<=n; kk++) {
                        nCars = sc.nextInt();
                        for ( kkk=1; kkk <=nCars; kkk++) {
                                System.out.println(sc.nextInt());
                        }
                }
*/
                sc.close();
        }
}
