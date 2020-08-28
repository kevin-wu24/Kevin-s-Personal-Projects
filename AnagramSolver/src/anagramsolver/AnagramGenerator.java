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
	ArrayList<String> result = new ArrayList<String>();
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
        		{
        			anagrams.get(sorted).add(str);
        		}
        	}
        }
        br.close();
	}
	public ArrayList<String> GetAnagrams(String str)
	{
		
		Set<String> keys = anagrams.keySet();
		for(String key: keys)
		{
			StringBuilder sortedParam = new StringBuilder(str);
			boolean sameChars = true;
			for(int i = 0 ; i < key.length(); i++)
			{
				if(sortedParam.toString().contains(key.substring(i,i+1)))
				{
					sortedParam = sortedParam.deleteCharAt(sortedParam.indexOf(key.substring(i,i+1)));
				}
				else
				{
					sameChars = false;
				}
			}
			if(sameChars)
			{
				result.addAll(anagrams.get(key));
			}
		}
		return result;
	}
	public int getScore()
	{
		int ans = 0;
		for(int i = 0; i < result.size(); i++)
		{
			if(result.get(i).length() == 3)
				ans += 100;
			else if(result.get(i).length() == 4)
				ans += 400;
			else if(result.get(i).length() == 5)
				ans += 1200;
			else if(result.get(i).length() == 6)
				ans += 2000;
			else if(result.get(i).length() == 7)
				ans += 3000;
		}
		return ans;		
	}
}

