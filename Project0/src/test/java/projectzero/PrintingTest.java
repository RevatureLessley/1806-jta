package projectzero;

import org.junit.Test;
import static org.junit.Assert.*;

import projectzero.utilities.Printing;

public class PrintingTest {

    @Test
    public void stringExceedingMaxLengthReturnsString() {
	String input = "Two";
	int maxLength = 0;
	String result = Printing.paddedString(input, maxLength);
	assertEquals(input, result);
    }
}
