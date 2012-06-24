import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	int n = sc.nextInt();
	int m = sc.nextInt();

	int ntest = 0;

	while (m != 0 && n != 0) {
	    int x[] = new int[n + 1];
	    int y[] = new int[m + 1];
	    int c[][] = new int[n + 1][m + 1];

	    for (int i=1;i<=n;i++)
		x[i] = sc.nextInt();
	    for (int i=1;i<=m;i++)
		y[i] = sc.nextInt();
	    
	    for (int i=1;i<=n;i++){
		for (int j=1;j<=m;j++) {
		    if (x[i] == y[j])
			c[i][j] = c[i-1][j-1] + 1;
		    else 
			c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
		}
	    }
		
	    System.out.format("Twin Towers #%d\n", ++ntest);
	    System.out.format("Number of Tiles : %d\n\n", c[n][m]);

	    n = sc.nextInt();
	    m = sc.nextInt();
	}
	    
	sc.close();
    }
}
