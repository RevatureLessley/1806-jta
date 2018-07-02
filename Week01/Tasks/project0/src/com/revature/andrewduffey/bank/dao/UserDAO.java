package com.revature.andrewduffey.bank.dao;

public interface UserDAO {
    public Integer selectIdByUsername(String username);
    public void insert(String username);
    public String selectUsernameById(Integer id);
}
