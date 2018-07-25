import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

public class UserInterface
{
    public Scanner scanner;
    public Hashtable<Integer, Runnable> commandTable;
    public boolean validInput = false;

    Account currentAccount;
    AccountsRecord accountsRecord;
    StatusLogger statusLogger;
    RecordsManager recordsManager;

    public UserInterface()
    {
        statusLogger = new StatusLogger();
//        accountsRecord = recordsManagerOld.getFile();

        accountsRecord = new AccountsRecord();
        recordsManager = new RecordsManager(accountsRecord);
    }

    public void userInputInt(Hashtable<Integer, Runnable> commands)
    {
        int inputValue = 99;

        do {
            scanner  = new Scanner(System.in);

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

    public Account accountInput(boolean isAdmin)
    {
        System.out.println("======================================");
        //Set up empty strings to check against in do-while loop
        String username = "";
        String password = "";

        scanner  = new Scanner(System.in);

        do {
            System.out.print("Enter a username: ");
            username = scanner.nextLine();

            if(username.isEmpty()) {System.out.println("Not a valid username");}
        }
        while(username.isEmpty());

        do {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();

            if (password.isEmpty()) {System.out.println("Not a valid password");}
        }
        while(password.isEmpty());

        if(!isAdmin)
            return new Account(username, password);
        else
            return new Account(username, password, isAdmin);
    }

    public void displayLogo()
    {
        try {
            File file = new File("C:/Users/cb/Documents/Revature/Assignments/Project0/resources/menu_files/monopolylogo.txt");
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            fr.close();
        }
        catch(Exception e)
        {
            statusLogger.logIssue("Could not find Monopoly logo file");
        }
    }

    public void mainMenu()
    {
        displayLogo();
        System.out.println("==============================\n" +
                "Menu:\n" +
                "\n" +
                "1. Login/Register\n" +
                "2. Quit\n" +
                "\n" +
                "Select an option: ");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::loginRegister);
        commandTable.put(2, new Runnable() {
            public void run() {
                displayLogo();
                System.out.println("Goodbye!"); }});

        userInputInt(commandTable);

    }
    public void loginRegister()
    {

        System.out.println("==============================\n" +
                "Login Menu:\n" +
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
        commandTable.put(1337, this::superAdminLogin);

        userInputInt(commandTable);
    }

    public void login()
    {

        Account newAccount = accountInput(false);

        if(accountsRecord.checkValidity(newAccount))
        {
            currentAccount = accountsRecord.getAccount(newAccount.getUsername());

            if(!currentAccount.isApproved()) {
                System.err.println("Your account has not yet been approved");
                mainMenu();
            }

            statusLogger.logMessage(currentAccount.getUsername() + "has logged in");

            if(currentAccount.isAdmin())
                adminMenu();
            else
                userMenu();
        }
        else
        {
            System.err.println("Login error. Username or password is not correct");
            mainMenu();
        }

    }

    public void superAdminLogin()
    {
        Account newAccount = accountInput(false);

        if(SuperUser.validate(newAccount)) {
            currentAccount = newAccount;
            statusLogger.logMessage("SuperUser has taken command");
            superAdminMenu();
        }
        else
        {
            System.err.println("Login error. Username or password is not correct");
            login();
        }
    }

    public void superAdminMenu()
    {
        System.out.println("==============================");
        System.out.println("Hello, Boss Overlord, Sir");
        System.out.println("==============================\n" +
                "SuperUser Menu:\n" +
                "\n" +
                "1. Create new account\n" +
                "2. Delete account\n" +
                "3. See all accounts\n" +
                "4. Logout\n" +
                "\n" +
                "Select an option: ");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::adminCreate);
        commandTable.put(2, this::adminDelete);
        commandTable.put(3, new Runnable() {
            public void run() {
                accountsRecord.debugAccounts(true);
                superAdminMenu();
            }
        });
        commandTable.put(4, this::logout);

        userInputInt(commandTable);
    }

    public void adminCreate()
    {
        System.out.println("Create Admin Account: ");
        Account adminAccount = accountInput(true);

        accountsRecord.addAccount(adminAccount);
        recordsManager.createAccount(adminAccount);
        superAdminMenu();
    }

    public void adminDelete()
    {
        System.out.println("Select Admin account: ");

        //Set up empty strings to check against in do-while loop
        String username = "";

        scanner  = new Scanner(System.in);

        do {
            System.out.print("Enter a username: ");
            username = scanner.nextLine();

            if(username.isEmpty()) {System.out.println("Not a valid username");}
            if(!accountsRecord.contains(username)) {
                System.err.println("Username doesn't exist!");
                username = "";
            }
        }
        while(username.isEmpty());

        Account selectedAccount = accountsRecord.getAccount(username);

        selectedAccount.printAccountDetails();
        System.out.println("ARE YOU SURE YOU WANT TO DELETE THIS ACCOUNT? (Y/N): ");
        String input = scanner.nextLine();

        if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            accountsRecord.deleteAccount(selectedAccount);
            System.out.println("Account has been deleted");
        }
        else
        {
            System.out.println("Account deletion process has been canceled");
        }

        superAdminMenu();

    }

