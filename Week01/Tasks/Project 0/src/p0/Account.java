package p0;

import java.io.Serializable;

public abstract class Account implements Serializable
{
	protected String uName;
	protected String uPass;
	protected String Name;
	protected transient Launch pgm;
	protected boolean accountFlagged = false;
	
	public Account()
	{
		
	}
	
	public Account(String name, String uname, String pword, Launch pgm)
	{
		this.Name = name;
		this.uName = uname;
		this.uPass = pword;
		this.pgm = pgm;
	}
	
	public void setPgm(Launch pgm)
	{
		this.pgm = pgm;
	}

	public String getName() {
		return Name;
	}

	public String getuName() {
		return uName;
	}

	public String getuPass() {
		return uPass;
	}	
	
	public boolean getFlagged()
	{
		return accountFlagged;
	}
	
	public abstract void menu();
	
	public void logout()
	{
		System.out.println("Farewell " + this.Name + " we hope to see you again soon.");
		System.out.println();
	}
}
