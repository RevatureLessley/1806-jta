package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Account;

import java.util.ArrayList;

public interface AccountDao {
    ArrayList<Account> getAllAccounts();
    boolean addAccount(Account account);
}
