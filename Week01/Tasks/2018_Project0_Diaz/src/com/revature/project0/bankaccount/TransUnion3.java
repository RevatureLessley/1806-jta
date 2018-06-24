package com.revature.project0.bankaccount;

import java.io.Serializable;
import java.util.Arrays;

public class TransUnion3 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484758735113820656L;

	private static String union3;
	private NewAccount3[] newAccount3;
	

	public String getUnion() {
		return union3;
	}

	public void setUnion(String union3) {
		this.union3 = union3;
	}

	public NewAccount3[] getNewAccount3() {
		return newAccount3;
	}

	public void setNewAccount3(NewAccount3[] newAccount3) {
		this.newAccount3 = newAccount3;
	}

	public TransUnion3(){
		super();
	}
	
	public TransUnion3(String union3, NewAccount3[] newAccount3){
		this.union3 =union3;
		this.newAccount3 = newAccount3;
	}
	
	
	@Override
	public String toString() {
		return "RyantureTransUnion [Account#3] " + "\n" + Arrays.toString(newAccount3) ;
	}
	
	
}
