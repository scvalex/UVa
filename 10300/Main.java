import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int nTests = sc.nextInt();
		int f, sum, i, size;
		for (int kk = 0; kk < nTests; kk++ ) {
			f = sc.nextInt();
			sum = 0;
			for (i=0; i < f; i++) {
				size = sc.nextInt();
				sc.nextInt();
				sum +=  size * sc.nextInt();
			}
			System.out.println(sum);
		}
		sc.close();
	}
}
