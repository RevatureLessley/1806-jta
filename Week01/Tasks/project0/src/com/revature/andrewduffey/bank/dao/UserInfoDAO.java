package com.revature.andrewduffey.bank.dao;

import java.util.List;

public interface UserInfoDAO {
    public String selectPasswordById(Integer id);
    public String selectAdminById(Integer id);
    public String selectPendingById(Integer id);
    public Integer selectCountByAdmin();
    public Integer insert(Integer id, String password, String admin, String pending, String locked);
    public List<Integer> selectPendingUserIds();
    public void updatePending(Integer id, String pending);
    public void updateLocked(Integer id, String locked);
}