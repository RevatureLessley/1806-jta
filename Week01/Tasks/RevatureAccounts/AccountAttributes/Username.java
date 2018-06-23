package Tasks.RevatureAccounts.AccountAttributes;;

import java.io.*;
import Tasks.RevatureAccounts.*;

public class Username extends AccountAttribute implements Serializable {
	private String username;
	
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
		return console.readLine("Username: ");
	}
	
	@Override
	public String get() {
		return username;
	}
	
	@Override
	public void print() {
		System.out.println("Username: " + username);
	}

	@Override
	public Integer getID() {
		return username.hashCode();
	}
}
