package com.revature.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.beans.NewAccount;
import com.revature.beans.NewAccount2;
import com.revature.beans.NewAccount3;
import com.revature.beans.TransUnion;
import com.revature.beans.TransUnion2;
import com.revature.beans.TransUnion3;
/**
 * Class BankMenu sets up the first menu to take usser input and it extends
 * SecondMenu to grab the information form the Supper Class
 * @author Zachary Diaz
 *
 */
public class BankMenu extends SecondMenu{
	
	private static double revatureTransUnion = 1_000_000;// The starting amount the bank has in its vault
	private static int i = 0;// sets up a count for the number of accounts that is currently set 
	Scanner in = new Scanner(System.in);// useer input
	/**
	 * menu is to take set up the three accounts ready to be save into an array 
	 * and then implements the first menu
	 */
	public static void menu() {
		Scanner in = new Scanner(System.in);// useer input
		int firstChoice =  0; // this int is for the switch statement
		
		//user accounts is only set up for a max of three, have not figured out how to make this easier
		
		NewAccount[] test = {
				new NewAccount(NewAccount.getFname(),NewAccount.getlName(),NewAccount.getAddress(),NewAccount.getPhone())		
				};
		NewAccount2[] test2 = {
				new NewAccount2(NewAccount2.getfName2(),NewAccount2.getlName2(),NewAccount2.getAddress2(),NewAccount2.getPhone2())
		};
		NewAccount3[] test3 = {
				new NewAccount3(NewAccount3.getfName3(),NewAccount3.getlName3(),NewAccount3.getAddress3(),NewAccount3.getPhone3())
		};
		
		TransUnion union = new TransUnion("RyantureTransUnion",test);
		TransUnion2 union2 = new TransUnion2("RyantureTransUnion",test2);
		TransUnion3 union3 = new TransUnion3("RyantureTransUnion",test3);
		
		
		try{	// all try catch methods here help serilize the information into arrays 
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion.txt"));
			oos.writeObject(union); 
		}catch(IOException e){
			e.printStackTrace();
		}	
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion2.ser"));
			oos.writeObject(union2); 
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion3.ser"));
			oos.writeObject(union3); 
		}catch(IOException e){
			e.printStackTrace();
		}
		
		intro();					// intro is at the bottom of SecondMenue class
		do {						// Do while statement sets up the first switch that will show the menu and its possibilities
			firstChoice = in.nextInt();
			switch(firstChoice){
				default: // default case
					System.err.println("Invalid entry please try again later. Pleaese try agan. ");
					break; 
				case 0:// case 0 logs out of application for some reason i have to exit three times to complete get out of application 
					System.out.println("     Please come again.");
					break;
				case 1:// case 1 logs into account must know account info that is displayed when creating the account
					System.out.println("       What is you Admin ID?");
					int a = in.nextInt();
					if(a == admidId) {
						System.out.println("       What is you Admin Password?");
						int b = in.nextInt();
						if(b == adminPassword) {
							intro2();
							int choice =0;
							choice = in.nextInt();
							switch(choice){
								default: 
									System.err.println("Invalid entry please try again later. Pleaese try agan. ");
									break; 
								case 0:
									System.out.println("     Please come again.");
									break;
								case 1://creates new admin
								
								case 2:
									
									try {
										PreparedStatement ps = null;
										PreparedStatement ps1 = null;
										CallableStatement callSt = null;
										Statement stmt = null; 
										ResultSet rs = null;
										Connection conn = Connections.getConnection();
										// adding to customer addres table
										System.out.println("What is the customers street number?");
										int streetNum = in.nextInt();
										System.out.println("What is the customers street name?");
										String strreName = in.next();
										ps = conn.prepareStatement("CALL INSERTINTOADDRESS(?,?)");
										ps.setInt(1, streetNum);
										ps.setString(2, strreName);		
										ps.executeUpdate();
										// gettint result of addres id to pass on the customer
										callSt  = conn.prepareCall(" { ? = call GET_MAX_ADD_ID}");
										callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
										callSt.execute();
										int result = callSt.getInt(1);
										System.out.println("Addres ID is: " + result );
										// adding to customer table
										System.out.println("What is the customers Last Name");
										String lName = in.next();
										System.out.println("What is the customers First Name");
										String fName = in.next();
										System.out.println("What is the customers password");
										int password = in.nextInt();
										ps1 = conn.prepareStatement("CALL INSERTIntoCUSTOMER(?,?,?,?)");
										ps1.setString(1, lName);
										ps1.setString(2, fName);
										ps1.setInt(3, password);
										ps1.setInt(4, result);
										ps1.executeUpdate();
										
										// Adding to bank account table
										System.out.println(" Press 1 for REVATURE TRANSUNION TX " + "\n"
												+ "Press 2 for  REVATURE TRANSUNION FL" + "\n"
												+ "Press 3 for  REVATURE TRANSUNION VA "+ "\n");
										System.out.println("What is the Branch ID for this account: ");
										int branchId = in.nextInt();
										System.out.println("Press 1 for Saveing:" + "\n" 
												+ "Press 2 forfor checking:"+ "\n");
										String accounType = in.next();
										System.out.println("What is the customers opening balance");
										int balance = in.nextInt();
										ps1 = conn.prepareStatement("CALL insertIntoBank(?,?,?)");
										ps1.setInt(1, branchId);
										ps1.setString(2, accounType);
										ps1.setInt(3, balance);
										ps1.executeUpdate();
										
										
										
									} catch (SQLException e) {
										e.printStackTrace();
									}finally{
										//close(stmt);
									
									}
									menu();
							}
						}
					}else {
						System.out.println("Invalid entry please try again later. Pleaese try agan. ");
						menu();				
					}
					break;
				case 2:// case 2 is to create account and for it to be approved by the admin			
					System.out.println(" Thanks for choseing Revature TransUnion.");
				case 3:// case allows you into the admin area	
			}	
		}while(firstChoice != 0);
		
		System.out.println("    END OF EXECUTION");
		
	}
	
}
	

