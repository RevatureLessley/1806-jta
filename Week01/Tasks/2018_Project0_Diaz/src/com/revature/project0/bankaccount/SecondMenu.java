package com.revature.project0.bankaccount;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

public class SecondMenu {
	public static double balance;
	public static double balance2;
	public static double balance3;
	private static double receipt;
	private static double receipt2;
	private static double receipt3;
	private static double loan;
	private static double loan2;
	private static double loan3;
	private static double pay1;
	private static double pay2;
	private static double pay3;
	public static double temp;
	
	
	static String customerName;
	static String accountNumber;
	

	public SecondMenu() {
		
	}

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
	//////////////////////////////////////////////
	public static double addToBank(double a,double b, double c,double d ){		
		return (a+ b + c + d);
	}
	public static double loanIntrest(double a,double b){
		return( (a * b) + a);
	}
	public static double transferOut(double a,double b){
		return( a - b);
	}
	public static double transferIn(double a,double b){
		return( a + b);
	}
	
	//////////////////////////////////////
	public int subFromBank(int a, int b){
		return a - b;
	}

	public double getReceipt() {
		if(temp == 1) {
			if(receipt > 0){
				System.out.println("You Deposited " + receipt +  " today.");
			}else if(receipt < 0){
				System.out.println("You Withdraw " + Math.abs(receipt) + " today.");
			}else {
				System.out.println("You decided to do nothing today.");
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

	public void setReceipt(double receipt) {
		this.receipt = receipt;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public static void showSecondMenu() {
		
		Scanner in = new Scanner(System.in);
		int secondChoice =  0;
		intro2();
		secondChoice = in.nextInt();
		switch(secondChoice){
		default: 
			System.out.println(" Invalid entry please try again later. Pleaese try agan. ");
			break; 
		case 0:
			System.out.println(" Please come again.");
			BankMenu.menu();
			break;
		case 1:
			System.out.println(" How much would you like to deposit?");
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
		case 2:
			System.out.println(" How much do you want to Withdraw ");
			System.out.println("====================================");
			double dc2 = in.nextDouble();
			if(temp ==1) {
				if(dc2 < balance) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
				}else
					System.out.println("Insuffcient funds");			
				showSecondMenu();
			}else if(temp == 2) {
				if(dc2 < balance2) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
					}
				showSecondMenu();
			}else if(temp == 3) {
				if(dc2 < balance3) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
					}
				}else
					System.out.println("Insuffcient funds");			
				showSecondMenu();	
			break;
		case 3:
			if(temp ==1) {
				System.out.println(" Your Balance is:  " + (balance ) + "$");	
				System.out.println("You also owe the bank " + (loanIntrest(loan,0.20) - pay1));
				showSecondMenu();
				break;
			}else if(temp == 2) {
				System.out.println(" Your Balance is:  " + (balance2 ) + "$");
				System.out.println("You also owe the bank " + (loanIntrest(loan2,0.20) - pay2));
				showSecondMenu();
				break;
			}else if(temp == 3) {
				System.out.println(" Your Balance is:  " + (balance3 ) + "$");
				System.out.println("You also owe the bank  " + (loanIntrest(loan3,0.20) - pay3));
				showSecondMenu();
				break;
			}
		case 4:
			System.out.println("How much would you like for a loan? ");
			loan = in.nextDouble();
			System.out.println("PLease stand by for admin password ");
			int adminPassword = in.nextInt();
			if(adminPassword == 118034) {
				if(temp ==1) {
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
					}
				}	
					showSecondMenu();					
				}else if(temp == 2) {
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
					}
				}else if(temp == 3) {
					System.out.println("You are aproved for a loan with a one time intrest charge at 20% " + "\n"
							+ "Do you except press 1 for yes and 2 for no");
					int accept = in.nextInt();
					if(accept == 1) {
						balance3 = balance3 + loan;
						loanIntrest(loan3,0.20);
						System.out.println("Your new balance is: " + "$" + balance3);
						System.out.println("You now owe the bank  $" + (loanIntrest(loan3,0.20)));
					}else {
						System.out.println("sorry we could not help you");						
					}
			}else {
				System.out.println("we can not approve you for a loan at this time please try at a latter date ");
				showSecondMenu();
			}break;
		case 5:
			if(temp ==1) {
				System.out.println("Yoa owe the bank " + loanIntrest(loan,0.20));
				System.out.println("how much will you pay off today?");
				pay1 = in.nextDouble();
				System.out.println("You want to pay " + pay1 + " press 1 for yes and 2 for no");
				int accept = in.nextInt();
				if(accept == 1) {
					if(pay1 <= balance) {
						System.out.println("Thanks Your new balance is " + (balance - pay1) );
						System.out.println("You owe the back " + (loanIntrest(loan,0.20) - pay1));
						showSecondMenu();
					}else {
						System.err.println("YOU CAN NOT OVER DRAW YOU ACCOUNT!!!!");
						showSecondMenu();
					}
				}else
					showSecondMenu();
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
						System.err.println("YOU CAN NOT OVER DRAW YOU ACCOUNT!!!!");
						showSecondMenu();
					}					
				}
					showSecondMenu();
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
						System.err.println("YOU CAN NOT OVER DRAW YOU ACCOUNT!!!!");
						showSecondMenu();
					}
				}else
					showSecondMenu();
			}
		case 6:
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
							showSecondMenu();
						}
					}else if(transfer == 3) {
						if(amount <= balance) {
							balance = transferOut(balance, amount);
							balance3 =transferIn(balance3, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
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
							showSecondMenu();
						}
					}else if(transfer == 3) {
						if(amount <= balance2) {
							balance2 =transferOut(balance2, amount);
							balance3 = transferIn(balance3, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
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
							showSecondMenu();
						}
					}else if(transfer == 1) {
						if(amount <= balance3) {
							balance3 = transferOut(balance3, amount);
							balance = transferIn(balance, amount);
							showSecondMenu();
						}else {
							System.err.println("Insuffeciant Funds!!!!!!");
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
		System.out.println("      To Log In 	   press 1 ");		
		System.out.println("      To create an account press 2");		
		System.out.println("      If you are an admin  press 3");
		System.out.println("      If you want to exit  press 0" + "\n");
	}
	
	public static void intro2() {
		System.out.println("\n");
		System.out.println("=============Hello=============");
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
	public static void intro3() {
		
	}

	
	
}
