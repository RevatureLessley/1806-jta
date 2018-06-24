package discompanydatcompany.vendingmachine.utilities;

import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class Printing {

    /**
     * Returns a String that is padded with repeated characters @padCharacter 
     * to the left of @param string. If the @string is null this function returns
     * an empty String. If the @param padCharacter is null this function
     *  returns @param string. If @param maxLength is less than zero or
     * if @param is greater or equal to the length of the string itself this
     * function returns @param string.
     *
     * @param string String to pad
     * @param maxLength maximum length of the string value.
     * @param padCharacter repeating character to pad with.
     */
    public static String leftPadString(String string, int maxLength, char padCharacter) {
	if (string == null) {
	    return "";
	} else if (String.valueOf(padCharacter) == null) {
	    return string;
	} else if (maxLength < 0) {
	    return string;
	} else if (string.length() >= maxLength) {
	    return string;
	} else {
	    int difference = maxLength - string.length();
	    String pad = new String(new char[difference]).replace("\0", String.valueOf(padCharacter));
	    return pad + string;
	}
    }

    
    /**
     * Returns a String that is padded with repeated characters @padCharacter 
     * to the right of @param string. If the @string is null this function returns
     * an empty String. If the @param padCharacter is null this function
     *  returns @param string. If @param maxLength is less than zero or
     * if @param is greater or equal to the length of the string itself this
     * function returns @param string.
     *
     * @param string String to pad
     * @param maxLength maximum length of the string value.
     * @param padCharacter repeating character to pad with.
     */
    public static String rightPadString(String string, int maxLength, char padCharacter) {
	if (string == null) {
	    return "";
	} else if (String.valueOf(padCharacter) == null) {
	    return string;
	} else if (maxLength < 0) {
	    return string;
	} else if (string.length() >= maxLength) {
	    return string;
	} else {
	    int difference = maxLength - string.length();
	    String pad = new String(new char[difference]).replace("\0", String.valueOf(padCharacter));
	    return string + pad;
	}
    }

    /**
     * This functions pads @param string to the left and right sides 
     * with @param padCharacter. If @param padCharacter is null this function
     * pads the string with the space character by default. If @param maxLength
     * is greater than or equal to thie size of the string this function returns
     * the string itself. If the difference between @param maxLength and 
     * @param string 's size is odd this function adds the additional padding
     * character to the right side of @param string.
     *
     * @param string
     * @param maxLength
     * @param padCharacter
     * @return the padded string
     */
    public static String paddedString(String string, int maxLength, char padCharacter) {

	if (String.valueOf(padCharacter) == null) {
	    padCharacter = ' ';
	}
	if (string.length() >= maxLength) {
	    return string;
	}
	StringBuffer stringBuffer = new StringBuffer();
	int difference = maxLength - string.length();
	int leftPadLength = difference / 2;
	int rightPadLength = difference % 2 != 0 ? (difference / 2) + 1 : leftPadLength;
	// stringBuffer.append(new String(new char[leftPadLength]).replace("\0", " "));
	stringBuffer.append(leftPadString("", leftPadLength, padCharacter));
	stringBuffer.append(string);
	// stringBuffer.append(new String(new char[rightPadLength]).replace("\0", " "));
	stringBuffer.append(rightPadString("", rightPadLength, padCharacter));

	return stringBuffer.toString();
    }

    
    /**
     * Defaults padding with the space character.
     * This functions pads @param string to the left and right sides 
     * with @param padCharacter. If @param padCharacter is null this function
     * pads the string with the space character by default. If @param maxLength
     * is greater than or equal to thie size of the string this function returns
     * the string itself. If the difference between @param maxLength and 
     * @param string 's size is odd this function adds the additional padding
     * character to the right side of @param string.
     *
     * @param string
     * @param maxLength
     * @param padCharacter
     * @return the padded string
     */
    public static String paddedString(String string, int maxLength) {
	return paddedString(string, maxLength, ' ');
    }
    
    /**
     * Inserts a newline every @lineLength characters
     *
     */
    public static String rowLengthLimitedString(String string, int rowLengthLimit) {

	StringBuffer stringBuffer = new StringBuffer(); 
	
	if (rowLengthLimit <= 0) {
	    return string;
	}

	for (int i = 0; i < string.length(); i += rowLengthLimit) {
	    int tail = i + rowLengthLimit >= string.length() ? string.length() - 1 : i + rowLengthLimit;
	    
	    stringBuffer.append(string.substring(i,tail) + "\n"); 
	    
	
	    /**
	     *
	     
	    if (i + columnLengthLimit >= string.length()) {
		stringBuffer.append(string.substring(i, string.length()) + "\n");
		}*/
	}
	return stringBuffer.toString();
    }

    /**
     * Removes newlines from an input string.
     * @param string String to remove newlines from.
     * @return returns a string without newlines.
     */
    public static String removeNewlines(String string) {
	return string.replaceAll("\n", "");
    }
    
    /**
     * Inserts a newline every @lineLength characters
     *
     */
    public static String rowLengthLimitedString(String string, int rowLengthLimit, char dividerCharacter) {

	StringBuffer stringBuffer = new StringBuffer(); 
	
	if (rowLengthLimit <= 0) {
	    return string;
	}

	for (int i = 0; i < string.length(); i += rowLengthLimit) {
	    int tail = i + rowLengthLimit + 2 >= string.length() + 2 ? string.length() - 3 : i + rowLengthLimit + 2;
	    
	    stringBuffer.append(String.valueOf(dividerCharacter) + string.substring(i,tail) + String.valueOf(dividerCharacter) + "\n"); 
	}
	return stringBuffer.toString();
    }
}
