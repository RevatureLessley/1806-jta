package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.database.beans.BoardSquarePartial;

public interface BoardSquareDao {
    boolean addBoardSquare(Board.BoardSquare square, int id, int boardId);
    BoardSquarePartial getBoardSquare(int squareId);
    void deleteAllBoardSquaresFromGame(int gameId, int squareId);
}
