import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int WELLINGTON = 13;

    private static final int MAX_SIZE = 101;

    private static final int [] marked = new int [MAX_SIZE];

    public static void main(String[] args) {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	int n;

	StringBuilder sb = new StringBuilder();

	boolean first = true;

	while ( true ) {
	    n = sc.nextInt();

	    if ( n == 0 )
		break;

	    System.out.println(findM(n));
	}

	// gives WA if last line is trimmed

	sc.close();
    }

    private static int findM(int n)  {

	boolean found = false;
	int nMarked = 0, pos = 1, count, m = 1;
	

	List<Integer> sol = new ArrayList<Integer>();
	
	while (!found) {

	    pos = 1;
	    nMarked = 1;
	    
	    for (int i=2;i<marked.length;i++)
		marked[i] = 0;
	    marked[1] = 1;

	    //	    sol.clear();
	    //	    sol.add(1);

	    while ( (pos != WELLINGTON) && (nMarked < n) ) {
		count = 0;
		
		while ( count < m) {
		    pos = ( pos == n ? 1 : pos + 1);

		    if (marked[pos] == 0)
			count++;
		}

		marked[pos] = 1;
		nMarked++;
		sol.add(pos);
	    }

	    found = ( pos == WELLINGTON && nMarked == n);

	    //	    System.out.println("m: " + m + " " + " found: " + found +  " " + sol.toString());
	    
	    m++;
	}

	m--;

	//	System.out.println("m: " + m + " " + sol.toString());

	return m;
    }
     
}
