package com.revature.dal;

import com.revature.beans.EmployeeBean;
import com.revature.beans.ReimbursementBean;
import com.revature.utils.DatabaseConnection;
import com.revature.utils.LogWrapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;

import static com.revature.utils.CloseStreams.close;

/**
 * The Data Access Object (DAO) accessing the Database on behalf of Reimbursement Requests Beans
 */
public class ReimbursementDaoImpl implements ReimbursementDao {

    /**
     * Accesses the database and retrieves all Reimbursement Requests belonging to an Employee
     * @param employeeId the ID of the Employee
     * @return an ArrayList of Reimbursement Beans
     */
    @Override
    public ArrayList<ReimbursementBean> retrieveReimbursementsByEmployee(int employeeId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Complete_Employee a INNER JOIN Reimbursement_Requests b ON a.employee_id = b.employee_id WHERE b.employee_id = ?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, employeeId);
            rs = statement.executeQuery();
            ArrayList<ReimbursementBean> beanList = new ArrayList<>();
            while (rs.next()){
                Reader reader = rs.getCharacterStream("optional_file");
                int i;
                StringBuilder sb = new StringBuilder();
                if (reader != null) while((i = reader.read()) != -1) sb.append((char)i);
                ReimbursementBean bean = new ReimbursementBean(
                        rs.getInt("request_id"),
                        new EmployeeBean(
                                rs.getInt("employee_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("password"),
                                rs.getLong("phone"),
                                rs.getString("email"),
                                rs.getDouble("pending_reimbursement"),
                                rs.getDouble("awarded_reimbursement"),
                                rs.getInt("dir_supervisor_id"),
                                rs.getInt("dept_head_id"),
                                rs.getInt("ben_co_id")
                        ),
                        rs.getDate("date_requested").toLocalDate(),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getDouble("reimbursement"),
                        rs.getString("grading_format"),
                        rs.getInt("event_type"),
                        rs.getInt("status"),
                        sb.toString(),
                        rs.getString("file_name")
                );
                beanList.add(bean);
            }
            LogWrapper.log(this.getClass(), "Retrieve Reimbursement Request Successful", LogWrapper.Severity.DEBUG);
            return beanList;
        } catch (SQLException | IOException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
            close(rs);
        }
        return null;

    }

    /**
     * Accesses the database and retrieves a Reimbursement by its unique ID
     * @param id the ID of the Reimbursement Request
     * @return the Reimbursement bean
     */
    @Override
    public ReimbursementBean retrieveReimbursementFormById(int id){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Complete_Employee a INNER JOIN Reimbursement_Requests b ON a.employee_id = b.employee_id WHERE b.request_id = ?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()){ //if instead of while because expecting only one result.
                Reader reader = rs.getCharacterStream("optional_file");
                int i;
                StringBuilder sb = new StringBuilder();
                if (reader != null) while((i = reader.read()) != -1) sb.append((char)i);
                ReimbursementBean bean = new ReimbursementBean(
                        rs.getInt("request_id"),
                        new EmployeeBean(
                                rs.getInt("employee_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("password"),
                                rs.getLong("phone"),
                                rs.getString("email"),
                                rs.getDouble("pending_reimbursement"),
                                rs.getDouble("awarded_reimbursement"),
                                rs.getInt("dir_supervisor_id"),
                                rs.getInt("dept_head_id"),
                                rs.getInt("ben_co_id")
                        ),
                        rs.getDate("date_requested").toLocalDate(),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getDouble("reimbursement"),
                        rs.getString("grading_format"),
                        rs.getInt("event_type"),
                        rs.getInt("status"),
                        sb.toString(),
                        rs.getString("file_name")
                );
                LogWrapper.log(this.getClass(), "Retrieve Reimbursement Request Successful", LogWrapper.Severity.DEBUG);
                return bean;
            }
        } catch (SQLException |IOException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
            close(rs);
        }
        return null;

    }

    /**
     * Accesses the database and Inserts a Reimbursement Request
     * @param bean the Reimbursement Bean containing the data being stored
     * @param supervisorId the requestor's supervisor (Database Stored Procedure creates a Notification in tandem)
     * @return true if insertion was successful, false otherwise
     */
    @Override
    public boolean insertReimbursementForm(ReimbursementBean bean, int supervisorId) {
        CallableStatement statement = null;

        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "{call Insert_Reimbursement_Request(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setNull(1, Types.INTEGER);
            statement.setInt(2, bean.getEmployee().getId());
            statement.setDate(3, java.sql.Date.valueOf(bean.getDate())); //date
            statement.setString(4, bean.getLocation());
            statement.setString(5, bean.getDescription());
            statement.setDouble(6, bean.getReimbursement());
            statement.setString(7, bean.getGradingFormat());
            statement.setInt(8, bean.getEventType());
            statement.setInt(9, bean.getStatus());
            statement.setCharacterStream(10, new StringReader(bean.getFileUrl()));
            statement.setString(11, bean.getFileName());
            statement.setNull(12, Types.INTEGER);
            statement.setInt(13, supervisorId);
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

    /**
     * Accesses the database and Updates the Reimbursement with the new Status information
     * @param id the Reimbursement's ID
     * @param status The Status. 0 = Pending, 1 = Approved, 2 = Denied
     * @return true if update was successful, false otherwise
     */
    @Override
    public boolean updateReimbursementStatus(int id, int status) {
        PreparedStatement statement = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "UPDATE Reimbursement_Requests SET status=? WHERE request_id=?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, id);
            statement.execute();
            LogWrapper.log(this.getClass(), "Update Reimbursement Request Successful", LogWrapper.Severity.DEBUG);
            return true;

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(statement);
        }
        return false;
        
    }

    /**
     * Accesses the database and deletes the Reimbursement Request
     * @param id the ID of the Reimbursement being deleted
     * @return true if delete was successful, false otherwise
     */
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
