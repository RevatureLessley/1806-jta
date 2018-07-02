import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App
{
    public static void main(String[] args) {
        Connection connection = Database_Connection.getConnection();

        UserInterface userInterface = new UserInterface();

        userInterface.mainMenu();


    }

}

