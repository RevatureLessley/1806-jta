package p0.beans;

public class Banker {
	private double interest;
	private Account AccountInfo;

	public Banker(String name, String uname, String pword, double i) {
		AccountInfo = new Account(uname, pword, name);
		this.interest = i;
	}

	public Account getAccountInfo() {
		return AccountInfo;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
	
}
