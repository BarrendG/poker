/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class Player {

    /**
     * True if the player is the dealer.
     */
    private boolean isDealer;
    
    /**
     * True if the player must place the small blind.
     */
    private boolean isSmallBlind;
    
    /**
     * True if the player must place the big blind.
     */
    private boolean isBigBlind;
    
    /**
     * True if the player is the user.
     */
    private boolean isUser;
    
    /**
     * True if the player is in this round.
     */
    private boolean isInTurn;
    
    /**
     * True if the player is ready in the betting round.
     */
    private boolean isReady;
    
    /**
     * The chips that the player currently owns.
     */
    private int currentChips;
    
    /**
     * Auxiliary variable.
     */
    private int j;
    
    /**
     * An array list with elements the cards that a player holds.
     */
    private ArrayList<String> cardsOnHand;
    
    /**
     * An array list with elements the open cards that a player holds.
     */
    private ArrayList<String> openCards;

    /**
     * The Class Constructor.
     * @param c The amount of starting chips.
     */
    public Player(int c)
    {
        isDealer = false;
        currentChips = c;
        isUser = false;
        cardsOnHand = new ArrayList();
        openCards = new ArrayList();
        isSmallBlind = false;
        isBigBlind = false;
        isInTurn = true;
        isReady = false;
    }
    
    /**
     * Method that returns the value of isDealer.
     * @return True if the player is the dealer.
     */
    public boolean getIsDealer()
    {
        return isDealer;
    }
    
    /**
     * Method that changes the value of isDealer.
     * @param d The new value of isDealer.
     */
    public void setIsDealer(boolean d)
    {
        isDealer = d;
    }
    
    /**
     * Method that returns the value of isSmallBlind.
     * @return True if the player must place the small blind.
     */
    public boolean getIsSmallBlind()
    {
        return isSmallBlind;
    }
    
    /**
     * Method that changes the value of isSmallBlind.
     * @param s The new value of isSmallBlind.
     */
    public void setIsSmallBlind(boolean s)
    {
        isSmallBlind = s;
    }
    
    /**
     * Method that returns the value of isBigBlind.
     * @return True if the player must place the big blind.
     */
    public boolean getIsBigBlind()
    {
        return isBigBlind;
    }
    
    /**
     * Method that changes the value of isBigBlind.
     * @param b The new value of isBigBlind.
     */
    public void setIsBigBlind(boolean b)
    {
        isBigBlind = b;
    }
    
    /**
     * A method that returns the amount of the player's current chips.
     * @return The amount of the player's current chips.
     */
    public int getCurrentChips()
    {
        return currentChips;
    }
    
    /**
     * A method that changes the amount of the player's current chips.
     * @param c The new value of currentChips.
     */
    public void setCurrentChips(int c)
    {
        currentChips = c;
    }
    
    /**
     * A method that says if the player is in this turn.
     * @return True if the player is in this turn.
     */
    public boolean getIsInTurn()
    {
        return isInTurn;
    }
    
    /**
     * A method that changes the value of isInTurn.
     * @param t The new value of isInTurn.
     */
    public void setIsInTurn(boolean t)
    {
        isInTurn = t;
    }
    
    /**
     * Method that says if the player is the user.
     * @return True if the player is the user.
     */
    public boolean getIsUser()
    {
        return isUser;
    }
    
    /**
     * A method that changes the value of isUser.
     * @param u The new value of isUser.
     */
    public void setIsUser(boolean u)
    {
        isUser = u;
    }
    
    /**
     * Method that says if the player is ready in the betting round.
     * @return True if the player is ready in the betting round.
     */
    public boolean getIsReady()
    {
        return isReady;
    }
    
    /**
     * A method that changes the value of isReady.
     * @param r The new value of isReady.
     */
    public void setIsReady(boolean r)
    {
        isReady = r;
    }
    
    /**
     * Method that returns the card from the player's hand with index i.
     * @param i The index of the card that we want to return.
     */
    public String getCard(int i)
    {
        return cardsOnHand.get(i);
    }
    
    /**
     * Method that adds a card in the player's hand.
     * @param s The new card.
     */
    public void addCard(String s)
    {
        cardsOnHand.add(s);
    }
    
    /**
     * Method that adds a card in the player's list of open cards.
     * @param s The new card.
     */
    public void addOpenCard(String s)
    {
        openCards.add(s);
    }
    
    /**
     * Method that removes a card from the player's hand.
     * @param i The index of the card that we want to remove.
     */
    public void removeCard(int i)
    {
        if(i>=0 && i<cardsOnHand.size())
        {
            cardsOnHand.remove(i);
        }
        else
        {
            System.out.println("Error.");
        }
    }
    
    /**
     * Method that removes all the cards from the player's hand.
     */
    public void removeAllCards()
    {
        for (j=0;j<cardsOnHand.size();j++)
        {
            cardsOnHand.remove(j);
        }
    }
    
    /**
     * Method that prints all the cards that the player holds.
     */
    public void displayCardsOnHand()
    {
        for (int i=0;i<cardsOnHand.size();i++)
        {
            System.out.println(cardsOnHand.get(i));
        }
    }
    
    /**
     * Method that prints all the open cards that the player holds.
     */
    public void displayOpenCards()
    {
        for (int i=0;i<openCards.size();i++)
        {
            System.out.println(openCards.get(i));
        }
    }
    
    /**
     * Method that removes all the cards from the player's hand.
     */
    public void clearCardsOnHand()
    {
        cardsOnHand.clear();
    }
    
    /**
     * Method that removes all the open cards from the player's hand.
     */
    public void clearOpenCards()
    {
        openCards.clear();
    }
    
    /**
     * Method that returns the size of the array list with the cards that the
     * player holds.
     * @return The amount of cards that the player holds.
     */
    public int getCardsOnHandSize()
    {
        return cardsOnHand.size();
    }
}
