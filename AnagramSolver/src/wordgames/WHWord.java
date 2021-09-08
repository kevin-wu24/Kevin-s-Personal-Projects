package wordgames;
import java.util.LinkedList;

public class WHWord {
	private LinkedList<WHLetter> word;
	
	public WHWord() {
		word = new LinkedList<WHLetter>();
	}
	
	/*
	 * Appends a WHLetter to the current word if it isn't already in the word
	 * The letter is then marked as used
	 */
	public void append(WHLetter c) {
		if(c.used = true) {
			return;
		}
		word.add(c);
		c.used = true;
	}
	
	/*
	 * Clears the word of all WHLetters and resets all WHLetters in the WHWord as unused
	 */
	public void clear() {
		while(!word.isEmpty()) {
			word.getFirst().used = false;
			word.remove();
		}
	}
	
	/*
	 * Convert from WHWord to a string
	 */
	public String toString() {
		String result = "";
		for(WHLetter w : word) {
			result += w.letter;
		}
		return result;
	}
}
