package Project0.bean;

/**
 * The User class is a blueprint for a user object following the 'bean' design pattern.
 * @author Vladimir Bukhalo
 *
 */
public class User {
	private Integer id;
	private String userName;
	private String password;
	private Integer account;
	private boolean isApproved;
	private boolean isAdmin;
	
	
	public User(Integer id, String userName, String password, Integer account, boolean isApproved, boolean isAdmin) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.account = account;
		this.isApproved = isApproved;
		this.isAdmin = isAdmin;
	}
	
	public User(String userName, String password, Integer account, boolean isApproved, boolean isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.account = account;
		this.isApproved = isApproved;
		this.isAdmin = isAdmin;
	}
	
	public User(String userName, String password, boolean isApproved, boolean isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.isApproved = isApproved;
		this.isAdmin = isAdmin;
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAccount() {
		return account;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	

}
