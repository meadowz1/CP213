package cp213;

/**
 * @author Mike Bangar 169073786 bang3786@mylaurier.ca
 * @version 2024-10-03
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {
	StringBuilder result = new StringBuilder();
	int shiftAmount = n % ALPHA_LENGTH;

	for (char c : s.toUpperCase().toCharArray()) {
	    if (Character.isLetter(c)) {
		int originalIndex = ALPHA.indexOf(c);
		int newIndex = (originalIndex + shiftAmount + ALPHA_LENGTH) % ALPHA_LENGTH;
		result.append(ALPHA.charAt(newIndex));
	    } else {
		result.append(c);
	    }
	}

	return result.toString();
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

	StringBuilder result = new StringBuilder();

	for (char c : s.toUpperCase().toCharArray()) {
	    if (Character.isLetter(c)) {
		int originalIndex = ALPHA.indexOf(c);
		result.append(ciphertext.charAt(originalIndex));
	    } else {
		result.append(c);
	    }
	}

	return result.toString();
    }

}
