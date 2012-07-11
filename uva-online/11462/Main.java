import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                int [] nums;

                int n;

                while (true) {

                    String line = "";

                    //long start = System.currentTimeMillis();
                    n = 0;

                    try { 
                        n = Integer.parseInt(br.readLine().trim());
                        if ( n == 0 )
                            break;
                        line = br.readLine();
                    } catch (IOException ioe) {}

                    String[] ns = line.split(" ");

                    nums = new int[n];

                    int idx = 0;

                    for (String s : ns)
                        nums[idx++] = Integer.parseInt(s);

                    long end = System.currentTimeMillis();

                    // System.err.println("read numbers in: " + (end - start) / 1000.0);

                    // start = System.currentTimeMillis();
                    Arrays.sort(nums);
                    // end = System.currentTimeMillis();
                    // System.err.println("Sorted in: " + (end - start) / 1000.0);

                    // start = System.currentTimeMillis();
                    boolean first = true;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < n; i++ ) {
                        if (!first) {
                            sb.append(" ");
                        }
                        sb.append(nums[i]);
                        first = false;
                    }
                    // end = System.currentTimeMillis();

                    System.out.println(sb.toString());

                    // System.err.println("Output in: " + (end - start) / 1000.0);
                }

                sc.close();
        }
}
