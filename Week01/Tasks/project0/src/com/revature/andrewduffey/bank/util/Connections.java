package com.revature.andrewduffey.bank.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
    private static Connection conn = null;
    private final static String DB_PROPS = "dbprops.properties";
    private static Properties prop = null;

    public static Connection getConnection() {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(DB_PROPS));
            Class.forName(prop.getProperty("class"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
