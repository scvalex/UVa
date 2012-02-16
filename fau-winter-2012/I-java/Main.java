import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.io.IOException;

public class Main {

	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		String [] nums = br.readLine().split(" ");

		int n = Integer.parseInt(nums[0]);
		int m = Integer.parseInt(nums[1]);

		HashMap<String, ArrayList<String>> dictionary=new HashMap<String, ArrayList<String>>();

		///System.out.println("m: " + m + " n: " + n);		

		ArrayList<String> allWords = new ArrayList<String>();
		HashMap<String, HashMap<Integer, Pair>> count = new HashMap<String, HashMap<Integer, Pair>>();
		for (int i=0;i<n;i++) {
			String word = br.readLine();
			allWords.add(word);
		}

		Collections.sort(allWords);

		for (int i=0;i<n;i++) {
			String word = allWords.get(i);
			int len = word.length();
			for (int j=0;j<=len; j++) {
				String suffix = word.substring(j);
				ArrayList<String> words = dictionary.get(suffix);
				if ( words == null ) {
					words = new ArrayList<String>();
					dictionary.put(suffix, words);
					count.put(suffix, new HashMap<Integer, Pair>());
				}
				//System.out.print("suffix: \'" + suffix + "\'");
				//System.out.println(" idx: " + index);
				words.add(word);

				HashMap<Integer, Pair> countLen = count.get(suffix);
				Pair pair = countLen.get(len);
				if ( pair == null ) 
					countLen.put(len, new Pair(1, word));
				else {
					Pair p = countLen.get(len);
					p.count++;
				}
			}
		}

		//System.out.println("Built dictionary: ");
		//System.out.println(dictionary.toString());

		StringBuilder sb = new StringBuilder();
		for (int i=0;i<m;i++) {
			//System.out.println("-------------" + i + "--------------");
			String word = br.readLine();
			//System.out.println("word \'"  + word + "\'");
			String suff = findSuffix(word);
			//System.out.println("suffix : \'" + suff + "\'");
			ArrayList<String> words = dictionary.get(suff);
			if ( words == null ) {
				sb.append("not found\n");
				continue;
			}

			int found = 0;
			String foundWord = "";
			if ( word.charAt(0) == ' ' ) {
				Pair p = count.get(suff).get(word.length());
				
				if ( p == null || p.count == 0 )
					sb.append("not found\n");
				else if ( p.count == 1 )
					sb.append( p.word + "\n");
				else
					sb.append("not unique\n");				
				continue;
			}
			
			// System.out.println(words.toString());
			int index = Collections.binarySearch(words, word);
			if ( index < 0 ) {
				index = -(index + 1);
			}
			
			if ( wordsEqual(word, words.get(index)) ) {
				if ( index + 1 < words.size() && 
					wordsEqual(word, words.get(index+1)) ) {
					sb.append("not unique\n");
				} else {
					sb.append(words.get(index) + "\n");
				}
			} else {
				sb.append("not found\n");
			}
		}

		System.out.println(sb.toString());
	
	}

	static String findSuffix(String word) {
		StringBuilder suff = new StringBuilder();
		int len = word.length();
		int idx = word.lastIndexOf(" ");
		if ( idx == -1 )
			return word;
		return word.substring(idx + 1 , word.length());
	}
	
	static boolean wordsEqual(String word1, String word2) {
		if ( word1.length() != word2.length()) 
			return false;

		for (int i=0;i<word1.length();i++) {
			if ( ! (word1.charAt(i) == word2.charAt(i) || 
				word1.charAt(i) == ' ' || word2.charAt(i) == ' '))
				return false;
		}

		return true;
	}
}

class Pair {
	int count;
	String word;
	Pair (int count, String word) {
		this.count = count;
		this.word  = word;
	}
}
