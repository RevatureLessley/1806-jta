package trms.beans;

public class Role {
	private User user;
	private String roleName;
	
	public Role() {}
	
	public Role(User user, String roleName) {
		this.user = user;
		this.roleName = roleName;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
