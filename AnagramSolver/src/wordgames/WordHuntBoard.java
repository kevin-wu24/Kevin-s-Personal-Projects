package wordgames;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordHuntBoard 
{
	private int boardDimensions;
	private WHLetter[][] board;
	
	public WordHuntBoard(int boardSize)
	{
		//create a buffered 2D WHLetter array with null values around
		//the actual board of letters
		boardDimensions = boardSize;
		board = new WHLetter[boardDimensions + 2][boardDimensions + 2];
		for(int r = 1; r < boardDimensions + 1; ++r)
		{
			for(int c = 1; c < boardDimensions + 1; ++c)
			{
				board[r][c] = new WHLetter((char)(Math.random() * 26 + 97));
			}
		}
	}
	
	/*
	 * Solve the Word Hunt board, with strings contained in words_alpha.txt as words 
	 */
	public ArrayList<String> solve() throws Exception {
		File words = new File("words_alpha.txt");
		ArrayList<String> solution = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(words));
		String str;
		while((str = br.readLine()) != null) {
			WHLetter cur;
			WHWord word = new WHWord();
			for(int r = 1; r < boardDimensions + 1; ++r)
			{
				for(int c = 1; c < boardDimensions + 1; ++c)
				{
					if(board[r][c].letter == str.charAt(0)) {
						cur = board[r][c];
						word.append(cur);
						if(str.length() >= 3 && buildWord(board, r, c, cur, word, str)) {
							solution.add(str);
						}
					}
				}
			}
		}
		br.close();
		return solution;
	}
	
	public boolean buildWord(WHLetter[][] board, int r, int c, WHLetter cur, WHWord word, String str) {
		if(word.toString().equals(str)) {
			return true;
		}
		else {
			for(int i = -1; i < 2; ++i) {
				for(int j = -1; i < 2; ++j) {
					if(i == 0 && j == 0) {
						continue;
					}
					else {
						char NULL_CHAR = '\u0000';
						if(board[r + i][c + j].letter != NULL_CHAR && board[r + i][c + j].used == false) {
							cur = board[r + i][c + j];
							word.append(cur);
							buildWord(board, r + i, c + j, cur, word, str);
						}
					}
				}
			}
			return false;
		}
	}
	

}
