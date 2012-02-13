import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int i,j,k,r,d,s;
		int paths[][];
		Map<Integer, List<Integer>> lights = new HashMap<Integer, List<Integer>>();
		int nsteps, villa = 1;
		boolean lightsOn[];
		
		while (true) {

			nsteps = 0;
			
			r = sc.nextInt();
			d = sc.nextInt();
			s = sc.nextInt();

						
			if ( r == d && d == s && s == 0) break;
			
			paths = new int [r+1][r+1];
			lightsOn = new boolean[r+1];
			
			for (k=0;k<d;k++) {
				i = sc.nextInt();
				j = sc.nextInt();
				paths[i][j] = paths[j][i] = 1;
			}
			
			for (k=0;k<s;k++) {
				int room = sc.nextInt();
				
				List<Integer> rooms;
				if (lights.get(room) == null) {
					rooms = new ArrayList<Integer>();
					lights.put(room, rooms);
				}
				else		
					rooms = lights.get(room);
					
				rooms.add(sc.nextInt());
			}
			
			
			lightsOn[1] = true;
			
			
			
			
			System.out.println("Lights: " + lights.toString());	
			for (i=0;i<r+1;i++) {
				System.out.println(Arrays.toString(paths[i]));
			}
		
			System.out.println("Villa #" + villa);
			System.out.println("The problem can be solved in " + nsteps + " steps: ");
			
			villa++;
			lights.clear();
			
		}
		sc.close();
	}
}
