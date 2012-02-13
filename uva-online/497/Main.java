import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
	
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> p = new ArrayList<Integer>();
		List<Integer> prev = new ArrayList<Integer>();
		List<Integer> values = new ArrayList<Integer>();
		
		boolean notFirst = false;
			
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int nTests = sc.nextInt(), num, pos, i;
		sc.nextLine();
		sc.nextLine();
		
		for (int kk=0; kk<nTests; kk++) {
		
			num = Integer.parseInt(sc.nextLine());
			a.add(num);
			p.add(0);
			prev.add(-1);
			values.add(num);
			
			i = 1;
		
			while (true) { 
			
				try {
						num = Integer.parseInt(sc.nextLine());
						values.add(num);
				} catch (Exception e) {
					//e.printStackTrace();
					break;
				}

				if (a.get(0) > num) {
					a.set(0, num);
					p.set(0, i);
					pos = 0;
				} else {
					pos = Collections.binarySearch(a, num);
					
					if ( pos < 0 ) {
						pos = -(pos + 1);
					}
					
					if ( pos >= a.size() ) {
						a.add(num);
						p.add(i);
					} else  if ( a.get(pos) > num )  {
						a.set(pos, num);
						p.set(pos, i);
					}
					
				}
				
				if ( pos > 0 ) 
					prev.add(p.get(pos-1));
				else 
					prev.add(-1);

				i++;
				
			}


			if (notFirst) 
				System.out.println();
			else 
				notFirst = true;

			System.out.println("Max hits: "  + a.size());
			
			Stack<Integer> stack = new Stack<Integer>();
			int	pr = p.get(p.size()-1);
			while ( pr != -1 ) {
				stack.push(values.get(pr));
				pr = prev.get(pr);
			}
			
			while (!stack.isEmpty()) {
				System.out.println(stack.pop());
			}
		
			a.clear();
			p.clear();
			prev.clear();
			values.clear();
		}
		
		sc.close();
	}
}
