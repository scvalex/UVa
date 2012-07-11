import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	int test = 1;
	String nums[];
	int p, e, i, d;
	int x, y, z;

	boolean first = true;

	while (true) {
	    
	    nums = br.readLine().split(" ");
	    p = Integer.parseInt(nums[0]);
	    if ( p == -1 )
		break;

	    e = Integer.parseInt(nums[1]);
	    i = Integer.parseInt(nums[2]);
	    d = Integer.parseInt(nums[3]);
	    
	    int max = (21252 + d) / 33;

	    x = i - p;
	    y = i - e;

	    for ( z = - i; z <=max && 
		   !(((y + z * 33) % 28 == 0) && ((x + z * 33)% 23  == 0) 
		     && 33 * z + i > d); 
                 z++ ) 
		;

	    // gives WA without final endline
	    System.out.format("Case %d: the next triple peak occurs in %d days.\n",
			      test++, 33 * z + i - d);
	}

	br.close();
    }
}
