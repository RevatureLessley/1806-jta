package com.revature.andrewduffey.bank.dao;

import com.revature.andrewduffey.bank.util.Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.andrewduffey.bank.util.CloseStreams.close;

public class UserInfoDAOImpl implements UserInfoDAO {

    @Override
    public String selectPasswordById(Integer id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT password FROM UserInfo WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString("password");
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
    public String selectAdminById(Integer id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT admin FROM UserInfo WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString("admin");
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
    public String selectPendingById(Integer id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT pending FROM UserInfo WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString("pending");
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
    public String selectLockedById(Integer id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT locked FROM UserInfo WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return rs.getString("locked");
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
    public Integer selectCountByAdmin() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM UserInfo WHERE admin = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Y");
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

    @Override
    public Integer insert(Integer id, String password, String admin, String pending, String locked) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO UserInfo(user_id, password, admin, pending, locked) VALUES(?,?,?,?,?)";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, password);
            stmt.setString(3, admin);
            stmt.setString(4,pending);
            stmt.setString(5, locked);

            return stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        return null;
    }

    @Override
    public List<Integer> selectPendingUserIds() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT user_id FROM UserInfo WHERE pending = ?";
        List<Integer> users = new ArrayList<>();
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Y");
            rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(rs.getInt("user_id"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
        return users;
    }

    @Override
    public void updatePending(Integer id, String pending) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "UPDATE UserInfo SET pending = ? WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pending);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
    }

    @Override
    public void updateLocked(Integer id, String locked) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "UPDATE UserInfo SET locked = ? WHERE user_id = ?";
        try (Connection conn = Connections.getConnection()) {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, locked);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs);
            close(stmt);
        }
    }
}
