import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static char[][] map;	
	static int n,m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int field = 1, i, j;
		boolean first = true;
			
		while (true) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			if ( n == m && m == 0 ) break;

			map = new char[n][m];
			sc.nextLine();
			
			for (i = 0; i < n; i++) {
				map[i] =  sc.nextLine().toCharArray();
			}
			if (!first)
				System.out.println();
			System.out.println("Field #" + field +":");

			for (i =0; i < n; i++) {
				for (j = 0; j < m; j++) {
					if (map[i][j] == '.')
						System.out.print(count(i,j));
					else
						System.out.print("*");
				}
				System.out.println();
			}	
			
			first = false;

			field++;
		}
		
		sc.close();
	}
	
	static int count(int i, int j) {
		char c = map[i][j];
		int sum = 0;
		for (int di=-1;di<=1;di++)
			for (int dj=-1;dj<=1;dj++) 
				if (check(i+di, j+dj))
				sum += itemCount(map[i+di][j+dj]);
		return sum - itemCount(map[i][j]);
	}
	
	static int itemCount(char c) {
		return c == '*' ? 1 : 0;
	}
	
	static boolean check(int i, int j) {
		return ( i >= 0 && j >= 0 && i < n && j < m);
	}
	
}
