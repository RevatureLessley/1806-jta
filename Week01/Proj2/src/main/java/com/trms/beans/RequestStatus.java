package com.trms.beans;

public class RequestStatus {
//    statusID number(6),
//    superApprovalID number(6) UNIQUE,
//    depHeadApprovalID number(6) UNIQUE,
//    benCoApprovalID number(6) UNIQUE,
//    urgent number(1) CHECK(urgent=1 OR urgent=0),
//    canceled number(1) CHECK(canceled=1 OR canceled=0),
//    exceededAvailableFunds number(1)
	private int statusID, superApprovalID, depHeadApprovalID, benCoApprovalID;
	boolean urgent, canceled, exceededAvailableFunds;
	
	public RequestStatus() {
		super();
	}

	
	
	public RequestStatus(int statusID, int superApprovalID, int depHeadApprovalID, int benCoApprovalID, boolean urgent,
			boolean canceled, boolean exceedeAvailableFunds) {
		super();
		this.statusID = statusID;
		this.superApprovalID = superApprovalID;
		this.depHeadApprovalID = depHeadApprovalID;
		this.benCoApprovalID = benCoApprovalID;
		this.urgent = urgent;
		this.canceled = canceled;
		this.exceededAvailableFunds = exceedeAvailableFunds;
	}



	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getSuperApprovalID() {
		return superApprovalID;
	}

	public void setSuperApprovalID(int superApprovalID) {
		this.superApprovalID = superApprovalID;
	}

	public int getDepHeadApprovalID() {
		return depHeadApprovalID;
	}

	public void setDepHeadApprovalID(int depHeadApprovalID) {
		this.depHeadApprovalID = depHeadApprovalID;
	}

	public int getBenCoApprovalID() {
		return benCoApprovalID;
	}

	public void setBenCoApprovalID(int benCoApprovalID) {
		this.benCoApprovalID = benCoApprovalID;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public boolean isExceededAvailableFunds() {
		return exceededAvailableFunds;
	}

	public void setExceededAvailableFunds(boolean exceedeAvailableFunds) {
		this.exceededAvailableFunds = exceedeAvailableFunds;
	}

	@Override
	public String toString() {
		return "RequestStatus [statusID=" + statusID + ", superApprovalID=" + superApprovalID + ", depHeadApprovalID="
				+ depHeadApprovalID + ", benCoApprovalID=" + benCoApprovalID + ", urgent=" + urgent + ", canceled="
				+ canceled + ", exceedeAvailableFunds=" + exceededAvailableFunds + "]";
	}
}
