package com.revature.project0.monopoly.core;

/**
 * This class represents an Account for the players, storing their username and password.
 * NOTE: It does NOT encrypt the password.
 */
public class Account {

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

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    public String toString(){
        return "username: " + username + " | password: "+ password;
    }
}
