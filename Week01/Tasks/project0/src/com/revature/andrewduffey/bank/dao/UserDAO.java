package com.revature.andrewduffey.bank.dao;

public interface UserDAO {
    Integer selectIdByUsername(String username);
    void insert(String username);
    String selectUsernameById(Integer id);
}
