import java.io.*;

public abstract class AccountAttribute {
	protected static Console console = System.console();
	
	AccountAttribute(Account a) {
		a.addAttribute(this);
	}

	abstract void print();
}
