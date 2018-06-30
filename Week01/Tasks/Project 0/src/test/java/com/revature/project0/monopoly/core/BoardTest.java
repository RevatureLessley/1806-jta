package com.revature.project0.monopoly.core;

import com.revature.project0.monopoly.core.Board;
import com.revature.project0.monopoly.core.Player;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoardTest {

    private static Board board;

    @BeforeClass
    public static void beforeClass(){
        board = new Board();
        board.initBoard();
    }

    @Test
    public void selectPieceTest() {
        assertEquals(Board.BoardPiece.TOPHAT, board.selectPiece("Tophat"));
        assertEquals(Board.BoardPiece.THIMBLE, board.selectPiece("Thimble"));
        assertEquals(Board.BoardPiece.IRON, board.selectPiece("Iron"));
        assertEquals(Board.BoardPiece.BOOT, board.selectPiece("Boot"));
        assertEquals(Board.BoardPiece.BATTLESHIP, board.selectPiece("Battleship"));
        assertEquals(Board.BoardPiece.RACECAR, board.selectPiece("Racecar"));
        assertEquals(Board.BoardPiece.DOG, board.selectPiece("Dog"));
        assertEquals(Board.BoardPiece.WHEELBARROW, board.selectPiece("Wheelbarrow"));
        assertNull(board.selectPiece(""));
        assertNull(board.selectPiece("Topphat"));
        assertNull(board.selectPiece(" Tophat"));
        assertNull(board.selectPiece("Tophat "));
        assertNull(board.selectPiece("Top hat"));

        assertEquals(Board.BoardPiece.RACECAR, board.selectPiece("RACECAR"));
        assertEquals(Board.BoardPiece.RACECAR, board.selectPiece("RaCeCaR"));


    }

    @Test
    public void initBoardTest() {
        assertEquals("GO" ,board.getBoardSquare(0).getName());
        assertEquals("Community Chest" ,board.getBoardSquare(2).getName());
        assertEquals("Chance" ,board.getBoardSquare(7).getName());
        assertEquals("Boardwalk" ,board.getBoardSquare(39).getName());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void initBoardTestException() {
        board.getBoardSquare(40);
    }

    @Test
    public void getAllSquaresOfColorTest() {
        Board.BoardSquare[] array = board.getAllSquaresOfColor(Color.CYAN);
        assertEquals(3, array.length);
        assertEquals("Oriental Avenue", array[0].getName());
        assertEquals("Vermont Avenue", array[1].getName());
        assertEquals("Connecticut Avenue", array[2].getName());
        array = board.getAllSquaresOfColor(board.getBoardSquare(1).getColor());
        assertEquals(3, array.length);
        assertEquals("Mediterranean Avenue", array[0].getName());
        assertEquals("Baltic Avenue", array[1].getName());
        assertNull(array[2]);
    }

    @Test
    public void getBoardSquareTest() {
        Board.BoardSquare test = board.getBoardSquare(20);
        //NOTE: this could be easier by overriding equals() in BoardSquare
        assertEquals("Free Parking", test.getName());
        assertEquals(0, test.getExpansions());
        assertEquals(-1, test.getBuyPrice());
        assertNull(test.getOwner());
        assertEquals(5, test.getExpansionsRemaining());
        assertNull(test.getColor());
        assertEquals(0, test.getMortgageValue());

        test = board.getBoardSquare(21);
        assertEquals("Kentucky Avenue", test.getName());
        assertEquals(0, test.getExpansions());
        assertEquals(220, test.getBuyPrice());
        assertNull(test.getOwner());
        assertEquals(5, test.getExpansionsRemaining());
        assertEquals(Color.RED,test.getColor());
        assertEquals(110, test.getMortgageValue());
        test.setOwner(new Player("Test", null));
        assertEquals(18, test.calculateRent(board, 3));
        assertEquals(18, test.getVisitPrice());
    }

    @Test
    public void addInterestTest(){
        final int INDEX = 39;
        final float INTEREST = 1.1f;
        Board.BoardSquare square = board.getBoardSquare(INDEX);
        int value = square.getBuyBackPrice();
        Player testPlayer = new Player("", null);
        testPlayer.boughtProperty(square, true);
        assertEquals(value, square.getBuyBackPrice());
        for (int i = 0; i < 100; i++){
            square.addInterest();
            value = (int)(value * INTEREST);
            assertEquals(value, square.getBuyBackPrice());
        }
    }
}