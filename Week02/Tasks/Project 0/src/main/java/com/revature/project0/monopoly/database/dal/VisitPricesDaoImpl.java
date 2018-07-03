package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.database.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

import static com.revature.project0.monopoly.database.utilities.CloseStreams.close;

/**
 * This class accesses the database on behalf of an array of prices
 */
public class VisitPricesDaoImpl implements VisitPricesDao{

    /**
     * deletes all price data of a BoardSquare from the database
     * @param squareId the BoardSquare to index by
     */
    @Override
    public void deleteVisitPricesFromGame(int squareId) {
        PreparedStatement pStatement = null;

        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM Visit_Prices WHERE board_square_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, squareId);
            pStatement.execute();
            LogWrapper.log(this.getClass(), "Prices deleted successfully.", LogWrapper.Severity.DEBUG);

        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally {
            close(pStatement);
        }
    }

    /**
     * Inserts the price data into the database
     * @param squareId the Boardsquare it belongs to
     * @param prices the array being inserted
     * @return true if successful, false if not
     */
    @Override
    public boolean setVisitPricesForSquare(int squareId, int[] prices) {
        PreparedStatement pStatement = null;

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Visit_Prices VALUES (?,?,?,?,?,?,?,?)";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, squareId);
            pStatement.setInt(2, squareId);
            if (prices == null){
                pStatement.setNull(3, Types.INTEGER);
                pStatement.setNull(4, Types.INTEGER);
                pStatement.setNull(5, Types.INTEGER);
                pStatement.setNull(6, Types.INTEGER);
                pStatement.setNull(7, Types.INTEGER);
                pStatement.setNull(8, Types.INTEGER);
            }
            else if (prices.length == 4){
                pStatement.setInt(3, prices[0]);
                pStatement.setInt(4, prices[1]);
                pStatement.setInt(5, prices[2]);
                pStatement.setInt(6, prices[3]);
                pStatement.setNull(7, Types.INTEGER);
                pStatement.setNull(8, Types.INTEGER);
            }
            else{
                pStatement.setInt(3, prices[0]);
                pStatement.setInt(4, prices[1]);
                pStatement.setInt(5, prices[2]);
                pStatement.setInt(6, prices[3]);
                pStatement.setInt(7, prices[4]);
                pStatement.setInt(8, prices[5]);
            }

            pStatement.execute();
            LogWrapper.log(this.getClass(), "Prices inserted successfully.", LogWrapper.Severity.DEBUG);

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
     * retrieves the price data from the database
     * @param squareId the BoardSquare it belongs to
     * @return the int array of price data
     */
    @Override
    public int[] getVisitPrices(int squareId) {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> prices = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()){
            String sql = "SELECT * FROM Visit_Prices WHERE board_square_id = ?";

            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, squareId);
            resultSet = pStatement.executeQuery();

            while(resultSet.next()) {
                int temp1 = resultSet.getInt("cell_0");
                int temp2 = resultSet.getInt("cell_1");
                int temp3 = resultSet.getInt("cell_2");
                int temp4 = resultSet.getInt("cell_3");
                int temp5 = resultSet.getInt("cell_4");
                int temp6 = resultSet.getInt("cell_5");

                LogWrapper.log(this.getClass(), "Prices retrieved successfully.", LogWrapper.Severity.DEBUG);
                return (temp5 == 0) ? new int[]{temp1,temp2,temp3,temp4} : new int[]{temp1,temp2,temp3,temp4,temp5,temp6};
            }


        } catch (SQLException e) {
            LogWrapper.log(this.getClass(), e);
        }
        finally{
            close(pStatement);
        }
        return null;
    }
}
