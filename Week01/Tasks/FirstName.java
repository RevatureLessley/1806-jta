import java.io.*;

public class FirstName extends AccountAttribute {
	private String firstname;

	FirstName() {
		firstname = console.readLine("firstname: ");
	}

	public String get() {
		return firstname;
	}
}
