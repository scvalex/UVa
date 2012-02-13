import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	static final BigInteger ZERO = new BigInteger("0");
	static final BigInteger ONE  = new BigInteger("1");
	
	static final Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) {

		int nd;		
		sc.nextInt();
		
		while (sc.hasNext()) {
			nd = sc.nextInt();
			
			BigInteger d1 = new BigInteger(sc.next());
			BigInteger l1 = new BigInteger(sc.next());
			BigInteger d2, l2;
			
			boolean impossible = false;
			
			BigInteger max = d1.add(l1);
			
			for (int i=1;i<nd;i++) {
				d2 = new BigInteger(sc.next());
				l2 = new BigInteger(sc.next());
				
				if ( d2.add(l2).compareTo(max) == 1 ) {
					max = d2.add(l2);
				}
				
				if (!impossible) {
					Triple t = euclid(d1, d2);				
					if ( !l2.subtract(l1).abs().mod(t.fst).equals(ZERO) ) {
						impossible = true;
					} else {
						BigInteger k1 = d1.multiply(d2).divide(t.fst);
						BigInteger modInv = d1.divide(t.fst).modInverse(d2.divide(t.fst));
						BigInteger k2 = modInv.multiply(l2.subtract(l1).divide(t.fst).multiply(d1)).add(l1);
						d1 = k1;
						l1 = k2.mod(d1);
					}
				}
			}
			
			if ( impossible )
				System.out.println("Impossible");
			else {

					while (l1.compareTo(max) == -1){
						l1 = l1.add(d1); 
					}
					
					System.out.println(l1);
			}
		}
		
		sc.close();
	}
	
	public static Triple euclid(BigInteger a, BigInteger b) {
		if ( b.equals(ZERO) )
			return new Triple(a, ONE, ZERO);
		Triple newt = euclid(b, a.mod(b));
		BigInteger tmp = new BigInteger(newt.snd.toString());
		newt.snd = newt.trd;
		newt.trd = tmp.subtract(a.divide(b).multiply(newt.trd));
		return newt;
	}
	
	public static BigInteger lcm(BigInteger a, BigInteger b) {
		return a.multiply(b).divide(a.gcd(b));
	}
}

class Triple {
	BigInteger fst;
	BigInteger snd;
	BigInteger trd;
	
	Triple(BigInteger fst, BigInteger snd, BigInteger trd) {
		this.fst = fst;
		this.snd = snd;
		this.trd = trd;
	}
	
	@Override
	public String toString() {
		return "(" + fst.toString() + ", " + snd.toString() + ", " + trd.toString() + ")";
	}
}
