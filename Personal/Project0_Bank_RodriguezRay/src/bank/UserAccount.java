package bank;

public class UserAccount extends Account{
	double balance;
	
	public UserAccount(String accType, String fName, String lName, String userName, String password) {
		super(accType, fName, lName, userName, password);
		balance = 0;
	}
	
	public UserAccount(String userName, String password) {
		super(userName, password);
	}
	
	public void DisplayAccDetails() {
		super.DisplayAccDetails();
		System.out.format("%-10f", balance);
		System.out.println();
	}
	
	public void Withdrawal(double amount) {
		System.out.println("Withdawing...\n");
		System.out.println("Previous Balance: "+balance+"\n");
		if (balance >= amount)
		{
			balance -= amount;
			System.out.println("New Balance: "+balance+"\n");
		} else {
			System.out.println("Insufficient funds!");
		}
	}
	
	public void Deposit(double amount) {
		System.out.println("Depositing...");
		System.out.println("Previous Balance: "+balance+"\n");
		balance += amount;
		System.out.println("New Balance: "+balance+"\n");
	}
	
	public void Loan() {
		
	}
}
