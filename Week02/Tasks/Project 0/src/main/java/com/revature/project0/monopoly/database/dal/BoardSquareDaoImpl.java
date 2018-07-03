package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.beans.BoardSquarePartial;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.sql.*;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class accesses the database on behalf of the BoardSquare class
 */
public class BoardSquareDaoImpl implements BoardSquareDao{

    /**
     * This deletes all BoardSquare data
     * @param gameId the Game ID of the squares being removed
     * @param squareId this shouldn't exist TODO?
     */
    @Override
    public void deleteAllBoardSquaresFromGame(int gameId, int squareId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM Board_Squares WHERE board_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId+1);     //TODO: Hardcode. (-1)
           //pStatement.setInt(2, squareId);
            pStatement.execute();

            LogWrapper.log(this.getClass(), "BoardSquares deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * This method inserts a BoardSquare into the database
     * @param square the square being inserted
     * @param id the unique id of the square
     * @param boardId the board this square belongs to
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addBoardSquare(Board.BoardSquare square, int id, int boardId) {
        PreparedStatement pStatement = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Board_Squares VALUES (?,?,?,?,?,?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            pStatement.setInt(2, boardId);
            pStatement.setString(3, square.getName());
            pStatement.setInt(4, square.getBuyPrice());
            if (square.getOwner() == null) pStatement.setNull(5, Types.INTEGER);
            else pStatement.setInt(5, square.getOwner().getId());
            pStatement.setInt(6, square.getExpansions());
            pStatement.setInt(7, square.getMortgageValue());

            pStatement.execute();
            LogWrapper.log(this.getClass(), "BoardSquare added successfully.", LogWrapper.Severity.DEBUG);

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
     * retrieves all the BoardSquare data contained in the Board_Squares table
     * @param squareId the unique ID of the square
     * @return the BoardSquarePartial containing the metadata
     */
    @Override
    public BoardSquarePartial getBoardSquare(int squareId) {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Board_Squares WHERE square_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, squareId);
            resultSet = pStatement.executeQuery();

            while(resultSet.next()) {
                return new BoardSquarePartial(
                        resultSet.getInt("square_id"),
                        resultSet.getInt("board_id"),
                        resultSet.getString("square_name"),
                        resultSet.getInt("buy_price"),
                        resultSet.getInt("owner_id"),
                        resultSet.getInt("expansion_count"),
                        resultSet.getInt("mortgage_value")
                );
            }
            LogWrapper.log(this.getClass(), "This should be unreachable code", LogWrapper.Severity.WARN);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally{
            close(pStatement);
        }
        return null;
    }
}
