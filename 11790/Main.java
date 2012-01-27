import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt();
		
		ArrayList<Integer> heights = new ArrayList<Integer>();
		ArrayList<Integer> widths = new ArrayList<Integer>();
		
		
		int i,kk,j,n, increasing, decreasing;
		
		for (kk=1;kk<=nTests;kk++) {
			n = sc.nextInt();
			for (i=0;i<n;i++) {
				heights.add(sc.nextInt());
			}
			
			for (i=0;i<n;i++) {
				widths.add(sc.nextInt());
			}

			increasing = longestSeq(heights, widths);

			for (i=0;i<n;i++) {
				heights.set(i, -heights.get(i));
			}

			decreasing = longestSeq(heights, widths);
			
			System.out.print("Case " + kk + ". ");
			if (increasing >= decreasing) {
				System.out.println("Increasing (" + increasing + "). Decreasing (" + decreasing + ").");
			} else {
				System.out.println("Decreasing (" + decreasing + "). Increasing (" + increasing + ").");
			}
			
			heights.clear();
			widths.clear();
		}
		
		sc.close();
	}
	
	static int longestSeq(ArrayList<Integer> heights, ArrayList<Integer> widths) {
		int bestWidth = 0;
		
		ArrayList<Integer> best = new ArrayList<Integer>();
		int max, i, j, n = heights.size();
		
		for (i=0;i<n;i++) {
			max = 0;
			for (j=0;j<i;j++) {
				if ( heights.get(j) < heights.get(i) ) {
					max = Math.max(max, best.get(j));
				}
			}
			max += widths.get(i);
			best.add(max);
			bestWidth = Math.max(bestWidth, max);
		}
		
		return bestWidth;
	}
	
}
