package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.*;
import Project0.RevatureAccounts.*;

public class Username extends AccountAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3539688509393580418L;
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
	
	@SuppressWarnings("unchecked")
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
