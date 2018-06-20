import java.io.*;

public class Username extends AccountAttribute implements Serializable {
	private String username;

	Username(Account a) {
		super(a);
		username = console.readLine("username: ");
	}

	@Override
	public void print() {
		System.out.println(username);
	}
}
