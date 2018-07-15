package com.revature.dal;

import com.revature.beans.EmployeeBean;
import com.revature.beans.EventTypeBean;
import com.revature.beans.ReimbursementBean;
import com.revature.utils.DatabaseConnection;
import com.revature.utils.LogWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.revature.utils.CloseStreams.close;

public class ReimbursementTypeDaoImpl implements ReimbursementTypeDao{

    @Override
    public EventTypeBean selectEventType(int type) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Reimbursement_Types WHERE reimbursement_id = ?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, type);
            rs = statement.executeQuery();
            if (rs.next()){     //if okay because expecting one result
                EventTypeBean bean = new EventTypeBean(
                        rs.getInt("reimbursement_id"),
                        rs.getString("reimbursement_type"),
                        rs.getInt("reimbursement_percent")
                );
                LogWrapper.log(this.getClass(), "Retrieve Reimbursement Type Successful", LogWrapper.Severity.DEBUG);
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
}
