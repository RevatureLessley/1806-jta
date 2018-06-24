package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

public abstract class AccountAttribute implements ConsoleReference, Serializable{
 	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1507218302429698089L;

	public AccountAttribute(AdminAccount aa) {}
 	
	public AccountAttribute(UserAccount ua) {}

	public void approve() {}
	
	public String askUser() {
		return null;
	}

	public void deny () {}
	
	public void deposit() {}
	
	public void display(Account a) {}
	
	public <E> E get() {
		return null;
	}

 	abstract public void print();

	abstract public Integer getID();
	
	public void withdraw() {}
}
