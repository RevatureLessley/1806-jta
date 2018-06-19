import java.io.*;

public class Password extends AccountDetails {
	// This class needs to be secured.
	private String password;
	Password() {
		password = String.valueOf(console.readPassword("password: "));
	}

	public String get() {
		return password;
	}
}
