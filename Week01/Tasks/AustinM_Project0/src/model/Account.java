package model;
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
	private boolean validated;
	private User owner;

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public Account(User owner, String name, int type) {
		this.name = name;
		this.accountType = type;
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}
	
	public String getOwnerName() {
		return owner.getName();
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

	public User getOwner() {
		return owner;
	}
}
