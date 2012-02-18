import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int MAX_SIZE = 100001;

	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt();
		
		PriorityQueue<Material> queue = new PriorityQueue<Material>();
		ArrayList<Integer> queries = new ArrayList<Integer>();
		ArrayList<Material> materials = new ArrayList<Material>();
		
		int s,q,l,u,i, query;
		long j;
		long L, U, I;
		long max;
		int kk = 0;
		boolean first = true;
		while ( kk < nTests) {
			kk++;

			queue.clear();
			materials.clear();
			queries.clear();

			s = sc.nextInt();
			q = sc.nextInt();
			
			for (i=0;i<s;i++) {
				Material m = new Material(sc.nextLong(), sc.nextLong(), sc.nextLong(), (i+1));
				queue.add(m);
			}

			//System.out.println("------------------------");

			max = 0;
			for (i=0;i<q;i++) {
				query = sc.nextInt();
				queries.add(query);
				max = Math.max(query, max);
			}
			
			while (true) {
				//System.out.println(queue.toString());
				//System.out.println(materials.toString());
				
				Material top = queue.poll();
				if ( top == null )
					break;	
				
				materials.add(new Material(top));
				
				if ( materials.size() > max + 1 || materials.size() > MAX_SIZE )
					break;
				
				if ( top.canReuse() ) {
					queue.add(top.inc());
				}
			}

			//System.out.println(s + " " + q);
			//System.out.println(materials.toString());
			
			for (i=0;i<q;i++) {
				Material m = materials.get(queries.get(i) - 1);
				System.out.println(m.currentHeight + " " + m.type);
			}
			
		}

		sc.close();
	}
}

class Material implements Comparable<Material>{
	long currentHeight;
	long maxHeight;
	long inc;
	int type;
	Material(Material m) {
		this.currentHeight = m.currentHeight;
		this.maxHeight = m.maxHeight;
		this.inc = m.inc;
		this.type = m.type;
	}
	Material(long l, long u, long i, int type) {
		this.currentHeight = l;
		this.maxHeight = u;
		this.inc = i;
		this.type = type;
	}
	
	@Override
	public int compareTo(Material m) {
		if (currentHeight == m.currentHeight)
			return type - m.type;
		if ( currentHeight < m.currentHeight)
			return -1;
		return 1;
	}
	boolean canReuse(){
		return currentHeight + inc <= maxHeight;
	}
	Material inc() {	
		currentHeight += inc;
		return this;
	}
	public String toString() {
		return "(" + currentHeight +  ", " + maxHeight + ", " +  inc + ", " + type + ")";
	}
}
