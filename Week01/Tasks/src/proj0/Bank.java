package proj0;

public class Bank{
	public enum Userinput{
		authorize,goodbye;
	}
	public static void main(String args[]){
		LoginPrompt lp = new LoginPrompt();
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
			case "1":
				this.deposit();
			case "2":
				this.withdraw();
			case "3":
				break;
			case "authorize":
				if(user instanceof Admin) {
					this.approveUser(users);
					input = "options";
				}else {
					System.out.println("Only the Admin can approve users");
					input = "options";
				}
			case "goodbye":
				this.deposit();
			case "options":
				System.out.println(
						"'0' Show balance \n"
						+ "'1' Deposit \n"
						+ "'2' Withdraw \n"
						+ "'3' Exit");
				input = LoginPrompt.console.readLine(": ");
		}
	}
	
	public void deposit() {
		
	}
	
	public void withdraw() {
		
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
