package Project0.bean;

/**
 * The UnAppUser class is a blueprint for an unapproved user object following the 'bean' design pattern.
 * @author Vladimir Bukhalo
 *
 */
public class UnAppUser {
	private Integer id;
	private String username;
	
	public UnAppUser(Integer id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	public UnAppUser(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