    public void adminMenu()
    {
        System.out.println("=======================\n" +
                "Admin Menu:\n" +
                "1. See all user accounts\n" +
                "2. Approve all pending user accounts\n" +
                "3. Logout\n" +
                "\n" +
                "Select an option:");

        commandTable = new Hashtable<>();
        commandTable.put(1, new Runnable() {
            @Override
            public void run() {
                accountsRecord.debugAccounts(false);
                adminMenu();
            }});
        commandTable.put(2, new Runnable() {
            @Override
            public void run() {
                accountsRecord.approveAllAccounts();
                recordsManager.updateApprovalStatus();
                System.out.println("All pending user accounts approved");
                adminMenu();
            }
        });
        commandTable.put(3, this::logout);

        userInputInt(commandTable);
    }

    public void userMenu()
    {
        System.out.println("==============================\n"
                +"Menu:\n" +
                "\n" +
                "1. Withdraw\n" +
                "2. Deposit\n" +
//                "3. Get loan\n" +
//                "4. Account Settings \n" +
                "5. Logout\n" +
                "\n" +
                "Select an option: ");

        commandTable = new Hashtable<>();
        commandTable.put(1, this::withdraw);
        commandTable.put(2, this::deposit);
//        commandTable.put(3, this::getLoan);
//        commandTable.put(4, this::showAccountDetails);
        commandTable.put(5, this::logout);

        userInputInt(commandTable);
    }

    public void register()
    {
        Account newAccount = accountInput(false);

        if(accountsRecord.contains(newAccount.getUsername()))
        {
            System.err.println("Account with that username already exists!");
            loginRegister();
        }
        else
        {
            accountsRecord.addAccount(newAccount);
            recordsManager.createAccount(newAccount);
            System.err.println("Your account must be approved");

            statusLogger.logMessage(newAccount.getUsername() + "has registered, waiting approval");

            mainMenu();
        }

    }

    public void withdraw()
    {
        System.out.println("=====================================");
        double withdrawalAmount = -1;

        do {

            scanner = new Scanner(System.in);
            System.out.println("Balance: " +currentAccount.getAccountBalance());
            System.out.println("Enter withdrawal amount: ");
            if(scanner.hasNextDouble())
                withdrawalAmount = scanner.nextDouble();
            else
                System.err.println("Enter a valid amount");

            if(currentAccount.getAccountBalance() < withdrawalAmount)
            {
                System.err.println("The entered withdrawal amount is too high");
                withdrawalAmount = -1;
            }
        }
        while(withdrawalAmount < 0);

        currentAccount.withdraw(withdrawalAmount);
        System.out.println("You have withdrawn $" + withdrawalAmount);

        statusLogger.logMessage(currentAccount.getUsername() + " withdrew $" + withdrawalAmount);


        accountsRecord.updateAccount(currentAccount);
        recordsManager.updateBalance(currentAccount);
        userMenu();
    }

    public void deposit()
    {
        System.out.println("=====================================");
        double depositAmount = -1;

        do {

            scanner = new Scanner(System.in);
            System.out.println("Balance: " +currentAccount.getAccountBalance());
            System.out.println("Enter deposit amount: ");

            if(scanner.hasNextDouble())
                depositAmount = scanner.nextDouble();
            else

                System.err.println("Enter a valid amount");
        }
        while(depositAmount < 0);

        currentAccount.deposit(depositAmount);
        System.out.println("You have deposited $" + depositAmount);

        statusLogger.logMessage(currentAccount.getUsername() + "has deposited $" + depositAmount);

        accountsRecord.updateAccount(currentAccount);
        recordsManager.updateBalance(currentAccount);
        userMenu();
    }
//
//    public void getLoan()
//    {
//        System.out.println("=====================================");
//        scanner = new Scanner(System.in);
//        System.out.println("Enter loan request amount: ");
//        int withdrawalAmount = scanner.nextInt();
//        System.out.println("You have requested a loan for $" + withdrawalAmount);
//        System.err.println("Your loan request has been denied!");
//        userMenu();
//    }

    public void logout()
    {
        statusLogger.logMessage(currentAccount.getUsername() + " has logged out");
        statusLogger.logMessage("Saving accounts and writing file");

        currentAccount = null;
        System.out.println("You have logged out.");
        mainMenu();
    }
//
//    public void showAccountDetails()
//    {
//        System.out.println("Here are your account details");
//        System.out.println("Menu: \n" +
//                "1. View Account Details" +
//                "2. Go Back");
//
//        commandTable = new Hashtable<>();
//        commandTable.put(1, new Runnable() {
//            @Override
//            public void run() {
//                currentAccount.printAccountDetails();
//
//            }
//        });
//        commandTable.put(2, this::userMenu);
//
//        userInputInt(commandTable);
//
//    }

    public void submitLoan()
    {

    }
}
