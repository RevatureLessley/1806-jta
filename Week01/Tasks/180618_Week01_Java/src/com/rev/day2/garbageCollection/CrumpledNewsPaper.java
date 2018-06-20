package com.rev.day2.garbageCollection;

public class CrumpledNewsPaper {
	public int id;
	
	public CrumpledNewsPaper(int id) {
		System.out.println(id + "created");
		this.id = id;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("\t\t" + id + "collected");
	}
}
