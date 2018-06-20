import java.util.ArrayList;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class User {

	private ArrayList<Account> accounts;
	private String name;

	private String password;
	private boolean validated = false;
	private boolean admin = false;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
		accounts = new ArrayList<Account>();
	}

	public String getName() {
		return name;
	}

	public void validateNewUser() {
		validated = true;
	}

	public boolean validateLogin(String password) {
		if (!validated)
			return false;
		if (password.equals(this.password))
			return true;

		return false;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void addAccount(String name, int type) {
		Account account = new Account(name, type);
		accounts.add(account);
	}

	public String[] getAccountNames() {
		String[] acctNames = new String[accounts.size()];

		for (int i = 0; i < acctNames.length; i++) {
			acctNames[i] = accounts.get(i).getName();
		}

		return acctNames;
	}

	public Account getAccount(int i) {
		try {
			return accounts.get(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public String accountSummary() {
		StringBuilder sb = new StringBuilder();

		for (Account a : accounts) {
			sb.append(a.toString());
		}

		return null;
	}

}
