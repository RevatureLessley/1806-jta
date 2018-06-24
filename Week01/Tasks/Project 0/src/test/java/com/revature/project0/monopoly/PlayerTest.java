package com.revature.project0.monopoly;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private final String NAME = "TestName";
    private final Board.BoardPiece PIECE = Board.BoardPiece.RACECAR;
    private Player test;
    @Before
    public void before(){
        test = new Player(NAME, PIECE);
    }

    @Test
    public void moveTest() {
        assertEquals(0, test.getLocation());
        test.move(3);
        assertEquals(3, test.getLocation());
    }

    @Test
    public void getNameTest() {
        assertEquals(NAME, test.getName());
    }

    @Test
    public void setNameTest() {
        String newName = "new name";
        assertEquals(NAME, test.getName());
        test.setName(newName);
        assertEquals(newName, test.getName());
    }

    @Test
    public void getPieceTest() {
        assertNotNull(test.getPiece());
        assertEquals(PIECE, test.getPiece());
    }

    @Test
    public void setPieceTest() {
        assertEquals(PIECE, test.getPiece());
        Board.BoardPiece newPiece = Board.BoardPiece.BATTLESHIP;
        test.setPiece(newPiece);
        assertEquals(newPiece, test.getPiece());
    }

    @Test
    public void getMoneyTest() {
        assertEquals(1500, test.getMoney());
    }

    @Test
    public void setMoneyTest() {
        assertEquals(1500, test.getMoney());
        int newMoney = 756;
        test.setMoney(newMoney);
        assertEquals(newMoney, test.getMoney());
    }

    @Test
    public void getLocationTest() {
        assertEquals(0, test.getLocation());
    }

    @Test
    public void setLocationTest() {
        assertEquals(0, test.getLocation());
        int newLocation = 21;
        test.setLocation(newLocation);
        assertEquals(newLocation, test.getLocation());
    }

    @Test
    public void getOwnedPropertiesTest() {
        Board board = new Board();
        board.initBoard();

        assertEquals(0, test.getOwnedProperties().size());
        test.boughtProperty(board.getBoardSquare(21), false);
        assertEquals(1, test.getOwnedProperties().size());

    }

    @Test
    public void getUnMortgagedPropertiesTest() {
        Board board = new Board();
        board.initBoard();

        assertEquals(0, test.getUnMortgagedProperties().size());
        test.boughtProperty(board.getBoardSquare(21), false);
        assertEquals(1, test.getUnMortgagedProperties().size());
        test.boughtProperty(board.getBoardSquare(23), true);
        assertEquals(1, test.getUnMortgagedProperties().size());
        assertEquals(2, test.getOwnedProperties().size());
    }

    @Test
    public void getMortgagedPropertiesTest() {
        Board board = new Board();
        board.initBoard();

        assertEquals(0, test.getMortgagedProperties().size());
        test.boughtProperty(board.getBoardSquare(21), false);
        assertEquals(0, test.getMortgagedProperties().size());
        test.boughtProperty(board.getBoardSquare(23), true);
        assertEquals(1, test.getMortgagedProperties().size());
        assertEquals(2, test.getOwnedProperties().size());
    }

    @Test
    public void boughtPropertyTest() {
        Board board = new Board();
        board.initBoard();

        assertEquals(0, test.getOwnedProperties().size());
        test.boughtProperty(board.getBoardSquare(21), false);
        assertEquals(1, test.getOwnedProperties().size());
    }

    @Test
    public void getRailRoadCountTest() {
        assertEquals(0, test.getRailRoadCount());

        Board board = new Board();
        board.initBoard();

        test.boughtProperty(board.getBoardSquare(5), false);
        assertEquals(1, test.getRailRoadCount());
        test.boughtProperty(board.getBoardSquare(15), false);
        assertEquals(2, test.getRailRoadCount());
        test.boughtProperty(board.getBoardSquare(25), false);
        assertEquals(3, test.getRailRoadCount());
        test.boughtProperty(board.getBoardSquare(26), false);
        assertEquals(3, test.getRailRoadCount());
        test.boughtProperty(board.getBoardSquare(35), false);
        assertEquals(4, test.getRailRoadCount());
        
    }

    @Test
    public void getUtilityCountTest() {
        assertEquals(0, test.getUtilityCount());

        Board board = new Board();
        board.initBoard();

        test.boughtProperty(board.getBoardSquare(12), false);
        assertEquals(1, test.getUtilityCount());
        test.boughtProperty(board.getBoardSquare(15), false);
        assertEquals(1, test.getUtilityCount());
        test.boughtProperty(board.getBoardSquare(28), false);
        assertEquals(2, test.getUtilityCount());
    }

    @Test
    public void ownsBlockTest() {
        Board board = new Board();
        board.initBoard();
        Board.BoardSquare square = board.getBoardSquare(6);
        test.boughtProperty(square, false);

        assertFalse(test.ownsBlock(board, square));

        test.boughtProperty(board.getBoardSquare(8), false);
        test.boughtProperty(board.getBoardSquare(9), false);
        assertTrue(test.ownsBlock(board, square));
    }

    @Test
    public void owesMoneyTest() {
        //NOTE: cannot test due to NullPointerException on Scanner.
    }

    @Test
    public void mortgageTest() {
        //NOTE: cannot test due to NullPointerException on Scanner.
    }

    @Test
    public void unMortgageTest() {
        //NOTE: cannot test due to NullPointerException on Scanner.
    }

    @Test
    public void getTotalWorthTest() {
        assertEquals(1500, test.getTotalWorth());

        Board board = new Board();
        board.initBoard();

        test.boughtProperty(board.getBoardSquare(11), false);
        assertEquals(1640, test.getTotalWorth());

        test.boughtProperty(board.getBoardSquare(19), true);
        assertEquals(1840, test.getTotalWorth());
    }
}