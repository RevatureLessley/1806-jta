package com.revature.day3.pillars;

public class Dog extends Animal implements AnimalActions{
	private int limbs = 4;
	//This Dog is-a Animal.
	//This Dog has-a Heart.
	//This is called a Has-a relationship.
	private Heart heart;
	
	/**
	 * The bark() method is a call unique to the dog
	 * class which will return something all dogs would
	 * be known to say if you walk by their house 
	 * unannounced.
	 * @return String
	 */
	public String bark(){
		return "Bow wow wow... " + " My heart weighs " + heart.getWeight() + " pounds.";
	}
	
	public Dog(){
		heart = new Heart(true, 524);
	}
	
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		//The super keyword is always a reference to the direct parent class.
		return super.isAlive();
		//So essentially this translates into Animal.isAlive();
	}
	
	@Override
	public String eat() {
		// TODO Auto-generated method stub
		return "Chowin down on some kibble";
	}

	@Override
	public String move() {
		// TODO Auto-generated method stub
		return "Trotting on all fours.";
	}

	@Override
	public int getLimbs() {
		// TODO Auto-generated method stub
		return limbs;
	}

}
