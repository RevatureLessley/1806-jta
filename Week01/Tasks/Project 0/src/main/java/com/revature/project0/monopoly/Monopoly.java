package com.revature.project0.monopoly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*  TODO List:
    TODO: Buying and Selling of property between players
    TODO: Restrict developing of properties so that at any given time, min number of expansions +1 = max number of expansions
 */

public class Monopoly {
    private static boolean DEBUG = false;
    private static AccountContainer accContainer;
    private static ArrayList<Player> playerList;
    private static ArrayList<Board.BoardPiece> availablePieces;
    private static Scanner scanner;

    private static Board board;

    private static final int JACKPOT_BASE_VALUE = 500;
    private static int jackpot;


    public static void main(String[] args){
        scanner = new Scanner(System.in);
        //accContainer = new AccountContainer();
        playerList = new ArrayList<>();
        availablePieces = new ArrayList<>();

        System.out.println("Welcome to Monopoly!\n");
        login();

        String msg = "How many players will be playing this game? (1-8)";
        System.out.println(msg);
        String line = waitForValidInput(msg, "1", "2", "3", "4", "5", "6", "7", "8");
        createNewGame(Integer.parseInt(line));
    }

    /**
     * This method initializes a new Monopoly Game
     */
    private static void createNewGame(int numPlayers){
        board = new Board();
        board.initBoard();

        jackpot = JACKPOT_BASE_VALUE;

        availablePieces.addAll(Arrays.asList(Board.BoardPiece.values()));
        for (int i = 1; i <= numPlayers; i++) {
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
        beginPlaying(); //game loop is contained in this method, so this method returns when the game is over.

        if (scanner != null) scanner.close();
    }

    /**
     * This method simulates gameplay of Monopoly, running in an infinite loop until a player is declared the winner.
     */
    private static void beginPlaying(){
        while(true){    //game loop
            for (int i = 0; i < playerList.size(); i++){
                Player player = playerList.get(i);
                //Win condition
                if (playerList.size() == 1){
                    System.out.println(player.getName() + " wins!");
                    return;
                }
                //add interest to mortgages per turn    //TODO: This could be desired.
                //for (Board.BoardSquare square : player.getMortgagedProperties()) square.addInterest();

                System.out.printf("Player %d's turn.\n", i+1);
                boolean doubles = false;
                int doublesCount = 0;
                player.printInfo();
                do{
                    String msg = "What would you like to do? Roll (R), Mortgage (M), Buy a Property back from the Bank (B), Expand a Property (E)";
                    System.out.println(msg);
                    String line = waitForValidInput(msg, "r", "m", "b", "e");
                    while (!line.equals("r")) {
                        if (line.equals("m")) player.mortgage(0);
                        else if(line.equals("b")) player.unMortgage();
                        else {  //line.equals(e)
                            if (player.getUnMortgagedProperties().size() == 0) System.out.println("You don't have any properties to develop.");
                            else {
                                int count = 0;
                                StringBuilder sb = new StringBuilder();
                                String[] validInputs = new String[player.getUnMortgagedProperties().size()];
                                for(Board.BoardSquare property : player.getUnMortgagedProperties()){
                                    sb.append(property.getName() + " ("+ ++count +"), ");
                                    validInputs[count-1] = "" + count;
                                }
                                sb.delete(sb.length() - 2, sb.length());    //delete ', '
                                System.out.println("Choose a property: " + sb.toString());
                                line = waitForValidInput(sb.toString(), validInputs);

                                //get the property user selected
                                Board.BoardSquare square = ((Board.BoardSquare)player.getUnMortgagedProperties().toArray()[Integer.parseInt(line)-1]);
                                if (!player.ownsBlock(board, square)){
                                    System.out.println("You may not expand upon this property until you own all properties in its group.");  //TODO: Specifiy which properties are missing
                                }
                                else{
                                    System.out.printf("%s has %d expansions remaining.\n", square.getName(), square.getExpansionsRemaining());
                                    if (square.getExpansionsRemaining() > 0){
                                        System.out.println("You currently have: $" + player.getMoney());
                                        boolean stillCompletingTransaction = true;
                                        while(stillCompletingTransaction) {
                                            System.out.printf("How many expansions would you like to buy? Each one costs: $%d\n", square.getBuyPrice());
                                            validInputs = new String[square.getExpansionsRemaining()+1];    //at least 0 is an option
                                            for (int j = 0; j < validInputs.length; j++) {
                                                validInputs[j] = "" + (j);
                                            }
                                            System.out.println(Arrays.asList(validInputs).toString());
                                            line = waitForValidInput("How many expansions would you like to buy? Each one costs: $" + square.getBuyPrice(), validInputs);
                                            int totalCost = square.getBuyPrice() * Integer.parseInt(line);
                                            if (line.equals("0")){
                                                System.out.println("Transaction Canceled.");
                                                stillCompletingTransaction = false;
                                            }
                                            else if (totalCost > player.getMoney()) {
                                                System.out.printf("You do not have enough money to make this purchase. (Cost: $%d)\n", totalCost);
                                            }
                                            else
                                            {
                                                player.setMoney(player.getMoney() - totalCost);
                                                System.out.println("Expansions purchased.");
                                                square.setExpansions(square.getExpansions()+Integer.parseInt(line));
                                                System.out.printf("Visitors to %s will now have to pay $%d\n", square.getName(), square.getVisitPrice());
                                                stillCompletingTransaction = false;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        System.out.println("It is still your turn.");
                        System.out.println(msg);
                        line = waitForValidInput(msg, "r", "m", "e", "b");
                    }
                    //Roll was selected
                    if (player.isInJail) {
                        msg = "Before you roll, would you like to pay $50 to get out now? Yes (Y), No (N)";
                        System.out.println(msg);
                        line = waitForValidInput(msg, "y", "n");
                        if (line.equals("y")) player.isInJail = false;
                    }
                    board.drawBoard(playerList);
                    int[] roll = Dice.roll();
                    System.out.printf("You rolled a %d and a %d, for a total of %d!\n", roll[0], roll[1], roll[0]+roll[1]);
                    if (roll[0] == roll[1]){
                        if (!player.isInJail) {
                            doubles = true;
                            if (++doublesCount != 3)System.out.println("Wow, doubles! You get an extra turn!");
                        }
                        else {
                            player.isInJail = false;
                            System.out.println("You rolled doubles! You're free to leave.");
                            player.jailTurnCount = 0;
                            doubles = false;
                            doublesCount = 0;
                            roll[0]--;  //to 'eat up' the space where you move from jail to visiting.
                        }
                    }
                    else {
                        doubles = false;
                        if (player.isInJail) player.jailTurnCount++;
                        if (player.jailTurnCount == 3){
                            player.jailTurnCount = 0;
                            player.isInJail = false;
                            System.out.println("You were unable to roll doubles thrice in a row, so we're kicking you out.");
                            if (!owesMoney(player, 50, null)) break;
                            else{
                                player.setMoney(player.getMoney() - 50);
                                System.out.println("Fee paid. You're out of jail!");
                            }
                        }
                    }
                    if (doublesCount == 3){
                        player.setLocation(10); //Jail
                        player.isInJail = true;
                        System.out.println("Uh-oh! You rolled doubles thrice in a row, into jail you go!");
                        break;  //out of do-while
                    }
                    if (!player.isInJail) player.move(roll[0] + roll[1]);

                    System.out.printf("You landed on %s! ", board.getBoardSquare(player.getLocation()).getName());
                    if (!handleSquareEvent(player, roll[0] + roll[1])) break;
                    if (player.isInJail) doubles = false;   //prevent extra turn
                } while (doubles);
                System.out.printf("End of %s's turn.\n", player.getName());
                System.out.println("(Press Enter to continue)");
                scanner.nextLine();
            }
        }   //end game loop
    }
    //NOTE: do I really need to pass a reference to the dice sum?
    private static boolean handleSquareEvent(Player player, int diceSum){
        Board.BoardSquare square = board.getBoardSquare(player.getLocation());
        String line;
        switch(square.getName()) {
            case "GO":
                ;   //implemented in Player.move() (condition does not require stopping on square)
            break;  //end GO case
            case "Community Chest":
                System.err.println("Not implemented yet.");
            break;  //end Community Chest case
            case "Chance":
                System.err.println("Not implemented yet.");
            break;  //end Chance case
            case "Income Tax":
                String msg = "Do you want to pay 10% of your total worth (A), or the flat rate of $200 (B)?";
                System.out.println("\n" + msg);
                line = waitForValidInput(msg, "a", "b");
                int tax;
                if (line.equals("a")){
                    tax = (int)(player.getTotalWorth() / 10.0f);
                }
                else {
                    tax = 200;
                }
                if (!owesMoney(player, tax, null)) return false;
                System.out.printf("Income Tax of $%d paid.\n", tax);     //will only print if player *can* pay i.e. doesnt go bankrupt
            break;  //end Income Tax case
            case "Luxury Tax":
                System.out.println("The tax is $75.00.");
                if (!owesMoney(player, 75, null)) return false;
                else {
                    System.out.printf("%s payed $75.00 to the Bank.\n", player.getName());
                }
            break;  //end Luxury Tax case
            case "Jail":
                if (player.isInJail) System.out.println("How unfortunate.");    //NOTE: Getting out is handled earlier, in beginPlaying()
                else System.out.println("Visiting hours are over today, but feel free to hang around until your next turn.");
            break;  //end Jail case
            case "Go To Jail":
                System.out.printf("\nSending %s to jail.\n", player.getName());
                player.setLocation(10); //10 is jail
                player.isInJail = true;
            break;  //end Go to Jail case
            case "Free Parking":
                player.setMoney(player.getMoney() + jackpot);
                System.out.printf("The jackpot of $%d has been added to your bank account.\n", jackpot);
                jackpot = JACKPOT_BASE_VALUE;
            break; //end Free parking case
            default:    //property square
                if(square.getOwner() != null){  //if someone owns the property
                    System.out.printf("\n%s is owned by %s.\n", square.getName(), square.getOwner().getName());
                    if (!square.getOwner().equals(player)){ //then player owes rent (if its not mortgaged)
                        if (square.getOwner().getUnMortgagedProperties().contains(square)) {
                            int rent = square.calculateRent(board, diceSum);
                            System.out.printf("You owe them $%d.\n", rent);

                            if (!owesMoney(player, rent, square.getOwner())) return false;    //break out of the method
                            System.out.println("Rent paid.");
                        }
                        else {
                            System.out.println("But unfortunately it is under mortgage right now, so rent cannot be collected.");
                        }
                    }
                }
                else {   //else no one owns it and it is available for purchase
                    System.out.printf("\nNo one owns %s, would you like to buy it? Yes (Y), No (N)\n", square.getName());
                    line = waitForValidInput("Would you like to buy it? Yes (Y), No (N)", "y", "n");
                    if (line.equals("y")) {  //Buying
                        System.out.printf("%s costs $%d to buy.\n", square.getName(), square.getBuyPrice());
                        System.out.printf("You have $%d.\n", player.getMoney());
                        if (square.getBuyPrice() > player.getMoney()) { //if player doesn't have enough money
                            System.out.println("You don't have enough money for this purchase.");
                        } else {
                            System.out.printf("Confirm Purchase ($%s): Yes (Y), No (N)\n", square.getBuyPrice());
                            line = waitForValidInput("Confirm Purchase ($" + square.getBuyPrice() + "): Yes (Y), No (N)", "y", "n");
                            if (line.equals("y")) {   //if player confirms yes
                                player.setMoney(player.getMoney() - square.getBuyPrice());
                                System.out.printf("%s bought %s!\n", player.getName(), square.getName());
                                square.setOwner(player);
                                player.boughtProperty(square, false);
                            }
                        }
                    }
                }
            break;  //end default case
        }   //end switch
        return true;
    }

    private static boolean owesMoney(Player player, int debt, Player otherPlayer){
        if (player.getMoney() >= debt){ //if the player can pay their debt
            if (otherPlayer != null) otherPlayer.setMoney(otherPlayer.getMoney() + debt);
         player.setMoney(player.getMoney() - debt);
         return true;
        }
        else {  //else they need to mortgage something to acquire funds
            if (!player.owesMoney(debt, otherPlayer)) { //if they went bankrupt
                playerList.remove(player);
                return false;
            } else {    //previous method said you have enough money now. But this should do it
                System.out.println("Recursion!");
                return owesMoney(player, debt, otherPlayer);
                //return true;
            }
        }
    }

    private static void login(){
        try {
            accContainer = AccountContainerSerializer.deserialize();
        }
        catch (IOException e){
            System.err.println(e.getClass() + ": File not found, Dont be stupid.");
            accContainer = new AccountContainer();
        }
        String line;
        do {
            System.out.println("Please enter your account credentials.");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.println();   //because last one was a print()
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.println();   //because last one was a print()
            if (accContainer.findAccount(username, password) != null) {
                System.out.printf("Logged in as %s\n", username);
                System.err.println("TODO");
                //TODO: list paused games?
                break;
            }
            else{
                String msg = "The username or password you entered was not recognized. Would you like to try again (T) or create this account (C)?";
                System.out.println(msg);
                line = waitForValidInput(msg, "t", "c");
                if (line.equals("c")){
                    accContainer.addAccount(new Account(username, password));
                    System.out.println("Account created.");
                    System.out.printf("Logged in as %s\n", username);
                    break;
                }
                //else, loop.
            }
        } while (line.equals("t"));

        //TODO: remove this
        //AccountContainerSerializer.serialize(accContainer);
    }

    /**
     * This method will put the user in a feedback loop until they enter a valid token
     * @param msg the message to be printed when the user inputs something invalid
     * @param options String vararg of valid inputs
     * @return  String the valid input of user
     */
    static String waitForValidInput(String msg, String...options){
        String line = scanner.nextLine().toLowerCase();
        while (!Arrays.asList(options).contains(line)) {
            System.out.println("Invalid option. " + msg);
            line = scanner.nextLine().toLowerCase();
        }
        return line;
    }

    private static class Dice{

        private static Random r = new Random();
        private static int[] roll(){

            if (!DEBUG) return new int[] {r.nextInt(6)+1, r.nextInt(6)+1};
            else {
                //Scanner scan = new Scanner(System.in);  //NOTE: I don't want to deal with nextInt vs nextLine '\n' issue
                System.out.print("Enter the dice rolls: ");
                int roll1 = scanner.nextInt();
                scanner.nextLine();
                int roll2 = scanner.nextInt();
                scanner.nextLine();
                return new int[] {roll1, roll2};
            }
        }
    }
}
