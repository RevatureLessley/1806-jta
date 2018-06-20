import java.io.*;

public class FirstName extends AccountAttribute implements Serializable{
	private String firstname;

	FirstName(Account a) {
		super(a);
		firstname = console.readLine("firstname: ");
	}
	
	@Override
	public void print() {
		System.out.println(firstname);
	}
}
