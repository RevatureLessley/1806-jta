package Tasks.RevatureAccounts.AccountAttributes;;

import java.io.*;
import Tasks.RevatureAccounts.*;

public class LastName extends AccountAttribute implements Serializable{
	private String lastname;
	
	public LastName(AdminAccount aa) {
		super(aa);
		lastname = "admin";
		aa.addAttribute("Lastname", this);
	}

	public LastName(UserAccount ua) {
		super(ua);
		lastname = askUser();
		ua.addAttribute("Lastname", this);
	}

	@Override
	public String askUser() {
		return console.readLine("Lastname: ");
	}
	
	@Override
	public String get() {
		return lastname;
	}
	
	@Override
	public void print() {
		System.out.println("Lastname: " + lastname);
	}

	@Override 
	public Integer getID() {
		return lastname.hashCode();
	}
}
