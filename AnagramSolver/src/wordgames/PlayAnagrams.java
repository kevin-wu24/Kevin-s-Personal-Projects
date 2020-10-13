package wordgames;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PlayAnagrams 
{
	private static int score = 0;
	private static int numLetters = 0;
	private static String anagramsString;
	private static ArrayList<String> anagramsKey;
	private static ArrayList<String> previousAnswers;
	
	public static void main(String[] args) throws Exception
	{
		//Ask client for a valid String size to play anagrams with
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome To Anagrams! How many letters would you like to play Anagrams with?");
		while(numLetters == 0)
		{
			try 
			{
				numLetters = input.nextInt();
				if(numLetters <= 0)
				{
					System.out.println("Input must be greater than 0. "
							+ "Try entering another input.");
					numLetters = 0;
				}
				input.nextLine();
			}
			catch(java.util.InputMismatchException e)
			{
				System.out.println("Input not an integer. "
						+ "Try entering another input.");
				input.nextLine();
			}
		}
		
		//Generate a random String
		String vowels = "aeiou";
		StringBuilder temp = new StringBuilder();
		temp.append(vowels.charAt((int)(Math.random()*5)));
		for(int i = 0; i < numLetters-1; i++)
		{
			int letter = (int)(Math.random()*26)+97;
			temp.append((char)letter);
		}
		anagramsString = temp.toString();
		System.out.println("Get ready to create as many words as possible in 80 seconds using these characters: " + anagramsString);
		
		//Create the anagrams key to compare client answers against
		char[] anagramsCharArray = anagramsString.toCharArray();
		Arrays.parallelSort(anagramsCharArray);
		String anagramsStringSorted = new String(anagramsCharArray);
		AnagramGenerator allAnagrams = new AnagramGenerator();
		anagramsKey = allAnagrams.GetAnagrams(anagramsStringSorted);
		TimeUnit.SECONDS.sleep(1);
		System.out.println("3...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("2...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("1...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Begin creating anagrams now");
		
		long start = System.nanoTime();
		long elapsedTime = 0L; 
		long gameDuration = 80000000000L;
		previousAnswers = new ArrayList<String>();
		while(elapsedTime < start + gameDuration)
		{
			String currentWord = input.nextLine();
			if(isValid(currentWord) && !previousAnswers.contains(currentWord))
			{
				previousAnswers.add(currentWord);
				addScore(currentWord);
				System.out.println("Current score: " + score);
				System.out.println("Letters: " + anagramsString);
			}
			else
			{
				if(previousAnswers.contains(currentWord))
				{
					System.out.println(currentWord + " has already been played. Try again \nLetters: " + anagramsString);
				}
				else
				{
					System.out.println(currentWord + " is not a valid anagram. Try again \nLetters: " + anagramsString);
				}
			}
			elapsedTime = System.nanoTime();
		}
		System.out.println("Thanks for playing Anagrams! \nYour total score was: " + score);
		input.close();
	}
	
	private static boolean isValid(String inputStr)
	{
		return anagramsKey.contains(inputStr);
	}
	
	private static void addScore(String inputStr)
	{
		if(inputStr.length() == 3)
		{
			score += 100;
		}
		else if(inputStr.length() == 4)
		{
			score += 400;
		}
		else if(inputStr.length() == 5)
		{
			score += 1200;
		}
		else if(inputStr.length() == 6)
		{
			score += 2000;
		}
		else
		{
			score += 3000;
		}
	}
}
