import java.io.*;

public class LastName extends AccountAttribute {
	private String lastname;

	LastName(Account a){
		super(a);
		lastname = console.readLine("lastname: ");
	}
	
	@Override
	public void print() {
		System.out.println(lastname);
	}
}
