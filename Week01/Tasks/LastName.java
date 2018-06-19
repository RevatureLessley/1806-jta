import java.io.*;

public class LastName extends AccountAttribute {
	private String lastname;

	LastName() {
		lastname = console.readLine("lastname: ");
	}

	public String get() {
		return lastname;
	}
}
