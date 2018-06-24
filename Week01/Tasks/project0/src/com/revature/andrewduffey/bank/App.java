package com.revature.andrewduffey.bank;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.Scanner;

public class App {
    final static Logger logger = Logger.getLogger(App.class);
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean quit = false;
    public static final String USERS_PATH = "data/users/";
    public static final String PENDING_PATH = USERS_PATH + "pending/";

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

        if (userExists(username)) {
            File file = new File(USERS_PATH + username + ".ser");
            User user = User.deserialize(username, (file.exists() ? App.USERS_PATH : App.PENDING_PATH));
            if (user.validatePassword(password)) {
                if (!file.exists()) {
                    System.out.println("Still waiting to be approved by an admin");
                } else {
                    if (user.isAdmin()) {
                        logger.info("Admin user logged in");
                        ((AdminUser)user).prompt();
                    } else {
                        logger.info("Regular user logged in");
                        ((RegularUser)user).prompt();
                    }
                }
            } else {
                System.out.println("Username and Password combination is incorrect!");
            }
        } else {
            if (activeUsers() == 0) {
                logger.info("Admin user created");
                System.out.println("Admin account created!");
                User.serialize(new AdminUser(username, password), App.USERS_PATH);
            } else {
                logger.info("Regular user created");
                System.out.println("Account created! Waiting to be approved.");
                User.serialize(new RegularUser(username, password), App.PENDING_PATH);
            }
        }
    }

    /**
     * Returns the number of active users
     * @return
     */
    private static int activeUsers() {
        File directory = new File(USERS_PATH);
        int result = 0;

        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".ser")) result++;
        }
        return result;
    }

    /**
     * Checks if a user account has been created
     * @param username
     * @return
     */
    private static boolean userExists(String username) {
        File users = new File(USERS_PATH + username + ".ser");
        File pending = new File(PENDING_PATH + username + ".ser");
        return users.exists() || pending.exists();
    }
}
