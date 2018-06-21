import java.io.*;

public class Username extends AccountAttribute implements Serializable {
	private String username;
	
	Username(AdminAccount aa) {
		super(aa);
		username = "admin";
		aa.addAttribute("Username", this);
	}
	
	Username(Account a) {
		super(a);
		username = console.readLine("Username: ");
		a.addAttribute("Username", this);
	}

	@Override
	public void print() {
		System.out.println("Username: " + username);
	}

	@Override
	public Integer getID() {
		return username.hashCode();
	}
}
