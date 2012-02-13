import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final ArrayList<Integer> heights = new ArrayList<Integer>();
	static final ArrayList<Integer> widths = new ArrayList<Integer>();
	static final ArrayList<Integer> bestInc = new ArrayList<Integer>();
	static final ArrayList<Integer> bestDec = new ArrayList<Integer>();


	static final Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
	static final StringBuilder sb = new StringBuilder("");

	public static void main(String[] args) {
		
		int nTests = sc.nextInt();
		
		int i,kk,j,n, increasing, decreasing;
		
		for (kk=1;kk<=nTests;kk++) {
			n = sc.nextInt();
			for (i=0;i<n;i++) {
				heights.add(sc.nextInt());
			}
			
			for (i=0;i<n;i++) {
				widths.add(sc.nextInt());
			}

			sb.append("Case " + kk + ". ");

			longestSeq(heights, widths);
			
			heights.clear();
			widths.clear();
		}
		
		System.out.print(sb.toString());
		
		sc.close();
	}
	
	static void longestSeq(ArrayList<Integer> heights, ArrayList<Integer> widths) {
		
		int maxInc, i, j, n = heights.size();
		int maxDec;
		int bestWidthInc = 0, bestWidthDec = 0;
		
		for (i=0;i<n;i++) {
			maxInc = 0;
			maxDec = 0;
			for (j=0;j<i;j++) {
				if ( heights.get(j) < heights.get(i) ) {
					maxInc = Math.max(maxInc, bestInc.get(j));
				} else if ( heights.get(j) > heights.get(i) ) {
					maxDec = Math.max(maxDec, bestDec.get(j));
				}
			}
			maxInc += widths.get(i);
			maxDec += widths.get(j);
			bestInc.add(maxInc);
			bestDec.add(maxDec);
			bestWidthInc = Math.max(bestWidthInc, maxInc);
			bestWidthDec = Math.max(bestWidthDec, maxDec);
		}


		if (bestWidthInc >= bestWidthDec) {
			sb.append( "Increasing (" + bestWidthInc + "). Decreasing (" + bestWidthDec + ").\n");
		} else {
			sb.append("Decreasing ("  + bestWidthDec + "). Increasing (" + bestWidthInc + ").\n");
		}
		
		bestInc.clear();
		bestDec.clear();
	}
	
}
