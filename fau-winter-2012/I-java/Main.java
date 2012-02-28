import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.io.IOException;

public class Main
{
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static long start, end;

    public static void main(String[] args) throws IOException
    {
        start = System.currentTimeMillis();

        String [] nums = br.readLine().split(" ");
        int n = Integer.parseInt(nums[0]);
        int m = Integer.parseInt(nums[1]);
        HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
        ///System.out.println("m: " + m + " n: " + n);
        ArrayList<String> allWords = new ArrayList<String>();
        ArrayList<String> allSuffixes = new ArrayList<String>();
        ArrayList<String> queries = new ArrayList<String>();
        HashMap<String, HashMap<Integer, Pair>> count = new HashMap<String, HashMap<Integer, Pair>>();

        for(int i = 0; i < n; i++) {
            String word = br.readLine();
            allWords.add(word);
        }
        /*
        for(int i = 0; i < allWords.size(); i++)
        {
            System.out.println(i + ": " + allWords.get(i));
        }
        */
        //System.err.println((System.currentTimeMillis() - start) / 1000.0 + " seconds");

        start = System.currentTimeMillis();
        for(int i = 0; i < m; i++) {
            String word = br.readLine();
            String suffix = findSuffix(word);

            /*
            System.err.println("-------------" + i + "--------------");
            System.err.println("word: \'"  + word + "\'");
            System.err.println("suffix: \'" + suffix + "\'");
            */

            allSuffixes.add(suffix);
            queries.add(word);
            dictionary.put(suffix, new ArrayList<String>());
            count.put(suffix, new HashMap<Integer, Pair>());
        }
        //System.out.println(dictionary.toString());
        /*
        for(int i = 0; i < allSuffixes.size(); i++)
        {
            System.out.println(i + ": " + allSuffixes.get(i));
        }
        */
        //System.err.println((System.currentTimeMillis() - start) / 1000.0 + " seconds");

        Collections.sort(allWords);

        start = System.currentTimeMillis();
        for(int i = 0; i < n; i++) {
            String word = allWords.get(i);
            int len = word.length();
            //System.out.println("i: " + i + " word: " + word + " len: " + len);
            for(int j = 0; j <= len; j++) {
                String suffix = word.substring(j);
                ArrayList<String> words = dictionary.get(suffix);
                if(words != null) {
                    //System.out.println("suffix: " + suffix + " j: " + j);
                    words.add(word);
                    HashMap<Integer, Pair> countLen = count.get(suffix);
                    Pair pair = countLen.get(len);

                    if(pair == null)
                    {
                        countLen.put(len, new Pair(1, word));
                    }
                    else
                    {
                        //System.out.println("before pair: (" + len + ", " + countLen.get(len) + ")");
                        Pair p = countLen.get(len);
                        p.count++;
                        //System.out.println("after pair: (" + len + ", " + countLen.get(len) + ")");
                    }
                }
            }
        }
        //System.err.println((System.currentTimeMillis() - start) / 1000.0 + " seconds");

        /*
        StringBuffer sbuff = new StringBuffer();
        for (Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
            sbuff.append(entry.getKey());
            sbuff.append(": ");
            sbuff.append(entry.getValue());
            sbuff.append("\n");
        }
        System.out.print(sbuff.toString());
        System.out.println("\tsize of dict = " + dictionary.size());
        sbuff = new StringBuffer();
        for (Map.Entry<String, HashMap<Integer, Pair>> entry : count.entrySet()) {
            sbuff.append(entry.getKey());
            sbuff.append(": ");
            sbuff.append(entry.getValue());
            sbuff.append("\n");
        }
        System.out.print(sbuff.toString());
        System.out.println("\tsize of count = " + count.size());
        */

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++)
        {
            String suff = allSuffixes.get(i);
            String word = queries.get(i);
            ArrayList<String> words = dictionary.get(suff);
            
            /*
            System.out.println("-------------" + i + "--------------");
            System.out.println("word: \'"  + word + "\'");
            System.out.println("suffix: \'" + suff + "\'");
            */

            if(words == null) {
                sb.append("not found\n");
                continue;
            }

            //System.err.println("words: " + words.toString() + " " + words.size() + " #1");

            if(word.length() > 0 && word.charAt(0) == ' ') {
                Pair p = count.get(suff).get(word.length());
                if(p == null || p.count == 0)
                    sb.append("not found\n");
                else if(p.count == 1)
                    sb.append(p.word + "\n");
                else
                    sb.append("not unique\n");
                continue;
            }

            //System.err.println("words: " + words.toString() + " " + words.size()+ " #2");

            int index = Collections.binarySearch(words, word);
            if(index < 0)
            {
                index = -(index + 1);
            }
            if(index < words.size() && wordsEqual(word, words.get(index)))
            {   
                if(index + 1 < words.size() &&
                        wordsEqual(word, words.get(index + 1)))
                {
                    //System.err.println("#1: " + index + ": " + word + "/" + words.get(index + 1));
                    sb.append("not unique\n");
                }
                else
                {
                    //System.err.println("#2: " + index + ": " + word + "/" + words.get(index + 1));
                    sb.append(words.get(index) + "\n");
                }
            }
            else {
                sb.append("not found\n");
            }
        }

        // no trim gives WA
        System.out.println(sb.toString().trim());

        //System.err.println((System.currentTimeMillis() - start) / 1000.0 + " seconds");
    }

    static String findSuffix(String word)
    {
        StringBuilder suff = new StringBuilder();
        int len = word.length();
        int idx = word.lastIndexOf(" ");

        if(idx == -1)
            return word;

        return word.substring(idx + 1 , word.length());
    }

    static boolean wordsEqual(String word1, String word2)
    {
        if(word1.length() != word2.length())
            return false;

        for(int i = 0; i < word1.length() && word1.charAt(i) != ' ' && word2.charAt(i) != ' '; i++) {
            if(word1.charAt(i) != word2.charAt(i))
                return false;
        }

        return true;
    }
}

class Pair
{
    int count;
    String word;
    Pair(int count, String word)
    {
        this.count = count;
        this.word  = word;
    }
    public String toString()
    {
        return String.format("(%d, %s)", count, word);
    }
}
