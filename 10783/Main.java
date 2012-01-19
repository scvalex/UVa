import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		sc.nextInt();
		int sum, a, b, i, nTests = 1;
		
		while (sc.hasNext()) {
			a = sc.nextInt();
			b = sc.nextInt();
			sum = 0;
			for (i=a;i<=b;i++)
				if ( i % 2 == 1 ) sum+=i;
			System.out.println("Case " + nTests + ": " + sum);
			nTests++;
		}
		
		sc.close();
	}
}
