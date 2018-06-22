package proj0;

import java.text.DecimalFormat;

public class Bank{
	public enum Userinput{
		authorize,goodbye;
	}
	public static void main(String args[]){
		LoginPrompt lp = new LoginPrompt();
		//Users users = new Users();
		Users users = lp.retrieveUsers();
		User user = lp.enterLogin(users);
		if (user == null) return;
		Bank bank = new Bank();
		bank.options(user,users);
		lp.storeUsers(users);
	}
	
	public void options(User user,Users users) {
		
		System.out.println(
				"'0' Show balance \n"
				+ "'1' Deposit \n"
				+ "'2' Withdraw \n"
				+ "'3' Exit");
		
		String input = LoginPrompt.console.readLine(": ");
		
		switch(input) {
			case "0":
				System.out.println(user.getBalance());
				input = "options";
				break;
			case "1":
				this.deposit(user);
				input = "options";
				break;
			case "2":
				this.withdraw(user);
				input = "options";
				break;
			case "3":
				return;
			case "authorize":
				if(user instanceof Admin) {
					this.approveUser(users);
					input = "options";
				}else {
					System.out.println("Only the Admin can approve users");
					input = "options";
				}
				break;
			case "goodbye":
				return;
		}
		this.options(user,users);
	}
	
	public void deposit(User user) {
		System.out.println("How much would you like to deposit?");
		String amount = LoginPrompt.console.readLine(": ");
		double d = Double.parseDouble(amount);
		amount = new DecimalFormat("##.##").format(d);
		d = Double.parseDouble(amount);
		if (d <= 0) { 
			System.out.println("Invalid amount");
			return;
		}
		user.setBalance(user.getBalance() + d);
		System.out.println("Current balance: " + user.getBalance());
		
	}
	
	public void withdraw(User user) {
		
	}
	
	public void approveUser(Users users) {
		String username = LoginPrompt.console.readLine(": ");
		User user = users.getUsers().get(username);
		if(user == null) System.out.println("User does not exist");
		else {
			System.out.println("User successfully approved");
			user.setAuth(true);
		}
	}
}
