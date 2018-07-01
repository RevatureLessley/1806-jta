package main;

public class Admin extends User {

	/**
	 * default constructor
	 */
	public Admin() {
		super();

	}
	
	/**
	 * Constructor from inherited class
	 * @param userid
	 * @param pass
	 * @param fname
	 * @param lname
	 */
	public Admin(String userid, String pass, String fname, String lname) {
		super(userid, pass, fname, lname);
	}
	
	
}
