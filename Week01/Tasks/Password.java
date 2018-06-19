import java.io.*;

public class Password extends AccountAttribute {
	// This class needs to be secured.
	private String password;

	Password(Account a) {
		super(a);
		password = String.valueOf(console.readPassword("password: "));
	}
	
	@Override
	public void print() {
		System.out.println(password);
	}
}
