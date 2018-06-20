package com.revature.project0.monopoly;

import java.awt.Color;
import java.util.ArrayList;

import com.revature.project0.monopoly.Board.BoardPiece;

public class Board {

    private final Color PURPLE = new Color(87, 0, 127);
    private final Color CYAN = Color.CYAN;
    private final Color MAHOGANY = new Color(127,35,63);
    private final Color ORANGE = new Color(255,106,0);
    private final Color RED = Color.RED;
    private final Color YELLOW = Color.YELLOW;
    private final Color GREEN = new Color(0,181,98);
    private final Color BLUE = new Color(0,38,133);

    BoardSquare[] squares;

    public enum BoardPiece{
        TOPHAT, THIMBLE, IRON, BOOT, BATTLESHIP, RACECAR, DOG, WHEELBARROW
    }

    public BoardPiece selectPiece(String piece){
        switch (piece.toLowerCase()){
            case "tophat": return BoardPiece.TOPHAT;
            case "thimble": return BoardPiece.THIMBLE;
            case "iron": return BoardPiece.IRON;
            case "boot": return BoardPiece.BOOT;
            case "battleship": return BoardPiece.BATTLESHIP;
            case "racecar": return BoardPiece.RACECAR;
            case "dog": return BoardPiece.DOG;
            case "wheelbarrow": return BoardPiece.WHEELBARROW;
            default: return null;
        }
    }

