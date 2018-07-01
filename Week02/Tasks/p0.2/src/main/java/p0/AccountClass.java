package p0;

import p0.beans.Account;

public abstract class AccountClass
{
	protected Account AccountInfo;
	protected Launcher pgm;
	
	public AccountClass()
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
	public AccountClass(String name, String uname, String pword)
	{
		AccountInfo = new Account(name, uname, pword);
	}
	
	public void setPgm(Launcher pgm)
	{
		this.pgm = pgm;
	}

	public String getName() {
		return AccountInfo.getName();
	}

	public String getuName() {
		return AccountInfo.getuName();
	}

	public String getuPass() {
		return AccountInfo.getuPass();
	}
	
	/**
	 * Abstract method to represent the default selection menu for each account.
	 */
	public abstract void menu();
	
	/**
	 * Give the player the ability to flag their account to be deleted upon the next time they
	 * log out, removing the given player account from the list of saved accounts in AccountList
	 */

	
	public void logout()
	{
		System.out.println("Farewell " + AccountInfo.getName() + " we hope to see you again soon.");
		System.out.println();
	}
}