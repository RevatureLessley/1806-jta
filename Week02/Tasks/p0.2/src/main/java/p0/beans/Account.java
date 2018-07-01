package p0.beans;

import p0.Launcher;

public class Account {

	protected String uName;
	protected String uPass;
	protected String Name;
	protected transient Launcher pgm;
	
	public Account(String uName, String uPass, String name) {
		super();
		this.uName = uName;
		this.uPass = uPass;
		Name = name;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPass() {
		return uPass;
	}

	public void setuPass(String uPass) {
		this.uPass = uPass;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Launcher getPgm() {
		return pgm;
	}

	public void setPgm(Launcher pgm) {
		this.pgm = pgm;
	}
}
