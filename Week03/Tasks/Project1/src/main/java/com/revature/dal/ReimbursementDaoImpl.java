package com.revature.dal;

import com.revature.beans.EmployeeBean;
import com.revature.beans.ReimbursementBean;
import com.revature.utils.DatabaseConnection;
import com.revature.utils.LogWrapper;

import java.sql.*;

import static com.revature.utils.CloseStreams.close;

public class ReimbursementDaoImpl implements ReimbursementDao {

    @Override
    public ReimbursementBean retrieveReimbursementFormById(int id){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Reimbursement_Requests a INNER JOIN Employees b ON a.employee_id = b.employee_id";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()){ //if instead of while because expecting only one result.
                ReimbursementBean bean = new ReimbursementBean(
                        rs.getInt("request_id"),
                        new EmployeeBean(
                                rs.getInt("employee_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getLong("phone"),
                                rs.getString("email"),
                                rs.getDouble("pending_reimbursement"),
                                rs.getDouble("awarded_reimbursement"),
                                rs.getInt("dir_supervisor_id"),
                                rs.getInt("dept_head_id"),
                                rs.getInt("ben_co_id")
                        ),
                        rs.getDate("date_requested"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getInt("reimbursement"),
                        rs.getString("grading_format"),
                        rs.getInt("event_type"),
                        rs.getInt("is_approved") != 0
                );
                LogWrapper.log(this.getClass(), "Retrieve Reimbursement Request Successful", LogWrapper.Severity.DEBUG);
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

    @Override
    public boolean insertReimbursementForm(ReimbursementBean bean) {
        PreparedStatement statement = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Reimbursement_Requests VALUES (?,?,?,?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            //statement.setNull(1, Types.INTEGER);
            statement.setInt(1, bean.getId());
            statement.setInt(2, bean.getEmployee().getId());
            statement.setDate(3, (java.sql.Date)bean.getDate()); //date
            statement.setString(4, bean.getLocation());
            statement.setString(5, bean.getDescription());
            statement.setInt(6, bean.getReimbursement());
            statement.setString(7, bean.getGradingFormat());
            statement.setInt(8, bean.getEventType());
            statement.setInt(9, bean.isApproved() ? 1 : 0);
            statement.execute();
            LogWrapper.log(this.getClass(), "Insert Reimbursement Request Successful", LogWrapper.Severity.DEBUG);
            return true;

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(statement);
        }
        return false;
    }

    @Override
    public boolean deleteReimbursementFormById(int id) {
        PreparedStatement statement = null;
        String sql = "DELETE FROM Reimbursement_Requests WHERE request_id = ?";
        try(Connection conn = DatabaseConnection.getConnection()) {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            LogWrapper.log(this.getClass(), "Reimbursement Deletion Successful.", LogWrapper.Severity.DEBUG);
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
