import java.io.*;

public class LastName extends AccountAttribute {
	private String lastname;

	LastName(Account a){
		super(a);
	}

	public void askUser() {
		lastname = console.readLine("lastname: ");
	}

	public String get() {
		return lastname;
	}
}
