package com.revature.andrewduffey.bank.dao;

import com.revature.andrewduffey.bank.util.Connections;

import java.sql.*;

import static com.revature.andrewduffey.bank.util.CloseStreams.close;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void insertAccount(Integer id) {
        CallableStatement stmt = null;
        String sql = "{CALL create_account(?)}";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(stmt);
        }
    }

    @Override
    public Integer selectAccountIdByUserId(int userId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT account_id FROM Accounts WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getInt("account_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        return null;
    }
}
