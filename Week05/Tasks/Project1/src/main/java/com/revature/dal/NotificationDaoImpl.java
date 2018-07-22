package com.revature.dal;

import com.revature.beans.NotificationBean;
import com.revature.beans.NotificationBeanProxy;
import com.revature.utils.DatabaseConnection;
import com.revature.utils.LogWrapper;

import java.sql.*;
import java.util.ArrayList;

import static com.revature.utils.CloseStreams.close;

/**
 * The Data Access Object (DAO) accessing the Database on behalf of Notification Beans
 */
public class NotificationDaoImpl implements NotificationDao{
    /**
     * Accesses the database and retrieves the Notifications based on who they notify
     * @param id the ID of the Employee being notified
     * @return An ArrayList of Notification Beans
     */
    @Override
    public ArrayList<NotificationBeanProxy> retrieveNotificationsByEmployeeId(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<NotificationBeanProxy> beanList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Pending_Notifications WHERE employee_id = ?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()){
                NotificationBeanProxy bean = new NotificationBeanProxy(
                        rs.getInt("notification_id"),
                        rs.getInt("request_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("is_at_supervisor") != 0,
                        rs.getInt("is_at_dept_head") != 0,
                        rs.getInt("is_at_ben_co") != 0,
                        rs.getInt("approval_count")
                );
                LogWrapper.log(this.getClass(), "Retrieve Notification (Proxy) Successful", LogWrapper.Severity.DEBUG);
                beanList.add(bean);
            }
            LogWrapper.log(this.getClass(), "Retrieve Notification (Proxy) List Successful", LogWrapper.Severity.DEBUG);
            return beanList;

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
            close(rs);
        }
        return null;
    }

    /**
     * Accesses the Database and retrieves the Notification Bean via its ID
     * @param id the ID of the Notification
     * @return A proxy for the Notification Bean. (Similar to Hibernate's Lazy Fetch) Will be fulfilled in the BLL
     */
    @Override
    public NotificationBeanProxy retrieveNotificationsById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Pending_Notifications WHERE notification_id = ?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()){ //expecting one result
                NotificationBeanProxy bean = new NotificationBeanProxy(
                        rs.getInt("notification_id"),
                        rs.getInt("request_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("is_at_supervisor") != 0,
                        rs.getInt("is_at_dept_head") != 0,
                        rs.getInt("is_at_ben_co") != 0,
                        rs.getInt("approval_count")
                );
                LogWrapper.log(this.getClass(), "Retrieve Notification (Proxy) Successful", LogWrapper.Severity.DEBUG);
                return bean;
            }

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
            close(rs);
        }
        return null;
    }

    /**
     * Accesses the database and inserts a Notification.
     * @param bean the Bean containing the Notification metadata
     * @return true if insert was successful, false otherwise
     */
    @Override
    public boolean insertNotification(NotificationBean bean) {
        PreparedStatement statement = null;

        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Pending_Notifications VALUES (?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            if (bean.getId() > 0) statement.setInt(1, bean.getId());
            else statement.setNull(1, Types.INTEGER);
            statement.setInt(2, bean.getReimbursement().getId());
            statement.setInt(3, bean.getNotifiee().getId());
            statement.setInt(4, bean.isAtSupervisor() ? 1 : 0);
            statement.setInt(5, bean.isAtDeptHead() ? 1 : 0);
            statement.setInt(6, bean.isAtBenCo() ? 1 : 0);
            statement.execute();
            LogWrapper.log(this.getClass(), "Insert Notification Successful", LogWrapper.Severity.DEBUG);
            return true;

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
        }
        return false;
    }

    /**
     * Accesses the database and updates the Notification with the passed bean's id
     * @param bean the Bean containing the id of the existing Notification being updated (Updates the Employee being notified)
     * @return true if successful, false otherwise
     */
    @Override
    public boolean updateNotifiee(NotificationBean bean) {
        PreparedStatement statement = null;

        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "UPDATE Pending_Notifications SET employee_id=?, is_at_supervisor=?, is_at_dept_head=?, is_at_ben_co=?, approval_count=? WHERE notification_id=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, bean.getNotifiee().getId());
            statement.setInt(2, bean.isAtSupervisor() ? 1 : 0);
            statement.setInt(3, bean.isAtDeptHead() ? 1 : 0);
            statement.setInt(4, bean.isAtBenCo() ? 1 : 0);
            statement.setInt(5, bean.getApprovalCount()+1);
            statement.setInt(6, bean.getId());
            statement.execute();
            LogWrapper.log(this.getClass(), "Update Notification Successful.", LogWrapper.Severity.DEBUG);
            return true;
        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
        }
        return false;
    }

    /**
     * Accesses the database and deletes a Notification based on its ID
     * @param id the ID of the Notification being deleted
     * @return true if deletion is successful, false otherwise
     */
    @Override
    public boolean deleteNotificationById(int id) {
        PreparedStatement statement = null;
        String sql = "DELETE FROM Pending_Notifications WHERE notification_id = ?";
        try(Connection conn = DatabaseConnection.getConnection()) {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            LogWrapper.log(this.getClass(), "Notification Deletion Successful.", LogWrapper.Severity.DEBUG);
            return true;
        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally{
            close(statement);
        }
        return false;
    }
}
