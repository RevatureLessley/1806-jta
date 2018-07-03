package com.revature.project0.monopoly.database.dal;

import java.awt.*;

public interface ColorDao {

    Color getColor(int squareId);
    boolean addColor(int squareId, Color color);
    void deleteColorsFromGame(int squareId);
}
