import java.util.*;
import java.io.*;

public class Main {

    static final String words[] = new String[25000];

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	int nWords = 0;
	String line;
	
	Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
	Map<String, Integer> l = new HashMap<String, Integer>();

	while ( (line = br.readLine()) != null) {
	    words[nWords++] = line;
	}

	int longest = 0;
	for (int i=0;i<nWords;i++) {
	    int best = 0;
	    for (Integer hash : possibleHashes(words[i])) {
		if (map.get(hash) != null) {
		    for (String s : map.get(hash)) {
			if (editStep(words[i].toCharArray(), s.toCharArray()))
			    best = Math.max(best, l.get(s) + 1);
		    }
		} else {
		    map.put(hash, new ArrayList<String>());
		}
		map.get(hash).add(words[i]);
	    }
	    l.put(words[i], best);
	    longest = Math.max(best, longest);
	}

	// gets WA without newline
	System.out.println(longest + 1);
	br.close();
    }

    static Collection<Integer> possibleHashes(String word) {
	Collection<Integer> hashes = new HashSet<Integer>();
	hashes.add(word.hashCode());
	String toHash;

	for (int i=0;i<word.length();i++) {
	    toHash = word.substring(0, i) + word.substring(i + 1);
	    hashes.add(toHash.hashCode());
	}

	return hashes;
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
