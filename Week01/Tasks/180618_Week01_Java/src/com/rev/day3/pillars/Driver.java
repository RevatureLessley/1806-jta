package com.rev.day3.pillars;

public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();

		driver.covariance();
	}

	public void covariance() {
		A a = new A();
		B b = new B();
		C c = new C();

		//C c2 = (C) new A(); //Class cast exception - does not have the extra methods that C has

		A a2 = new C();
		a2.method();
		System.out.println(a2.field); // variable shadowing; still has the reference's values

		C c3 = new C();
		System.out.println(c3.field);

	}
}
