package com.revature.project0.monopoly.core;

import java.util.HashMap;
import java.util.Random;

import static com.revature.project0.monopoly.core.CardDeck.CardType.CHANCE;
import static com.revature.project0.monopoly.core.LogWrapper.Severity.ERROR;

/**
 * This class represents the Chance and Community Chest card deck
 */
public class CardDeck {

    enum CardType{
        CHANCE, COMMUNITY_CHEST
    }

    private static CardDeck deck;
    private HashMap<String, CardEvent> chanceCardList;
    private HashMap<String, CardEvent> communityChestCardList;

    /**
     * Singleton Constructor
     */
    private CardDeck(){
        chanceCardList = new HashMap<>();
        communityChestCardList = new HashMap<>();
        initializeCards();
    }

    public static CardDeck getCards(){
        if (deck == null) deck = new CardDeck();
        return deck;
    }

    private void initializeCards(){
        CardEvents events = new CardEvents();
        //NOTE: The :: was suggested by Intellij over using ()->events.method() (lambda expression)
        //TODO: Figure out why, and what's going on.
        //The Strings are not needed, but they help debug which Card I'm simulating
        chanceCardList.put("Elected Chairman", events::event01);
        chanceCardList.put("Building and Loan Matures", events::event02);
        chanceCardList.put("Get out of Jail Free", events::event03);
        chanceCardList.put("Bank Pays You", events::event04);
        chanceCardList.put("Poor Tax", events::event05);
        chanceCardList.put("Advance to Go", events::event06);
        chanceCardList.put("Go Back Three Spaces", events::event07);
        chanceCardList.put("Go to Nearest Utility", events::event08);
        chanceCardList.put("Go to Jail", events::event09);
        chanceCardList.put("Go to St. Charles Avenue", events::event10);
        chanceCardList.put("Go to Nearest Railroad", events::event11);
        chanceCardList.put("Go to Reading Railroad", events::event12);
        chanceCardList.put("Go to Boardwalk", events::event13);
        chanceCardList.put("Go to Illinois Avenue", events::event14);
        chanceCardList.put("General Repairs", events::event15);
        chanceCardList.put("Win Crossword Competition", events::event16);

        communityChestCardList.put("Go to Go", events::event06);    //redundant
        communityChestCardList.put("Income Tax Refund", events::event18);
        communityChestCardList.put("Sale of Stock", events::event19);
        communityChestCardList.put("Bank Error", events::event20);
        communityChestCardList.put("Fox Theatre Opening", events::event21);
        communityChestCardList.put("School Tax", events::event22);
        communityChestCardList.put("Receive for Services", events::event23);
        communityChestCardList.put("Street Repair Assessment", events::event24);
        communityChestCardList.put("Doctor Fee", events::event25);
        communityChestCardList.put("Get out of Jail Free", events::event03);
        communityChestCardList.put("Go to Jail", events::event09);
        communityChestCardList.put("Holiday Fund Matures", events::event28);
        communityChestCardList.put("Life Insurance Matures", events::event29);
        communityChestCardList.put("Birthday", events::event30);
        communityChestCardList.put("Second Prize in Beauty Contest", events::event31);
        communityChestCardList.put("Inherit Money", events::event32);
    }

    public String getCardName(int index, CardType type){
        if (type == CHANCE) return (String)chanceCardList.keySet().toArray()[index];
        else /*type == COMMUNITYCHEST */ return (String)communityChestCardList.keySet().toArray()[index];
    }
    public String drawRandomCard(CardType type){
        int num = new Random().nextInt(16);
        return getCardName(num, type);
    }
    public int runCardEvent(Player player, String name){
        if (chanceCardList.keySet().contains(name)) return chanceCardList.get(name).runEvent(player);
        else if (communityChestCardList.keySet().contains(name)) return communityChestCardList.get(name).runEvent(player);
        else LogWrapper.log(this.getClass(), "Card not found: " + name, ERROR); return 0;
    }


    /**
     * This class is a container holding all the card events
     */
    public class CardEvents implements CardEvent{
        
        
        public int runEvent(Player player){
            //This method block is empty, but for some reason when called executes the lambda expression
            //      associated with this CardEvent
            //Furthermore, the return value for this method is completely ignored; the lambda expression's return
            //      value is used. However,  language requirements require that this method has one. Curious.
            //TODO: Learn why this works.
            return 0;
        }

