package com.bank.main;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Bank {
	
	static final Scanner sc = new Scanner(System.in);
	public static File file = new File("users.ser");
	
	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.runProgram();
	}
	
	/**
	 * Runs the program. If there are no users in files, then it asks to create an
	 * administrator account. Else, it displays the initial menu (login, register).
	 */
	public void runProgram() {
		// If file containing users does not exist or is empty, create an admin account
		if(!file.isFile() || file.length() == 0) {
			System.out.println("There are no users registered. You must register"
					+ "an admin account.");
			register();
		} 
		
		// if user exists, then give options to register or login
		else {
			initialMenu();
		}
	}
	
	/**
	 * Displays the menu designed for regular users.
	 * @param user current user's data, needed to update its information.
	 */
	public void adminMenu() {
		int choice = 0;
		do {
			System.out.println("Enter 1 if you want to approve users, "
					+ "2 if you want print the existing users, or 3 if you want lo log out.");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				approveUser();
				break;
			case 2:
				printUsers();
				break;
				
			case 3:
				return;
			
			default:
				break;
			}
		} while (choice != 3);
	}
	
	/**
	 * Method only available to administrators, it prints all existing users.
	 */
	public void printUsers() {
		System.out.println("==========");
		Users myUserss = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("users.ser"));
			myUserss = (Users)ois.readObject();
			ois.close();
		} catch (EOFException e) {
			System.out.println("File is empty.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(myUserss);
	}
	
	/**
	 * Displays the menu designed for regular users.
	 * @param user current user's data, needed to update its information.
	 */
	public void userMenu(User user) {
		int choice = 0;
		do {
			System.out.println("Enter 1 if you want to add money to your account, "
					+ "2 if you want to withdraw money, or 3 if you want lo log out.");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addMoney(user);
				break;
			case 2:
				withdrawMoney(user);
				break;
				
			case 3:
				return;
			
			default:
				break;
			}
		} while (choice != 3);
	}
	
	/**
	 * Displays the initial menu (login, register, quit).
	 */
	public void initialMenu() {
		int choice = 0;
		do {
			System.out.println("Enter 1 to login, enter 2 to register, 3 to exit the program.");
			String input = sc.next();
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("You didn't enter an integer.");
			}
			switch (choice) {
			case 1:
				login();
				break;

			case 2:
				register();
				break;
				
			case 3:
				System.exit(0);
				break;
				
			default:
				System.out.println("Please enter a valid argument");
				break;
			}
		} while (choice != 3);
	}
	
	/**
	 * Handles login operations. It checks if the email and password match with our records.
	 * It also checks the user's role. It will give different options to administrators and
	 * regular users.
	 */
	public void login() {
		System.out.println("Enter your email:");
		String email = sc.next();
		System.out.println("Enter your password:");
		String password = sc.next();
		
		// Deserialize users
		ArrayList<User> existingUsers = deserializeUsers();
		// Check if email and password are in file
		for(User loginUser : existingUsers) {
			//System.out.println(newUser.getEmail());
			if(loginUser.getEmail().equals(email) && loginUser.getPassword().equals(password)) {
				if (!loginUser.isApproved()) {
					System.out.println("Your account has not been approved, yet. Please "
							+ "come back later.");
				} else {
					System.out.println("Logged in successfully");
					// If user is admin, then approve users
					if(loginUser.getRole() == 2) {
						adminMenu();
					}
					//else give the user more options
					else {
						userMenu(loginUser);
					}
				}
				return;
			}
		}
		System.out.println("Sorry, wrong user or password.");
	}
	
	/**
	 * Deserializes the data stored in files. 
	 * @return returns an array list containing all the users data
	 */
	public ArrayList<User> deserializeUsers(){
		Users deserializedUsers = null;
		
		// Deserialize Users
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("users.ser"));
			deserializedUsers = (Users)ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Store all existing users in arrayList
		ArrayList<User> existingUsers = deserializedUsers.getUsers();
		return existingUsers;
	}
	
	/**
	 * Function available to administrators. It allows administrators to approve or reject 
	 * users that registered recently.
	 */
	public void approveUser() {
		// Deserialize Users
		 ArrayList<User> existingUsers = deserializeUsers();

		int choice = 0;
		ListIterator<User> iter = existingUsers.listIterator(existingUsers.size());

		while (iter.hasPrevious()) {
		    User iterUser = iter.previous();
		    if (!iterUser.isApproved()) {
				do {
					System.out.println("User with email " + iterUser.getEmail() + " has not been approved. "
							+ "Enter 1 to approve it or 2 to reject it.");
					String input = sc.next();
					try {
						choice = Integer.parseInt(input);
					} catch (NumberFormatException e) {
						System.out.println("You didn't enter an integer.");
					}
				} while(choice != 1 && choice != 2);
				switch (choice) {
				case 1:
					iterUser.setApproved(true);
					break;

				case 2:
					iter.remove();
					break;
					
				default:
					System.out.println("Please enter a valid argument");
					break;
				}
			}
		}
		serializeUsers(existingUsers);
		System.out.println("You are done! There are no users waiting to be approved!");
	}
	
	/**
	 * Adds money to the given user's account. If the user doesn't have a valid 
	 * card number in the database, it prompts the user to update its card number.
	 * If user already has a valid card number it will use that card.
	 * @param user the user that will be updated
	 */
	public void addMoney(User user) {
		BigDecimal currentBalance = user.getBalance();
		if(currentBalance == null) {
			currentBalance = new BigDecimal(0);
		}
		System.out.println("Your current balance is " + currentBalance);
		System.out.println("How much money do you want to add?");
		BigDecimal amount = sc.nextBigDecimal();
		if (!validCardNumber(user.getCardNumber())) {
			int cardNum = 0;
			do{
				System.out.println("Please enter a valid card number: ");
				cardNum = sc.nextInt();
			} while(!validCardNumber(cardNum));
			user.setCardNumber(cardNum);
		}
		
		currentBalance = currentBalance.add(amount);
		user.setBalance(currentBalance);
		updateUser(user);
		System.out.println("Your new balance is " + user.getBalance());
	}
	
	/**
	 * Subtracts money from the user's account. If the user doesn't have enough funds 
	 * it won't update anything, else it will update the user's balance.
	 * @param user
	 */
	public void withdrawMoney(User user) {
		BigDecimal currentBalance = user.getBalance();
		if(currentBalance == null) {
			currentBalance = new BigDecimal(0);
		}
		System.out.println("Your current balance is " + currentBalance);
		System.out.println("How much money do you want to withdraw?");
		BigDecimal amount = sc.nextBigDecimal();
		if (currentBalance.compareTo(amount) < 0) {
			System.out.println("You don't have enough funds.");
		} else {
			currentBalance = currentBalance.subtract(amount);
			user.setBalance(currentBalance);
			updateUser(user);
			System.out.println("Please, take your money.");
			System.out.println("Your new balance is " + user.getBalance());
		}
	}
	
	/**
	 * Updates a given user. This function deserializes the data first, 
	 * then stores the existing users in an array list, finds the given user and 
	 * updates its data. Finally, it serializes the data again.
	 * @param user the user to be updated
	 */
	public void updateUser(User user) {
		// Deserialize Users
		ArrayList<User> existingUsers = deserializeUsers();
		// Check if user already exists (compare email address)
		for(User updateUser : existingUsers) {
			if(user.getEmail().equals(updateUser.getEmail())) {
				updateUser.setBalance(user.getBalance());
				updateUser.setApproved(user.isApproved());
				updateUser.setCardNumber(user.getCardNumber());
				updateUser.setRole(user.getRole());
				break;
			}
		}
		serializeUsers(existingUsers);
	}
	
	/**
	 * Checks if a number is valid credit card number.
	 * @param cardNum the card number to be checked
	 * @return true if it is a valid card number, otherwise false
	 */
	public boolean validCardNumber(int cardNum) {
		String pattern = "\\d{10}";
		String numString = Integer.toString(cardNum);
	    if (numString.matches(pattern)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Function that serializes the users. 
	 * @param userList array list containing the users to be serialized.
	 */
	public void serializeUsers(ArrayList<User> userList) {
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(
										new FileOutputStream("users.ser"));
			Users users = new Users(userList);
			// Serialize
			oos.writeObject(users);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Function that handles registration. If file is empty or does not exist, creates a new file.
	 * Otherwise it deserializes the data, adds the new data, and finally serializes again.
	 */
	public void register() {
		System.out.println("Enter your first name:");
		String fname = sc.next();
		System.out.println("Enter your last name:");
		String lname = sc.next();
		System.out.println("Enter your email:");
		String email = sc.next();
		System.out.println("Enter a password:");
		String password = sc.next();
		
		// If file does not exist or file is empty
		if(!file.isFile() || file.length() == 0) {	
			// The first user will be admin by default
			User newUser = new User(fname, lname, email, password, 2, true);
			// Add user to arrayList
			ArrayList<User> listUsers = new ArrayList<>();
			listUsers.add(newUser);
			// Serialize users
			serializeUsers(listUsers);
		}
		
		// Otherwise, if file already has users
		else {
			// Deserialize Users
			ArrayList<User> existingUsers = deserializeUsers();
			// Check if user already exists (compare email address)
			for(User newUser : existingUsers) {
				if(newUser.getEmail().equals(email)) {
					System.out.println("An user with that email already exists.");
					return;
				}
			}
			
			// If user doesn't exist, then create it
			User registeringUser = new User(fname, lname, email, password, 1, false);
			existingUsers.add(registeringUser);
			serializeUsers(existingUsers); // serialize users
			System.out.println("Thank you for registering! You have to be approved by an "
					+ "admin first. Check back later!");
		}
	}

}
