package com.revature.andrewduffey.bank.dao;

public interface TransactionDAO {
    void insertDeposit(int id, int amount);
    void insertWithdrawal(int id, int amount);
    Integer getBalance(int accountId);
}
