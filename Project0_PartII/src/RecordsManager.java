import java.sql.*;

public class RecordsManager
{
    public RecordsManager(AccountsRecord accountsRecord)
    {
        //Setup database connection
        //Initialize AccountRecord with accounts

        Statement statement;
        ResultSet rs = null; //Object that holds query results
        accountsRecord.clearRecords();

        try(Connection connection = Database_Connection.getConnection())
        {
            String sql = "SELECT * FROM Accounts";

            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            Account newAccount;
            String username, fName, lName, password, address;
            boolean role, approvalStatus;
            while(rs.next())
            {
                username = rs.getString(1);
//                String fName = rs.getString(2);
//                String lName = rs.getString(3);
                password = rs.getString(4);
//                address = rs.getString(5);
                role = (rs.getInt(6) == 0? false: true);
                approvalStatus = (rs.getInt(7) == 0? false: true);

                newAccount = new Account(username, password, role, approvalStatus);

                String query = "{? = call getBalance(?)}";

                CallableStatement callstatement = connection.prepareCall(query);
                callstatement.registerOutParameter(1, Types.NUMERIC);
                callstatement.setString(2, username);
                callstatement.execute();
                double balance = callstatement.getInt(1);

                newAccount.setAccountBalance(balance);

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

            sql = "INSERT INTO AccountBalance (username, balance)" +
                    "VALUES ('" + account.getUsername() + "', "
                                + account.getAccountBalance() + ")";

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
//        Statement statement = null; //Object that holds query results
        ResultSet rs;

        try(Connection connection = Database_Connection.getConnection())
        {
//            String sql;

            String query = "{? = call checkIfExists(?)}";
            String username = account.getUsername();

            CallableStatement statement = connection.prepareCall(query);
            statement.registerOutParameter(1, Types.NUMERIC);
            statement.setString(2, username);
            statement.execute();
            int colCount = statement.getInt(1);


            //Check if account exists

            if(colCount > 0) //It exists
            {
//                System.out.println("It exists in the table");
                query = "UPDATE AccountBalance " +
                        "SET balance = " + (int)account.getAccountBalance() +
                        " WHERE username = '" + account.getUsername() + "'";
            }
            else        //It doesn't exist, insert
            {
//                System.out.println("Inserting!");
                query = "INSERT INTO AccountBalance (username, balance)" +
                        "VALUES ('" + account.getUsername() + "', "
                        + (int)account.getAccountBalance() + ")";
            }
            statement.executeQuery(query);

            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateApprovalStatus()
    {

        try(Connection connection = Database_Connection.getConnection())
        {
            String query = "{call updateApprovalStatus}";
            CallableStatement statement = connection.prepareCall(query);
            statement.execute();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
