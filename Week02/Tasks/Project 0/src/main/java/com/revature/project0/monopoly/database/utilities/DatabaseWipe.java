package com.revature.project0.monopoly.database.utilities;


import com.revature.project0.monopoly.core.LogWrapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class purges the database (except the Accounts)
 */
public class DatabaseWipe {
    /**
     * Deletes all the data in the database, excluding Account data
     * This is so the database is "clean" for entering in more data
     */
    public static void wipe(){
        Statement statement = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql;
            statement = connection.createStatement();
            sql = "DELETE FROM Player_Properties";
            statement.execute(sql);
            sql = "DELETE FROM Players";
            statement.execute(sql);
            sql = "DELETE FROM Visit_Prices";
            statement.execute(sql);
            sql = "DELETE FROM Colors";
            statement.execute(sql);
            sql = "DELETE FROM Board_Squares";
            statement.execute(sql);
            sql = "DELETE FROM Boards";
            statement.execute(sql);
            sql = "DELETE FROM Game_States";
            statement.execute(sql);


            LogWrapper.log(DatabaseWipe.class, "Database wiped successfully.", LogWrapper.Severity.DEBUG);
        } catch (SQLException e) {
            LogWrapper.log(DatabaseWipe.class, e);
        }
        finally {
            close(statement);
        }
    }
}
