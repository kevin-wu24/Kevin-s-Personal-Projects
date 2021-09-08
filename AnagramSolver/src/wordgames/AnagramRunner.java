/*
 * Runner for anagrams algorithm
 * Given any input string, find all anagrams for the string
 * which are considered words, using words_alpha.txt as a reference
 * */
package wordgames;
import java.util.Arrays;
import java.util.Scanner;
public class AnagramRunner {
	public static void main(String args[]) throws Exception
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your string");
		String inputString = input.nextLine();
		char[] chars = inputString.toCharArray();
		Arrays.parallelSort(chars);
		String sorted = new String(chars);
		AnagramGenerator ag = new AnagramGenerator();
		System.out.println(Arrays.toString(ag.GetAnagrams(sorted).toArray()));
		System.out.println("Score: " + ag.getScore());
	}
}
