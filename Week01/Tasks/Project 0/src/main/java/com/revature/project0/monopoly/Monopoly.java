package com.revature.project0.monopoly;

/*
 * @Author Eric Sundberg
 * 6/18/18
 * This project simulates the Monopoly game by Hasbro, restricted to console output.
 * On top of that, game data will persist if the application is exited naturally, and players
 * may create accounts for themselves (actually they're required to).
 * Known Bugs: Players may choose already chosen game pieces (but this has no effect on gameplay)
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.revature.project0.monopoly.LogWrapper.Severity.*;

/*
    TODO List:
    TODO: Buying and Selling of property between players
    TODO: Restrict developing of properties so that at any given time, min number of expansions +1 = max number of expansions
*/

/**
 * This class simulates the Monopoly game and houses the main method.
 * It handles all the game logic and cooridinates with the Account
 * class (through it's AccountContainer object)
 */
public class Monopoly {

    private static boolean DEBUG = false;
    private static AccountContainer accContainer;
    private static ArrayList<Player> playerList;
    private static ArrayList<Board.BoardPiece> availablePieces;
    private static ArrayList<Account> loggedInPlayers;
    private static Scanner scanner;

    private static Board board;

    private static final int JACKPOT_BASE_VALUE = 500;
    private static int jackpot;

    private static boolean doOnce;


    private static void testFunction(){

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Something.ser"));
            ois.readObject();
            ois.close();
        }
        catch(Exception e){
            LogWrapper.log(Monopoly.class, e);
       }

