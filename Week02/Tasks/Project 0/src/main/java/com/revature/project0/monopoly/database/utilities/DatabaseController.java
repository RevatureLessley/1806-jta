package com.revature.project0.monopoly.database.utilities;

import com.revature.project0.monopoly.core.Account;
import com.revature.project0.monopoly.core.AccountContainer;
import com.revature.project0.monopoly.core.GameState;
import com.revature.project0.monopoly.database.bll.AccountService;
import com.revature.project0.monopoly.database.bll.GameStateService;

/**
 * This class is the entry point to the Database Package, using the Controller Pattern
 */
public class DatabaseController {

    /**
     * This method starts the process of inputting the Account data into the Database
     * @param account the Account being sent to the database
     */
    public static void addAccount(Account account){
        new AccountService().addAccount(account);
    }

    /**
     * This method starts the process of retrieving AccountContainer data from the Database
     * @return the AccountContainer containing all the Accounts
     */
    public static AccountContainer getAccountContainer() throws DatabaseAccessException{
        AccountContainer container = new AccountService().getAccountContainer();
        if (container == null) throw new DatabaseAccessException();
        return container;
    }

    /**
     * This method starts the process of inputting the GameState into the Database
     * @param state the GameState being saved to the database
     */
    public static void setGameState(GameState state){
        new GameStateService().setGameState(state);
    }

    /**
     * This method starts the process of retrieving GameState data from the Database
     * @return the GameState object in the database
     */
    public static GameState getGameState() {
        return new GameStateService().getGameState();
    }

    public static void deleteOldGameState(int oldGameId){
        new GameStateService().deleteGame(oldGameId);
    }

    public static class DatabaseAccessException extends Exception{

    }

}
