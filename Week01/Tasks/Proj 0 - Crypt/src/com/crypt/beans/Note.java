package com.crypt.beans;

public class Note {
	private int id;
	private int acctId;
	private String content;
	
	public Note() {
		super();
	}
	
	public Note(int id, int acctId, String content) {
		super();
		this.id = id;
		this.acctId = acctId;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAcctId() {
		return acctId;
	}
	
	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}	
}
