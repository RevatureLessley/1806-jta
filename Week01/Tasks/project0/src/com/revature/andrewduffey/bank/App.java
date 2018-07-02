package com.revature.andrewduffey.bank;

import com.revature.andrewduffey.bank.bean.AdminUser;
import com.revature.andrewduffey.bank.bean.RegularUser;
import com.revature.andrewduffey.bank.bean.User;
import com.revature.andrewduffey.bank.service.AccountService;
import com.revature.andrewduffey.bank.service.TransactionService;
import com.revature.andrewduffey.bank.service.UserInfoService;
import com.revature.andrewduffey.bank.service.UserService;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class App {
    final static Logger logger = Logger.getLogger(App.class);
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean quit = false;

    public static void main(String[] args) {
        logger.info("Application starting");
        while(!quit) {
            login();
        }
        logger.info("Application closing");
    }

    /**
     * Accepts an username and password from the user. Checks if the username exists if so validate
     * that the username and password are correct if not create a new username with the password
     * provided.
     */
    private static void login() {
        String username;
        String password;

        System.out.print("Username: ");
        username = scanner.nextLine().trim();
        logger.info("Username accepted");
        System.out.print("Password: ");
        password = scanner.nextLine().trim();
        logger.info("Password accepted");

        Integer id = UserService.getUserId(username);

        if (id != null) {
            if (UserInfoService.validatePassword(id, password)) {
                User user = null;
                if (UserInfoService.isAdmin(id)) {
                    user = new AdminUser(id, username);

                } else if (UserInfoService.isPending(id)) {
                    System.out.println("Still waiting to be approved by an admin");
                } else {
                    user = new RegularUser(id, username);
                }

                if (user != null) {
                    boolean isAdmin = user.isAdmin();
                    logger.info((isAdmin ? "Admin" : "Regular") + " user logged in");
                    if (!isAdmin) {
                        int accountId = AccountService.getAccountId(id);
                        int balance = TransactionService.getBalance(accountId);
                        ((RegularUser)user).getAccount().setBalance(balance);
                    }
                    user.prompt();
                }
            } else {
                System.out.println("Username and Password combination is incorrect!");
            }
        } else {
            id = UserService.insert(username);
            if (id != null) {
                boolean isAdmin = (UserInfoService.adminUsers() == 0);
                UserInfoService.insert(id, password, isAdmin, !isAdmin, false);
                System.out.println("Account creation success!");
                logger.info("Account creation success");
            } else {
                System.out.println("Account creation failed!");
                logger.info("Account creation failed");
            }
        }
    }
}
