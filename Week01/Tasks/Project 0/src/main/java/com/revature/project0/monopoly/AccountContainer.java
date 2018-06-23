package com.revature.project0.monopoly;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * POJO for holding accounts. This class will be serializable to store game states.
 */
public class AccountContainer implements Serializable {

    private static final long serialVersionUID = -577617069418873278L;
    private ArrayList<Account> accountList;

    public AccountContainer(){
        accountList = new ArrayList<>();
    }

    public void addAccount(Account acc){
        accountList.add(acc);
    }

    public Account findAccount(String accountName, String password){
        for(Account acc : accountList){
            if (acc.getUsername().equals(accountName) && acc.getPassword().equals(password)) return acc;
        }
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Account a : accountList) sb.append(a.toString() + "\n");
        return sb.toString();
    }
}
