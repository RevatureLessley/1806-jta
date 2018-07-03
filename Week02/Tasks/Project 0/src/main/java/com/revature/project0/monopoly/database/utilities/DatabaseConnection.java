package com.revature.project0.monopoly.database.utilities;

import com.revature.project0.monopoly.core.LogWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class handles the actual connecting to the Database.
 * It uses System Environment Variables to look up the necessary
 * database connection information.
 */
public class DatabaseConnection {
    private static Connection conn = null;

    /**
     * This method gets the connection to the database specified by the
     * User's system environment variable "DatabaseConnectionInfo"
     * @return A java.sql.Connection object representing the connection to the database.
     */
    public static Connection getConnection() {
        try {
            String[] connectionInfo = System.getenv("DatabaseConnectionInfo").split(";");
            Class.forName(connectionInfo[0]);
            conn = DriverManager.getConnection(connectionInfo[1], connectionInfo[2], connectionInfo[3]);
            LogWrapper.log(DatabaseConnection.class, "Connection established successfully.", LogWrapper.Severity.DEBUG);
        }
        catch (ClassNotFoundException | SQLException e) {
            LogWrapper.log(DatabaseConnection.class, e);
        }
        return conn;
    }
}
