package com.revature.andrewduffey.bank.dao;

import com.revature.andrewduffey.bank.util.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.andrewduffey.bank.util.CloseStreams.close;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public void insertDeposit(int id, int amount) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Transactions(account_id, amount) VALUES (?,?)";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, amount);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
    }

    @Override
    public void insertWithdrawal(int id, int amount) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Transactions(account_id, amount) VALUES (?,?)";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, -amount);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
    }

    @Override
    public Integer getBalance(int accountId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(amount) FROM Transactions WHERE account_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
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
