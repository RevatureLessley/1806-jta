package Tasks.RevatureAccounts.AccountAttributes;;

import java.io.*;
import Tasks.RevatureAccounts.*;

public class Status extends AccountAttribute implements Serializable {
	private enum States {
		APPROVED() {
			public void display(Account a) {
				a.approved();
			}
		}, 
	
		DENIED() {
			public void display(Account a) { 
				a.denied();
			}
		}, 
	
		PENDING() {
			public void display(Account a) {
				a.pending();
			}
		};

		public abstract void display(Account a);
	};

	private States status;
	
	public Status(AdminAccount aa) {
		super(aa);
		status = States.APPROVED;
		aa.addAttribute("Status", this);
	}
	
	public Status(UserAccount ua) {
		super(ua);
		status = States.PENDING;
		ua.addAttribute("Status", this);
	}

	@Override
	public void display(Account a) {
		status.display(a);
	}

	@Override
	public States get() {
		return status;
	}
	
	@Override
	public void print() {
		System.out.println("Status: " + status);
	}

	@Override
	public Integer getID() {
		return status.hashCode();
	}
}
