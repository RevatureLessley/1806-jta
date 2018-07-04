package com.revature.andrewduffey.bank.dao;

import com.revature.andrewduffey.bank.util.Connections;

import java.sql.*;

import static com.revature.andrewduffey.bank.util.CloseStreams.close;

public class UserDAOImpl implements UserDAO {

    /**
     * Returns the id of the user in the database otherwise returns null
     * @param username
     * @return
     */
    @Override
    public Integer selectIdByUsername(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT user_id FROM Users WHERE username = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        return null;
    }

    @Override
    public void insert(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Users(username) VALUES (?)";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
    }

    @Override
    public String selectUsernameById(Integer id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT username FROM Users WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("username");
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
