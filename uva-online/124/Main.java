import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int cstr[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        boolean first = true;
        while (sc.hasNext()) {

            if (!first)
                System.out.println();

            first = false;

            cstr = new int[30][30];

            String letters[] = sc.nextLine().split(" ");
            
            String cons[] = sc.nextLine().split(" ");
            for (int i=0;i<cons.length;i+=2) {
                cstr[cons[i].charAt(0) - 'a'][cons[i+1].charAt(0) - 'a'] = 1;
            }

            Arrays.sort(letters);
            ArrayList<Character> chars = new ArrayList<Character>();
            for (String s : letters)
                chars.add(s.charAt(0));

            backtrack(chars, "");

            
        }
        
        sc.close();
    }

    static void backtrack(List<Character> availableChars, String sol) {
        if (availableChars.size() == 0) 
            System.out.println(sol);
        else {
            for (Character c : availableChars) {
                if (valid(c, sol)) {
                    List<Character> newChars = new ArrayList<Character>(availableChars);
                    newChars.remove(c);
                    backtrack(newChars, sol + c);
                }
            }
        }
    }

    static boolean valid(Character toCheck, String sol) {
        boolean valid = true; 
        for (char c : sol.toCharArray()) {
            if (cstr[toCheck - 97][c-97] == 1) {
                valid = false;
                break;
            }
        }
        return valid;
    }

}
