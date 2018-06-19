import java.io.*;

public class FirstName extends AccountAttribute {
	private String firstname;

	FirstName(Account a) {
		super(a);
		firstname = console.readLine("firstname: ");
	}

	public String get() {
		return firstname;
	}
}
