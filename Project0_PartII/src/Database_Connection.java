import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connection
{
    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "bankDBA",
                    "banking");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.err.println("Connection FAILED");
        }

        return null;
    }
}
