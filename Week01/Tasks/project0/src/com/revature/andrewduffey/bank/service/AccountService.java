package com.revature.andrewduffey.bank.service;

import com.revature.andrewduffey.bank.dao.AccountDAO;
import com.revature.andrewduffey.bank.dao.AccountDAOImpl;

public class AccountService {

    public static void createAccount(Integer id) {
        AccountDAO ad = new AccountDAOImpl();
        ad.insertAccount(id);
    }

    public static int getAccountId(int userId) {
        AccountDAO ad = new AccountDAOImpl();
        return ad.selectAccountIdByUserId(userId);
    }
}
