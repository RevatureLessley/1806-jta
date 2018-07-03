package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.database.beans.BoardPartial;

public interface BoardDao {
    boolean setBoard(Board board, int gameId);
    BoardPartial getBoard();
    void deleteBoard(int gameId);
}
