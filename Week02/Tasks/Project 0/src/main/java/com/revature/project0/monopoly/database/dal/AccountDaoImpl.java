package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Account;
import com.revature.project0.monopoly.core.AccountContainer;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class is used to access the database on behalf of Accounts
 */
public class AccountDaoImpl implements AccountDao{

    /**
     * Retrieves all accounts from the database
     * @return ArrayList of Account objects
     */
    @Override
    public ArrayList<Account> getAllAccounts() {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Account> list = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()){   //autocloses!
            String sql = "SELECT * FROM Accounts";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){   //gets and increments
                //1-Based Indexing
                Account account = new Account(
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                list.add(account);
            }
            LogWrapper.log(this.getClass(), "Accounts retrieved successfully.", LogWrapper.Severity.DEBUG);
        }
        catch (SQLException e){
            LogWrapper.log(this.getClass(), e);
        }
        finally{
            close(statement);
            close(resultSet);
        }
        return list;
    }

    /**
     * Inserts an account into the database
     * @param account the Account being inserted
     * @return true if successful, false if not
     */
    @Override
    public boolean addAccount(Account account) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Accounts " +
                    "VALUES (?,?,?)";

            pStatement = connection.prepareStatement(sql);
            //1-based indexing
            pStatement.setString(1, null);
            pStatement.setString(2, account.getUsername());
            pStatement.setString(3, account.getPassword());

            pStatement.execute();
            LogWrapper.log(this.getClass(), "Account added successfully.", LogWrapper.Severity.DEBUG);
        }
        catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
            return false;
        }
        finally {
            close(pStatement);
        }
        return true;
    }
}
