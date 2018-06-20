package com.revature.day1.helloworld;
//The first line of any java file, will be the package structure.
//Exception for when you use the default package [don't do this])
/*
 * Packages are simply collections/groupings of folders that sort and organize classes.
 * These also play a role into which classes can access others depending on package location.
 */

//Double slashes are for single line comments
/*
 * Slash - star for block comments
 */
//ALWAYS COMMENT YOUR CODE

/*
 * The main method must be:
 * public
 * static
 * return void
 * lower case method name of 'main'
 * Take a single parameter of a String[]
 */
public class HelloWorld {
	public static void main(String[] args)
	{
		System.out.println("Hello World");
	}
	/*
	 * Shortcut for System.out.println():
	 * -syso + ctrl + spacebar
	 * -sysout + ctrl + spacebar
	 */
}
