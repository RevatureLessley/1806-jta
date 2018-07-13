package com.revature.dal;

import com.revature.beans.BeanFactory;
import com.revature.beans.EmployeeBean;
import com.revature.beans.ReimbursementBean;
import com.revature.utils.DatabaseConnection;
import com.revature.utils.LogWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.revature.utils.CloseStreams.close;

public class GenericDaoImpl<T> {

    public T insert(BeanFactory<T> obj, String tablename, int argCount){
        PreparedStatement statement = null;

        try(Connection conn = DatabaseConnection.getConnection()) {
            StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
            sqlBuilder.append(tablename).append(" VALUES (?");
            for (int i = 1; i < argCount; i++) sqlBuilder.append(",?");
            sqlBuilder.append(")");
            String sql = sqlBuilder.toString();

            statement = conn.prepareStatement(sql);
            //Block for inserting the generic class's fields
            T type = obj.constructor();
            if (type instanceof EmployeeBean){

                statement.setInt(1,((EmployeeBean) type).getId());
                statement.setString(2, ((EmployeeBean) type).getFirstName());
                statement.setString(3, ((EmployeeBean) type).getLastName());
                statement.setLong(4, ((EmployeeBean) type).getPhone());
                statement.setString(5, ((EmployeeBean) type).getEmail());
                statement.setDouble(6, ((EmployeeBean) type).getPendingReimbursements());
                statement.setDouble(7, ((EmployeeBean) type).getAwardedReimbursements());
                statement.setInt(8, ((EmployeeBean) type).getSupervisorId());
                statement.setInt(9, ((EmployeeBean) type).getDeptHeadId());
                statement.setInt(10, ((EmployeeBean) type).getBenCoId());
            }
            //else if(type instanceof ReimbursementBean){}
            //add more else ifs for each bean
            else LogWrapper.log(this.getClass(), "Unknown Object Class \'" +obj.toString()+ "\"", LogWrapper.Severity.WARN);
            //end Block
        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }finally {
            close(statement);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private T getObject(BeanFactory<T> obj){
        T type = obj.constructor();
        if (type instanceof EmployeeBean) type = (T)new EmployeeBean(1,"firs","las","pas", 100L,"emai",-4,101,3,1244,9);
        else LogWrapper.log(this.getClass(), "Unknown Object Class \'" +obj.toString()+ "\"", LogWrapper.Severity.WARN);
        return type;
    }
}