        /*
        For the following seven methods, a return value that is:
            positive signifies the player receiving money
            negative signifies the player is paying money
            zero means the player was moved.
         */
        private int payMoney(Player player, int amount){
            System.out.println("\tYou paid $" + amount + ".");
            return -amount;
        }
        private int getMoney(Player player, int amount){
            System.out.println("\tYou received $" + amount + ".");
            return amount;
        }
        private int getMoneyFromAllPlayers(Player player, int amount){
            for (Player otherPlayer : Monopoly.getPlayerList()){
                if (otherPlayer.getMoney() >= amount){
                    otherPlayer.setMoney(otherPlayer.getMoney() - amount);
                    player.setMoney(player.getMoney() + amount);
                }
                else {
                    if (otherPlayer != player && !otherPlayer.owesMoney(amount, player)) bankruptPlayer(otherPlayer);
                    else {  //NOTE: This will run if player == otherPlayer (but it *should* be irrelevant)
                        otherPlayer.setMoney(otherPlayer.getMoney() - amount);
                        player.setMoney(player.getMoney() + amount);
                    }
                }
            }
            return 0;   //all money moving is performed in owesMoney(), don't want to repeat it in calling class.
        }
        private int payMoneyToAllPlayers(Player player, int amount){
            for (Player otherPlayer : Monopoly.getPlayerList()){
                if (player.getMoney() >= amount){
                    player.setMoney(player.getMoney() - amount);
                    otherPlayer.setMoney(otherPlayer.getMoney() + amount);
                }
                else {
                    if (otherPlayer != player && !player.owesMoney(amount, otherPlayer)) bankruptPlayer(player);
                    else {  //NOTE: This will run if player == otherPlayer (but it *should* be irrelevant)
                        otherPlayer.setMoney(otherPlayer.getMoney() + amount);
                        player.setMoney(player.getMoney() - amount);
                    }
                }
            }
            return 0;   //all money moving is performed in owesMoney(), don't want to repeat it in calling class.
        }
        private int moveTo(Player player, int location){
            System.out.println("\tYou moved to location " + location + ".");
            player.setLocation(location);
            return 0;
        }
        private int move(Player player, int amount){
            player.setLocation(player.getLocation()-3);
            if (player.getLocation() < 0) player.setLocation(player.getLocation() + 40);    //No negative location values!!
            System.out.println("\tYou moved 3 squares back.");
            return 0;
        }
        private int moveToNearest(Player player, String type){
            int loc = player.getLocation();
            if (type.equals("Railroad")) player.setLocation((((((loc + 4) % 40) / 10) * 10) + 5));   //algorithm finds the nearest railroad
            else if (type.equals("Utility")) {
                if (player.getLocation() <= 28 && player.getLocation() > 12) player.setLocation(28);
                else player.setLocation(12);
            }
            System.out.println("\t\tMoved to the nearest " + type);
            return 0;
        }

