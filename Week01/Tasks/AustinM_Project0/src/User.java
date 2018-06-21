import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class User implements Serializable {

	private static final long serialVersionUID = 7881493291594545515L;
	private ArrayList<Account> accounts;
	private String name;

	private String password;
	private boolean validated = false;
	private boolean admin = false;
	
	private int nextAcctNum;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
		accounts = new ArrayList<Account>();
		nextAcctNum = (int)Math.floor(Math.random()*8000 + 1000);
	}

	public String getName() {
		return name;
	}

	public void validateNewUser() {
		validated = true;
	}

	public int validateLogin(String password) {
		if (!validated)
			return -1;
		if (password.equals(this.password))
			return 1;

		return 0;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Account addAccount(String name, int type) {
		Account account = new Account(name + " " + nextAcctNum, type);
		accounts.add(account);
		
		return account;
	}

	public String[] getAccountNames() {
		String[] acctNames = new String[accounts.size()];
		Account a;
		
		for (int i = 0; i < acctNames.length; i++) {
			a = accounts.get(i);
			acctNames[i] = a.getName() + " - " + Account.formatCurrency(a.getBalance());
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
			sb.append('\n');
		}

		return sb.toString();
	}

	public double totalBalance() {
		double total = 0;
		
		for(Account a : accounts) {
			total += a.getBalance();
		}
		
		return total;
	}

	public boolean isValidated() {
		return validated;
	}

}
