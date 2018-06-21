import java.io.*;

public class FirstName extends AccountAttribute implements Serializable{
	private String firstname;
	
	FirstName(AdminAccount aa) {
		super(aa);
		firstname = "admin";
		aa.addAttribute("Firstname", this);
	}
	
	FirstName(Account a) {
		super(a);
		firstname = console.readLine("Firstname: ");
		a.addAttribute("Firstname", this);
	}
	
	@Override
	public void print() {
		System.out.println("Firstname: " + firstname);
	}

	@Override
	public Integer getID() {
		return firstname.hashCode();
	}
}
