package com.revature.project0.monopoly.database.utilities;

import com.revature.project0.monopoly.core.LogWrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class to close streams
 */
public class CloseStreams {

    public static void close(Statement resource){
        if(resource!=null){
            try{
                resource.close();
            }catch(SQLException e){
                LogWrapper.log(CloseStreams.class, e);
            }
        }
    }
    public static void close(ResultSet resource){
        if(resource!=null){
            try{
                resource.close();
            }catch(SQLException e){
                LogWrapper.log(CloseStreams.class, e);
            }
        }
    }
    public static void close(FileInputStream resource){
        if(resource!=null){
            try{
                resource.close();
            }catch(IOException e){
                LogWrapper.log(CloseStreams.class, e);
            }
        }
    }
    public static void close(Connection resource){
        if(resource!=null){
            try{
                resource.close();
            }catch(SQLException e){
                LogWrapper.log(CloseStreams.class, e);
            }
        }
    }

}
