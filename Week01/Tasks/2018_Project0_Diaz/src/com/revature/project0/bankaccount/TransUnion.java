package com.revature.project0.bankaccount;

import java.io.Serializable;
import java.util.Arrays;
/**
 * TransUnion sets up the the bank itself so the bank name and union comes out of it  for the account 1
 * @author Zachary Diaz
 *
 */
public class TransUnion implements Serializable{
	private static final long serialVersionUID = -8827696702407498043L;
	private static String union;
	private NewAccount[] newAccount;
	
	public String getUnion() {
		return union;
	}
	public void setUnion(String union) {
		this.union = union;
	}
	public NewAccount[] getNewAccount() {
		return newAccount;
	}
	public void setNewAccount(NewAccount[] newAccount) {
		this.newAccount = newAccount;
	}
	public TransUnion(){
		super();
	}
	public TransUnion(String union, NewAccount[] newAccount){
		this.union =union;
		this.newAccount = newAccount;
	}
	@Override
	public String toString() {
		return "RyantureTransUnion [Account#1] " + "\n" + Arrays.toString(newAccount);
	}
	
	
}
