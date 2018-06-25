package com.bank.main;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable{
	
	private static final long serialVersionUID = -9160047938153344115L;
	
	private ArrayList<User> users;

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Users(ArrayList<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Users [users=" + users + "]";
	}
	
}
