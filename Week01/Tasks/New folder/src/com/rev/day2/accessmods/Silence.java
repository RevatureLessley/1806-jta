package com.rev.day2.accessmods;

public class Silence {

	private String priv = "private";
	String def = "default";
	protected String prot = "protected";
	public String pub = "Public";
	
	public void output() {
		System.err.println(priv);
		System.err.println(def);
		System.err.println(prot);
		System.err.println(pub);
	}
}
