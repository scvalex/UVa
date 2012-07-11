import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.*;

public class Main {
        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
                int sum, n;
                DecimalFormat df = new DecimalFormat("0.000");

                sc.nextInt();
                
                int[] grades;
                int aboveAvg, i;

                while ( sc.hasNext() ) {
                        n = sc.nextInt();               
                        sum = 0;
                        grades = new int[n];
                        
                        for (i = 0; i < n; i++) {
                                grades[i] = sc.nextInt();
                                sum += grades[i];
                        }
                        
                        double avg = (double)sum / (double)n;
                        aboveAvg = 0;
                        for (i=0;i<n;i++) {
                                if (grades[i] > avg)
                                        aboveAvg++;
                        }
                        
                        
                        System.out.println( df.format((double)aboveAvg * 100/ (double)n) + "%");
                }
                sc.close();
        }
}
