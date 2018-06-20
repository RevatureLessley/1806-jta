package com.revature.day3.pillars;

public class Bat extends Animal implements AnimalActions{ //Abstraction and Polymorphism
	private int limbs = 3; //Encapsulation
	
	public String batStuff(){
		return "Yup, that's what it is...";
	}
	
	@Override //Inheritance
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String eat() {
		// TODO Auto-generated method stub
		return "Consuming innocent blood.";
	}

	@Override
	public String move() {
		// TODO Auto-generated method stub
		return "Flappin about....";
	}

	@Override
	public int getLimbs() {
		// TODO Auto-generated method stub
		return limbs;
	}
	
}
