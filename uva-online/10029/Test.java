import java.util.*;

public class Test {
    public static void main (String[] args) {
	int n = 25000;
	ArrayList<Character> chars = new ArrayList<Character>();
	for ( Character c : "abcdefghabcdefgh".toCharArray()) {
	    chars.add(c);
	}

	if (args.length > 0)
	    n = Integer.parseInt(args[0]);

	for (int i=0;i<25000;i++){
	    Collections.shuffle(chars);
	    for (char c : chars) {
		System.out.print(c);
	    }
	    System.out.println();
	}
    }
	
}