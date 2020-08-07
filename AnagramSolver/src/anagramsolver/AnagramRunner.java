package anagramsolver;
import java.util.Arrays;
import java.util.Scanner;
public class AnagramRunner {
	public static void main(String args[]) throws Exception
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your 6/7 lowercase string");
		String inputString = input.nextLine();
		char[] chars = inputString.toCharArray();
		Arrays.parallelSort(chars);
		String sorted = new String(chars);
		AnagramGenerator ag = new AnagramGenerator();
		System.out.println(Arrays.toString(ag.GetAnagrams(sorted).toArray()));
		System.out.println("Score: " + ag.getScore());
	}
}
