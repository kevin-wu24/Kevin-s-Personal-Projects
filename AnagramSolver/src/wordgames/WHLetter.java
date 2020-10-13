package wordgames;

public class WHLetter 
{
	private char letter;
	private boolean used;
	
	public WHLetter(char let)
	{
		letter = let;
		used = false;
	}
	
	public void resetUsed()
	{
		used = false;
	}
	
	public char getLetter()
	{
		return letter;
	}
}
