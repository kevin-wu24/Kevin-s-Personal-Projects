package anagramsolver;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class AnagramGenerator 
{
	File words = new File("words_alpha.txt");
	Hashtable<String, ArrayList<String>> anagrams = new Hashtable<String,ArrayList<String>>();
	ArrayList<String> result = new ArrayList<String>();
	
	/**
	 * Anagrams hash table will have an alphabetized String as key.
	 * Each key's value is an list of all possible anagrams created from the key string.
	 * @throws Exception
	 */
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
	/**
	 * @param str
	 * @return a list of all possible anagrams that can be made using
	 * the characters in the parameter String.
	 */
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
	/**
	 * @return maximum Anagrams score possible for a given string
	 */
	public int getScore()
	{
		int ans = 0;
		for(int i = 0; i < result.size(); i++)
		{
			if(result.get(i).length() == 3)
			{
				ans += 100;
			}
			else if(result.get(i).length() == 4)
			{
				ans += 400;
			}
			else if(result.get(i).length() == 5)
			{
				ans += 1200;
			}
			else if(result.get(i).length() == 6)
			{
				ans += 2000;
			}
			else
			{
				ans += 3000;
			}
		}
		return ans;		
	}
}

