package com.revature.project0.monopoly.database.dal;

import java.util.ArrayList;

public interface VisitPricesDao {
    boolean setVisitPricesForSquare(int squareId, int[] prices);
    int[] getVisitPrices(int squareId);
    void deleteVisitPricesFromGame(int squareId);
}
