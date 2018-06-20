package com.rev.day3.pillars;

public abstract class Animal {
	private String name;
	
	public Animal(String name) {
		this.name = name;
	}
	
	public abstract int getLimbs();
	
	protected void sound() {
		System.out.println("sadasda");
	}
	
	
}
