package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import Project0.RevatureAccounts.*;

public class Status extends AccountAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1583445065455260161L;
	private AccountStatus status;
	
	public Status(AdminAccount aa) {
		super(aa);
		status = AccountStatus.APPROVED;
		aa.addAttribute("Status", this);
	}
	
	public Status(UserAccount ua) {
		super(ua);
		status = AccountStatus.PENDING;
		ua.addAttribute("Status", this);
	}
	
	@Override
	public void approve() {
		status = AccountStatus.APPROVED;
	}
	
	@Override
	public void deny() {
		status = AccountStatus.DENIED;
	}

	@Override
	public void display(Account a) {
		status.display(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccountStatus get() {
		return status;
	}
	
	@Override
	public void print() {
		System.out.print("Status: " + status);
	}

	@Override
	public Integer getID() {
		return status.hashCode();
	}
}
