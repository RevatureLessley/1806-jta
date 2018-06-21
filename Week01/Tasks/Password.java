import java.io.*;

public class Password extends AccountAttribute implements Serializable {
	// This class needs to be secured.
	private String password;
	private String p;
	private String q;

	Password(AdminAccount aa) {
		super(aa);
		password = "admin";
		aa.addAttribute("Password", this);
	}

	Password(Account a) {
		super(a);

		do{
			getPassword();
		}while(!p.equals(q));

		password = p;
		a.addAttribute("Password", this);
	}

	private void getPassword(){
		p = String.valueOf(console.readPassword("Password: "));
		q = String.valueOf(console.readPassword("Confirm Password: "));
	}
	
	@Override
	public void print() {
		System.out.println("Password: " + password);
	}

	@Override
	public Integer getID() {
		return password.hashCode();
	}
}
