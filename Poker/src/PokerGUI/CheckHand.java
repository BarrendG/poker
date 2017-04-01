/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerGUI;

import java.util.Arrays;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class CheckHand {
    
    /**
     * Auxiliary variable table String type.
     */
    private String[] arrNumber, arrColour, tempString;
    
    /**
     * Auxiliary int type variable.
     */
    public int i, j;
    
    /**
     * The amount of double cards.
     */
    public int numberDoubles=0;
    
    /**
     * The amount of triple cards.
     */
    public int numberTriples=0;
    
    /**
     * The amount of quadraple cards.
     */
    public int numberQuadras=0;
    
    /**
     * The max card's value.
     */
    public int maxNumber=0;
    
    /**
     * A number that says how many times we have 5 cards of the same colour.
     */
    public int colourPentas=0;
    
    /**
     * The cards' importance.
     */
    public int importance, theImportance;
    
    /**
     * A number that says how many times we have 4 cards of the same colour.
     */
    public int colourQuadras = 0;
    
    /**
     * A number that says how many times we have 3 cards of the same colour.
     */
    public int colourTriples = 0;
    
    /**
     * Max card's index.
     */
    public int maxId;
    
    /**
     * 2nd max card's value.
     */
    public int secondMaxNumber = 0;
    
    /**
     * 2nd max card's index.
     */
    public int secondMaxId;
    
    /**
     * Auxiliary variable table int type.
     */
    public int[] counterNumber, counterColour, tempInt, tempInt2;
    
    /**
     * True if a single card was found.
     */
    public boolean singleFound = false;
    
    /**
     * True if a Straight was found.
     */
    public boolean straightFound = false;
    
    /**
     * True if a Royal Flush was found.
     */
    public boolean royalFlushFound = false;
    
    /**
     * True if a Straight Flush was found.
     */
    public boolean straightFlush = false;
    
    /**
     * Auxiliary String type variable.
     */
    public String temp;
    
    /**
     * An array that says which cards are important.
     */
    public boolean[] impArray, imArray;
    
    /**
     * The Class Constructor.
     * @param arrayNumber The array with the cards' values.
     * @param arrayColour The array with the cards' colours.
     */
    CheckHand(String[] arrayNumber, String[] arrayColour)
    {
        arrNumber = arrayNumber;
        arrColour = arrayColour;
        tempString = new String[5];
        counterNumber = new int[5];
        counterColour = new int[5];
        tempInt = new int [5];
        tempInt2 = new int [5];
        impArray = new boolean[5];
        imArray = new boolean[5];
    }
    
    /**
     * A method that finds out the hand of a player's cards.
     * @param counterNumber Auxiliary variable table int type.
     * @param counterColour Auxiliary variable table int type.
     * @param numberDoubles Auxiliary variable for double cards.
     * @param numberTriples Auxiliary variable for triple cards.
     * @param numberQuadras Auxiliary variable for quadraple cards.
     * @param singleFound True if a single card was found.
     * @param colourPentas A number that says how many times we have 5 cards of the same colour.
     * @param straightFound A variable that is true is a Straight if found.
     * @param royalFlushFound A variable that is true is a Royal Flush if found.
     */
    public void check()
    {
        // For each card...
        for (i=0;i<5;i++)
        {
            // ...we find...
            for (j=0;j<5;j++)
            {
                // ...how many times its value is appeared.
                if (arrNumber[i].equals(arrNumber[j]))
                {
                    counterNumber[i]++;
                }
                // ...how many times its colour is appeared.
                if (arrColour[i].equals(arrColour[j]))
                {
                    counterColour[i]++;
                }
            }
        }
        
        // Check for:
        for (i=0;i<5;i++)
        {
            // doubles.
            if (counterNumber[i] == 2)
            {
                numberDoubles++;
                impArray[i] = true;
            }
            // triples.
            else if (counterNumber[i] == 3)
            {
                numberTriples++;
                impArray[i] = true;
            }
            // quadras.
            else if (counterNumber[i] == 4)
            {
                numberQuadras++;
                impArray[i] = true;
            }
            // single cards.
            else
            {
                singleFound = true;
            }
            
            // 5 same colours.
            if (counterColour[i] == 3)
            {
                colourTriples++;
                impArray[i] = true;
            }
            // 4 same colours
            if (counterColour[i] == 4)
            {
                colourQuadras++;
                impArray[i] = true;
            }
            // 3 same colours
            if (counterColour[i] == 5)
            {
                colourPentas++;
                impArray[i] = true;
            }
        }
        
        /*
         * We divide with the respectively number each time so there will be
         * always 0 or 1.
         */
        numberDoubles = numberDoubles/2;
        numberTriples = numberTriples/3;
        numberQuadras = numberQuadras/4;
        colourTriples = colourTriples/3;
        colourQuadras = colourQuadras/4;
        colourPentas = colourPentas/5;
        
        
        // In case that a single card was found...
        if (singleFound = true)
        {
            // ...make a copy of arrNumber...
            tempString = arrNumber;
            // ...and convert it to int...
            for(j=0;j<5;j++)
            {
                if (tempString[j].equals("J"))
                {
                    tempString[j]="10";
                    tempInt[j] = Integer.parseInt(tempString[j]);
                }
                else if (tempString[j].equals("Q"))
                {
                    tempString[j]="11";
                    tempInt[j] = Integer.parseInt(tempString[j]);
                }
                else if (tempString[j].equals("K"))
                {
                    tempString[j]="12";
                    tempInt[j] = Integer.parseInt(tempString[j]);
                }
                else if (tempString[j].equals("A"))
                {
                    maxNumber=1;
                    maxId = j;
                }
                else
                {
                    tempInt[j] = Integer.parseInt(tempString[j]);
                }
            }
            
            /*
             *  Sorting.
             */
            // Check for Straight in case that 1<K.
            tempInt2 = tempInt;
            Arrays.sort(tempInt);
            if (tempInt[4] - tempInt[0] == 4)
            {
                straightFound = true;
            }
            
            // Check for Straight in case that 1>K.
            for (i=0;i<5;i++)
            {
                if (tempInt2[i] == 1)
                {
                    tempInt2[i] = 14;
                }
            }
            Arrays.sort(tempInt2);
            if (tempInt2[4] - tempInt2[0] == 4)
            {
                straightFound = true;
            }
            
            /*
             * Check for Straight Flush (when we have a Straight Flush, we no
             * longer have a Straight).
             */
            if ( (straightFound == true) && (colourPentas == 1) )
            {
                straightFound = false;
                straightFlush = true;
            }
            
            /*
             * Check for Royal Flush (when we have a Royal Flush, we no
             * longer have a Straight Flush).
             */
            if ( (straightFlush == true) && (tempInt2[4] == 14))
            {
                straightFlush = false;
                royalFlushFound = true;
            }
            
            /*
             * Find the highest card.
             */
            secondMaxId = -1;
            maxId = 0;
            if (maxNumber!=1)
            {
                for(j=0;j<5;j++)
                {
                    if ((tempInt[j]>maxNumber) || (tempInt2[j]>maxNumber))
                    {
                        maxNumber = tempInt[j];
                        secondMaxId = maxId;
                        maxId = j;
                    }
                }
            }
            else
            {
                for(j=0;j<5;j++)
                {
                    if ((tempInt[j]!=maxNumber) || (tempInt2[j]!=maxNumber))
                    {
                        if ((tempInt[j]>secondMaxNumber) || (tempInt2[j]>secondMaxNumber))
                        {
                            secondMaxNumber = tempInt[j];
                            secondMaxId = j;
                        }
                        
                    }
                }
            }
            imArray[maxId] = true;
            imArray[secondMaxId] = true;
            
        }
    }
    
    /**
     * A method that finds out and return the hightest card.
     * @return Highest card's value.
     */
    public String getMaxNumber()
    {
        if (maxNumber == 11)
        {
            temp = "J";
        }
        else if (maxNumber == 12)
        {
            temp = "Q";
        }
        else if (maxNumber == 13)
        {
            temp = "K";
        }
        else if (maxNumber == 1 || maxNumber == 14)
        {
            temp = "A";
        }
        else
        {
            temp = Integer.toString(maxNumber);
        }
        return temp;
    }
    
    /**
     * A method that calls the method check() to find if there is a hand and
     * then it returns it.
     * @return The importance of the hand (1 most important- 10 least one).
     */
    public int results()
    {
        check();
        if (numberQuadras == 1)
        {
            importance = 3;
            //System.out.println("Four Of A Kind!");
        }
        else if (colourPentas == 1)
        {
            importance = 5;
            for (i=0;i<5;i++)
            {
                imArray[i] = true;
            }
            //System.out.println("Flush!");
        }
        else if (numberTriples == 1 && numberDoubles == 1)
        {
            importance = 4;
            //System.out.println("Full House!");
        }
        else if (numberTriples == 1 && numberDoubles == 0)
        {
            importance = 7;
            //System.out.println("Three Of A Kind!");
        }
        else if (numberTriples == 0 && numberDoubles == 1)
        {
            importance = 9;
            //System.out.println("One Pair!");
        }
        else if (numberDoubles == 2)
        {
            importance = 8;
            //System.out.println("Two Pair!");
        }
        else if (straightFound == true)
        {
            importance = 6;
            for (i=0;i<5;i++)
            {
                imArray[i] = true;
            }
            //System.out.println("Straight!");
        }
        else if (royalFlushFound == true)
        {
            importance = 1;
            for (i=0;i<5;i++)
            {
                imArray[i] = true;
            }
            //System.out.println("Royal Flush!");
        }
        else if (straightFlush = true)
        {
            importance = 2;
            for (i=0;i<5;i++)
            {
                imArray[i] = true;
            }
            //System.out.println("Straight Flush!");
        }
        else if (colourTriples == 1)
        {
            importance = 12;
            //System.out.println("3 same colour.");
        }
        else if (colourQuadras == 1)
        {
            importance = 11;
            //System.out.println("4 same colour.");
        }
        else
        {
            importance = 10;
            //System.out.println("Highest Card: " + getMaxNumber());
        }
        return importance;
    }
    
    /**
     * A method that says which cards are important for the player.
     * @return A boolean table that inticates which cards are important.
     */
    public boolean[] cardsToGiveBack()
    {
        theImportance = results();
        return imArray;
    }
}
