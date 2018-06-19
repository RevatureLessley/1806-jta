import java.io.*;

public class LastName extends AccountAttribute {
	private String lastname;

	LastName(Account a){
		super(a);
		lastname = console.readLine("lastname: ");
	}

	public String get() {
		return lastname;
	}
}
