package com.revature.project0.monopoly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {
    private static boolean DEBUG = false;
    private static ArrayList<Player> playerList;
    private static ArrayList<Board.BoardPiece> availablePieces;

    private static Board board;


    public static void main(String[] args){

        playerList = new ArrayList<>();
        availablePieces = new ArrayList<>();

        System.out.println("Welcome to Monopoly!\n");
        createNewGame();
    }

    private static void createNewGame(){
        board = new Board();
        board.initBoard();

        availablePieces.addAll(Arrays.asList(Board.BoardPiece.values()));
        for (int i = 1; i <= 2; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Player "+i+"'s Name: ");
            String playerName = scanner.nextLine();
            if (playerName.equals("DEBUG")){
                System.out.println("Debug mode activated.");
                DEBUG = true;
                playerName = "Administrator";
            }
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
        beginPlaying();
    }
    private static void beginPlaying(){
        Scanner scanner = new Scanner(System.in);
        //while(true){    //game loop
            for (Player player : playerList){
                //Win condition
                if (playerList.size() == 1){
                    System.out.println(player + " wins!");
                    return;
                }
                System.out.printf("%s's turn.\n", player.getName());
                boolean doubles = false;
                int doublesCount = 0;
                do{
                    int[] roll = Dice.roll();
                    System.out.printf("You rolled a %d and a %d, for a total of %d!\n", roll[0], roll[1], roll[0]+roll[1]);
                    if (roll[0] == roll[1]){
                        doubles = true;
                        doublesCount++;
                        System.out.println("Wow, doubles! You get an extra turn!");
                    }
                    else doubles = false;
                    if (doublesCount == 3){
                        //TODO: go to jail.
                        System.out.println("Uh-oh! You rolled doubles thrice in a row, to jail you go!");
                        System.err.println("TODO: Not implemented yet.");
                    }
                    player.move(roll[0] + roll[1]);
                    board.drawBoard(playerList);
                    System.out.printf("You landed on %s! What would you like to do?\n", board.getBoardSquare(player.getLocation()).getName());
                    //TODO: Handling of event based on square landed on
                    System.out.println("Buy (B), Nothing (N)");
                    String line = scanner.nextLine().toLowerCase();
                    while (!line.equals("b") && !line.equals("n") ){
                        System.out.println("Invalid option. What would you like to do?\nBuy (B), Nothing (N)");
                        line = scanner.nextLine().toLowerCase();
                    }
                    if (line.equals("b")){  //Buying something
                        Board.BoardSquare square = board.getBoardSquare(player.getLocation());
                        if (square.getOwner() == null){     //if property is buyable
                            System.out.printf("%s costs $%d to buy.\n", square.getName(),square.getBuyPrice());
                            System.out.printf("You have $%d.\n", player.getMoney());
                            if (square.getBuyPrice() > player.getMoney()) { //if player doesn't have enough money
                                System.out.println("You don't have enough money for this purchase.");
                                //TODO: Allow player to mortgage something to make funds
                                break;
                            }
                            else{
                                System.out.printf("Confirm Purchase ($%s): Yes (Y), No (N)\n", square.getBuyPrice());
                                line = scanner.nextLine().toLowerCase();
                                while (!line.equals("y") && !line.equals("n") ){
                                    System.out.printf("Invalid option.\nConfirm Purchase ($%s): Yes (Y), No (N)\n", square.getBuyPrice());
                                    line = scanner.nextLine().toLowerCase();
                                }
                                if (line.equals("y")) {   //if player confirms yes
                                    System.out.printf("%s bought %s!\n", player.getName(), square.getName());
                                    square.setOwner(player);
                                    //TODO: player has list of properties they own?
                                }
                            }

                        }
                        else {  //TODO: Assuming the case where owner != player is checked already
                            System.out.println("Expanding on this property will cost $" + square.getBuyPrice());
                        }
                    }
                    else{}  //next player's turn.
                } while (doubles);
                System.out.printf("End of %s's turn.\n", player.getName());
            }
        //}   //end game loop
    }

    private static class Dice{

        private static Random r = new Random();
        private static int[] roll(){

            if (!DEBUG) return new int[] {r.nextInt(6)+1, r.nextInt(6)+1};
            else {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter the number of squares you would like to move: ");
                return new int[] {scan.nextInt(), 0};
            }
        }
    }
}
