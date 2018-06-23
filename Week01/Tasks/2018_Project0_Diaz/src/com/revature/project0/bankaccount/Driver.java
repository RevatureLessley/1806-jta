package com.revature.project0.bankaccount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		
		
//		NewAccount[] test = {
//				new NewAccount(NewAccount.getFname(),NewAccount.getlName(),NewAccount.getAccountNummber())
//				//new NewAccount("Jhon","Doe","125")		
//				};
//		NewAccount2[] test2 = {
//				new NewAccount2(NewAccount2.getfName2(),NewAccount2.getlName2(),NewAccount2.getaccountNummber2())
//		};
//		
//		TransUnion union = new TransUnion("RyantureTransUnion",test);
//		TransUnion2 union2 = new TransUnion2("RyantureTransUnion",test2);
//		
//		try{
//			ObjectOutputStream oos = new ObjectOutputStream(
//										new FileOutputStream("TransUnion.ser"));
//			oos.writeObject(union); 
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		
//		try{
//			ObjectOutputStream oos = new ObjectOutputStream(
//										new FileOutputStream("TransUnion2.ser"));
//			oos.writeObject(union2); 
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		
		//SecondMenu actions = new SecondMenu("zack","1180");
		//actions.show();
		
		
		//NewAccount.userInfor();
		//NewAccount2.userInfor2();	
		
		

		//System.out.println(union2);
		
		BankMenu.menu();

	}
	
	
}
