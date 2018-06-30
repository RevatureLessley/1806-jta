package com.revature.project0.monopoly.core;

import java.io.Serializable;
import java.util.ArrayList;

import static com.revature.project0.monopoly.core.LogWrapper.Severity.DEBUG;

/**
 * POJO for holding accounts. This class will be serializable to store Account information for all the players.
 */
public class AccountContainer implements Serializable {

    private static final long serialVersionUID = 3992351913256300598L;
    private ArrayList<Account> accountList;

    /**
     * no-arg constructor; initializes Object fields.
     */
    public AccountContainer(){
        accountList = new ArrayList<>();
        LogWrapper.log(this.getClass(), "AccountContainer created.", DEBUG);
    }

    /**
     * This method adds an account to the ArrayList of accounts maintained by this class.
     * @param acc the Account to be added to the list.
     */
    void addAccount(Account acc){
        accountList.add(acc);
        LogWrapper.log(this.getClass(), "Account added to List:" + acc.toString(), DEBUG);
    }

    /**
     * This method will find and return the account specified by it's username and password. NOTE: Duplicates are
     * theoretically not allowed in this List.
     * @param accountName the username of the Account
     * @param password the password of the Account
     * @return the Account matching the two parameters, or null if no match was found.
     */
    Account findAccount(String accountName, String password){
        for(Account acc : accountList){
            if (acc.getUsername().equals(accountName) && acc.getPassword().equals(password)) {
                LogWrapper.log(this.getClass(), "Found Account: " + acc.toString(), DEBUG);
                return acc;}
        }
        LogWrapper.log(this.getClass(), "Account not found.", DEBUG);
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Account a : accountList) sb.append(a.toString()).append("\n");
        return sb.toString();
    }
}
