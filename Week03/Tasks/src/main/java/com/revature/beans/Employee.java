package com.revature.beans;

public class Employee {
	private int empid;
	private String userN;
	private String passW;
	private String firstN;
	private String lastN;
	private int dirSupId;
	private int depId;
	private double balance;
	private double pending;
	private double awarded;
	private int empType;
	private int auth;
	
	public Employee(int empid, String userN, String passW, String firstN, String lastN, int dirSupId, int depId,
			double balance, double pending, double awarded, int empType, int auth) {
		super();
		this.empid = empid;
		this.userN = userN;
		this.passW = passW;
		this.firstN = firstN;
		this.lastN = lastN;
		this.dirSupId = dirSupId;
		this.depId = depId;
		this.balance = balance;
		this.pending = pending;
		this.awarded = awarded;
		this.empType = empType;
		this.auth = auth;
	}
	public Employee(int empid, String userN, String passW, String firstN, String lastN,
			int dirSupId, int depId, int empType) {
		super();
		this.empid = empid;
		this.userN = userN;
		this.passW = passW;
		this.firstN = firstN;
		this.lastN = lastN;
		this.dirSupId = dirSupId;
		this.depId = depId;
		this.balance = 0;
		this.pending = 0;
		this.awarded = 0;
		this.empType = empType;
		this.auth = 0;
	}
	/**
	 * @return the empid
	 */
	public int getEmpid() {
		return empid;
	}
	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	/**
	 * @return the userN
	 */
	public String getUserN() {
		return userN;
	}
	/**
	 * @param userN the userN to set
	 */
	public void setUserN(String userN) {
		this.userN = userN;
	}
	/**
	 * @return the passW
	 */
	public String getPassW() {
		return passW;
	}
	/**
	 * @param passW the passW to set
	 */
	public void setPassW(String passW) {
		this.passW = passW;
	}
	/**
	 * @return the firstN
	 */
	public String getFirstN() {
		return firstN;
	}
	/**
	 * @param firstN the firstN to set
	 */
	public void setFirstN(String firstN) {
		this.firstN = firstN;
	}
	/**
	 * @return the lastN
	 */
	public String getLastN() {
		return lastN;
	}
	/**
	 * @param lastN the lastN to set
	 */
	public void setLastN(String lastN) {
		this.lastN = lastN;
	}
	/**
	 * @return the dirSupId
	 */
	public int getDirSupId() {
		return dirSupId;
	}
	/**
	 * @param dirSupId the dirSupId to set
	 */
	public void setDirSupId(int dirSupId) {
		this.dirSupId = dirSupId;
	}
	/**
	 * @return the depId
	 */
	public int getDepId() {
		return depId;
	}
	/**
	 * @param depId the depId to set
	 */
	public void setDepId(int depId) {
		this.depId = depId;
	}
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * @return the pending
	 */
	public double getPending() {
		return pending;
	}
	/**
	 * @param pending the pending to set
	 */
	public void setPending(double pending) {
		this.pending = pending;
	}
	/**
	 * @return the awarded
	 */
	public double getAwarded() {
		return awarded;
	}
	/**
	 * @param awarded the awarded to set
	 */
	public void setAwarded(double awarded) {
		this.awarded = awarded;
	}
	/**
	 * @return the empType
	 */
	public int getEmpType() {
		return empType;
	}
	/**
	 * @param empType the empType to set
	 */
	public void setEmpType(int empType) {
		this.empType = empType;
	}
	/**
	 * @return the auth
	 */
	public int getAuth() {
		return auth;
	}
	/**
	 * @param auth the auth to set
	 */
	public void setAuth(int auth) {
		this.auth = auth;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", userN=" + userN + ", passW=" + passW + ", firstN=" + firstN + ", lastN="
				+ lastN + ", dirSupId=" + dirSupId + ", depId=" + depId + ", balance=" + balance + ", pending="
				+ pending + ", awarded=" + awarded + ", empType=" + empType + ", auth=" + auth + "]";
	}
	
}
