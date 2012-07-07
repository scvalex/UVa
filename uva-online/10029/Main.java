import java.util.*;
import java.io.*;

public class Main {

    static final char words[][] = new char[25000][16];

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	int nWords = 0;
	String line;
	
	while ( (line = br.readLine()) != null) {
	    words[nWords++] = line.toCharArray();
	}

	int best = 1;

	Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
	map.put(1, new ArrayList<String>());

	for (int i=0;i<nWords;i++) {
	    boolean found = false;
	    String crtWord = new String(words[i]);

	    for (int ll=best; ll>0 && !found; ll--) {
		if (map.get(ll) != null) {
		    for (String s : map.get(ll)) {
			if ( editStep(words[i], s.toCharArray()) ) {
			    List<String> list = map.get(ll + 1);
			    if (list == null) {
				map.put(ll + 1, list = new ArrayList<String>());
			    }
			    list.add(crtWord);
			    found = true;
			    best = Math.max(best, ll + 1);
			    break;
			}
		    }
		}
	    }

	    if (!found) {
		map.get(1).add(crtWord);
	    }
	}

	System.out.print(best);
	br.close();
    }

    static boolean editStep(char[] word1, char[] word2) {
	boolean editStep = false;
	if (word1.length == word2.length) {
	    int diff = 0;
	    for (int i=0;i<word1.length && diff < 2;i++)
		if (word1[i] != word2[i])
		    diff++;
	    editStep = (diff == 1);
	} else {
	    editStep = hasOneDiffChar(word1, word2);
	    editStep |= hasOneDiffChar(word2, word1);
	}

	return editStep;
    }

    static boolean hasOneDiffChar(char[] word1, char[] word2) {
	if (word1.length == word2.length + 1) {
	    int diff = 0;
	    for (int i=0;i<word2.length && diff < 2;i++)
		if (word1[i + diff] != word2[i]) {
		    diff++;
		    i--;
		}
	    return (diff < 2);
	}

	return false;
    }

}
