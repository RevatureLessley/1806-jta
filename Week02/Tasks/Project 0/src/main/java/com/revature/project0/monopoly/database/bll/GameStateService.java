package com.revature.project0.monopoly.database.bll;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.core.GameState;
import com.revature.project0.monopoly.core.LogWrapper;
import com.revature.project0.monopoly.core.Player;
import com.revature.project0.monopoly.database.beans.BoardPartial;
import com.revature.project0.monopoly.database.beans.BoardSquarePartial;
import com.revature.project0.monopoly.database.beans.GameStatePartial;
import com.revature.project0.monopoly.database.dal.*;
import com.revature.project0.monopoly.database.utilities.DatabaseWipe;

import java.util.ArrayList;

/**
 * This class acts as the in-between the DAO and the Application.
 */
public class GameStateService {

    /**
     * Requests the DAO to grab the gamestate from the Database
     * @return the GameState retrieved.
     */
    public GameState getGameState(){    //TODO: there really should be an ID here
        GameState gameState = new GameState(false);

        GameStatePartial gsPartial = new GameStateDaoImpl().getGameStateData();
        if (gsPartial == null) return null;
        ArrayList<Player> playerlist = new PlayerDaoImpl().getAllPlayersofGame(gsPartial.getGameStateId());
        ColorDaoImpl colorDao = new ColorDaoImpl();
        VisitPricesDaoImpl visitPricesDao = new VisitPricesDaoImpl();

        Board board = new Board();
        board.initBoard();

        BoardPartial bPartial = new BoardDaoImpl().getBoard();
        ArrayList<Board.BoardSquare> squares = new ArrayList<>();
        for (int i = 0; i < 40; i++){   //TODO: Hardcode. please fix
            BoardSquarePartial bsPartial = new BoardSquareDaoImpl().getBoardSquare(i);
            Board.BoardSquare square = board.getBoardSquare(bsPartial.getSquareId());
            square.setName(bsPartial.getName());
            square.setOwner(matchIdToPlayer(bsPartial.getOwnerId(), playerlist));
            square.setExpansions(bsPartial.getExpansionCount());
            square.setMortgageValue(bsPartial.getMortgageValue());
            square.setBuyPrice(bsPartial.getBuyPrice());
            square.setColor(colorDao.getColor(bsPartial.getSquareId()));
            square.setMortgageCost(bsPartial.getMortgageValue());
            square.setVisitPrices(visitPricesDao.getVisitPrices(bsPartial.getSquareId()));
            squares.add(square);
        }
        board.updateBoardSquareMetadata(squares);

        for(Player p : playerlist) p.setOwnedProperties(new PlayerPropertyDaoImpl().getAllPropertiesOfPlayer(p.getId()));


        gameState.setGameState(
                playerlist,
                board,
                gsPartial.getPlayerTurn(),
                gsPartial.getJackpotValue()
        );

        return gameState;
    }

    /**
     * Passes on the GameState to be inserted by the DAO
     * @param state the GameState being passed on
     * @return true if successful, false if not.
     */
    public boolean setGameState(GameState state){    //TODO: Full GameState
        boolean success = true;
        //NOTE: Order matters, to avoid foreign key integrity constraint violations.
        if (!new GameStateDaoImpl().addGameState(state)) success = false;
        if (!new BoardDaoImpl().setBoard(state.getBoard(), state.getIdNum())) success = false;
        //TODO: put a log between each one of these to see where it errs

        for (int i = 0; i < 40; i++){   //TODO: hardcode. get rid of it

            if (!new BoardSquareDaoImpl().addBoardSquare(state.getBoard().getBoardSquare(i), i, state.getIdNum())) success = false;
            if (!new ColorDaoImpl().addColor(i, state.getBoard().getBoardSquare(i).getColor())) success = false;
            if (!new VisitPricesDaoImpl().setVisitPricesForSquare(i, state.getBoard().getBoardSquare(i).getAllVisitPrices())) success = false;
        }
        for (Player p : state.getPlayers()){
            if (!new PlayerDaoImpl().addPlayer(p, state.getIdNum())) success = false;
            for (Board.BoardSquare square : p.getOwnedProperties()){
                if (!new PlayerPropertyDaoImpl().addProperty(p.getId(), square.getLocation(), p.isPropertyMortgaged(square))) success = false;
            }
        }
        if (success)LogWrapper.log(this.getClass(), "setGameState() finished successfully.", LogWrapper.Severity.DEBUG);
        else LogWrapper.log(this.getClass(), "setGameState() finished unsuccessfully!", LogWrapper.Severity.WARN);
        return success;
    }

    public void deleteGame(int gameId){
        //NOTE: Order matters.
//        new PlayerPropertyDaoImpl().deleteMetaDataFromGame(gameId);
//        for (int i = 0; i < 40; i++) {
//            new ColorDaoImpl().deleteColorsFromGame(i);
//            new VisitPricesDaoImpl().deleteVisitPricesFromGame(i);
//            new BoardSquareDaoImpl().deleteAllBoardSquaresFromGame(gameId,i);
//        }
//        new PlayerDaoImpl().deletePlayers(gameId);
//        new BoardDaoImpl().deleteBoard(gameId);
//        new GameStateDaoImpl().deleteGameState(gameId);
        LogWrapper.log(this.getClass(), "Commencing database purge.");
        DatabaseWipe.wipe();
    }

    private Player matchIdToPlayer(int id, ArrayList<Player> list){
        if (id ==0) return null; //SQL.NULL = 0, player.getId guarenteed to not be 0.
        for (Player p : list) {
            if (id == p.getId()) return p;
        }
        return null;    //wasn't in the playerlist.
    }
}
