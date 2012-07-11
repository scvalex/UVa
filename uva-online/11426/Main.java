import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    static int phi[] = new int[4000002];
    static int primes[] = new int[4000002];

    public static void main(String[] args) {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	// generate primes
	int k = 2;
	while (k < primes.length) {
	    while (k < primes.length && primes[k] != 0) k++;
	    if ( k  < primes.length) {
		for ( int i = k; i < primes.length; i+=k)
		    primes[i] = k;
		primes[k] = 1;
	    }
	}

	// compute phi
	phi[1] = 1;
	for (int i=2;i<phi.length;i++) {
	    int div = primes[i];
	    if (div == 1) {
		phi[i] = i - 1;
	    } else {
		int ti = i, factor = 1;
		while ( ti % div == 0) {
		    ti /= div;
		    factor *= div;
		}
		if (ti == 1)
		    phi[i] = factor - factor / div;
		else 
		    phi[i] = phi[factor] * phi[ti];
	    }
	}

	long sum, n;
	while ( (n = sc.nextInt()) != 0 ) {
	    sum = 0;

	    for (long i = 1; i<=n; i++) {
		sum +=  (s(n / i) * phi[(int)i] - i);
	    }
	    
	    System.out.println(sum);
	}	    

	sc.close();
    }

    static long s(long n) {
	return n * (n + 1) / 2;
    }


}
