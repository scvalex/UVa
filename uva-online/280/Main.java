import java.util.*;
import java.io.*;

public class Main {
    
    static int n;
    static int[] reached;

    static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	n = Integer.parseInt(br.readLine());
	while (n != 0) {

	    map.clear();
	    
	    String[] line = br.readLine().split(" ");

	    int v = Integer.parseInt(line[0]);
	    while (v != 0) {
		for (int k=1;k<line.length - 1;k++) {
		    int q = Integer.parseInt(line[k]);

		    List<Integer> neighbours = map.get(v);
		    if (neighbours == null) {
			neighbours = new ArrayList<Integer>();
			map.put(v, neighbours);
		    }
		    neighbours.add(q);

		}
		line = br.readLine().split(" ");
		v  = Integer.parseInt(line[0]);
	    }
		
	    line = br.readLine().split(" ");
	    int t = Integer.parseInt(line[0]);

	    for (int i=1;i<=t;i++) {
		int k = Integer.parseInt(line[i]);
		reached = new int[n + 1];
		//dfs(k);
		dfsIterative(k);
		StringBuilder out = new StringBuilder();
		int count = 0;
		for (int j=1;j<reached.length;j++) {
		    if (reached[j] == 0) {
			count++;
			out.append(" ").append(j);
		    }
		}

		System.out.format("%d%s\n", count, out);
	    }

	    n = Integer.parseInt(br.readLine());
	}

	br.close();
    }

    static Stack<Integer> stack = new Stack<Integer>();

    public static void dfsIterative(int startNode) {
	//	stack.clear();
	stack.push(startNode);
	while (!stack.isEmpty()) {
	    int node = stack.pop();
	    List<Integer> neighbours = map.get(node);
	    if (neighbours != null) {
		for (Integer i : neighbours)  {
		    if (reached[i] == 0) {
			reached[i] = 1;
			stack.push(i);
		    }
		 }
	    }
	}
	    
    }

    public static void dfs(int currentNode) {
	List<Integer> neighbours = map.get(currentNode);

	if (neighbours == null) 
	    return;

	for (Integer i : neighbours) {
	    if (reached[i] == 0) {
		reached[i] = 1;
		dfs(i);
	    }
	}
    }

}
