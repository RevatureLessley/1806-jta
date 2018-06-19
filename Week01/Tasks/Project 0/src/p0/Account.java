package p0;

public class Account 
{
	protected String uName;
	protected String uPass;
	protected String Name;
	
	public Account()
	{
		
	}
	
	public Account(String name, String uname, String pword)
	{
		this.Name = name;
		this.uName = uname;
		this.uPass = pword;
	}

	public String getName() {
		return Name;
	}

	public String getuName() {
		return uName;
	}

	public String getuPass() {
		return uPass;
	}	
}
