import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

        static final int N_LETTERS = 26;
        static int[] list = new int[N_LETTERS];
        
        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

                int nTests = sc.nextInt();
                sc.nextLine();
                
                char[] line;
                
                for (int kk = 0; kk < nTests; kk++) {
                        line = sc.nextLine().toCharArray();
                        for ( char c : line ) {
                                if ( Character.isLetter(c) )
                                        list[(int)Character.toUpperCase(c) - 65]++;
                        }
                }
                
        
                List<Pair> ls = new ArrayList<Pair>();
                for (int i=0;i<N_LETTERS;i++)
                        ls.add(new Pair(list[i], (char)(i + 65)));
                
                Collections.sort(ls, new Comp());                       
                
                for (Pair p : ls) 
                        if ( p.freq > 0 )
                                System.out.println( p.c + " " + p.freq);
                
                
                sc.close();
        }
}

class Comp implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2) {
                if (p1.freq < p2.freq) return 1;
                else if (p1.freq == p2.freq) {
                        if ( p1.c < p2.c ) return -1;
                        else if ( p1.c > p2.c ) return 1;
                        return 0;
                } 
                return -1;
        }
}

class Pair {
        int freq;
        char c;
        Pair(int freq, char c){
                this.freq = freq;
                this.c = c;
        }
}
