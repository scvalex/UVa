import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m[][] = new int[n][n];

        for (int i=1;i<n;i++){
            String line[] = br.readLine().split(" ");
            for (int j=0;j<i;j++) {
                int val;
                if ("x".equals(line[j]))
                    val = Integer.MAX_VALUE;
                else {
                    val = Integer.parseInt(line[j]);
                }
                m[i][j] = m[j][i] = val;
            }
        }
        
        int longestTime = -1;

        Queue<Node> dist = new PriorityQueue<Node>();
        Map<Integer, Node> lookup = new HashMap<Integer, Node>();

        for (int i=1;i<n;i++) {
            Node node = new Node(i);
            dist.add(node);
            lookup.put(i, node);
        }

        Node start = new Node(0,0);
        dist.add(start);
        lookup.put(0, start);

        int d[] = new int[n];
        for (int i=1;i<d.length;i++)
            d[i] = Integer.MAX_VALUE;

        while (!dist.isEmpty()) {
            Node best = dist.poll();
            
            longestTime = Math.max(best.dist, longestTime);

            for (int i=0;i<n;i++) {
                if (d[best.n] == Integer.MAX_VALUE ||
                    m[best.n][i] == Integer.MAX_VALUE)
                    continue;

                int alt = d[best.n] + m[best.n][i];
                if (alt < d[i]) {
                    d[i] = alt;
                    Node node = lookup.get(i);
                    dist.remove(node);
                    node.dist = d[i];
                    dist.add(node);
                }
            }
                
        }

        System.out.println(longestTime);

        br.close();
    }
}

class Node implements Comparable<Node> {
        public int n;
        public Integer dist = Integer.MAX_VALUE;
        
        public Node(int n, int dist) {
            this.n = n;
            this.dist = dist;
        }

        public Node(int n) {
            this.n = n;
        }

        @Override
        public int compareTo(Node n) {
            return dist.compareTo(n.dist);
        }
}
