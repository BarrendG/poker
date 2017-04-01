package poker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class DeckOfCards {
    
    /**
     * A String type array list that contains the deck.
     */
    private ArrayList<String> cards;
    
    /**
     * Auxiliary int type variable.
     */
    public int i, j;
    
    /**
     * Auxiliary String type variable.
     */
    public String tempCard;
    
    /**
     * The Class Constructor.
     */
    DeckOfCards()
    {
        cards = new ArrayList();
    }
    
    /**
     * A method that creates and stores the 52 cards of a deck into a String
     * type array list according to this format: "Card Number (1,2,...,Q,K) Card
     * Colour (Hearts, Clubs, Spades, Diamonds.)", f.e.: "Q Diamonds".
     */
    public void initializeDeck()
    {
        for(j=1;j<5;j++)
        {
            for (i=1;i<14;i++)
            {
                switch (j) {
                    case 1: // Hearts
                        if(i!=11 && i!=12 && i!=13)
                        {
                            tempCard = i + " Hearts";
                        }
                        else if(i==11)
                        {
                            tempCard = "J" + " Hearts";
                        }
                        else if(i==12)
                        {
                            tempCard = "Q" + " Hearts";
                        }
                        else if(i==13)
                        {
                            tempCard = "K" + " Hearts";
                        }
                        break;
                    case 2: // Clubs
                        if(i!=11 && i!=12 && i!=13)
                        {
                            tempCard = i + " Clubs";
                        }
                        else if(i==11)
                        {
                            tempCard = "J" + " Clubs";
                        }
                        else if(i==12)
                        {
                            tempCard = "Q" + " Clubs";
                        }
                        else if(i==13)
                        {
                            tempCard = "K" + " Clubs";
                        }
                        break;
                    case 3: // Spades
                        if(i!=11 && i!=12 && i!=13)
                        {
                            tempCard = i + " Spades";
                        }
                        else if(i==11)
                        {
                            tempCard = "J" + " Spades";
                        }
                        else if(i==12)
                        {
                            tempCard = "Q" + " Spades";
                        }
                        else if(i==13)
                        {
                            tempCard = "K" + " Spades";
                        }
                        break;
                    case 4: // Diamonds
                        if(i!=11 && i!=12 && i!=13)
                        {
                            tempCard = i + " Diamonds";
                        }
                        else if(i==11)
                        {
                            tempCard = "J" + " Diamonds";
                        }
                        else if(i==12)
                        {
                            tempCard = "Q" + " Diamonds";
                        }
                        else if(i==13)
                        {
                            tempCard = "K" + " Diamonds";
                        }
                        break;
                    default:
                        System.out.println("Error.");
                    }
                    cards.add(tempCard);
            }
        }
    }
    
    /**
     * A method that prints the deck.
     */
    public void displayDeck()
    {
        for (String c : cards)
        {
            System.out.println(c);
        }
        System.out.println("");
    }
    
    /**
     * A method that returns the deck of cards.
     * @return An array list with the deck cards.
     */
    public ArrayList<String> getCards()
    {
        return cards;
    }
    
    /**
     * Method that returns the card in the i index..
     * @param i The index of the card.
     * @return The card.
     */
    public String getCard(int i)
    {
        return cards.get(i);
    }
    
    /**
     * Method that removes a card with index i from the deck.
     * @param i The index of the card that we want to remove.
     */
    public void removeCard(int i)
    {
        if(i>=0 && i<cards.size())
        {
            cards.remove(i);
        }
        else
        {
            System.out.println("Error.");
        }
    }
}
