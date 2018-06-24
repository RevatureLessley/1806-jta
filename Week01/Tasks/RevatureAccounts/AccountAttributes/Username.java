package Tasks.RevatureAccounts.AccountAttributes;;

import java.io.*;
import Tasks.*;
import Tasks.RevatureAccounts.*;

public class Username extends AccountAttribute implements Serializable {
	private String username;
	private String u;
	
	public Username(AdminAccount aa) {
		super(aa);
		username = "admin";
		aa.addAttribute("Username", this);
	}
	
	public Username(UserAccount ua) {
		super(ua);
		username = askUser();
		ua.addAttribute("Username", this);
	}

	@Override
	public String askUser() {
		do{
			getUsername();
		} while(RevatureBank.accountExists(u));

		return u;
	}

	public void getUsername() {
		u = console.readLine("Username: ");
	}
	
	@Override
	public String get() {
		return username;
	}
	
	@Override
	public void print() {
		System.out.print("Username: " + username);
	}

	@Override
	public Integer getID() {
		return username.hashCode();
	}
}
