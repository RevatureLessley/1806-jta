package Tasks.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.math.*;
import Tasks.*;
import Tasks.RevatureAccounts.*;

public abstract class AccountAttribute implements ConsoleReference, Serializable{
 	
	public AccountAttribute(AdminAccount aa) {}
 	
	public AccountAttribute(UserAccount ua) {}

	abstract public String askUser();

	public void deposit() {}

	public <E> E get() {
		return null;
	}

 	abstract public void print();

	abstract public Integer getID();
}
