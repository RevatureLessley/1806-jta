import java.io.*;

public class LastName extends AccountAttribute implements Serializable{
	private String lastname;
	
	LastName(AdminAccount aa) {
		super(aa);
		lastname = "admin";
	}

	LastName(Account a) {
		super(a);
		lastname = console.readLine("lastname: ");
	}
	
	@Override
	public void print() {
		System.out.println(lastname);
	}
}
