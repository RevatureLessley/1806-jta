import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

public class Menu
{
    public Scanner scanner;
    public Hashtable<Integer, Runnable> commandTable;
    public boolean validInput = false;

    public void userInputInt(Hashtable<Integer, Runnable> commands)
    {
        int inputValue = 99;

        do {
            scanner  = new Scanner(System.in);
            System.out.println("Select an option: ");

            if(scanner.hasNext() && scanner.hasNextInt())
            {
                inputValue = scanner.nextInt();
                validInput = commands.containsKey(inputValue);
            }

            if(!validInput) {System.out.println("Invalid option");}
        }
        while(validInput == false);

        Runnable r = commands.get(inputValue);
        if (r != null) {r.run();}
    }


    public void displayMenu()
    {
        try {
            File file = new File("C:/Users/cb/Documents/Revature/Assignments/Project0/resources/menu_files/main_menu.txt");
            FileReader fr = new FileReader(file);
            char[] a = new char[100];
            fr.read(a);   // reads the content to the array

            for (char c : a)
                System.out.print(c);   // prints the characters one by one

            fr.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void mainMenu()
    {
        System.out.println("Menu:\n" +
                "\n" +
                "1. Login/Register\n" +
                "2. Quit\n" +
                "\n" +
                "Select an option: ");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::loginRegister);
        commandTable.put(2, new Runnable() {
            public void run() { System.out.println("Goodbye!"); }});

        userInputInt(commandTable);

    }
    public void loginRegister()
    {
        System.out.println("Menu:\n" +
                "\n" +
                "1. Login\n" +
                "2. Create new account\n" +
                "3. Go Back\n" +
                "\n" +
                "Select an option:");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::login);
        commandTable.put(2, this::register);
        commandTable.put(3, this::mainMenu);

        userInputInt(commandTable);
    }

    public void login()
    {
        scanner  = new Scanner(System.in);
        System.out.print("Username: ");
        if (scanner.hasNext())
        {
            String username = scanner.nextLine(); //TODO: check for username validity
            System.out.println(username);
        }

        System.out.print("Password: ");

        if(scanner.hasNext())
        {
            String password = scanner.nextLine(); //TODO: check for password validity
            System.out.println(password);
        }

        //TODO: check for account existence
        loggedIn();
    }

    public void loggedIn()
    {
        System.out.println("Menu:\n" +
                "\n" +
                "1. Withdraw\n" +
                "2. Deposit\n" +
                "3. Get loan\n" +
                "4. Account Settings \n" +
                "5. Logout\n" +
                "\n" +
                "Select an option: ");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::withdraw);
        commandTable.put(2, this::deposit);
        commandTable.put(3, this::getLoan);
        commandTable.put(4, this::showAccountDetails);
        commandTable.put(5, this::logout);

        userInputInt(commandTable);
    }

    public void register()
    {
        String username = "";
        String password = "";

        scanner  = new Scanner(System.in);

        do {
            System.out.print("Enter a username: ");
            username = scanner.nextLine();       //TODO: check for username validity

            if(username.isEmpty()) {System.out.println("Not a valid username");}
        }
        while(username.isEmpty());

        do {
            System.out.print("Enter a password: ");     //TODO: check for password validity
            password = scanner.nextLine();

            if (password.isEmpty()) {System.out.println("Not a valid password");}
        }
        while(password.isEmpty());

        Account newAccount = new Account(username, password);

        System.err.println("Your account must be approved");
        mainMenu();
    }

    public void withdraw()
    {

        double withdrawalAmount = -1;

        do {

            scanner = new Scanner(System.in);
            //TODO: Add user balance printout here
            System.out.println("Enter withdrawal amount: ");
            if(scanner.hasNextDouble())
            {
                withdrawalAmount = scanner.nextDouble(); //TODO: check for corrent withdrawal amount/userinput
            }
            else
            {
                System.err.println("Enter a valid amount");
            }
        }
        while(withdrawalAmount < 0);
        System.out.println("You have withdrawn $" + withdrawalAmount);
        loggedIn();
    }

    public void deposit()
    {
        double depositAmount = -1;

        do {

            scanner = new Scanner(System.in);
            //TODO: Add user balance printout here
            System.out.println("Enter deposit amount: ");

            if(scanner.hasNextDouble())
            {
                depositAmount = scanner.nextDouble(); //TODO: check for corrent withdrawal amount/userinput
            }
            else
            {
                System.err.println("Enter a valid amount");
            }
        }
        while(depositAmount < 0);
        System.out.println("You have deposited $" + depositAmount);
        loggedIn();
    }

    public void getLoan()
    {
        scanner = new Scanner(System.in);
        System.out.println("Enter loan request amount: ");
        int withdrawalAmount = scanner.nextInt(); //TODO: check for corrent withdrawal amount/userinput
        System.out.println("You have requested a loan for $" + withdrawalAmount);
        System.err.println("Your loan request has been denied!"); //TODO: add in loan calc feature
        loggedIn();
    }

    public void logout()
    {
        System.out.println("You have logged out. Goodbye!");
    }

    public void showAccountDetails()
    {
        System.out.println("Here are your account details");
        System.out.println("Menu: \n" +
                "1. Go Back");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::loggedIn);

        userInputInt(commandTable);

    }

    public void submitForApproval()
    {

    }

    public void approveAccount()
    {

    }

    public void submitLoan()
    {

    }
}
