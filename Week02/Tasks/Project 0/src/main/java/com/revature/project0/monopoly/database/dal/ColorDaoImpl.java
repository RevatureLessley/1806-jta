package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.awt.*;
import java.sql.*;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class accesses the Database on behalf of the Color class
 */
public class ColorDaoImpl implements ColorDao{

    /**
     * deletes all the colors from this game
     * @param gameId the game the colors are being deleted from
     */
    @Override
    public void deleteColorsFromGame(int gameId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM Colors WHERE board_square_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, gameId);
            pStatement.execute();

            LogWrapper.log(this.getClass(), "Color deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * retrieves a color from the database
     * @param squareId the BoardSquare the color belongs to
     * @return The color object contained from the table
     */
    @Override
    public Color getColor(int squareId) {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Colors WHERE board_square_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, squareId);
            resultSet = pStatement.executeQuery();

            while(resultSet.next()) {
                return new Color(
                        resultSet.getInt("red"),
                        resultSet.getInt("green"),
                        resultSet.getInt("blue")
                );
            }

            LogWrapper.log(this.getClass(), "This should be unreacheable code.", LogWrapper.Severity.WARN);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally{
            close(pStatement);
        }
        return null;
    }

    /**
     *  this method inserts into the database a color
     * @param squareId the BoardSquare from whom which the color belongs
     * @param color the color being inserted
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addColor(int squareId, Color color) {
        PreparedStatement pStatement = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Colors VALUES (?,?,?,?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, squareId);
            pStatement.setInt(2, squareId);
            if(color != null) {
                pStatement.setInt(3, color.getRed());
                pStatement.setInt(4, color.getGreen());
                pStatement.setInt(5, color.getBlue());
            }
            else {
                pStatement.setNull(3, Types.INTEGER);
                pStatement.setNull(4, Types.INTEGER);
                pStatement.setNull(5, Types.INTEGER);
            }
            pStatement.execute();

            LogWrapper.log(this.getClass(), "Color inserted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
            return false;
        }
        finally{
            close(pStatement);
        }
        return true;
    }
}
