import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
        static String [] rows = {
                "1234567890-=",
                "QWERTYUIOP[]\\",
                "ASDFGHJKL;'",
                "ZXCVBNM,./"};
        
        public static void main(String[] args) {
                Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
                while (sc.hasNext()) {
                        StringBuilder sb = new StringBuilder();
                        for (char c :  sc.nextLine().toCharArray()) {
                                System.out.print(getLeft(c));
                        }
                        System.out.println();
                }
                sc.close();
        }
        
        static char getLeft(char c){
                int index;
                for (String row : rows) {
                        index = row.indexOf(c);
                        if ( index > 0 )
                                return row.charAt(index-1);
                        else if (index == 0)
                                return row.charAt(0);
                }
                return  ' ';
        }
}
