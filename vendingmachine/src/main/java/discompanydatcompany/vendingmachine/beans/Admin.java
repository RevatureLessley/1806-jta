package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;

public class Admin extends User implements Serializable{
	
	public Admin() {
		super();
	}
	
	public Admin(String name, String password, String aboutMe) {
		super(name, password, aboutMe, true);
	}
	
}
