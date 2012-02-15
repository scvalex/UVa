import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.text.DecimalFormat;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt();
		int [] C;
		int sum, R, nc, b, n, minSum;
		Pair [] T;
		
		DecimalFormat df = new DecimalFormat("0.000000");
		
		boolean nonfirst = false;
		
		while (sc.hasNext()) {
			nc = sc.nextInt();
			b = sc.nextInt();

			if (nonfirst)
				System.out.println();
			else
				nonfirst = true;
			
			
			for (int kk=0;kk<nc;kk++){
				n = sc.nextInt();
			
					
				C = new int[n];
				sum = 0;
				for (int i=0;i<n;i++) {
					C[i] = sc.nextInt();
					sum+= C[i];
				}
				
				if ( n == 1 ) {
					System.out.println(df.format((double)2 * b / (double)sum));
				}
			
				Arrays.sort(C);

				T = new Pair[sum/2 + 1];
				
				R = 0;

				T[0] = new Pair(1);
				T[0].lengths.add(0);
				
				for (int i=0;i<n;i++) {
					int newR = -1;
					for (int j=R;j>=0;j-- ) {
						if ( T[j] != null && T[j].val == 1) {
							int newIdx = j + C[i];
							if (newIdx > sum/2) continue;
							if (newR == -1)
								newR = newIdx;
							if (T[newIdx] == null) {
								T[newIdx] = new Pair(0);
							}
							T[newIdx].val = 1;
							for (Integer l : T[j].lengths) {
								if ( l  <= n/2 )
									T[newIdx].lengths.add(l+1);
							}
						}
					}
					
					R = Math.min(newR, sum/2);
				}
				
				minSum = -1;
				
				for (int i=sum/2; i>=0;i--) {
					if (T[i] != null) {
						if ( T[i].lengths.contains(n/2) || ( n % 2 == 1 && T[i].lengths.contains(n/2+1))) {
							minSum = i;
							break;
						}
					}
				}
				
				minSum = Math.min(minSum, sum - minSum);
				if ( n != 1 )
					System.out.println(df.format((double)b / (double)minSum));
			}
			
			
		}
		
		sc.close();
	}
}

class Pair {

	int val;
	Set<Integer> lengths = new HashSet<Integer>();
	
	Pair(int val){
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "(" + val + ", " + lengths.toString() + ")";
	}

}
