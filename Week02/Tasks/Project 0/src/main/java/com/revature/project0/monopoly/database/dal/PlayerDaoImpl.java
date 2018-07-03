package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.core.Player;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class accesses the database on behalf of the Player object
 */
public class PlayerDaoImpl implements PlayerDao {

    /**
     * Deletes all players of a game from the database
     * @param gameId the Game the Players are being deleted from
     */
    @Override
    public void deletePlayers(int gameId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM Players WHERE game_state_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId);
            pStatement.execute();

            LogWrapper.log(this.getClass(), "Player deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * Inserts a player into the database
     * @param player the Player being inserted
     * @param gameId the Game the Player belongs to
     * @return true if successful, false if otherwise
     */
    @Override
    public boolean addPlayer(Player player, int gameId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Players VALUES (?,?,?,?,?,?,?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, player.getId());
            pStatement.setString(2, player.getName());
            pStatement.setString(3, player.getPiece().toString());  //TODO: This might not be the right string...
            pStatement.setInt(4, player.getMoney());
            pStatement.setInt(5, player.getLocation());
            pStatement.setInt(6, player.getIsInJail() ? 1 : 0);
            pStatement.setInt(7, player.hasGetOutOfJailCard() ? 1 : 0);
            pStatement.setInt(8, gameId);

            pStatement.execute();
            LogWrapper.log(this.getClass(), "Player inserted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
            return false;
        }
        finally {
            close(pStatement);

        }
        return true;
    }

    /**
     * retrieves all players (of a game) from the database
     * @param gameId the Game to index the Players
     * @return an ArrayList of Players
     */
    @Override
    public ArrayList<Player> getAllPlayersofGame(int gameId) {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        ArrayList<Player> players = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Players WHERE game_state_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId);
            resultSet = pStatement.executeQuery();

            while(resultSet.next()){
                Player p = new Player(
                        resultSet.getString("player_name"),
                        Board.selectPiece(resultSet.getString("board_piece"))
                );
                p.setId(resultSet.getInt("player_id"));
                p.setMoney(resultSet.getInt("money"));
                p.setLocation(resultSet.getInt("location_id"));
                p.setIsInJail(resultSet.getInt("is_in_jail") != 0);
                p.setHasJailCard(resultSet.getInt("has_jail_card") != 0);
                players.add(p);
            }
            LogWrapper.log(this.getClass(), "Player retrieved successfully.", LogWrapper.Severity.DEBUG);
            return players;

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return null;
    }
}
