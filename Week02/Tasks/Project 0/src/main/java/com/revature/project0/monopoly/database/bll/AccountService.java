package com.revature.project0.monopoly.database.bll;

import com.revature.project0.monopoly.core.Account;
import com.revature.project0.monopoly.core.AccountContainer;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.dal.AccountDaoImpl;

/**
 * This class represents the intersection of Java objects necessary to run the application, and the raw data of the database.
 * It works in conjunction with the AccountDao.
 */
public class AccountService {

    /**
     * retrieves the Accounts from the database
     * @return AccountContainer containing all the Accounts
     */
    public AccountContainer getAccountContainer(){
        AccountDaoImpl accountDao = new AccountDaoImpl();
        AccountContainer container = new AccountContainer();

        for (Account acc : accountDao.getAllAccounts()){
            container.addAccount(acc);
        }

        return container;
    }

    /**
     * Passes on this account to the DAO
     * @param account the account being inserted
     * @return true if successful, false if not.
     */
    public boolean addAccount(Account account){
        LogWrapper.log(this.getClass(), "Adding Account.", LogWrapper.Severity.DEBUG);
        return new AccountDaoImpl().addAccount(account);
    }
}
