package com.revature.andrewduffey.bank.service;

import com.revature.andrewduffey.bank.dao.TransactionDAO;
import com.revature.andrewduffey.bank.dao.TransactionDAOImpl;

public class TransactionService {
    public static void addDeposit(int id, int amount) {
        TransactionDAO td = new TransactionDAOImpl();
        td.insertDeposit(id, amount);
    }

    public static void addWithdrawal(int id, int amount) {
        TransactionDAO td = new TransactionDAOImpl();
        td.insertWithdrawal(id, amount);
    }

    public static Integer getBalance(int accountId) {
        TransactionDAO td = new TransactionDAOImpl();
        return td.getBalance(accountId);
    }
}
