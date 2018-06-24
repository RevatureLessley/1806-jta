package com.revature.project0.bankaccount;

import java.io.Serializable;
import java.util.Arrays;

public class TransUnion2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484758735113820656L;

	private static String union;
	private NewAccount2[] newAccount2;
	

	public String getUnion() {
		return union;
	}

	public void setUnion(String union) {
		this.union = union;
	}

	public NewAccount2[] getNewAccount2() {
		return newAccount2;
	}

	public void setNewAccount2(NewAccount2[] newAccount2) {
		this.newAccount2 = newAccount2;
	}

	public TransUnion2(){
		super();
	}
	
	public TransUnion2(String union, NewAccount2[] newAccount2){
		this.union =union;
		this.newAccount2 = newAccount2;
	}
	
	
	@Override
	public String toString() {
		return "RyantureTransUnion [Account#2] " + "\n" + Arrays.toString(newAccount2) + "]";
	}
	
	
}
