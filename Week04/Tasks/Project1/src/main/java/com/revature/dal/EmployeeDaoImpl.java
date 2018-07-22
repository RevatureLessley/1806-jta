package com.revature.dal;

import com.revature.beans.EmployeeBean;
import com.revature.utils.DatabaseConnection;
import com.revature.utils.LogWrapper;

import java.sql.*;
import java.util.ArrayList;

import static com.revature.utils.CloseStreams.close;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public EmployeeBean retrieveEmployeeById(int id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Employees a INNER JOIN Clearances b ON a.employee_id = b.employee_id WHERE a.employee_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()){ //if instead of while because expecting only one result.
                EmployeeBean bean = new EmployeeBean(
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
                );
                LogWrapper.log(this.getClass(), "Retrieve Employee Successful", LogWrapper.Severity.DEBUG);
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
    public ArrayList<EmployeeBean> retrieveEmployeesByEmail(String email) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Employees a INNER JOIN Clearances b ON a.employee_id = b.employee_id WHERE a.email = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            rs = statement.executeQuery();
            ArrayList<EmployeeBean> beanList = new ArrayList<>();
            while (rs.next()){
                EmployeeBean bean = new EmployeeBean(
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
                );
                beanList.add(bean);
            }
            LogWrapper.log(this.getClass(), "Retrieve Employees Successful", LogWrapper.Severity.DEBUG);
            return beanList;
        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
            close(rs);
        }
        return null;

    }

    @Override
    public ArrayList<EmployeeBean> retrieveAllEmployees() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Employees a INNER JOIN Clearances b ON a.employee_id = b.employee_id";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            ArrayList<EmployeeBean> beanList = new ArrayList<>();
            while (rs.next()){
                EmployeeBean bean = new EmployeeBean(
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
                );
                beanList.add(bean);
            }
            LogWrapper.log(this.getClass(), "Retrieve Employees Successful", LogWrapper.Severity.DEBUG);
            return beanList;
        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
            close(rs);
        }
        return null;

    }

    @Override
    public boolean insertEmployee(EmployeeBean bean) {
        CallableStatement statement = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "{call Insert_Employee(?,?,?,?,?,?,?,?,?,?,?)}";

            statement = conn.prepareCall(sql);
            if (bean.getId() > 0) statement.setInt(1, bean.getId());
            else statement.setNull(1, Types.INTEGER);
            statement.setString(2, bean.getFirstName());
            statement.setString(3, bean.getLastName());
            statement.setString(4, bean.getPassword());
            statement.setLong(5, bean.getPhone());
            statement.setString(6, bean.getEmail());
            statement.setDouble(7, bean.getPendingReimbursements());
            statement.setDouble(8, bean.getAwardedReimbursements());
            statement.setInt(9, bean.getSupervisorId());
            statement.setInt(10, bean.getDeptHeadId());
            statement.setInt(11, bean.getBenCoId());
            statement.execute();
            LogWrapper.log(this.getClass(), "Insert Employee Successful", LogWrapper.Severity.DEBUG);
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
    public boolean deleteEmployeeById(int id) {
        CallableStatement statement = null;
        String sql = "{call Delete_Employee(?)}";
        try(Connection conn = DatabaseConnection.getConnection()) {
            statement = conn.prepareCall(sql);
            statement.setInt(1, id);
            statement.execute();
            LogWrapper.log(this.getClass(), "Employee Deletion Successful.", LogWrapper.Severity.DEBUG);
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