    public void initBoard() {
        squares = new BoardSquare[]{
                new BoardSquare("GO", null, -1, null),
                new BoardSquare("Mediterranean Avenue", PURPLE, 60, new int[]{2,10,30,90,160,250}),
                new BoardSquare("Community Chest", null, -1, null),
                new BoardSquare("Baltic Avenue", PURPLE, 60, new int[]{4,20,60,180,320,450}),
                new BoardSquare("Income Tax", null, -1, null),
                new BoardSquare("Reading Railroad", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("Oriental Avenue", CYAN, 100, new int[]{6,30,90,270,400,550}),
                new BoardSquare("Chance", null, -1, null),
                new BoardSquare("Vermont Avenue", CYAN, 100, new int[]{6,30,90,270,400,550}),
                new BoardSquare("Connecticut Avenue", CYAN, 120, new int[]{8,40,100,300,450}),
                new BoardSquare("Jail", null, -1, null),
                new BoardSquare("St. Charles Place", MAHOGANY, 140, new int[]{10,50,150,450,625}),
                new BoardSquare("Electric Company", null, 150, null),
                new BoardSquare("States Avenue", MAHOGANY, 140, new int[]{10,50,150,450,625}),
                new BoardSquare("Virginia Avenue", MAHOGANY, 160, new int[]{12,60,180,500,700,900}),
                new BoardSquare("Pennsylvania Railroad", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("St. James Place", ORANGE, 180, new int[]{14,70,200,550,750}),
                new BoardSquare("Community Chest", null, -1, null),
                new BoardSquare("Tennessee Avenue", ORANGE, 180, new int[]{14,70,200,550,750}),
                new BoardSquare("New York Avenue", ORANGE, 200, new int[]{16,80,220,600,800,1000}),
                new BoardSquare("Free Parking", null, -1, null),
                new BoardSquare("Kentucky Avenue", RED, 220, new int[]{18,90,250,700,875,1050}),
                new BoardSquare("Chance", null, -1, null),
                new BoardSquare("Indiana Avenue", RED, 220, new int[]{18,90,250,700,875,1050}),
                new BoardSquare("Illinois Avenue", RED, 240, new int[]{20,100,300,750,925,1100}),
                new BoardSquare("B & O Railroad", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("Atlantic Avenue", YELLOW, 260, new int[]{22,110,330,800,975,1150}),
                new BoardSquare("Ventor Avenue", YELLOW, 260, new int[]{22,110,330,800,975,1150}),
                new BoardSquare("Water Works", null, 150, null),
                new BoardSquare("Marvin Gardens", YELLOW, 280, new int[]{24,120,360,850,1025,1200}),
                new BoardSquare("Go To Jail", null, -1, null),
                new BoardSquare("Pacific Avenue", GREEN, 300, new int[]{26,130,390,900,1100,1275}),
                new BoardSquare("North Carolina Avenue", GREEN, 300, new int[]{26,130,390,900,1100,1275}),
                new BoardSquare("Community Chest", null, -1, null),
                new BoardSquare("Pennsylvania Avenue", GREEN, 320, new int[]{28,150,450,1000,1200,1400}),
                new BoardSquare("Short Line", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("Chance", null, -1, null),
                new BoardSquare("Park Place", BLUE, 350, new int[]{35,175,500,1100,1300,1500}),
                new BoardSquare("Luxury Tax", null, -1, null),
                new BoardSquare("Boardwalk", BLUE, 400, new int[]{50,200,600,1400,1700,2000})
        };
    }

    public void drawBoard(ArrayList<Player> pList) {
        String[] cells = new String[40];
        for (int i = 0; i < 40; i++) {
            if ( (i >= 10 && i <= 20) || i == 1 || i == 29 ) cells[i] = "_";
            else cells[i] = " ";
        }
        int spaces = 1;
        String[] extraTopLines = new String[pList.size()-1];
        String[] extraBottomLines = new String[pList.size()-1];
        for (int i = 0; i < extraTopLines.length; i++) extraTopLines[i] = String.format("%-21s", "");  //String of 21 spaces
        for (int i = 0; i < extraBottomLines.length; i++) extraBottomLines[i] = String.format("%-21s", "");  //String of 21 spaces
        for (int i = 0; i < pList.size(); i++){
            int index = pList.get(i).getLocation();
            if (cells[index].equals(" ") || cells[index].equals("_")) cells[index] = i+1 + "";
            else {
                if (index < 11){
                    cells[index] += ("," + (i+1));
                    if (spaces < cells[index].length()) spaces = cells[index].length();     //only possible if >5 players
                }
                else if (index < 21){   //NOTE: lazy if
                    int j = 0;
                    while (extraTopLines[j].charAt(convert1(index)) != ' ') j++; //loop until you find an extraline not already occupied at that location
                    extraTopLines[j] = extraTopLines[j].substring(0,convert1(index)) + (i+1) + extraTopLines[j].substring(convert1(index)+1);
                }
                else if (index < 31){   //NOTE: lazy if
                    cells[index] += ("," + (i+1));
                }
                else{   //NOTE: lazy else
                    int j = 0;
                    while (extraBottomLines[j].charAt(convert2(index)) != ' ') j++; //loop until you find an extraline not already occupied at that location
                    extraBottomLines[j] = extraBottomLines[j].substring(0,convert2(index)) + (i+1) + extraBottomLines[j].substring(convert2(index)+1);
                }
            }
        }   //end for
        for(int i = extraTopLines.length-1; i >= 0; i--){
            if (!extraTopLines[i].equals(String.format("%-21s", ""))) System.out.println(extraTopLines[i]);
        }
        System.out.printf("%"+spaces+"s|%s %s %s %s %s %s %s %s %s|%s\n", cells[10], cells[11], cells[12], cells[13], cells[14], cells[15], cells[16], cells[17], cells[18], cells[19], cells[20]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[9], cells[21]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[8], cells[22]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[7], cells[23]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[6], cells[24]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[5], cells[25]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[4], cells[26]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[3], cells[27]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[2], cells[28]);
        System.out.printf("%"+spaces+"s|_ _ _ _ _ _ _ _ _|%s\n", cells[1], cells[29]);
        System.out.printf("%"+spaces+"s|%s %s %s %s %s %s %s %s %s|%s\n", cells[0], cells[39], cells[38], cells[37], cells[36], cells[35], cells[34], cells[33], cells[32], cells[31], cells[30]);

        for(int i = 0; i < extraBottomLines.length; i++){
            if (!extraBottomLines[i].equals(String.format("%-21s", ""))) System.out.println(extraBottomLines[i]);
        }

    }

    private int convert1(int index){
        return (index - 10) * 2;
    }
    private int convert2(int index){
        return (40 - index) * 2;
    }

    public BoardSquare getBoardSquare(int index){
        return squares[index];
    }
    public class BoardSquare {
        private Color color;    //null if no color
        private String name;
        private int buyPrice;   // -1 if cannot be bought
        private int[] visitPrices;    //{nothing, 1 house, 2, 3, 4, hotel}
        private Player owner;
        private int expansions;
        //TODO effect?

        private BoardSquare(String name, Color color, int buyPrice, int[] visitPrices) {
            this.name = name;
            this.color = color;
            this.buyPrice = buyPrice;
            this.visitPrices = visitPrices;
            this.owner = null;
            this.expansions = 0;
        }

        public Color getColor() {
            return color;
        }

        public String getName() {
            return name;
        }

        public int getBuyPrice() {
            if (owner == null) return buyPrice;
            else{
                if (color == PURPLE) return 50;
                else if (color == ORANGE || color == MAHOGANY) return 100;
                else if (color == RED || color == YELLOW) return 150;
                else if (color == GREEN || color == BLUE) return 200;
                else{
                    System.err.println("Board.getBuyPrice() encountered something unexpected");
                    return -1;
                }
            }
        }

        public int getVisitPrice() {
            return visitPrices[0];
        }

        public Player getOwner() {
            return owner;
        }

        public void setOwner(Player owner){
            this.owner = owner;
        }

        public int getExpansions(){
            return this.expansions;
        }

        public void setExpansions(int value){
            this.expansions = value;
        }

    }
}
