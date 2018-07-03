package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.GameState;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.beans.GameStatePartial;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.sql.*;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class accesses the database on behalf of the GameState
 */
public class GameStateDaoImpl implements GameStateDao{

    /**
     * This method deletes the GameState from the database
     * @param gameId the Game being deleted
     */
    @Override
    public void deleteGameState(int gameId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM Game_States WHERE game_state_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId);
            pStatement.execute();

            LogWrapper.log(this.getClass(), "GameState deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * Adds a GameState to the database
     * @param state the GameState being saved
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addGameState(GameState state){
        PreparedStatement pStatement = null;
        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Game_States VALUES (?,?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, state.getIdNum());
            pStatement.setInt(2, state.getPlayerTurn());
            pStatement.setInt(3, state.getJackpotValue());

            pStatement.executeUpdate();
            LogWrapper.log(this.getClass(), "GameState inserted successfully.", LogWrapper.Severity.DEBUG);

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
     * Updates an already existing GameState
     * @param state the GameState being updated
     * @return true if successful, false otherwise
     */
    @Override
    public boolean setGameState(GameState state) {
        PreparedStatement pStatement = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "UPDATE Game_States " +
                    "SET " +
                    "player_turn = ?," +
                    "jackpot_value = ?" +
                    "WHERE game_state_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, state.getPlayerTurn());
            pStatement.setInt(2, state.getJackpotValue());
            pStatement.setInt(3, state.getIdNum());

            pStatement.executeUpdate();
            LogWrapper.log(this.getClass(), "GameState updated successfully.", LogWrapper.Severity.DEBUG);

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
     * Retrieves the GameState from the database
     * @return The partial data retrieved.
     */
    @Override
    public GameStatePartial getGameStateData() {
        Statement statement = null;
        ResultSet resultSet = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Game_States";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                //NOTE: This will only return the first record in the Table
                return new GameStatePartial(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                );

            }
            LogWrapper.log(this.getClass(), "This should be unreacheable code.", LogWrapper.Severity.WARN);

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
