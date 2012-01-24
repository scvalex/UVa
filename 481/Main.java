import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Store 
 - seq[k] - longest sequence which can be obtained with the numbers up to k.
 - num[i] - the number
 - prev[i] - the previous number from the sequence
 
seq[k+1] = max(1<=i<=k){seq[i] | num[i] < num[k] } + 1

As an optimization, store the list sorted 
*/

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		

		List<Pair> seq = new ArrayList<Pair>();

		int n, maxLen;
		
		Pair prev = null;
		
		Pair best = null;
		
		while ( sc.hasNext() ) {
		
			maxLen = 0;
			
			n = sc.nextInt();
			
			for ( Pair p : seq ) {
				if ( p.num < n && p.len >= maxLen ) {
					prev = p;
				}
			}
			
			Pair newPair = new Pair(n, (prev == null ? 0 : prev.len) + 1);
			newPair.prev = prev;			
			
			seq.add(newPair);
			
			if (best == null) 
				best = newPair;
			else if (newPair.len >= best.len) 
				best = newPair;
		}
		
		Pair c = best;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(best.num);
		while ( c.prev != null )  {
			c = c.prev;
			stack.push(c.num);
		}
		
		System.out.println(best.len);
		System.out.println("-");
		
		while ( !stack.isEmpty() ) {
			System.out.println(stack.pop());
		}
		
		sc.close();
	}
	
	static Pair pair(int num, int len) {
		return new Pair(num, len);
	}
}

class Pair {
	int num;
	int len;
	Pair prev; 
	Pair(int num, int len) {
		this.num = num;
		this.len = len;
	}
	@Override
	public String toString() {
		return "(num: " + num + ", len: " + len + ", prev: " + (prev == null ? "null" : prev.num) + ")" ;	
	}
}
