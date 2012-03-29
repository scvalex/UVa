import java.util.*;

public class Main {

    private static int SIZE = 8;
    private static int POINTS = 5;

    public static void main(String[] args) {

	int sol[] = new int[SIZE + 1];
	int a[][] = new int[POINTS + 1][POINTS + 1];
	
	for (int i=0;i<a.length;i++) {
	    Arrays.fill(a[i], 1);
	}

	a[1][4] = a[2][4] = a[4][1] = a[4][2] = 0;
	for (int i=0;i<=POINTS;i++) {
	    a[i][i] = 0;
	}

	int k = 0;
	sol[k++] = 1;

	while ( k >= 1) {

	    a[sol[k-1]][sol[k]] = a[sol[k]][sol[k-1]] = 1;

	    sol[k]++;

	    while ( sol[k] <= POINTS && a[sol[k-1]][sol[k]] == 0) {
			sol[k]++;
	    }

	    if ( sol[k] <= POINTS )
		a[sol[k-1]][sol[k]] = a[sol[k]][sol[k-1]] = 0;
	    
	    if ( k == SIZE && sol[k] <= POINTS) {
		for (Integer i : sol)
		    System.out.print(i);
		System.out.println();
	    }

	    if ( sol[k] <= POINTS && k < SIZE) {
		k++;
	    } else if ( sol[k] > POINTS ) {
		sol[k] = 0;
		k--;

	    }
	
	}
    }

}
