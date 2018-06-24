package com.revature.andrewduffey.bank;

import org.apache.log4j.Logger;

import java.io.*;

public abstract class User implements Serializable {
    final static Logger logger = Logger.getLogger(User.class);
    private static final long serialVersionUID = 8726383913231548129L;

    // instance variables
    private String username;
    private String password;
    protected boolean admin;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.admin = false;
    }

    /**
     * Returns the username for this user
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if this user is an admin
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Checks the password passed to the method matches the password for this user
     * @param password
     * @return
     */
    public final boolean validatePassword(String password) {
        boolean result = this.password.equals(password);
        logger.info("Password entered: " + (result ? "successful" : "unsuccessful"));
        return result;
    }

    /**
     * Serializes the user and outputs the serialization file in the path parameter.
     * @param user
     * @param path
     */
    public static void serialize(User user, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path + user.getUsername() + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.close();
            fos.close();
            logger.info(user.getUsername() + " successfully serialized");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deserializes the user and from the serialization file in the path parameter.
     * @param username
     * @param path
     */
    public static  User deserialize(String username, String path) {
        User user = null;
        try {
            FileInputStream fis = new FileInputStream(path + username + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (User)ois.readObject();
            ois.close();
            fis.close();
            logger.info(username + " successfully deserialized");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Prompts the user for a command and passes it to the processCommand() method that implemented by the subclass
     */
    public final void prompt() {
        String command;
        do {
            System.out.print("Enter Command: ");
            command = App.scanner.nextLine();
        } while (processCommand(command));
    }

    /**
     * Handles all the logic for a given command
     * @param command
     * @return
     */
    protected abstract boolean processCommand(String command);

    /**
     * Displays all the valid command for this type of user
     */
    protected abstract void printHelp();

}
