package bank;

import java.util.List;
import java.util.Scanner;

public class AdminAccount {
	public void ApproveUsers(List<UserAccount> users, Scanner reader) {
		DisplayUsers(users);
	}
	
	public void DisplayUsers(List<UserAccount> users) {
		System.out.format("|%-15s|%-10s|%-20s|%-20s|-10s%|%-9s|", "Account Type", "Fist Name", "Last Name", "Banned");
		for(UserAccount user : users)
			user.DisplayAccDetails();
		System.out.println();
	}
}
