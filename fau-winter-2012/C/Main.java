import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt();
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int s,q,l,u,i,j;
		while (sc.hasNext()) {
			s = sc.nextInt();
			q = sc.nextInt();
			l = sc.nextInt();
			u = sc.nextInt();
			i = sc.nextInt();
			for (j = 0;j<=u; j+=i) {
				queue.add(j);
			}
			
		}

		sc.close();
	}
}

class Pair implements Comparable<Pair>{
	int height;
	int type;
	
	@Override
	public int compareTo(Pair p) {
		return this.height - p.height;
	}
}
