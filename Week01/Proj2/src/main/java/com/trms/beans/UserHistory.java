package com.trms.beans;

public class UserHistory {
//    historyID number(6),
//    userID number(6),
//    content VARCHAR(2000),
	
	private int historyID, userID;
	private String content;
	
	public UserHistory() {
		super();
	}

	public UserHistory(int historyID, int userID, String content) {
		super();
		this.historyID = historyID;
		this.userID = userID;
		this.content = content;
	}

	public int getHistoryID() {
		return historyID;
	}

	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "UserHistory [historyID=" + historyID + ", userID=" + userID + ", content=" + content + "]";
	}
	
	
}
