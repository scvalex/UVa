import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
                int b,p,m;
                
                modPow(3,0,1);
                
                while (sc.hasNext()) {
                        b = sc.nextInt();
                        p = sc.nextInt();
                        m = sc.nextInt();

                        if ( m == 1 )
                                System.out.println("0");
                        else if ( p == 0 ) 
                                System.out.println("1");
                        else 
                                System.out.println(modPow(b,p,m));      
                }
                
                sc.close();
        }
        
        static int modPow(int b,int p,int m) {
                if ( p == 1 )
                        return b % m;
                int r  = modPow(b, p/2, m) % m;
                if ( p % 2 == 0) {
                        return (r * r) % m;
                } else {
                        return (((r * r) % m ) * (b % m)) % m;  
                }
                        
        }
}
