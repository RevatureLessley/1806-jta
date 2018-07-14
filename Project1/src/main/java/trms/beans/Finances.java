package trms.beans;

public class Finances {
	private String employeeUUID;
	private int availableReimbursement;
	private int pendingReimbursement;
	private int awardedReimbursement;
	
	public Finances() {
		
	}
	
	public String getEmployeeUUID() {
		return employeeUUID;
	}
	
	public void setEmployeeUUID(String uuid) {
		this.employeeUUID = uuid;
	}
	
	public int getAvailableReimbursement() {
		return this.availableReimbursement;
	}
	
	public void setAvailableReimbursement(int amount) {
		this.availableReimbursement = amount;
	}
	
	public int getPendingReimbursement() {
		return this.pendingReimbursement;
	}
	
	public void setPendingReimbursement(int amount) {
		this.pendingReimbursement = amount;
	}
	
	public int getAwardedReimbursement() {
		return this.awardedReimbursement;
	}
	
	public void setAwardedReimbursement(int amount) {
		this.awardedReimbursement = amount;
	}
}
