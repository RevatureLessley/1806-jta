package com.revature.project0.bankaccount;

import java.util.Scanner;

//import org.apache.log4j.Logger;
/**
 * 
 * In Second Menu it has the bulk of the methods that make the bank work such as deposit, balance, withdraw and introuductin menus
 * @author Zachary Diaz
 *
 */
public class SecondMenu {
	final static int adminPassword = 118034; // admin password s not completely set up need to fix
	final static int secrPassword = 203065; // admin password s not completely set up need to fix
	//final static Logger logger = Logger.getLogger(SecondMenu.class);
	public static double balance;
	public static double balance2;
	public static double balance3;
	private static double receipt;
	private static double receipt2;
	private static double receipt3;
	public static double loan;
	public static double loan2;
	public static double loan3;
	public static double pay1;
	public static double pay2;
	public static double pay3;
	public static double temp;
	
	// all of these above is due to me having to hard code the amount of account that i have i plan to get ride of this at a latter time
	public SecondMenu() {
	}
	/**
	 * deposit takes the customers money and adds it to there account and then sets up a receipt
	 * @param dc
	 */
	public static void deposit(double dc) {
		if(temp ==1) {	
		
			if(dc != 0) {
				balance = balance + dc;
				receipt = dc;
			}	
		}else if(temp == 2) {
			if(dc != 0) {
				balance2 = balance2 + dc;
				receipt2 = dc;
			}
		}else if(temp == 3) {
			if(dc != 0) {
				balance3 = balance3 + dc;
				receipt3 = dc;
			}
		}
	}
	/**
	 * withdraw lets the customer take money from there account and sets up a receipt
	 * @param dc
	 */
	public static void withdraw(double dc ) {
		if(temp == 1) {
			if(dc != 0) {
			balance = balance - dc;
			receipt = -dc;
			}
		}else if(temp == 2) {
			if(dc != 0) {
			balance2 = balance2 - dc;
			receipt2 = -dc;
			}
		}else if(temp == 3) {
			if(dc != 0) {
			balance3 = balance3 - dc;
			receipt3 = -dc;
			}
		}
		
	}
	/**
	 * addToBank takes all the accounts and add them together and the existing balance of the back and returns the total 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public static double addToBank(double a,double b, double c,double d){		
		return (a+ b + c + d);
	}
	/**
	 * loanIntrest sets up the loan info for the account user when they need a laon
	 * @param a
	 * @param b
	 * @return
	 */
	public static double loanIntrest(double a,double b){
		return( (a * b) + a);
	}
	/**
	 * transferOut sets up a transfer to another account and subtracts form there account balance
	 * @param a
	 * @param b
	 * @return
	 */
	public static double transferOut(double a,double b){
		return( a - b);
	}
	/**
	 * transferIn sets up receiving a transfer from another account and adds to there account balance
	 * @param a
	 * @param b
	 * @return
	 */
	public static double transferIn(double a,double b){
		return( a + b);
	}
	/**\
	 * subFromBank sets up when an account user is withdrawing from there account and takes from both there balance and the banks balance
	 * @param a
	 * @param b
	 * @return
	 */
	public int subFromBank(int a, int b){
		return a - b;
	}
	/**
	 * getReceipt sets up a receipt method. when called outputs what has happen
	 * @return
	 */
	public double getReceipt() {
		if(temp == 1) {
			if(receipt > 0){
				System.out.println("You Deposited " + receipt +  " today.");			// Deposit
			}else if(receipt < 0){
				System.out.println("You Withdraw " + Math.abs(receipt) + " today.");	// Withdraw
			}else {
				System.out.println("You decided to do nothing today.");					//just in case one of the two arnt selected
			}
			
		}else if(temp == 2) {
			if(receipt2 > 0){
				System.out.println("You Deposited " + receipt +  " today.");
			}else if(receipt2 < 0){
				System.out.println("You Withdraw " + Math.abs(receipt2) + " today.");
			}else {
				System.out.println("You decided to do nothing today.");
			}
		}else if(temp == 3) {
			if(receipt3 > 0){
				System.out.println("You Deposited " + receipt +  " today.");
			}else if(receipt3 < 0){
				System.out.println("You Withdraw " + Math.abs(receipt3) + " today.");
			}else {
				System.out.println("You decided to do nothing today.");
			}
		}
		
		return receipt;
	}
	/**
	 * returns a balance
	 * @return
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * showSecondMenu sets up the second menu that lets the user do actions such as check balance
	 */
	public static void showSecondMenu() {
		Scanner in = new Scanner(System.in);
		int secondChoice =  0;
		intro2();
		secondChoice = in.nextInt();
		switch(secondChoice){
		default: // default sets up an invalid entry
			System.out.println(" Invalid entry please try again later. Pleaese try agan. ");
			//logger.error(null);
			break; 
		case 0:// case 0 exits to main menu
			System.out.println(" Please come again.");
			BankMenu.menu();
			break;
		case 1://case 1 sets up deposit with if statments
			System.out.println("How much would you like to deposit?");
			System.out.println("====================================");
			if(temp ==1) {
				double dc = in.nextDouble();
				deposit(dc);;
				System.out.println("You deposited: " + dc + "\n");
				showSecondMenu();
				break;		
			}else if(temp == 2) {
				double dc3 = in.nextDouble();
				deposit(dc3);
				System.out.println("You deposited: " + dc3 + "\n");
				showSecondMenu();
				break;	
			}else if(temp == 3) {
				double dc5 = in.nextDouble();
				deposit(dc5);
				System.out.println("You deposited: " + dc5 + "\n");
				showSecondMenu();
				break;
			}
		case 2: // case 2 sets up customer withdraw
			System.out.println("How much do you want to Withdraw ");
			System.out.println("====================================");
			double dc2 = in.nextDouble();
			if(temp ==1) {
				if(dc2 <= balance) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
				}else
					System.out.println("Insuffcient funds");			
				showSecondMenu();
			}else if(temp == 2) {
				if(dc2 <= balance2) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
					}
				showSecondMenu();
			}else if(temp == 3) {
				if(dc2 <= balance3) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
					}
				}else {
					System.out.println("Insuffcient funds");	
					//logger.error(null);
				}
				showSecondMenu();	
			break;
		case 3:// case 3 sets up checks balance and account info
			if(temp ==1) {
				System.out.println("====================================");
				System.out.println("Your Balance is:  " + "$" +(balance - pay1 ));	
				System.out.println("You also owe the bank " + (loanIntrest(loan,0.20) - pay1));
				showSecondMenu();
				break;
			}else if(temp == 2) {
				System.out.println("====================================");
				System.out.println(" Your Balance is:  " + "$" + (balance2 - pay2 ));
				System.out.println("You also owe the bank " + (loanIntrest(loan2,0.20) - pay2));
				showSecondMenu();
				break;
			}else if(temp == 3) {
				System.out.println("====================================");
				System.out.println(" Your Balance is:  " + "$" + (balance3 - pay3));
				System.out.println("You also owe the bank  " + (loanIntrest(loan3,0.20) - pay3));
				showSecondMenu();
				break;
			}
		case 4:// case 4 sets up initial loan agreement 			
			if(temp ==1) {
				System.out.println("How much would you like for a loan? ");
				loan = in.nextDouble();
				System.out.println("PLease stand by for admin password ");
				int password = in.nextInt();
				if(password == adminPassword) {
					System.out.println("You are aproved for a loan with a one time intrest charge at 20% " + "\n"
								+ "Do you except press 1 for yes and 2 for no");
					int accept = in.nextInt();
					if(accept == 1) {
						balance = balance + loan;
						loanIntrest(loan,0.20);
						System.out.println("Your new balance is: " + "$" + balance);	
						System.out.println("You now owe the bank  $" + (loanIntrest(loan,0.20)));
					}else {
						System.out.println("sorry we could not help you");
						loan = 0;
					}
				}	
					showSecondMenu();					
				}else if(temp == 2) {
					System.out.println("How much would you like for a loan? ");
					loan2 = in.nextDouble();
					System.out.println("PLease stand by for admin password ");
					int password = in.nextInt();
					if(password == adminPassword) {
						System.out.println("You are aproved for a loan with a one time intrest charge at 20% " + "\n"
								+ "Do you except press 1 for yes and 2 for no");
						int accept = in.nextInt();
						if(accept == 1) {
							balance2 = balance2 + loan2;
							loanIntrest(loan2,0.20);
							System.out.println("Your new balance is: " + "$" +balance2 );
							System.out.println("You now owe the bank  $" + (loanIntrest(loan2,0.20)));
						}else {
							System.out.println("sorry we could not help you");
							loan2 = 0;
						}
					}
					showSecondMenu();
				}else if(temp == 3) {
					System.out.println("How much would you like for a loan? ");
					loan3 = in.nextDouble();
					System.out.println("PLease stand by for admin password ");
					int password = in.nextInt();
					if(password == adminPassword) {
						System.out.println("You are aproved for a loan with a one time intrest charge at 20% " + "\n"
								+ "Do you except press 1 for yes and 2 for no");
						int accept = in.nextInt();
						if(accept == 1) {
							balance3 = balance3 + loan3;
							loanIntrest(loan3,0.20);
							System.out.println("Your new balance is: " + "$" + balance3);
							System.out.println("You now owe the bank  $" + (loanIntrest(loan3,0.20)));
						}else {
							System.out.println("sorry we could not help you");	
							loan3 = 0;
						}
					}
					showSecondMenu();
			}else {
				System.out.println("we can not approve you for a loan at this time please try at a latter date ");
				showSecondMenu();
			}break;
		case 5: // case 5 sets up loan pay back
			if(temp ==1) {
				System.out.println("You owe the bank " + loanIntrest(loan,0.20));
				System.out.println("How much will you pay off today?");
				pay1 = in.nextDouble();
				System.out.println("You want to pay " + pay1 + " press 1 for yes and 2 for no");
				int accept = in.nextInt();
				if(accept <= 1) {
					if(pay1 <= balance) {						
						System.out.println("Thanks Your new balance is " + (balance - pay1) );
						System.out.println("You owe the back " + (loanIntrest(loan,0.20) - pay1));
						showSecondMenu();
					}else {
						System.err.println("Insuficiant Funds:YOU CAN NOT OVER DRAW YOU ACCOUNT!!!!");
						pay1 = 0;
						showSecondMenu();
					}
				}else {
					System.out.println("Nothing happened exintg to your menu");
					pay1 = 0;
					showSecondMenu();
				}
			}else if(temp ==2) {
				System.out.println("Yoa owe the bank " + loanIntrest(loan2,0.20));
				System.out.println("how much will you pay off today?");
				pay2 = in.nextDouble();
				System.out.println("You want to pay " + pay2 + " press 1 for yes and 2 for no");
				int accept = in.nextInt();
				if(accept == 1) {
					if(pay2 <= balance2){						
						System.out.println("Thanks Your new balance is " + (balance2 - pay2) );
						System.out.println("You owe the back " + (loanIntrest(loan2,0.20) - pay2));
						showSecondMenu();
					}else {
						System.err.println("Insuficiant Funds:YOU CAN NOT OVER DRAW YOU ACCOUNT!!!!");
						pay2 = 0;
						showSecondMenu();
					}					
				}else {
					System.out.println("Nothing happened exintg to your menu");
					pay2 = 0;
					showSecondMenu();
				}
					
			}else if(temp ==3) {
				System.out.println("You owe the bank " + loanIntrest(loan3,0.20));
				System.out.println("how much will you pay off today?");
				pay3 = in.nextDouble();
				System.out.println("You want to pay " + pay3 + " press 1 for yes and 2 for no");
				int accept = in.nextInt();
				if(accept == 1) {
					if(pay3 <= balance3) {
						System.out.println("Thanks Your new balance is " + (balance3 - pay3) );
						System.out.println("You owe the back " + (loanIntrest(loan3,0.20) - pay3));
						showSecondMenu();
					}else {
						System.err.println("Insuficiant Funds:YOU CAN NOT OVER DRAW YOU ACCOUNT!!!!");
						pay3 = 0;
						showSecondMenu();
					}
				}else {
					System.out.println("Nothing happened exintg to your menu");
					pay3 = 0;
					showSecondMenu();
				}
			}
		case 6:// case 6 sets up transfer in and out
			System.out.println("What is tha account number you would like to transfer to?");
			int transfer = in.nextInt();
			System.out.println("How much do you want to transfer?");
			double amount = in.nextDouble();
			System.out.println("You want to transfer " + amount + " to account numbe " + transfer + " press 1 for yes and 2 for no ");
			int accept = in.nextInt();
			if(accept == 1) {
				if(temp ==1) {
					if(transfer == 2){
						if(amount <= balance) {
							balance = transferOut(balance, amount);
							balance2 = transferIn(balance2, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
							//logger.error(null);
							showSecondMenu();
						}
					}else if(transfer == 3) {
						if(amount <= balance) {
							balance = transferOut(balance, amount);
							balance3 =transferIn(balance3, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
							//logger.error(null);
							showSecondMenu();
						}
					}else {
						System.err.println("Could not find account try again latter");
						showSecondMenu();
					}						
				}else if(temp == 2) {
					if(transfer == 1){
						if(amount <= balance2) {
							balance2 = transferOut(balance2, amount);
							balance = transferIn(balance, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
							//logger.error(null);
							showSecondMenu();
						}
					}else if(transfer == 3) {
						if(amount <= balance2) {
							balance2 =transferOut(balance2, amount);
							balance3 = transferIn(balance3, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
							//logger.error(null);
							showSecondMenu();
						}
					}else {
						System.err.println("Could not find account try again latter");
						showSecondMenu();
					}					
				}else if(temp == 3) {
					if(transfer == 2){
						if(amount <= balance3) {
							balance3 = transferOut(balance3, amount);
							balance2 = transferIn(balance2, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
							//logger.error(null);
							showSecondMenu();
						}
					}else if(transfer == 1) {
						if(amount <= balance3) {
							balance3 = transferOut(balance3, amount);
							balance = transferIn(balance, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
							//logger.error(null);
							showSecondMenu();
						}
					}else {
						System.err.println("Could not find account try again latter");
						showSecondMenu();
					}
				}
			}
		}

		
	}
	
	

	public static void intro() {
		System.out.println("\n");
		System.out.println("========Welcome To Revature TransUnion========");
		System.out.println("");
		System.out.println("      Please select one of the following ");
		System.out.println("");
		System.out.println("      To Log In 	   	press 1 ");		
		System.out.println("      To create an account 	press 2");		
		System.out.println("      If you are an admin  	press 3");
		System.out.println("      If you want to exit  	press 0" + "\n");
	}
	
	public static void intro2() {
		System.out.println("\n");
		System.out.println("=====================Hello=====================");
		System.out.println("");
		System.out.println("     Please select one of the following ");
		System.out.println("");
		System.out.println("     Deposit 					Press 1");		
		System.out.println("     Withdraw 					Press 2");		
		System.out.println("     Check Balance 				Press 3");
		System.out.println("     Too take out a loan 			Press 4");
		System.out.println("     Too pay offf a loan 			Press 5");
		System.out.println("     Transfer to another account 		press 6");
		System.out.println("     If you want to exit 			press 0" + "\n");
	}
	public static void intro3(){
		
	}

	
	
}
