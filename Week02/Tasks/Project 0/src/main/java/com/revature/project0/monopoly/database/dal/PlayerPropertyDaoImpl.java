package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.core.Player;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class accesses the database to insert the contents of a Map
 */
public class PlayerPropertyDaoImpl implements PlayerPropertyDao{

    /**
     * Deletes all the data of the Property info of a game
     * @param gameId the Game to index by
     */
    @Override
    public void deleteMetaDataFromGame(int gameId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            //TODO: THIS IS REALLY BAD. Figure out how to delete based on gameId
            String sql = "DELETE FROM Player_Properties";
            pStatement = connection.prepareStatement(sql);
            pStatement.execute();

            LogWrapper.log(this.getClass(), "Properties deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * Inserts a property into the database
     * @param playerId the Player it belongs to
     * @param propertyId the unique ID for this property
     * @param isMortgaged whether it is mortgaged or not
     * @return true if successful, false if not
     */
    @Override
    public boolean addProperty(int playerId, int propertyId, boolean isMortgaged) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Player_Properties VALUES (?,?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, playerId);
            pStatement.setInt(2, propertyId);
            pStatement.setInt(3, isMortgaged ? 1 : 0);

            pStatement.execute();

            LogWrapper.log(this.getClass(), "Property inserted successfully.", LogWrapper.Severity.DEBUG);

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
     * gets the HashMap data of a Player's owned properties
     * @param playerId the Player the Properties belong to
     * @return the HashMap containing a Player's property data
     */
    @Override
    public HashMap<Board.BoardSquare, Boolean> getAllPropertiesOfPlayer(int playerId) {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        HashMap<Board.BoardSquare, Boolean> map = new HashMap<>();

        try(Connection connection = DatabaseConnection.getConnection()){
            //NOTE: SQL advises against string concatenation in its queries, so, no line breaks.
            String sql = "SELECT b_s.square_name, b_s.square_id, p_p.is_mortgaged, c.red, c.blue, c.green, b_s.buy_price, v_p.CELL_0, v_p.CELL_1, v_p.CELL_2, v_p.CELL_3, v_p.CELL_4, v_p.CELL_5, b_s.EXPANSION_COUNT, b_s.MORTGAGE_VALUE, p.PLAYER_NAME FROM Player_Properties p_p INNER JOIN Players p ON p_p.player_id = p.player_id INNER JOIN Board_Squares b_s ON p_p.property_id = b_s.square_id  INNER JOIN Colors c ON c.board_square_id = b_s.square_id  INNER JOIN Visit_Prices v_p  ON v_p.board_square_id = b_s.square_id  WHERE p_p.player_id = ?";
//            String sql = "SELECT b_s.square_name, p_p.is_mortgaged, c.red, c.blue, c.green, b_s.buy_price, v_p.CELL_0, v_p.CELL_1, v_p.CELL_2, v_p.CELL_3, v_p.CELL_4, v_p.CELL_5, b_s.EXPANSION_COUNT, b_s.MORTGAGE_VALUE, p.PLAYER_NAME" +
//                    "FROM Player_Properties p_p" +
//                    "INNER JOIN Players p" +
//                    "ON p_p.player_id = p.player_id" +
//                    "INNER JOIN Board_Squares b_s" +
//                    "ON p_p.property_id = b_s.square_id" +
//                    "INNER JOIN Colors c" +
//                    "ON c.board_square_id = b_s.square_id" +
//                    "INNER JOIN Visit_Prices v_p" +
//                    "ON v_p.board_square_id = b_s.square_id" +
//                    "WHERE p_p.player_id = ?";


            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, playerId);
            resultSet = pStatement.executeQuery();

            while(resultSet.next()){
                int[] visitPrices;
                if (resultSet.getInt("cell_4") == 0){
                    visitPrices = new int[]{
                            resultSet.getInt("cell_0"),
                            resultSet.getInt("cell_1"),
                            resultSet.getInt("cell_2"),
                            resultSet.getInt("cell_3")
                    };
                }
                else if(resultSet.getInt("cell_5") != 0){
                    visitPrices = new int[]{
                            resultSet.getInt("cell_0"),
                            resultSet.getInt("cell_1"),
                            resultSet.getInt("cell_2"),
                            resultSet.getInt("cell_3"),
                            resultSet.getInt("cell_4"),
                            resultSet.getInt("cell_5")
                    };
                }
                else visitPrices = null;
                Board.BoardSquare square = new Board.BoardSquare(
                        resultSet.getInt("square_id"),
                        resultSet.getString("square_name"),
                        new Color(resultSet.getInt("red"), resultSet.getInt("green"), resultSet.getInt("blue")),
                        resultSet.getInt("buy_price"),
                        visitPrices
                );
                map.put(square, resultSet.getInt("is_mortgaged") != 0);
                System.out.println(square.getName() +": "+ map.get(square));
            }
            LogWrapper.log(this.getClass(), "Properties retrieved successfully.", LogWrapper.Severity.DEBUG);
            return map;

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
