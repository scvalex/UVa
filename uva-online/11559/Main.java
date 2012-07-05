import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));


	int price[] = new int[20];
	int beds[][] = new int[20][15];
	
	while (sc.hasNext()) {

	    String line[] = sc.nextLine().split(" ");

	    int n = Integer.parseInt(line[0]);
	    int b = Integer.parseInt(line[1]);
	    int h = Integer.parseInt(line[2]);
	    int w = Integer.parseInt(line[3]);
	    
	    for (int i=0;i<h;i++) {
		price[i] = Integer.parseInt(sc.nextLine());

		String bds[] = sc.nextLine().split(" ");
		int weekend = 0;
		for (String s : bds) {
		    beds[i][weekend++] = Integer.parseInt(s);
		}
		
	    }
	    
	    int bestPrice = Integer.MAX_VALUE;
	
	    for (int wknd=0;wknd<w;wknd++) {
		for (int hotel=0;hotel<h;hotel++) {
		    if (beds[hotel][wknd] >= n) {
			int p = n * price[hotel];
			if (p <= b)
			    bestPrice = Math.min(p, bestPrice); 
		    }
		}
	    }

	    if (bestPrice == Integer.MAX_VALUE)
		System.out.println("stay home");
	    else 
		System.out.println(bestPrice);
	}

	sc.close();
    }
}
