package p0.beans;

public class Banker {
	private double interest;
	private Account AccountInfo;

	public Banker(double i, String name, String uname, String pword) {
		this.interest = i;
		AccountInfo = new Account(name, uname, pword);
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
