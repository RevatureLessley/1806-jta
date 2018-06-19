import java.io.*;

public class Username extends AccountDetails {
	private String username;

	Username() {
		username = console.readLine("username: ");
	}

	public String get() {
		return username;
	}
}
