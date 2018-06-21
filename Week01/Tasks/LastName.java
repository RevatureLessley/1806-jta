import java.io.*;

public class LastName extends AccountAttribute implements Serializable{
	private String lastname;
	
	LastName(AdminAccount aa) {
		super(aa);
		lastname = "admin";
		aa.addAttribute("Lastname", this);
	}

	LastName(UserAccount ua) {
		super(ua);
		lastname = askUser();
		ua.addAttribute("Lastname", this);
	}

	@Override
	public String askUser() {
		return console.readLine("lastname: ");
	}
	
	@Override
	public void print() {
		System.out.println("Lastname: " + lastname);
	}

	@Override Integer getID() {
		return lastname.hashCode();
	}
}
