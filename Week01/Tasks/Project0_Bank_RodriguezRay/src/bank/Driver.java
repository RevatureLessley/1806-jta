package bank;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Driver {
	List<UserAccount> userAccounts = new ArrayList<UserAccount>();
	UserAccount loggedIn;
	
	public static void main(String[] args) {
		UserAccount acc = new UserAccount("user", "Ray", "Rodriguez", "rrod", "cards");
//		acc.DisplayAccDetails();
		Scanner reader = new Scanner(System.in);
		Driver d = new Driver();
		d.userAccounts.add(acc);
		int option = 0;
		
		do {
			System.out.println("Please Choose an option 1-2: \n");
			System.out.println("1: Login\n"
							 + "2: Sign up"
							 + "0: Exit App\n");
			option = Integer.parseInt(reader.nextLine());
			
			
			switch(option) {
			case 1:
				d.Login(reader);
				break;
			case 2:
				d.Signup(reader);
				break;
			}
		} while (option != 0);
		
		
		reader.close();
	}
	
	public void Login(Scanner reader) {
		String un;
		String pw;
		System.out.println("Please enter your Username: ");
		un = reader.nextLine();
		
		System.out.println("Please enter you Password: ");
		pw = reader.nextLine();
		
		UserAccount key = new UserAccount(un, pw);
		
		int index = userAccounts.indexOf(key);
		
		if (index > -1) {
			loggedIn = userAccounts.get(index);
			System.out.println(un + " logged in");
		} else {
			System.out.println("User not found!");
		}
		
	}
	public void Signup(Scanner reader) {
		String accType;
		String fName;
		String lName;
		String un;
		String pw;
		
		System.out.println("Please enter your first name: ");
		fName = reader.nextLine();
		
		System.out.println("Please enter your last name: ");
		lName = reader.nextLine();
		
		System.out.println("Please enter an account type: ");
		accType = reader.nextLine();
		
		System.out.println("Please enter a username: ");
		un = reader.nextLine();
		
		System.out.println("Please enter a password: ");
		pw = reader.nextLine();
		
		UserAccount user = new UserAccount(accType, fName, lName, un, pw);
		userAccounts.add(user);
		System.out.println(un + " signed up");
	}


}
