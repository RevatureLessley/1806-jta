package p0.beans;

public class Administrator {
	private Account AccountInfo;
	
	public Administrator(String name, String uName, String pWord) {
		AccountInfo = new Account(name,uName, pWord);
	}
	
	public Account getAccountInfo() {
		return AccountInfo;
	}
}
