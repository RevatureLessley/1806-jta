import java.io.*;

public class Username extends AccountAttribute {
	private String username;

	Username() {
		username = console.readLine("username: ");
	}

	public String get() {
		return username;
	}
}
