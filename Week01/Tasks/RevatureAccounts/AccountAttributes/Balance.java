package Tasks.RevatureAccounts.AccountAttributes;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import Tasks.RevatureAccounts.*;

public class Balance extends AccountAttribute implements Serializable {
	private BigDecimal balance;
	
	public Balance(UserAccount ua) {
		super(ua);
		balance = new BigDecimal(0.0);
		ua.addAttribute("Balance", this);
	}

	@Override
	public String askUser() {
		return console.readLine("Please enter a dollar value:\n>");
	}

	@Override
	public void deposit() {
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

		balance = balance.add(new BigDecimal(num.toString()));
		print();
	}
	
	@Override
	public BigDecimal get() {
		return balance;
	}
	
	@Override
	public void print() {
		System.out.println("Transaction approved.");
		System.out.print("Your current balance is: ");
		System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(balance));
	}

	@Override
	public Integer getID() {
		return balance.hashCode();
	}
}
