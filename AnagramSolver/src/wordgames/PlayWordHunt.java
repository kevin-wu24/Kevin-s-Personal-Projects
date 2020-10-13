package wordgames;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayWordHunt 
{
	private static int score = 0;
	private static int numRowsAndCols = 0;
	private static char[][] wordHuntBoard;
	private static ArrayList<String> boardKey;
	private static ArrayList<String> previousAnswers;
	
	public static void main(String[] args)
	{
		//Ask client for a valid board size to play word hunt
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome To WordHunt! "
				+ "What dimensions of board would you like?"
				+ "Enter a singular integer greater than 0, "
				+ "and the board will ahve that number of rows and columns");
		while(numRowsAndCols == 0)
		{
			try 
			{
				numRowsAndCols = input.nextInt();
				if(numRowsAndCols <= 0)
				{
					System.out.println("Input must be greater than 0. "
							+ "Try entering another input.");
					numRowsAndCols = 0;
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
		
		
	}
}
