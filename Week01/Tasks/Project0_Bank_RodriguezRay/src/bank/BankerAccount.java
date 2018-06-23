package bank;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class BankerAccount extends Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819807580931107845L;

	public BankerAccount(String accType, String fName, String lName, String userName, String password) {
		super(accType, fName, lName, userName, password);
		
	}
	
	public BankerAccount() {
		super();
	}

	/**
	 * This method will allow the admin to view all account, and enter user account numbers in a string separated 
	 * by spacing to select the ones to be approved, once approved user accounts will be allowed to login
	 * 
	 * @param users list of the user accounts
	 * @param reader global reader to prompt the user
	 */
	public void ApproveUserLoans(List<UserAccount> users, Scanner reader) {
		DisplayUsers(users);
		
		System.out.println("Please enter the Account Number to view loans: ");
		int key = Integer.parseInt(reader.nextLine());
		
		UserAccount selected = null;
		
		for (UserAccount user : users) {
			if (user.getAccNumber() == key)
				selected = user;
		}
		
		if (selected != null)
			selected.DisplayLoansDetails();
		else {
			System.out.println("User not found");
			Driver.logger.error("User not found");
			return;
		}
		
		System.out.println("Please enter loan ids to approve: ");
		String input = reader.nextLine();
		String[] loanIDs = input.split(" ");
		for(String s : loanIDs) {
			for (Loan l : selected.getLoans()) {
				if (l.id == Integer.parseInt(s)) {
					l.approved = true;
				}
			}
		}
	}
	
	public void DisplayUsers(List<UserAccount> users) {
		System.out.format("|%-15s|%-12s|%-20s|%-20s|%-15s|%-9s|%-9s|\n", "Account Number", "Account Type", "Fist Name", "Last Name", "Balance", "Banned", "Approved");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		for(UserAccount user : users)
			user.DisplayAccDetails();
		System.out.println();
	}

	@Override
	public String toString() {
		return "AdminAccount [accType=" + accType + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
	
}
