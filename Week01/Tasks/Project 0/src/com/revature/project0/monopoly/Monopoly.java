package com.revature.project0.monopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Monopoly {

    private static ArrayList<Player> playerList;
    private static ArrayList<Board.BoardPiece> availablePieces;


    public static void main(String[] args){

        playerList = new ArrayList<>();
        availablePieces = new ArrayList<>();

        System.out.println("Welcome to Monopoly!\n");
        createNewGame();
    }

    private static void createNewGame(){
        Board board = new Board();
        board.initBoard();

        availablePieces.addAll(Arrays.asList(Board.BoardPiece.values()));
        for (int i = 1; i <= 2; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Player "+i+"'s Name: ");
            String playerName = scanner.nextLine();
            System.out.println("Choose your playing piece:\nAvailable pieces: ");
            System.out.println(availablePieces.toString());
            Board.BoardPiece playerPiece;
            while ((playerPiece = board.selectPiece(scanner.nextLine())) == null) { //BUG: player can still select from full list, even if another player has that piece
                System.out.println("Invalid piece, choose again:");
                System.out.println(availablePieces.toString());
            }
            playerList.add(new Player(playerName, playerPiece));
            availablePieces.remove(playerPiece);
        }
        //draw the board with the player pieces on it
        board.drawBoard(playerList);
    }
}
