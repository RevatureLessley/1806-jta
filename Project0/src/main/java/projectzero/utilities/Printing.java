package projectzero.utilities;

import java.lang.StringBuffer;

public class Printing {

    public static String paddedString(String string, int maxLength) {

	if (string.length() >= maxLength) {
	    return string;
	}
	StringBuffer stringBuffer = new StringBuffer();
	int difference = maxLength - string.length();
	int leftPadLength = difference / 2;
	int rightPadLength = difference % 2 != 0 ? (difference / 2) + 1 : leftPadLength;
	stringBuffer.append(new String(new char[leftPadLength]).replace("\0", " "));
	stringBuffer.append(string);
	stringBuffer.append(new String(new char[rightPadLength]).replace("\0", " "));

	return stringBuffer.toString();
    }

    /**
     * Inserts a newline every @lineLength characters
     *
     */
    public static String columnLengthLimitedString(String string, int columnLengthLimit) {

	StringBuffer stringBuffer = new StringBuffer(); 
	
	if (columnLengthLimit <= 0) {
	    return string;
	}

	for (int i = 0; i < string.length(); i += columnLengthLimit) {
	    int tail = i + columnLengthLimit >= string.length() ? string.length() - 1 : i + columnLengthLimit;
	    
	    stringBuffer.append(string.substring(i,tail) + "\n"); 
	    
	
	    /**
	     *
	     
	    if (i + columnLengthLimit >= string.length()) {
		stringBuffer.append(string.substring(i, string.length()) + "\n");
		}*/
	}
	return stringBuffer.toString();
    }
}
