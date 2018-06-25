package com.revature.andrewduffey.bank;

import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;

public class RegularUser extends User {
    final static Logger logger = Logger.getLogger(RegularUser.class);
    private static final long serialVersionUID = -8360048087474347678L;

    private Account account;

    public RegularUser(String username, String password) throws NoSuchAlgorithmException {
        super(username, password);
        account = new Account();
    }

    /**
     * Returns the account for this user
     * @return
     */
    public Account getAccount() {
        return account;
    }

    @Override
    protected boolean processCommand(String command) {
        boolean valid = true;
        double amount;
        logger.info("User entered: " + command);
        switch (command) {
            case "logout":
                return false;
            case "balance":
                System.out.println("Balance: " + account.getBalance());
                break;
            case "deposit":
                do {
                    System.out.print("Enter an amount to deposit: ");
                    try {
                        amount = Double.parseDouble(App.scanner.nextLine());
                        if (account.deposit(amount)) logger.info("User deposited " + amount);
                    } catch (NumberFormatException ex) {
                        logger.warn("User entered invalid number to deposit");
                        System.out.println("Amount entered is not a valid number!");
                        valid  = false;
                    }
                } while (!valid);
                User.serialize(this, App.USERS_PATH);
                break;
            case "withdrawal":
                do {
                    System.out.print("Enter an amount to withdrawal: ");
                    try {
                        amount = Double.parseDouble(App.scanner.nextLine());
                        if (account.withdrawal(amount)) logger.info("User withdrew " + amount);
                    } catch (NumberFormatException ex) {
                        logger.warn("User entered invalid number to withdrawal");
                        System.out.println("Amount entered is not a valid number!");
                        valid  = false;
                    }
                } while (!valid);
                User.serialize(this, App.USERS_PATH);
                break;
            case "help":
                printHelp();
                break;
            case "quit":
                App.quit = true;
                return false;
            default:
                System.out.println("Error: Invalid Command!\nType 'help' for a list of valid commands.");
                break;
        }
        return true;
    }

    @Override
    protected void printHelp() {
        logger.info("Help screen displayed");
        System.out.println("Logout: logout");
        System.out.println("Balance: balance");
        System.out.println("Deposit: deposit");
        System.out.println("Withdrawal: withdrawal");
        System.out.println("Help: help");
        System.out.println("Quit: quit");
    }
}
