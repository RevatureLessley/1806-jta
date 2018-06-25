package com.revature.project0.monopoly;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonopolyTest {

    @Test
    public void waitForValidInputTest() {

        //NOTE: The following line returns a NullPointerException because the
        //      Scanner it uses is not initialized.
        //Monopoly.waitForValidInput("message", "input");
    }

    @Test
    public void getPlayerListTest() {
        //NOTE: Cannot test because requires initializing the player list, which requires running main()
        Player player = new Player("Player 1", null);
        Monopoly.getPlayerList();
    }

    @Test
    public void removePlayerTest(){
        //NOTE: Cannot test because requires initializing the player list, which requires running main()
    }
}