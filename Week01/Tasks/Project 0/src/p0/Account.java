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
		super();
	}
	/**
	 * Generic constructor class to be called by children
	 * 
	 * @param name What the user is referred to in their own menu
	 * @param uname The login name that a user uses to gain access to their account
	 * @param pword The secure word used to authorize access to the account
	 * @param pgm
	 */
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
	
	public void setFlagged(boolean flag)
	{
		accountFlagged = flag;
	}
	
	public boolean getFlagged()
	{
		return accountFlagged;
	}
	public abstract void menu();
	
	public void deleteAcc()
	{
		System.out.println("You have chosen to dissable your account...");
		System.out.print("Are you sure this is what you want to do? Y/N:");
		char selection = pgm.in.next().charAt(0);
		if (selection =='Y' || selection =='y')
		{
			accountFlagged = true;
			System.out.println("Thank you for your stay with Aeva Arena.");
			System.out.println("Your account has been flagged for deletion");
			System.out.println("and will be destroyed upon next logout.");
			pgm.in.nextLine();
			pgm.in.nextLine();
		}
		else if(selection == 'N' || selection == 'n')
		{
			System.out.println("I'll take it this was just an accident then.");
		}
		else 
		{
			System.out.println("That wasn't something I recognize. \n Try that again when you want to give a true answer");
		}
	}
	
	public void saveAcc()
	{
		System.out.println("You have chosen to keep your account...");
		System.out.print("Are you sure this is what you want to do? Y/N:");
		char selection = pgm.in.next().charAt(0);
		if (selection =='Y' || selection =='y')
		{
			accountFlagged = false;
			System.out.println("Thank you for choosing to stay with Aeva Arena.");
			System.out.println("We have removed your deletion status");
			System.out.println("We are glad to have you back.");
			pgm.in.nextLine();
			pgm.in.nextLine();
		}
		else if(selection == 'N' || selection == 'n')
		{
			System.out.println("Sticking to your decision despite second thoughts, at least you are sure.");
		}
		else 
		{
			System.out.println("That wasn't something I recognize. \n Try that again when you want to give a true answer");
		}
	}
	
	public void logout()
	{
		System.out.println("Farewell " + this.Name + " we hope to see you again soon.");
		System.out.println();
	}
}