package com.revature.day3.pillars;

//When in doubt, a -able named entity, is typically an interface.
/*
 * Interfaces should be used to enforce actions of classes.
 * This is by no menas a law, you can use interfaces simply to enforce specific
 * methods to be implemented for classes that implement it.
 * But typically, they should enforce actions.
 * An interface is a contract, that MUST be followed. Anything that implements it, MUST
 * provide implementation for every method within.
 * 
 * As of Java 8 things get weird.
 * For one, we can define an interface as such:
 * -A type of class that cannot have any method implementation
 * -Any interface variable MUST be public static and final.
 * -As of Java 8
 * 		-You are now allowed to make static methods.
 * 		-You are given a new keyword, 'default' which can only be used within interfaces
 * 			to signify that you are giving an implementation.
 */
public interface AnimalActions {
	public String eat();
	public String move();
	public static void method(){
		System.out.println("I am implementation within an interface...");
	}
	public default void defaultMethod(){
		System.out.println("Default method.");
	}
}

/*
 * Interfaces Vs Abstract Class
 * -Both can have concrete and abstract methods.
 * -As well both can NOT be instantiated as a stand alone instance.
 * -Interfaces are typically used for actions that should be required by classes where as
 * abstract classes are typically used as classes to be extended from with base functionality
 * -You can 'extend' a class, but you must 'implement' an interface.
 * -You can extend ONE class at most, whereas you can implement as many
 * interfaces as you want. 
*/