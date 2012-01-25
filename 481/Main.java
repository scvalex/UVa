import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 - a[j] - the smallest value which ends an increasing sequence of length j
 - values[]  - stores the values 
 - prev[i] - stores the previous value in the sequence for position i
*/

public class Main {

	static List<Integer> values = new ArrayList<Integer>();
	static List<Integer> a = new ArrayList<Integer>();
	static List<Integer> p = new ArrayList<Integer>();
	static List<Integer> prev = new ArrayList<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int n, maxLen = 0, pos;
		
		int i = 0;
		
		while ( sc.hasNext() ) {

			n = sc.nextInt();

			if ( a.size() > 0 && n > a.get(0) ) {
				pos =  Collections.binarySearch(a, n);
				if ( pos < 0 ) {
					pos = -(pos +1);
				}
 			} else {
 				if ( a.size() > 0 ) {
 					a.set(0, n);
 					p.set(0,i);
 				} else {
					a.add(0, n);	
					p.add(0, i);
 				}
				pos = 0;
			}
			
			if ( pos >= 0 ) {
				if ( pos < a.size() ) {
					if ( a.get(pos) >= n ) {
						a.set(pos, n);
						p.set(pos, i);
					}
				} else {
					a.add(pos, n);
					p.add(pos, i);
				}
			}

			if (pos>=1)
				prev.add(i, p.get(pos-1));
			else 
				prev.add(i, -1);

			values.add(n);

			i++;

		}
		
		pos = p.get(p.size() - 1);
		
		Stack<Integer> stack = new Stack<Integer>();
		while ( pos != -1 ) { 
			stack.push(values.get(pos));
			pos = prev.get(pos);
		}
		
		
		System.out.println(a.size());
		System.out.println("-");
		while ( !stack.isEmpty() ) {
				System.out.println(stack.pop());
		}

		sc.close();
	}
}

