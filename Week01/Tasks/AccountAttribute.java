import java.io.*;

public class AccountAttribute {
	protected static Console console = System.console();
	
	AccountAttribute(Account a){
		a.addAttribute(this);
	};
}
