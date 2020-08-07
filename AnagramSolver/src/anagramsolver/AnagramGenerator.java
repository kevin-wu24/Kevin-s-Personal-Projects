package anagramsolver;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class AnagramGenerator {
	//Generates anagrams with at least 3 letters
	File words = new File("words_alpha.txt");
	Hashtable<String, ArrayList<String>> anagrams = new Hashtable<String,ArrayList<String>>();
	public AnagramGenerator() throws Exception
	{
		String str = "";
		BufferedReader br = new BufferedReader(new FileReader(words));
        while((str = br.readLine()) != null)
        {
        	if(str.length() >= 3)
        	{        		
        		char[] chars = str.toCharArray();
        		Arrays.parallelSort(chars);
        		String sorted = new String(chars);
        		if(!anagrams.containsKey(sorted))
        		{
        			anagrams.put(sorted, new ArrayList<String>());
        			anagrams.get(sorted).add(str);
        		}
        		else
        			anagrams.get(sorted).add(str);
        	}
        }
        br.close();
	}
	public ArrayList<String> GetAnagrams(String str)
	{
		ArrayList<String> result = new ArrayList<String>();
		Set<String> keys = anagrams.keySet();
		for(String key: keys)
		{
			if(str.contains(key))
				result.addAll(anagrams.get(key));
		}
		return result;
	}
}

