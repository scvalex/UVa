import java.util.*;

public class Main {

	static int sema[] =  new int[3000];

	public static void main(String [] args) {
		int prev, i, curr, n;
		boolean jolly;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {	
			n = sc.nextInt();
			prev = sc.nextInt();
			for (i = 1; i < n; i++) {
				curr = sc.nextInt();
				sema[Math.abs(curr - prev)] = 1;	
				prev = curr;
			}
	
			jolly = true;

			for (i=1;i<n;i++)
				if (sema[i] == 0) {
					jolly = false;
				}

			if (jolly)
				System.out.println("Jolly");
			else
				System.out.println("Not jolly");

			for (i=1;i<n;i++)
				sema[i] = 0;
		}
	}

}
