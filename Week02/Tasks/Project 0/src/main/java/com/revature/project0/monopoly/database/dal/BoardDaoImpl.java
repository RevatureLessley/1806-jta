package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.beans.BoardPartial;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.sql.*;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class is the DAO on behalf of the Board Class
 */
public class BoardDaoImpl implements BoardDao{

    /**
     * Deletes the Board data from the database
     * @param gameId the ID of the Board being deleted
     */
    @Override
    public void deleteBoard(int gameId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM Boards WHERE game_state_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId);
            pStatement.execute();

            LogWrapper.log(this.getClass(), "Board deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * Inserts the Board into the Database
     * @param board the Board being inserted
     * @param gameId the Game the Board is tied to
     * @return true if successful, false otherwise
     */
    @Override
    public boolean setBoard(Board board, int gameId) {
        PreparedStatement pStatement = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Boards VALUES (?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId);
            pStatement.setInt(2, gameId);

            pStatement.execute();
            LogWrapper.log(this.getClass(), "Board inserted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
            return false;
        }
        finally{
            close(pStatement);
        }
        return true;
    }

    /**
     * Retrieves the Board object from the database
     * @return the BoardPartial containing data from the Boards table
     */
    @Override
    public BoardPartial getBoard() {
        Statement statement = null;
        ResultSet resultSet = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Boards";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                //NOTE: This will only return the first record in the Table
                return new BoardPartial(
                        resultSet.getInt("board_id"),
                        resultSet.getInt("game_state_id")
                );

            }
            LogWrapper.log(this.getClass(), "This should be unreachable code.", LogWrapper.Severity.WARN);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(statement);
            close(resultSet);
        }
        return null;
    }

}
