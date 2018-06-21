
public class Account 
{
	private String userName;
	private String password;
	private int accountNumber;
	private float accountValue;
	
	public Account(String userName, String password, int accountNumber, float accountValue) 
	{
		super();
		this.userName = userName;
		this.password = password;
		this.accountNumber = accountNumber;
		this.accountValue = accountValue;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public float getAccountValue() 
	{
		return accountValue;
	}

	public void setAccountValue(float accountValue) 
	{
		this.accountValue = accountValue;
	}
	
	
	
	
}
