package proj0;

public class Admin extends User {

	/**
	 * serialUID for Admin
	 */
	private static final long serialVersionUID = 7009823248799591097L;

	/**
	 * default constructor
	 */
	
	public Admin() {
		super();

	}

	public Admin(String userid, String pass, String fname, String lname) {
		super(userid, pass, fname, lname);
	}
	
	public void approveUser(User user) {
		
	}
	
}
