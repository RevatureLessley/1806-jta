package p0;

import p0.beans.Account;

public abstract class AccountClass
{
	protected Launcher pgm;
	
	public void setPgm(Launcher pgm)
	{
		this.pgm = pgm;
	}
	
	/**
	 * Abstract method to represent the default selection menu for each account.
	 */
	public abstract void menu();
	
	public abstract Account getAccount();
	
	/**
	 * Give the player the ability to flag their account to be deleted upon the next time they
	 * log out, removing the given player account from the list of saved accounts in AccountList
	 */
	public abstract void logout();
}