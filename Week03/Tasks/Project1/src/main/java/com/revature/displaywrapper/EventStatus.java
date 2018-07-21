package com.revature.displaywrapper;

public enum EventStatus{

	New("New"), 
	Processing("Processing"), 
	Urgent("Urgent"), 
	Approved("Approved"), 
	Pending("Pending"), 
	UnConfirmed("Awaiting Confirmation"), 
	Resolved("Resolved");

	private final String name;
	
	EventStatus(String name) {
		this.name= name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}