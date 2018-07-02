import java.sql.*;

public class RecordsManager_new
{
    public RecordsManager_new(AccountsRecord accountsRecord)
    {
        //Setup database connection
        //Initialize AccountRecord with accounts

        ResultSet rs = null; //Object that holds query results
        accountsRecord.clearRecords();

        try(Connection connection = Database_Connection.getConnection())
        {
            String sql = "SELECT * FROM Accounts";

            rs = connection.createStatement().executeQuery(sql);
            Account newAccount;
            String username, fName, lName, password, address;
            boolean role, approvalStatus;
            while(rs.next()){
                username = rs.getString(1);
//                String fName = rs.getString(2);
//                String lName = rs.getString(3);
                password = rs.getString(4);
//                address = rs.getString(5);
                role = (rs.getInt(6) == 0? false: true);
                approvalStatus = (rs.getInt(7) == 0? false: true);

                System.out.println(String.format("%s %s %b %b", username, password, role, approvalStatus));
                newAccount = new Account(username, password, role, approvalStatus);
                accountsRecord.addAccount(newAccount);
            }

            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createAccount(Account account)
    {
        Statement statement; //Object that holds query results

        try(Connection connection = Database_Connection.getConnection())
        {

            statement = connection.createStatement();

            String sql = "INSERT INTO Accounts (username, pass, roleID, approvalID)" +
                        "VALUES ('" + account.getUsername() + "', '"
                                    + account.getPassword() + "', "
                                    + (account.isAdmin()? 1: 0) + ", "
                                    + (account.isApproved()? 1: 0) + ")";

            statement.executeQuery(sql);

            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateBalance(Account account)
    {
        Statement statement = null; //Object that holds query results
        ResultSet rs;

        try(Connection connection = Database_Connection.getConnection())
        {
            String sql;
            statement = connection.createStatement();

            //Check if account exists

            sql = "SELECT COUNT(*) from AccountBalance WHERE username = '" + account.getUsername() + "'";
            rs = statement.executeQuery(sql);

            rs.next();
            int colCount = rs.getInt(1);

            if(colCount > 0) //It exists
            {
                System.out.println("It exists in the table");
                sql = "UPDATE AccountBalance " +
                        "SET balance = " + (int)account.getAccountBalance() +
                        " WHERE username = '" + account.getUsername() + "'";
            }
            else        //It doesn't exist, insert
            {
                System.out.println("Inserting!");
                sql = "INSERT INTO AccountBalance (username, balance)" +
                        "VALUES ('" + account.getUsername() + "', "
                        + (int)account.getAccountBalance() + ")";
            }
            statement.executeQuery(sql);

            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
