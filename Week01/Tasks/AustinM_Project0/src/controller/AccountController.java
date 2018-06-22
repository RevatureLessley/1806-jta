package controller;
import java.io.Serializable;
import java.util.ArrayList;

import model.Account;

public class AccountController implements Serializable {

	private static final long serialVersionUID = -1767835589025835300L;
	private ArrayList<Account> unvalidatedAccounts;
	private ArrayList<Account> allAccounts;
	private int nextAcctNum = 0;

	public AccountController() {
		unvalidatedAccounts = new ArrayList<Account>();
		allAccounts = new ArrayList<Account>();
	}

	public void validateAccount(Account account) {
		account.setValidated(true);
		unvalidatedAccounts.remove(account);
		allAccounts.add(account);
	}

	public int getWaitAccountCount() {
		return unvalidatedAccounts.size();
	}

	public Account getWaitAccount(int i) {
		return unvalidatedAccounts.get(i);
	}

	public void addNewAccount(Account account) {
		if (account.getOwner().isAdmin()) {
			allAccounts.add(account);
			account.setValidated(true);
		}else
			unvalidatedAccounts.add(account);
	}

	public String[] getWaitAccountNames() {
		String[] names = new String[unvalidatedAccounts.size()];
		int i = 0;

		for (Account a : unvalidatedAccounts) {
			names[i++] = a.getOwnerName() + " :: " + a.getName();
		}

		return names;
	}

	public int getNextNumber() {
		return nextAcctNum++;
	}
}
