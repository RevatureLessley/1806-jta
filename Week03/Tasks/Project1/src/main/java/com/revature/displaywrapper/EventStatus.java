package com.revature.displaywrapper;

public enum EventStatus{

	New("New"), 
	Processing("Processing"), 
	Urgent("Urgent"), 
	Approved("Approved"), 
	UnGraded("Awaiting Grade"), 
	UnConfirmed("Awaiting Confirmation"), 
	Resolved("Resolved");

	public final String name;
	
	EventStatus(String name) {
		this.name= name;
	}

}