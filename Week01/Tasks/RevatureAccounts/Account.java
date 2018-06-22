package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import Tasks.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public abstract class Account implements ConsoleReference, Serializable {
	protected HashMap<String, AccountAttribute> attributes = new HashMap<>();
	protected AccountStatus status;

 	public Account() {}

 	public void addAttribute(String field, AccountAttribute aa) {
  		attributes.put(field, aa);
 	}

	// abstract public void enter();

	public Integer getID(){
		Integer index = attributes.get("Username").getID() + 
				attributes.get("Password").getID();
		return index.hashCode();
	}

 	public void print(){
		for(AccountAttribute aa : attributes.values())
			aa.print();
 	}

	public static Integer signIn(){
		Integer username = console.readLine("Username: ").hashCode();
		Integer password = String.valueOf(console.readPassword("Password: ")).hashCode();
		Integer index = username + password;

		return index.hashCode();	
	}
}
