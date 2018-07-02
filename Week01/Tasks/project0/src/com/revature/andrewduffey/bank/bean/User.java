package com.revature.andrewduffey.bank.bean;

import com.revature.andrewduffey.bank.App;
import org.apache.log4j.Logger;

import java.io.*;

public abstract class User implements Serializable {
    final static Logger logger = Logger.getLogger(User.class);
    private static final long serialVersionUID = 8726383913231548129L;

    // instance variables
    private int id;
    private String username;
    protected boolean admin;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
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

    public int getId() {
        return id;
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