        System.exit(0);
    }

    /**
     * Main method.
     * @param args command line arguments
     */
    public static void main(String[] args){
        //testFunction();   //TODO: remove this call and the method itself

        scanner = new Scanner(System.in);
        //accContainer = new AccountContainer();
        playerList = new ArrayList<>();
        loggedInPlayers = new ArrayList<>();
        availablePieces = new ArrayList<>();
        LogWrapper.log(Monopoly.class, "Instance Variables initialized.");
        System.out.println("Welcome to Monopoly!\n");
        getAccountList();
        System.out.println("Please enter your account credentials:");
        login();
        GameState gs = null;
        try { gs = GameStateSerializer.deserialize(); }
        catch (FileNotFoundException e){
            System.out.println("No prior game data exists, creating new game.");
            LogWrapper.log(Monopoly.class, e);
        }
        if (gs != null){
            String line;
            do {
                String msg = "Would you like to start a new game (A), or resume your previous game (B)?";
                System.out.println(msg);
                line = waitForValidInput(msg, "a", "b");
                if (line.equals("b")) {
                    createExistingGame(gs);
                    if (scanner != null) scanner.close();   //clean up resources
                    return; //In other words, don't create a new game
                } else {
                    System.out.println("WARNING! Creating a new game will overwrite your old game. Are you sure you wish to continue? Yes (Y), No (N)");
                    line = waitForValidInput("Are you sure you wish to continue? (Y), No (N)", "y", "n");
                }
            } while (line.equals("n"));
        }

        String msg = "How many players will be playing this game? (1-8)";
        System.out.println(msg);
        String line = waitForValidInput(msg, "1", "2", "3", "4", "5", "6", "7", "8");
        createNewGame(Integer.parseInt(line));
        if (scanner != null) scanner.close();   //clean up resources
    }

    /**
     * This method will retrieve the instance of GameState of the saved game, and then start playing it.
     * It uses serialization to accomplish this.
     * @param gs the GameState will all the metadata of the saved game.
     */
    private static void createExistingGame(GameState gs){
        LogWrapper.log(Monopoly.class, "Using an existing Monopoly Game");
        playerList = gs.getPlayers();
        board = gs.getBoard();
        jackpot = gs.getJackpotValue();

        waitForOtherPlayers(playerList.size());

        doOnce = true;  //so that we correctly start on the correct player's turn in the next method
        beginPlaying(gs.getPlayerTurn());
    }

    /**
     * This method initializes a new Monopoly Game.
     */
    private static void createNewGame(int numPlayers){
        LogWrapper.log(Monopoly.class, "Creating new Monopoly Game");
        board = new Board();
        board.initBoard();

        jackpot = JACKPOT_BASE_VALUE;

        waitForOtherPlayers(numPlayers);
        System.out.println();
        availablePieces.addAll(Arrays.asList(Board.BoardPiece.values()));
        for (Account account : loggedInPlayers){
            System.out.println(account.getUsername() + ", pick your playing piece:\nAvailable pieces: ");
            System.out.println(availablePieces.toString());
            Board.BoardPiece playerPiece;
            while ((playerPiece = board.selectPiece(scanner.nextLine())) == null ) { //BUG: player can still select from full list, even if another player has that piece
                System.out.println("Invalid piece, choose again:");
                System.out.println(availablePieces.toString());
            }
            playerList.add(new Player(account.getUsername(), playerPiece));
            availablePieces.remove(playerPiece);
        }

        //draw the board with the player pieces on it
        //board.drawBoard(playerList);
        beginPlaying(1); //game loop is contained in this method, so this method returns when the game is over.

    }

    /**
     * This method will loop until the specfied number of players for the game have logged in to play.
     * Player 1 is always the admin, who gets to decide if the other players are allowed.
     * @param num the number of players playing.
     */
    private static void waitForOtherPlayers(int num){
        String line;
        do {
            Account admin = loggedInPlayers.get(0);
            loggedInPlayers = new ArrayList<>();
            loggedInPlayers.add(admin);
            LogWrapper.log(Monopoly.class, "Looping until all players are logged in");
            while (loggedInPlayers.size() < num) {
                System.out.println("Waiting for other players to join...");
                System.out.printf("Player %d login: \n", loggedInPlayers.size()+1);
                login();
            }

            System.out.println();
            int i = 1;
            for (Account acc : loggedInPlayers) {
                System.out.println("Player " + i++ + ": " + acc.getUsername());
            }
            System.out.printf("%s, Do you approve of these players? Yes (Y), No (N)", admin.getUsername()); //NOTE: this will be different for networking
            line = waitForValidInput("Do you approve of these players? Yes (Y), No (N)", "y", "n");
        } while (line.equals("n"));
        System.out.println();
    }

    /**
     * This method simulates gameplay of Monopoly, running in an infinite loop until a player is declared the winner.
     */
    private static void beginPlaying(int turn){
        //NOTE: Serialization doesn't technically belong here, but its a good place to stick it in the
        //      flow of the program execution, because it's guarenteed to be done being useful at this point.
        AccountContainerSerializer.serialize(accContainer);
        LogWrapper.log(Monopoly.class, "Beginning game simulation");
        while(true){    //game loop
            for (int i = 0; i < playerList.size(); i++){
                if (doOnce){
                    doOnce = false; //with no hope to ever be true again.
                    i = turn-1; //offset to transform Player num -> array index
                }
                Player player = playerList.get(i);
                //Win condition
                if (playerList.size() == 1){
                    System.out.println(player.getName() + " wins!");
                    LogWrapper.log(Monopoly.class, "Ending game simulation");
                    return;
                }
                //add interest to mortgages per turn    //NOTE: Not in original game rules.
                for (Board.BoardSquare square : player.getMortgagedProperties()) square.addInterest();

                System.out.printf("Player %d's turn.\n", i+1);
                boolean doubles;    // = false;
                int doublesCount = 0;
                player.printInfo();
                do{
                    board.drawBoard(playerList);
                    String msg = "What would you like to do? Roll (R), Mortgage (M), Buy a Property back from the Bank (B), Expand a Property (E), Leave the Game (L)";
                    System.out.println(msg);
                    String line = waitForValidInput(msg, "r", "m", "b", "e", "l");
                    while (!line.equals("r")) {
                        switch (line){
                            case "m":
                                player.mortgage(0);
                            break;
                            case "b":
                                player.unMortgage();
                            break;
                            case "l":
                                leaveAndSuspendGame(player);
                                return;
                            //break;    //handled by return;
                            default:    //"e"
                                if (player.getUnMortgagedProperties().size() == 0) System.out.println("You don't have any properties to develop.");
                                else {
                                    int count = 0;
                                    StringBuilder sb = new StringBuilder();
                                    String[] validInputs = new String[player.getUnMortgagedProperties().size()];
                                    for(Board.BoardSquare property : player.getUnMortgagedProperties()){
                                        sb.append(property.getName()).append(" (").append(++count).append("), ");
                                        validInputs[count-1] = "" + count;
                                    }
                                    sb.delete(sb.length() - 2, sb.length());    //delete ', '
                                    System.out.println("Choose a property: " + sb.toString());
                                    line = waitForValidInput(sb.toString(), validInputs);

                                    //get the property user selected
                                    Board.BoardSquare square = ((Board.BoardSquare)player.getUnMortgagedProperties().toArray()[Integer.parseInt(line)-1]);
                                    LogWrapper.log(Monopoly.class, "User is developing property \"" +square.getName()+ "\"");
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
                            break;  //optional
                        }   //end switch

                        System.out.println("It is still your turn.");
                        System.out.println(msg);
                        line = waitForValidInput(msg, "r", "m", "e", "b");
                    }
                    //Roll was selected
                    LogWrapper.log(Monopoly.class, "User is rolling dice");
                    if (player.isInJail) {
                        msg = "Before you roll, would you like to pay $50 to get out now? Yes (Y), No (N)";
                        System.out.println(msg);
                        line = waitForValidInput(msg, "y", "n");
                        if (line.equals("y")) player.isInJail = false;
                    }

                    int[] roll = Dice.roll();
                    LogWrapper.log(Monopoly.class, "Roll values: " +roll[0]+", "+roll[1]+". Sum: "+(roll[0] + roll[1]));
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

                    board.drawBoard(playerList);
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

    /**
     * This event will look up (switch statement) the square the player landed on and trigger an event to happen based
     * on that square.
     * @param player the player who landed on the square.
     * @param diceSum the sum of the dice roll. Used for Utilty rent.
     * @return true if player was able to pay rent, false if the player has to declare bankruptcy.
     */
    //NOTE: do I really need to pass a reference to the dice sum? Such a tiny thing to be placed on the parameter list.
    private static boolean handleSquareEvent(Player player, int diceSum){
        Board.BoardSquare square = board.getBoardSquare(player.getLocation());
        LogWrapper.log(Monopoly.class, "Simulating "+square.getName()+"'s event");
        String line;
        switch(square.getName()) {
            case "GO":
                //implemented in Player.move() (condition does not require stopping on square)
            break;  //end GO case
            case "Community Chest":
                System.err.println("Not implemented yet.");
                System.out.println("\n");
            break;  //end Community Chest case
            case "Chance":
                System.err.println("Not implemented yet.");
                System.out.println("\n");
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
                                player.boughtProperty(square, false);
                            }
                        }
                    }
                }
            break;  //end default case
        }   //end switch
        return true;
    }

    /**
     * This method is called when the player owes money to a player or the Bank (null) and steps them through the process
     * of liquidating their assets to get the money to cover the debt.
     * @param player the player owing money
     * @param debt the amonunt of money player needs to pay
     * @param otherPlayer the player to whom the debt is being paid. This value is null if it is the Bank.
     * @return true if the player was able to pay the debt, or false if the player has no way to pay the debt and must declare bankruptcy.
     */
    private static boolean owesMoney(Player player, int debt, Player otherPlayer){
        LogWrapper.log(Monopoly.class, "Initiating Debt cycle");
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
                LogWrapper.log(Monopoly.class, "Recursing", LogWrapper.Severity.DEBUG);
                return owesMoney(player, debt, otherPlayer);
                //return true;
            }
        }
    }

    /**
     * Deserializes the Account List.
     */
    private static void getAccountList(){
        try {
            accContainer = AccountContainerSerializer.deserialize();
        }
        catch (FileNotFoundException e){
            LogWrapper.log(Monopoly.class, e);
            System.out.println("Account List not found, generating new one.");
            accContainer = new AccountContainer();
        }
    }

    /**
     * This method handles player login. It walks the user through the steps of typing in their username and password.
     * If their combo is not recognized by the Account List, then the user is presented with the option to save what they
     * typed as a new account or to try again.
     */
    private static void login(){
        String line;
        LogWrapper.log(Monopoly.class, "Acquiring user credentials");
        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            //System.out.println();   //because last one was a print()
            System.out.print("Password: ");
            String password = scanner.nextLine();
            //System.out.println();   //because last one was a print()
            Account acc;
            if ((acc = accContainer.findAccount(username, password)) != null) {
                if (!loggedInPlayers.contains(acc)){
                    LogWrapper.log(Monopoly.class, "Successful Login: " + username);
                    System.out.printf("Logged in as %s.\n", username);
                    loggedInPlayers.add(acc);
                    break;
                }
                else {
                    System.out.println("You are already logged in as Player " + (loggedInPlayers.indexOf(acc)+1) + ".");
                    line = "t"; //hacking it so that it loops.
                }
            }
            else{
                String msg = "The username or password you entered was not recognized. Would you like to try again (T) or create this account (C)?";
                System.out.println(msg);
                line = waitForValidInput(msg, "t", "c");
                if (line.equals("c")){
                    acc = new Account(username, password);
                    accContainer.addAccount(acc);
                    System.out.println("Account created.");
                    LogWrapper.log(Monopoly.class, "Successful Login: " + username);
                    System.out.printf("Logged in as %s\n", username);
                    loggedInPlayers.add(acc);
                    break;
                }
                //else, loop.
            }
        } while (line.equals("t"));
    }

    /**
     * This method handles a player leaving the game. Doing so will end the game (for now)
     * @param player the player leaving the game.
     */
    private static void leaveAndSuspendGame(Player player){
        System.out.printf("Sorry everyone, %s has decided to leave the game. Come back later!\n", player.getName());
        //Save the game state
        GameState gs = new GameState();
        gs.setGameState(playerList, board, playerList.indexOf(player)+1, jackpot);
        GameStateSerializer.serialize(gs);
        //TODO: enter loop to fill their spot, or just shut down and force a new instance?
        //It's easier for now to just shut down.
        //NOTE: Handled in calling method.
        LogWrapper.log(Monopoly.class, "Ending game simulation");
    }

    /**
     * This method will put the user in a feedback loop until they enter a valid token
     * @param msg the message to be printed when the user inputs something invalid
     * @param options String vararg of valid inputs
     * @return  String the valid input of user
     */
    static String waitForValidInput(String msg, String...options){
        LogWrapper.log(Monopoly.class, "Requesting user input");
        String line = scanner.nextLine();
        LogWrapper.log(Monopoly.class, "User entered: \"" + line + "\"");
        if (line.equals("DEBUG")){
            System.out.println("Debug mode activated.");
            LogWrapper.log(Monopoly.class,"Debug mode activated");
            DEBUG = true;
        }
        line = line.toLowerCase();
        while (!Arrays.asList(options).contains(line)) {
            System.out.println("Invalid option. " + msg);
            line = scanner.nextLine().toLowerCase();
        }
        LogWrapper.log(Monopoly.class, "Returning valid input: \"" + line + "\"");
        return line;
    }

    /**
     * Inner class representing a classic six-sided die.
     */
    private static class Dice{

        private static Random r = new Random();

        /**
         * Handles rolling of the dice. If DEBUG mode is enabled, then it allows the user to enter the value of each die.
         * @return a size-2 array of the dice rolls. roll[0] is the first die and roll[1] is the second die.
         */
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
