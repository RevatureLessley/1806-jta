import java.io.*;

public class Username extends AccountAttribute {
	private String username;

	Username(Account a) {
		super(a);
	}

	public void askUser() {
		username = console.readLine("username: ");
	}

	public String get() {
		return username;
	}
}
