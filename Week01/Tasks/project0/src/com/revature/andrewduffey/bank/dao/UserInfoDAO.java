package com.revature.andrewduffey.bank.dao;

import java.util.Arrays;
import java.util.List;

public interface UserInfoDAO {
    String selectPasswordById(Integer id);
    String selectAdminById(Integer id);
    String selectPendingById(Integer id);
    String selectLockedById(Integer id);
    Integer selectCountByAdmin();
    Integer insert(Integer id, String password, String admin, String pending, String locked);
    List<Integer> selectPendingUserIds();
    void updatePending(Integer id, String pending);

    void updateLocked(Integer id, String locked);
}