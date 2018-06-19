import java.io.*;

public class Username extends AccountAttribute {
	private String username;

	Username(Account a) {
		super(a);
		username = console.readLine("username: ");
	}

	public String get() {
		return username;
	}
}
