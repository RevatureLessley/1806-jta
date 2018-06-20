package com.rev.day3.pillars;

public class Bat extends Animal implements AnimalActions{//polymorph and abstraction

	private int limbs = 5;//encapsulation
	
	public Bat(String name) {
		super(name);//Inheritance
		// TODO Auto-generated constructor stub
	}

	@Override
	public String move() {
		return "Flies on leathery wings";
	}

	@Override
	public String eat() {
		return "eats berries";
	}

	@Override
	public int getLimbs() {
		return limbs;
	}
	
	public String transform() {
		return "The bat turned into some guy";
	}

}
