/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerGUI;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class GameOfPoker {
    
    /**
     * A non shuffled deck of cards.
     */
    protected DeckOfCards deck;
    
    /**
     * The type of poker.
     */
    protected int typeOfGame;
    
    /**
     * The list with the players.
     */
    protected ArrayList<Player> p;
    
    /**
     * An object from the Betting Class.
     */
    protected Betting bet;
    
    /**
     * An array list with the total amount that each player has bet so far.
     */
    protected ArrayList<Integer> betSoFar;
    
    /**
     * An array list that says which cards are important for a player in the end
     * of the round.
     */
    protected ArrayList<Integer> finalResult;
    
    /**
     * An array list with each player's max card.
     */
    protected ArrayList<Integer> maxNumCard;
    
    /**
     * Auxiliary variable for the creating of random numbers.
     */
    protected Random randomGenerator;
    
    /**
     * The amount of players still playing in the game.
     */
    public int stillPlayingInGame;
    
    /**
     * The amount of players ready in a betting round.
     */
    public int playersReady;
    
    /**
     * The importance of one's player's cards.
     */
    public int result;
    
    /**
     * The ante value.
     */
    public int currentAnte;
    
    /**
     * The player's index who puts the small blind.
     */
    public int smallIndex = -1;
    
    /**
     * The player's index who puts the big blind.
     */
    public int bigIndex = -1;
    
    /**
     * The upper limit of betting in a round.
     */
    public int fixed;
    
    /**
     * The total upper limit of betting in a game.
     */
    public int limit;
    
    /**
     * The small blind's value.
     */
    public int smallBet;
    
    /**
     * The big blind's value.
     */
    public int bigBet;
    
    /**
     * The amount of the players competing in the current round.
     */
    public int stillPlayingInTurn;
    
    /**
     * The amount of cards that a player has returned back.
     */
    public int cardsGivenAway;
    
    /**
     * The amount of the cards in a player's hand.
     */
    public int currentCards;
    
    /**
     * The total amount of chips that have been bet.
     */
    public int bank;
    
    /**
     * The dealer's index.
     */
    public int dealer;
    
    /**
     * The next dealer's index.
     */
    public int nextDealer;
    
    /**
     * The first bet that has been placed.
     */
    public int somebodyHasBet = 0;
    
    /**
     * The raise that has been placed.
     */
    public int somebodyHasRaised = 0;
    
    /**
     * Auxiliary int type variable.
     */
    public int i, j, x, z, testBet, card3, chips, rand1, rand2, siz, counter,
            choice1, choice2, choice3, choice4, min, max, minNumber, minIndex,
            maxIndex;
    
    /**
     * Auxiliary String type variable.
     */
    public String card, card2, number, colour;
    
    /**
     * Auxiliary String type table variable.
     */
    public String[] arrayNumber, arrayColour;
    
    /**
     * An array list that says which cards are important for a player.
     */
    public boolean[] importanceArray;
    
    /**
     * The deck of cards.
     */
    public ArrayList<String> tempDeck;
    
    /**
     * The chance of an AI player bluffing.
     */
    public int bluffChance;
    
    
    /**
     * The constructor of the class declares the type of the game and makes a
     * new object from the class DeckOfCards so that we will have a deck of
     * cards.
     */
    GameOfPoker(int type, ArrayList<Player> pl)
    {
        typeOfGame = type;
        deck = new DeckOfCards();
        deck.initializeDeck();
        p = pl;
    }
    
    /**
     * A method that prints the cards of the deck.
     */
    public void display()
    {
        deck.displayDeck();
    }
    
    /**
     * A method that returns the type of poker game that is played.
     * @return The poker type that is played.
     */
    public int getTypeOfGame()
    {
        return typeOfGame;
    }
    
    /**
     * A method that changes the value of typeOfGame.
     * @param t The new value of typeOfGame.
     */
    public void setTypeOfGame(int t)
    {
        typeOfGame = t;
    }
    
    /**
     * A method that creates an object from the type of poker Class that the 
     * user wants to play (FiveCardDraw - SevenCardStud - TexasHoldEm).
     */
    public void begin()
    {
        switch (typeOfGame)
        {
            case 1: // 5 Card Draw
                System.out.println("So... Let's play 5 Card Draw!");
                FiveCardDraw game1 = new FiveCardDraw(typeOfGame, p);
                //game1.display();
                game1.Play();
                break;
            case 2: // 7 Card Stud
                System.out.println("So... Let's play 7 Card Stud!");
                SevenCardStud game2 = new SevenCardStud(typeOfGame, p);
                //game2.display();
                game2.Play();
                break;
            case 3: // Texas Hold'em
                System.out.println("So... Let's play Texas Hold'Em!");
                TexasHoldEm game3 = new TexasHoldEm(typeOfGame, p);
                //game3.display();
                game3.Play();
                break;
            default:
                System.out.println("Error.");
        }
    }
    
    /**
     * A method that all 3 subclasses use to see who the dealer is and who are 
     * the players that must place the blinds.
     */
    public void initialBetting()
    {
        /*
         * A procedure that finds which players must place the 2 blinds
         */
        for (i=0;i<stillPlayingInGame;i++)
        {
            if (p.get(i).getIsDealer() == true)
            {
                dealer = i;
                // A message informing the user who the dealer is.
                if (p.get(i).getIsUser() == true)
                {
                    System.out.println("You are the dealer.");
                }
                else
                {
                    System.out.println("Player " + (i+1) + " is the dealer.");
                }
                
                // The player next to the dealer must place the small blind.
                smallIndex = (i+1)%stillPlayingInGame;
                p.get(smallIndex).setIsSmallBlind(true);
                // The player after next to the dealer must place the big blind.
                bigIndex = (i+2)%stillPlayingInGame;
                p.get(bigIndex).setIsBigBlind(true);
            }
        }
        
        /*
         * A procedure that takes the chips from the players who placed the
         * ante and the 2 blinds.
         */
        for (i=0;i<stillPlayingInGame;i++)
        {
            /*
             * We check if the player has enough chips to pay for the ante. If
             * not, he/she is out of the game.
             */
            if (p.get(i).getCurrentChips() >= currentAnte)
            {
                // We remove the ante value from his chips.
                chips = p.get(i).getCurrentChips();
                chips = chips - currentAnte;
                p.get(i).setCurrentChips(chips);
                
                // We increase the bank by ante.
                bank = bank + currentAnte;
                
                // We update the amount of chips that the player has bet so far.
                testBet = betSoFar.get(i);
                testBet = testBet + currentAnte;
                betSoFar.set(i, testBet);
            }
            else
            {
                if (p.get(i).getIsUser())
                {
                    System.out.println("You are out of the game.");
                }
                else
                {
                    System.out.println("Player " + (i+1) + " is out of the game.");
                }
                // We decrease the amount of players playing in the game.
                stillPlayingInGame --;
                // We remove the player from the list of players.
                p.remove(i);
                betSoFar.remove(i);
            }
            
            /*
             * We check if the player must pay for the small blind.
             */
            if (p.get(i).getIsSmallBlind() == true)
            {
                /*
                 * We check if the player has enough chips to pay for the small
                 * blind. If not, he/she is out of the game.
                 */
                if (p.get(i).getCurrentChips() >= smallBet)
                {
                    chips = p.get(i).getCurrentChips();
                    chips = chips - smallBet;
                    p.get(i).setCurrentChips(chips);
                    
                    /*
                     * Since we don't need the imformation of which player has
                     * placed the small blind, we set the player ws false, so
                     * that it will be ready for the next turn.
                     */
                    p.get(i).setIsSmallBlind(false);
                    
                    // We increase the bank by the value of small blind.
                    bank = bank + smallBet;
                    
                    // We update the amount of chips that the player has bet so far.
                    testBet = betSoFar.get(i);
                    testBet = testBet + smallBet;
                    betSoFar.set(i, testBet);
                }
                else
                {
                    if (p.get(i).getIsUser() == true)
                    {
                        System.out.println("You are out of the game.");
                    }
                    else
                    {
                        System.out.println("Player " + (i+1) + " is out of the game.");
                    }
                    // We decrease the amount of players playing in the game.
                    stillPlayingInGame --;
                    // We remove the player from the list of players.
                    p.remove(i);
                    betSoFar.remove(i);
                }
            }
            
            /*
             * We check if the player must pay for the big blind.
             */
            if (p.get(i).getIsBigBlind() == true)
            {
                /*
                 * We check if the player has enough chips to pay for the big
                 * blind. If not, he/she is out of the game.
                 */
                if (p.get(i).getCurrentChips() >= bigBet)
                {
                    chips = p.get(i).getCurrentChips();
                    chips = chips - bigBet;
                    p.get(i).setCurrentChips(chips);
                    
                    /*
                     * Since we don't need the imformation of which player has
                     * placed the big blind, we set the player ws false, so
                     * that it will be ready for the next turn.
                     */
                    p.get(i).setIsBigBlind(false);
                    
                    //We increase the bank by the value of the big blind.
                    bank = bank + bigBet;
                    
                    // We update the amount of chips that the player has bet so far.
                    testBet = betSoFar.get(i);
                    testBet = testBet + bigBet;
                    betSoFar.set(i, testBet);
                }
                else
                {
                    if (p.get(i).getIsUser() == true)
                    {
                        System.out.println("You are out of the game.");
                    }
                    else
                    {
                        System.out.println("Player " + (i+1) + " is out of the game.");
                    }
                    // We decrease the amount of players playing in the game.
                    stillPlayingInGame --;
                    // We remove the player from the list of players.
                    p.remove(i);
                    betSoFar.remove(i);
                }
            }
        }
    }
    
    /**
     * A method that is called by the subclasses SevenCardStud and TexasHoldEm
     * in case all players but 1 have not called the 1's bet.
     */
    public void WinnerFound()
    {
        for (i=0;i<stillPlayingInGame;i++)
        {
            if (p.get(i).getIsInTurn() == true)
            {
                if (p.get(i).getIsUser() == false)
                {
                    System.out.println("The winner of this turn is Player " + (i+1) + ".");
                    System.out.println("Has won " + bank + " chips.");
                }
                else
                {
                    System.out.println("You are the winner of this turn!");
                    System.out.println("You won " + bank + " chips!");
                }
                chips = p.get(i).getCurrentChips();
                chips = chips + bank;
                p.get(i).setCurrentChips(chips);
            }
        }
    }
    
    /**
     * A method that all 3 subclasses use to print the cards and the chips of
     * the players.
     */
    public void showChipsAndHand()
    {
        for (i=0;i<stillPlayingInGame;i++)
        {
            // Check if the player is the user.
            if(p.get(i).getIsUser() == true)
            {
                System.out.println("\nYour hand:");
                p.get(i).displayCardsOnHand();
                System.out.println("Your chips: " +
                        p.get(i).getCurrentChips());
            }
            else
            {
                //System.out.println("\nPlayer " + (i+1) + "'s hand:");
                //p.get(i).displayCardsOnHand();
                System.out.println("Player " + (i+1) + "'s chips: " +
                        p.get(i).getCurrentChips());
            }
        }
    }
    
    /**
     * A method that finds out how many players are this turn.
     */
    public void calculatePlayersInTurn()
    {
        stillPlayingInTurn = 0;
        for(i=0;i<stillPlayingInGame;i++)
        {
            if(p.get(i).getIsInTurn() == true)
            {
                stillPlayingInTurn++;
            }
            // Arxikopoioume taftoxrona olous tous paiktes ws mi etoimous.
            p.get(i).setIsReady(false);
        }
    }
    
    /**
     * Initialize the array list betSoFar.
     */
    public void initializeBetSoFar()
    {
        for (i=0; i<stillPlayingInGame; i++)
        {
            betSoFar.add(i, 0);
        }
    }
    
    /**
     * A method that initializes the players.
     */
    public void initializePlayers()
    {
        for (i=0;i<stillPlayingInGame;i++)
        {
            // Check if the player is the user.
            if(p.get(i).getIsUser() == true)
            {
                System.out.println("Your chips: " + p.get(i).getCurrentChips());
            }
            else
            {
                System.out.println("Player " + (i+1) + "'s chips: " + p.get(i).getCurrentChips());
            }
            p.get(i).clearCardsOnHand();
            p.get(i).clearOpenCards();
            p.get(i).setIsInTurn(true);
            betSoFar.set(i, 0);
            p.get(i).setIsReady(false);
        }
        
        // We name the next dealer.
        if (dealer < p.size())
        {
            p.get(dealer).setIsDealer(false);
        }
        nextDealer = (dealer + 1)%p.size();
        if (nextDealer < p.size())
        {
            p.get(nextDealer).setIsDealer(true);
        }
        if (smallIndex < p.size())
        {
            p.get(smallIndex).setIsSmallBlind(false);
        }
        if (bigIndex < p.size())
        {
            p.get(bigIndex).setIsBigBlind(false);
        }
    }
}
