import java.util.Scanner;

public class Project0 {

  public static void main(String[] args){
	int menuinput = 1;
	Scanner input = new Scanner(System.in);
	while  (menuinput == 1) {
		System.out.println("Welcome to the main menu");
		menuinput=input.nextInt();
	}



  }

  public static class Admin {
    public Admin(){};
    public void authorize(Customer client){
      client.activated = true;
    }
  }

  public static class Customer {
    /*Variable and Data for each customer*/
    public double balance = 0;
    public boolean activated = false; 

    /*Constructor takes in one double variable to set the balance*/
    public Customer(Double newbalance){
      balance = newbalance;
    }

    /*Set the balance to a new value*/
    public void setbalance (double newbalance) {
      balance = newbalance;
    }
    /*Set the balance to a new value*/
    public void statement (){
      System.out.println("Balance is: ");
      System.out.printf("%.2f",balance);
      
    }
    /*Get the balance*/
    public double getbalance (){
      return balance; 
    }
    /*Deposit some munnies*/
    public void deposit (double depositamount){
      balance = balance + depositamount;
    }

    /*Take sum munnies*/
    public void withdraw (double withdrawamount){
      balance = balance - withdrawamount;
    }

  }

}

/*Hold on for testing*/
/*    /*Testing variable
    double testbalance = 100.12;
    double testdeposit = 00.12; 
    double testwithdraw = 00.24;
    int testswitch = 0;

    if (testswitch !=0){
     System.out.println("We will now begin testing");

    /*Testing the constructor
     Customer Dummy = new Customer(testbalance);
     if (Dummy.getbalance() != testbalance) {System.out.println("Constructor is not correct");}
     else {System.out.println("\nConstructor works");Dummy.statement();}
 
    /*Testing admin POWERS!!!!
     Admin AdminDummy = new Admin();
     AdminDummy.authorize(Dummy);
     if (Dummy.activated != true) {System.out.println("Not authorized");}

    
    /*Testing the depositing
     Dummy.deposit(testdeposit);
     if (Dummy.getbalance() != (testbalance + testdeposit)) {System.out.println("Deposit error");}
     else {System.out.println("\nDeposit works"); Dummy.statement();}

    /*Testing the withdrawing
     Dummy.withdraw(testwithdraw);
     if (Dummy.getbalance() != ((testbalance+testdeposit) - testwithdraw)) {System.out.println("Withdraw error");}
     else {System.out.println("\nWithdraw works"); Dummy.statement();}
    }*/



		/*
		customer dummy = new customer("fname","lname","socialsecuritynumber","passwordpassword");
		data_storage creator = new data_storage();
		
		
		//Testing account creation
		//dummy.statement();
		creator.create_client(dummy);
		
		//Testing account opening
		customer dummy_copy = new customer("WRONG","WRONG","WRONG","passwordpassword");
		creator.open_account(dummy_copy, "fname", "lname", "socialsecuritynumber","passwordpassword");
		//dummy_copy.statement(); //Should output fname lname and 0.00
		
		System.out.println("==============================");
		
		//Testing account closing
		dummy_copy.deposit(100.10f);
		//dummy_copy.statement();//Balance increases by 100.10
		creator.close_account(dummy_copy);
		creator.open_account(dummy, "fname", "lname", "socialsecuritynumber" , "passwordpassword");
		//dummy.statement();//Should output fname lname and 100.10
		//MainMenu startmenu = new MainMenu(); 
		//startmenu.start_menu();*/
