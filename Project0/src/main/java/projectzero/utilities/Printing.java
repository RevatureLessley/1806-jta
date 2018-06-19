package projectzero.utilities;

public class Printing {

    public static String paddedString(String string, int maxLength) {
	if (string.length() >= maxLength) {
	    return string;
	}
	    int difference = maxLength - string.length();
	    int leftPadLength = difference / 2;
	    int rightPadLength = difference % 2 != 0 ? (difference / 2) + 1 : leftPadLength;
	    String leftPadding = new String(new char[leftPadLength]).replace("\0", " ");
	    String rightPadding = new String(new char[rightPadLength]).replace("\0", " ");
	    return leftPadLength + string + rightPadLength;
    }
}
