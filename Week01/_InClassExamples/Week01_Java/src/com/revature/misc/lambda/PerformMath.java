package com.revature.misc.lambda;

/*
 * An interface, that has only one unimplemented method signature, is called
 * a FUNCTIONAL INTERFACE. These type of interfaces cater to lambda expressions.
 * 
 * You can have as many implemented methods as you want, but as soon as
 * you write 2 or more method signatures that aren't implemented, it is
 * no longer a functional interface, and therefore, cannot be used
 * with lambdas.
 */
public interface PerformMath {
	int perform(int a, int b);
}
