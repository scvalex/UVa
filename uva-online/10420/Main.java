import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
	Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

	int n = Integer.parseInt(sc.nextLine());
	
	Map <String, Integer> map = new HashMap<String, Integer>();
	while (n>0) {

	    String country = sc.nextLine().split(" ")[0];
	    Integer occ = map.get(country);

	    if (occ == null)
		map.put(country, 1);
	    else
		map.put(country, occ + 1);

	    n--;
	}

	List<String> countries = new ArrayList<String>(map.keySet());
	Collections.sort(countries);

	for (String c: countries) {
	    System.out.println(c + " " + map.get(c));
	}

	sc.close();
    }
}
