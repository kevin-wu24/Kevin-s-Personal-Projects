package wordgames;

public class WordHuntBoard 
{
	private int boardDimensions;
	private WHLetter[][] board;
	
	public WordHuntBoard(char[][] letters)
	{
		//create a buffered 2D WHLetter array with null values around
		//the actual board of letters
		boardDimensions = letters.length;
		board = new WHLetter[boardDimensions + 2][boardDimensions + 2];
		for(int r = 1; r < boardDimensions + 1; r++)
		{
			for(int c = 1; c < boardDimensions + 1; c++)
			{
				board[r][c] = new WHLetter(letters[r - 1][c - 1]);
			}
		}
	}
	
	public boolean guessWord(String str)
	{
		return false;
	}
}
