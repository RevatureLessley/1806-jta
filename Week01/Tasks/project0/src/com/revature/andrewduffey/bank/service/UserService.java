package com.revature.andrewduffey.bank.service;

import com.revature.andrewduffey.bank.dao.UserDAO;
import com.revature.andrewduffey.bank.dao.UserDAOImpl;

public class UserService {
    public static Integer getUserId(String username) {
        UserDAO ud = new UserDAOImpl();
        return ud.selectIdByUsername(username);
    }

    public static Integer insert(String username) {
        UserDAO ud = new UserDAOImpl();
        ud.insert(username);
        return getUserId(username);
    }

    public static String getUserName(Integer id) {
        UserDAO ud = new UserDAOImpl();
        return ud.selectUsernameById(id);
    }
}
