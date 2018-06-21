import java.io.Serializable;
import java.text.NumberFormat;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 451996026957339012L;
	private String name;
	private double balance;
	private int accountType = 0;

	public Account(String name, int type) {
		this.name = name;
		this.accountType = type;
	}

	public double getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		balance -= amount;
	}

	@Override
	public String toString() {
		String s = name + " [" + accountType + "] bal: " + formatCurrency(balance);
		return s;
	}

	public static String formatCurrency(double a) {

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = nf.format(a);

		return s;
	}

	public static String getTypeName(int input) {
		switch (input) {
		case 2:
			return "savings";
		default:
			return "checking";
		}

	}
}
