package com.revature.project0.monopoly.core;

import java.io.Serializable;

/**
 * This class represents an Account for the players, storing their username and password. It is serializable.
 * NOTE: It serializes the password, which is not a secure method to transmit this field.
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -6777518409670196094L;
    private String username;
    private String password;

    /**
     * Two-arg constructor for the Account.
     * @param username the player's username
     * @param password the player's password
     */
    public Account(String username, String password){
        this.password = password;
        this.username = username;
        LogWrapper.log(this.getClass(), "Account created: " + this.toString(), LogWrapper.Severity.DEBUG);
    }

    String getPassword(){
        return password;
    }

    String getUsername(){
        return username;
    }

    public String toString(){
        return "username: " + username + " | password: "+ password;
    }
}
