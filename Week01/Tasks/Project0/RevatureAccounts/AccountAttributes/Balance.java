package Project0.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import Project0.RevatureAccounts.*;

public class Balance extends AccountAttribute implements Serializable {
	private BigDecimal balance;
	
	public Balance(UserAccount ua) {
		super(ua);
		balance = new BigDecimal(0.0);
		ua.addAttribute("Balance", this);
	}

	@Override
	public String askUser() {
		return console.readLine("Please enter a $ followed by positive value:\n>");
	}

	@Override
	public void deposit() {
		balance = balance.add(getAmount());
		System.out.println("Transaction approved.");
		print();
	}

	private BigDecimal getAmount() {
		String value;
		Number num;

		do {
			try {
				value = askUser();
				num = NumberFormat.getCurrencyInstance(Locale.US).parse(value);
			}

			catch(ParseException pe) {
				num = null;
			}
		} while(num == null);

		return new BigDecimal(num.toString());
	}
	
	@Override
	public BigDecimal get() {
		return balance;
	}
	
	@Override
	public void print() {
		System.out.print("Your current balance is: ");
		System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(balance));
	}

	@Override
	public Integer getID() {
		return balance.hashCode();
	}

	@Override
	public void withdraw() {
		BigDecimal amount = getAmount();
		
		if(balance.compareTo(amount) < 0) {
			System.out.println("Transaction not approved.");
			System.out.println("You cannot withdraw more money than you have!.");

		}

		else {
			balance = balance.subtract(amount);
			System.out.println("Transaction approved.");

		}

		print();	
	}
}
