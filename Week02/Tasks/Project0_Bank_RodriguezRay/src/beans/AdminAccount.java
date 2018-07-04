package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import bank.Driver;

public class AdminAccount extends Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819807580931107845L;

	public AdminAccount(int accNum, String accType, String fName, String lName, String userName, String password) {
		super(accNum, accType, fName, lName, userName, password);
		
	}
	
	public AdminAccount(String accType, String fName, String lName, String userName, String password) {
		super(accType, fName, lName, userName, password);
		
	}
	
	public AdminAccount() {
		super();
	}

	/**
	 * This method will allow the admin to view all account, and enter user account numbers in a string separated 
	 * by spacing to select the ones to be approved, once approved user accounts will be allowed to login
	 * 
	 * @param users list of the user accounts
	 * @param reader global reader to prompt the user
	 */
	public void ApproveUsers(List<CustomerAccount> users, Scanner reader) {
		DisplayUsers(users);
		List<CustomerAccount> updateQueue = new ArrayList<>();
		
		System.out.println("Please enter the Account Numbers to approve: ");
		String input = reader.nextLine();
		List<String> accNumbers = Arrays.asList(input.split(" "));
		List<Integer> accNumbersInt = accNumbers.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());

		for (Integer i : accNumbersInt){
			for (CustomerAccount user : users) {
				if (user.getAccNumber() == i)
					user.approved = true;
					updateQueue.add(user);
			}
		}
		
		for(CustomerAccount cust : updateQueue) {
			Driver.as.updateCustomerAccount(cust);
		}
	}
	
	/**
	 * This method will allow the admin to view all acounts, and enter user account numbers in a string separated
	 * by spacing to select the users to ban, once banned user accounts will not be allowed to login
	 * 
	 * @param users list of the user accounts
	 * @param reader global reader to prompt the user
	 */
	public void BanUsers(List<CustomerAccount> users, Scanner reader) {
		DisplayUsers(users);
		List<CustomerAccount> updateQueue = new ArrayList<>();
		
		System.out.println("Please enter the Account Numbers to ban: ");
		String input = reader.nextLine();
		List<String> accNumbers = Arrays.asList(input.split(" "));
		List<Integer> accNumbersInt = accNumbers.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());

		for (Integer i : accNumbersInt){
			for (CustomerAccount user : users) {
				if (user.getAccNumber() == i)
					user.banned = true;
					updateQueue.add(user);
			}
		}
		
		for(CustomerAccount cust : updateQueue) {
			Driver.as.updateCustomerAccount(cust);
		}
	}
	
	public void DisplayUsers(List<CustomerAccount> users) {
		System.out.format("|%-15s|%-12s|%-20s|%-20s|%-15s|%-9s|%-9s|\n", "Account Number", "Account Type", "Fist Name", "Last Name", "Balance", "Banned", "Approved");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		for(CustomerAccount user : users)
			user.DisplayAccDetails();
		System.out.println();
	}

	@Override
	public String toString() {
		return "AdminAccount [accType=" + accType + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
	
}
