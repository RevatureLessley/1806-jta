package com.rev.day3.pillars;

public class Dog extends Animal implements AnimalActions{

	public Dog(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLimbs() {
		return 4;
	}

	@Override
	public String move() {
		return "trots on all fours";
	}

	@Override
	public String eat() {
		return "Eatin dog things";
	}

	public String bark() {
		return "Ruff";
	}
	
	@Override
	public void sound() {
		
	}
}