        //lambda definitions
        //Chance
        //Elected Chairman
        int event01(Player player){
            System.out.println("You have been elected Chairman of the Board! Pay each Player $50.");
            return payMoneyToAllPlayers(player, 50);
        }
        //Building and Loan Matures
        int event02(Player player){
            System.out.println("Your Building and Loan Matures. Collect $150.");
            return getMoney(player, 150);
        }
        //Get out of Jail Free
        int event03(Player player){
            System.out.println("Get out of Jail Free!");
            if (player.hasGetOutOfJailCard()) System.out.println("You already have one of these cards, and cannot have another.");
            else System.out.println("You receive a \'Get out of Jail Free\' card.");
            player.setHasJailCard(true);
            return 0;
        }
        //Bank Pays You
        int event04(Player player){
            System.out.println("Bank pays you dividend of $50.");
            return getMoney(player, 50);
        }
        //Poor Tax
        int event05(Player player){
            System.out.println("Pay Poor Tax of $15.");
            return payMoney(player, 15);
        }
        //Go to Go
        int event06(Player player){
            System.out.println("Advance to Go!");
            return moveTo(player, 0);
        }
        //Go Back Three Spaces
        int event07(Player player){
            System.out.println("Go back three spaces!");
            return move(player, -3);
        }
        //Go to Nearest Utility
        int event08(Player player){
            System.out.println("Advance to nearest Utility!");
            return moveToNearest(player, "Utility");
        }
        //Go to Jail
        int event09(Player player){
            System.out.println("Go directly to Jail, do not pass Go.");
            return moveTo(player, 10);
        }
        //Go to St. Charles Avenue
        int event10(Player player){
            System.out.println("Advance to St. Charles Avenue!");
            return moveTo(player, 11);
        }
        //Go to Nearest Railroad
        int event11(Player player){
            System.out.println("Advance to nearest Railroad");
            return moveToNearest(player, "Railroad");
        }
        //Go to Reading Railroad
        int event12(Player player){
            System.out.println("Take a ride on the Reading Railroad!");
            return moveTo(player, 5);
        }
        //Go to BoardWalk
        int event13(Player player){
            System.out.println("Take a walk on the Boardwalk!");
            return moveTo(player, 39);
        }
        //Go to Illinois Avenue
        int event14(Player player){
            System.out.println("Advance to Illinois Avenue!");
            return moveTo(player, 24);
        }
        //General Repairs
        int event15(Player player){
            System.out.println("Make general repairs on all your property. For each house pay $25, and for each " +
                    "hotel pay $100.");
            int payment = 0;
            for (Board.BoardSquare square : player.getOwnedProperties()){
                if (square.getExpansions() == 5) payment += 200;
                else payment += (square.getExpansions() * 25);
            }
            return -payment;
        }
        //Win Crossword Competition
        int event16(Player player){
            System.out.println("You won the Crossword Competition! Collect $100.");
            return getMoney(player, 100);
        }
        //Community Chest
//        //Go to Go (redundant)
//        int event17(Player player){
//            event06(Player player);
//        }
        //Income Tax Refund
        int event18(Player player){
            System.out.println("Income Tax refund! Collect $20.");
            return getMoney(player, 20);
        }
        //Sale of Stock
        int event19(Player player){
            System.out.println("From sale of stock, you get $45.");
            return getMoney(player, 45);
        }
        //Bank Error
        int event20(Player player){
            System.out.println("Bank error in your favor! Collect $200.");
            return getMoney(player, 200);
        }
        //Fox Theatre Opening
        int event21(Player player){
            System.out.println("Fox Theatre opening! Collect $50 from every player.");
            return getMoneyFromAllPlayers(player, 50);
        }
        //School Tax
        int event22(Player player){
            System.out.println("Pay School Tax of $150.");
            return payMoney(player, 150);
        }
        //Receive for Services
        int event23(Player player){
            System.out.println("Recieve for services rendered. Collect $25");
            return getMoney(player, 25);
        }
        //Street Repair Assessment
        int event24(Player player){
            System.out.println("You are assessed for street repairs. Pay $40 per house, and $115 per hotel.");
            int payment = 0;
            for (Board.BoardSquare square : player.getOwnedProperties()){
                if (square.getExpansions() == 5) payment += 200;
                else payment += (square.getExpansions() * 25);
            }
            return -payment;
        }
        //Doctor Fee
        int event25(Player player){
            System.out.println("Pay doctor's fee of $100.");
            return payMoney(player, 100);
        }
//        //Get out of Jail Free (redundant)
//        int event26(Player player){
//            event03(Player player);
//        }
//        //Go to Jail (redundant)
//        int event27(Player player){
//            event09(Player player);
//        }
        //Holiday Fund Matures
        int event28(Player player){
            System.out.println("Your holiday fund matures! Collect $100.");
            return getMoney(player, 100);
        }
        //Life Insurance Matures
        int event29(Player player){
            System.out.println("Your Life Insurance policy matures! Collect $100.");
            return getMoney(player, 100);
        }
        //Birthday
        int event30(Player player){
            System.out.println("It is your birthday. Collect $10 from each player.");
            return getMoneyFromAllPlayers(player, 10);
        }
        //Second Prize in Beauty Contest
        int event31(Player player){
            System.out.println("You have won second prize in a beauty contest! Collect $10.");
            return getMoney(player, 10);
        }
        //Inherit Money
        int event32(Player player){
            System.out.println("You inherit $100.");
            return getMoney(player, 100);
        }

        //Helper methods
        private void bankruptPlayer(Player player){
            Monopoly.removePlayer(player);
        }


    }
}
