package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.RevatureAccounts.*;

public class LastName extends AccountAttribute implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4784336663257381167L;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public String get() {
		return lastname;
	}
	
	@Override
	public void print() {
		System.out.print("Lastname: " + lastname);
	}

	@Override 
	public Integer getID() {
		return lastname.hashCode();
	}
}
