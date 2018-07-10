package com.revature.beans;

public class Approval {
	private int appId;
	private int rFormId;
	private int isApp;
	private int approverId;
	private int requesterId;
	/**
	 * @return the appId
	 */
	public int getAppId() {
		return appId;
	}
	/**
	 * @param appId the appId to set
	 */
	public void setAppId(int appId) {
		this.appId = appId;
	}
	/**
	 * @return the rFormId
	 */
	public int getrFormId() {
		return rFormId;
	}
	/**
	 * @param rFormId the rFormId to set
	 */
	public void setrFormId(int rFormId) {
		this.rFormId = rFormId;
	}
	/**
	 * @return the isApp
	 */
	public int getIsApp() {
		return isApp;
	}
	/**
	 * @param isApp the isApp to set
	 */
	public void setIsApp(int isApp) {
		this.isApp = isApp;
	}
	/**
	 * @return the approverId
	 */
	public int getApproverId() {
		return approverId;
	}
	/**
	 * @param approverId the approverId to set
	 */
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}
	/**
	 * @return the requesterId
	 */
	public int getRequesterId() {
		return requesterId;
	}
	/**
	 * @param requesterId the requesterId to set
	 */
	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Approval [appId=" + appId + ", rFormId=" + rFormId + ", isApp=" + isApp + ", approverId=" + approverId
				+ ", requesterId=" + requesterId + "]";
	}
	
}
