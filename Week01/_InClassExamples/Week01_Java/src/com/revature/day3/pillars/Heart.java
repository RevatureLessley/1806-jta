package com.revature.day3.pillars;

public class Heart {
	private boolean isBeating;
	private int weight;
	
	public Heart(boolean isBeating, int weight){
		this.isBeating = isBeating;
		this.weight = weight;
		
	}
	
	public boolean isBeating() {
		return isBeating;
	}
	public void setBeating(boolean isBeating) {
		this.isBeating = isBeating;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
