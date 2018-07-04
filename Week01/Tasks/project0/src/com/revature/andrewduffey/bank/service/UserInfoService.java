package com.revature.andrewduffey.bank.service;

import com.revature.andrewduffey.bank.dao.UserInfoDAO;
import com.revature.andrewduffey.bank.dao.UserInfoDAOImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserInfoService {

    public static boolean validatePassword(int id, String password) {
        String hash = hashPassword(password);
        UserInfoDAO uid = new UserInfoDAOImpl();
        return hash.equals(uid.selectPasswordById(id));
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder hex = new StringBuilder();
            for (int i = 0; i < digest.length; ++i) {
                String s = Integer.toHexString(digest[i] & 0xFF); //force positive between  0-255
                if (s.length() == 1) hex.append('0');
                hex.append(s);
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean isAdmin(Integer id) {
        UserInfoDAO uid = new UserInfoDAOImpl();
        return uid.selectAdminById(id).equals("Y");
    }

    public static boolean isPending(Integer id) {
        UserInfoDAO uid = new UserInfoDAOImpl();
        return uid.selectPendingById(id).equals("Y");
    }

    public static boolean isLocked(Integer id) {
        UserInfoDAO uid = new UserInfoDAOImpl();
        return uid.selectLockedById(id).equals("Y");
    }

    public static Integer adminUsers() {
        UserInfoDAO uid = new UserInfoDAOImpl();
        return uid.selectCountByAdmin();
    }

    public static Integer insert(Integer id, String password, boolean admin, boolean pending, boolean locked) {
        UserInfoDAO uid = new UserInfoDAOImpl();
        return uid.insert(id, hashPassword(password), (admin ? "Y" : "N"), (pending ? "Y" : "N"), (locked ? "Y" : "N"));
    }

    public static List<Integer> getPendingUserIds() {
        UserInfoDAO uid = new UserInfoDAOImpl();
        return uid.selectPendingUserIds();
    }

    public static void setPending(Integer id, boolean pending) {
        UserInfoDAO uid = new UserInfoDAOImpl();
        uid.updatePending(id, pending ? "Y" : "N");
    }

    public static void setLocked(Integer id, boolean locked) {
        UserInfoDAO uid = new UserInfoDAOImpl();
        uid.updateLocked(id, locked ? "Y" : "N");
    }
}
