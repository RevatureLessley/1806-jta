import java.io.*;

public class Password extends AccountAttribute implements Serializable {
	// This class needs to be secured.
	private String password;
	private String p;
	private String q;

	Password(Account a) {
		super(a);
		
		do{
			getPassword();
		}while(!p.equals(q));

		password = p;
	}

	private void getPassword(){
		p = String.valueOf(console.readPassword("password: "));
		q = String.valueOf(console.readPassword("confirm password: "));
	}
	
	@Override
	public void print() {
		System.out.println(password);
	}
}
