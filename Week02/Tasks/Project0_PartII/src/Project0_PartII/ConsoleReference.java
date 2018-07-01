package Project0_PartII;

import java.io.*;

/**
 * ConsoleReference contains the resource for input used throughout 
 * RevatureBank.
 */
public interface ConsoleReference{
	/**
	 * console will be null when the application runs anywhere besides the 
	 * command line.
	 */
	Console console = System.console();
}
