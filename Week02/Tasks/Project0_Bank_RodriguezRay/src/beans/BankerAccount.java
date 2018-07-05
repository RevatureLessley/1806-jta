package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import bank.Driver;

public class BankerAccount extends Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819807580931107845L;

	public BankerAccount(int accNum, String accType, String fName, String lName, String userName, String password) {
		super(accNum, accType, fName, lName, userName, password);
		
	}
	
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
	public void ApproveUserLoans(List<CustomerAccount> users, Scanner reader) {
		DisplayUsers(users);
		List<Loan> updateQueue = new ArrayList();
		System.out.println("Please enter the Account Number to view loans: ");
		int key = Integer.parseInt(reader.nextLine());
		
		CustomerAccount selected = null;
		
		for (CustomerAccount user : users) {
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
		List<String> loanIds = Arrays.asList(input.split(" "));
		List<Integer> loanIdsInt = loanIds.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
		List<Loan> loans = Driver.as.selectAllLoansByAccId(selected.accNum);
		for (Integer i : loanIdsInt){
			for (Loan loan : loans) {
				if (loan.getId() == i)
					loan.approved = true;
					updateQueue.add(loan);
			}
		}
		
		for(Loan loan : updateQueue) {
			Driver.as.updateLoan(loan, selected.accNum);
		}
	}
	
	public void DisplayUsers(List<CustomerAccount> users) {
		for(CustomerAccount user : users)
			user.DisplayAccDetails();
		System.out.println();
	}
	
	public void DisplayAllLoans() {
		System.out.format("%-12s|%-15s|%-14s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|\n", "Loan ID", "Interest Rate", 
				"APR", "Ori Fee", "Loan Term", "Loan Amount", "Approved", "Acc ID", "First Name", "Last Name");
		System.out.println("--------------------------------------------------------------------------------------------------------------------"
				+ "----------------------");
		Driver.as.selectAllLoans();
		System.out.println();
	}

	@Override
	public String toString() {
		return "AdminAccount [accType=" + accType + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
	
}
