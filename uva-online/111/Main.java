import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

        static final Map<Integer, Integer> orderMap = new HashMap<Integer, Integer>();

        static final Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        
        static int n;

        public static void main(String[] args) {
                n = sc.nextInt();
                int i;
                for (i=1; i<=n; i++) {
                        orderMap.put(i, sc.nextInt());
                }

                while ( sc.hasNext() ) {
                  List<Integer> nums = read();
                        System.out.println(longestSeq(nums));
                }

                sc.close();
        }
        
        static ArrayList<Integer> read() {
                int t;
                ArrayList<Integer> nums = new ArrayList<Integer>();
                for (int i=0;i<n        ;i++) nums.add(-1);
                for (int i=1; i<=n; i++) {
                        t = sc.nextInt();
                        nums.set(t - 1, orderMap.get(i));
                }
                
                return nums;
        }
        
        static int longestSeq(List<Integer> nums) {
        
                final List<Integer> a = new ArrayList<Integer>();
                int num, pos;
        
                a.add(nums.get(0));
                        
                for (int i=0; i<n; i++) {
                        num = nums.get(i);
                        pos = 0;

                  if ( a.get(0) > num ) {
                                a.set(0, num);
                        } else {
                                pos = Collections.binarySearch(a, num);
                                if ( pos < 0 ) {
                                        pos = -(pos + 1);
                                }
                                if ( pos < a.size() ) {
                                        if ( a.get(pos) > num ) {
                                                a.set(pos, num);
                                        }
                                } else {
                                        a.add(num);
                                }
                        }
                        
                }

                return a.size();
        }
}
