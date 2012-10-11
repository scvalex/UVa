import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int seen[], tasks[][], n, totalSeen;
    static String outBuffer;
	
	public static void main(String[] args) {
        	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	
		int m, tbefore, tafter, lastSeen;
		int count = 0;
		while (true) {
		    n = sc.nextInt();
		    m = sc.nextInt();
		    		    
		    if ( (n==0) && (m == n)) 
			break;


		    tasks = new int[n + 1][n + 1];
		    for (int i=1; i <= m; i++){
			    tbefore = sc.nextInt();
			    tafter = sc.nextInt();

			    tasks[tbefore][tafter] = 1;
			}

			seen = new int[n + 1];
			totalSeen = 0;
			lastSeen = 1;
			outBuffer = "";
			while (totalSeen < n) {
			    int i;
			for ( i = lastSeen; i <= n && seen[i] != 0; i++ )
			    ;
			if ( i > n ) 
			    break;
			
			dfs(i);
		     
		    }
			if ( count != 0)
			    System.out.println();
			System.out.print(outBuffer.trim());
			count = 1;
		}
	
	
	sc.close();
	}


    public static void dfs(int i) {
	if ( seen[i] == 1) 
	    return;
	    else {
		outBuffer = outBuffer + i + " ";
		seen[i] = 1;
		for ( int k = 1; k <= n; k++) {
		    if (tasks[i][k] == 1) {
			dfs(k);
		    }
		}
	    }
	}

}
