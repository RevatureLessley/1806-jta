package com.trms.beans;

import java.sql.Date;

public class Approval {
//    approvalID number(6),
//    approved number(1) CHECK(approved=1 OR approved=0),
//    approvingUserID number(6),
//    approvalDate date,

	private int approvalID, approvingUserID;
	private boolean approved;
	private Date approvalDate;
	
	public Approval() {
		super();
	}

	public Approval(int approvalID, int approvingUserID, boolean approved, Date approvalDate) {
		super();
		this.approvalID = approvalID;
		this.approvingUserID = approvingUserID;
		this.approved = approved;
		this.approvalDate = approvalDate;
	}

	public int getApprovalID() {
		return approvalID;
	}

	public void setApprovalID(int approvalID) {
		this.approvalID = approvalID;
	}

	public int getApprovingUserID() {
		return approvingUserID;
	}

	public void setApprovingUserID(int approvingUserID) {
		this.approvingUserID = approvingUserID;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Override
	public String toString() {
		return "Approval [approvalID=" + approvalID + ", approvingUserID=" + approvingUserID + ", approved=" + approved
				+ ", approvalDate=" + approvalDate + "]";
	}
}
