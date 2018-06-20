import java.io.*;

public class FirstName extends AccountAttribute implements Serializable{
	private String firstname;
	
	FirstName(AdminAccount aa) {
		super(aa);
		firstname = "admin";
	}
	
	FirstName(Account a) {
		super(a);
		firstname = console.readLine("firstname: ");
	}
	
	@Override
	public void print() {
		System.out.println(firstname);
	}
}
