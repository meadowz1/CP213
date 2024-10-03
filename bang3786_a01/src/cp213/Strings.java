package cp213;

/**
 * @author Mike Bangar 169073786 bang3786@mylaurier.ca
 * @version 2024-10-03
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	String cleanedString = "";

	for (int i = 0; i < string.length(); i++) {
	    char currentChar = string.charAt(i);
	    if (Character.isLetter(currentChar)) {
		cleanedString += Character.toLowerCase(currentChar);
	    }
	}

	String reversedString = "";

	for (int i = cleanedString.length() - 1; i >= 0; i--) {
	    reversedString += cleanedString.charAt(i);
	}

	return cleanedString.equals(reversedString);
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	if (name == null || name.length() == 0 || name.equals("_")) {
	    return false;
	}

	char firstChar = name.charAt(0);
	if (!Character.isLetter(firstChar) && firstChar != '_') {
	    return false;
	}

	for (int i = 1; i < name.length(); i++) {
	    char currentChar = name.charAt(i);
	    if (!Character.isLetterOrDigit(currentChar) && currentChar != '_') {
		return false;
	    }
	}

	return true;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

	if (word == null || word.length() == 0) {
	    return word;
	}

	char firstChar = word.charAt(0);
	boolean startsWithVowel = VOWELS.indexOf(firstChar) != -1;

	if (startsWithVowel) {
	    return word + "way";
	}

	int firstVowelIndex = -1;
	for (int i = 0; i < word.length(); i++) {
	    if (VOWELS.indexOf(word.charAt(i)) != -1) {
		firstVowelIndex = i;
		break;
	    }
	}

	if (firstVowelIndex != -1) {
	    String consonants = word.substring(0, firstVowelIndex);
	    String restOfWord = word.substring(firstVowelIndex);
	    return restOfWord + consonants + "ay";
	}

	return word + "ay";
    }

}
