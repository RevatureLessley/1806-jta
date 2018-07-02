package com.revature.andrewduffey.bank.dao;

public interface AccountDAO {
    void insertAccount(Integer id);
    Integer selectAccountIdByUserId(int userId);
}
