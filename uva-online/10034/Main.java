import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	Point fs[];
	double d[][];
	int nPoints;

	Set<Integer> V = new HashSet<Integer>();

	int n = sc.nextInt();
	for (;n>0;n--) {
	    nPoints = sc.nextInt();
	    fs = new Point[nPoints + 1];
	    for (int i=0;i<nPoints;i++) {
		fs[i] = new Point(sc.nextDouble(), sc.nextDouble());
	    }

	    d = new double[nPoints][nPoints];

	    for (int i=0;i<nPoints;i++)
		for (int j=0;j<nPoints;j++)
		    d[i][j] = Math.sqrt(Math.pow(fs[i].x - fs[j].x, 2) + Math.pow(fs[i].y - fs[j].y ,2));
	
	    V.add(1);
	    double res = 0; 

	    while (V.size() < nPoints) {

		double min = Double.MAX_VALUE;

		Edge e = null;

		for (Integer v : V) {
		    for (int i=0;i<nPoints;i++) {
			if (!V.contains(i) && d[v][i] < min) {
			    min = d[v][i];
			    e = new Edge(v ,i, d[v][i]);
			}
		    }
		}
		
		V.add(e.y);
		res += e.weight;
	    }

	    V.clear();

	    if ( n > 1 ) {
		System.out.format("%.2f\n\n", res);
	    } else {
		System.out.format("%.2f\n", res);
	    }
	}
	sc.close();
    }
}

class Point {
    double x;
    double y;
    public Point(double x, double y) {
	this.x = x;
	this.y = y;
    }
}
    
class Edge {
    int x;
    int y;
    double weight;
    public Edge(int x, int y, double weight) {
	this.x = x;
	this.y = y;
	this.weight = weight;
    }
}