package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Mike Bangar 169073786 bang3786@mylaurier.ca
 * @version 2024-10-03
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {
	boolean result = true;

	for (char c : str.toCharArray()) {
	    if (!Character.isDigit(c)) {
		result = false;
	    }
	}

	return result;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	if (sn.length() != 11) {
	    return false;
	}
	if (sn.charAt(0) != 'S' || sn.charAt(1) != 'N') {
	    return false;
	}
	if (sn.charAt(2) != '/') {
	    return false;
	}
	for (int i = 3; i < 7; i++) {
	    if (!Character.isDigit(sn.charAt(i))) {
		return false;
	    }
	}
	if (sn.charAt(7) != '-') {
	    return false;
	}
	for (int i = 8; i < 11; i++) {
	    if (!Character.isDigit(sn.charAt(i))) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	while (fileIn.hasNextLine()) {
	    String serialNumber = fileIn.nextLine().trim(); // Read and trim each line

	    // Check if the serial number is valid
	    if (validSn(serialNumber)) {
		goodSns.println(serialNumber); // Write valid serial numbers to goodSns
	    } else {
		badSns.println(serialNumber); // Write invalid serial numbers to badSns
	    }
	}
    }

}
