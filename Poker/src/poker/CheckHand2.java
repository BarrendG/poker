/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class CheckHand2 {
    
    /**
     * Auxiliary String type array list.
     */
    private ArrayList<String> arrNumber, arrColour, tempString;
    
    /**
     * Auxiliary int type variable.
     */
    public int i, j, x; 
    
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
     * I aksia tou 2o megaliterou xartiou.
     */
    public int secondMaxNumber = 0;
    
    /**
     * 2nd max card's value.
     */
    public int secondMaxId;
    
    /**
     * The size of the array lists that are inputs to the constructor.
     */
    public int size;
    
    /**
     * Auxiliary int type variable for the finding of straights.
     */
    public int first, last;
    
    /**
     * Auxiliary int type array list.
     */
    public ArrayList<Integer> counterNumber, counterColour, tempInt, tempInt2;
    
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
    public boolean straightFlushFound = false;
    
    /**
     * Auxiliary String type table variable.
     */
    public String temp;
    
    /**
     * An array list that says which cards are important.
     */
    public ArrayList<Boolean> impArray, imArray;
    
    /**
     * The Class Constructor.
     * @param arrLNumber The array list with the cards' values.
     * @param arrLColour The array list with the cards' colours.
     */
    CheckHand2(ArrayList<String> arrLNumber, ArrayList<String> arrLColour)
    {
        arrNumber = arrLNumber;
        arrColour = arrLColour;
        tempString = new ArrayList();
        counterNumber = new ArrayList();
        counterColour = new ArrayList();
        tempInt = new ArrayList();
        tempInt2 = new ArrayList();
        impArray = new ArrayList();
        imArray = new ArrayList();
        size = arrLNumber.size();
        for (i=0;i<size;i++)
        {
            counterNumber.add(i, 0);
            counterColour.add(i, 0);
            impArray.add(i, false);
            imArray.add(i, false);
            tempInt.add(i, 0);
            tempInt2.add(i, 0);
        }
    }
    
    
    /**
     * A method that finds out the hand of a player's cards.
     */
    public void check()
    {
        // For each card...
        for (i=0;i<size;i++)
        {
            // ...we find...
            for (j=0;j<size;j++)
            {
                // ...how many times its value is appeared.
                if (arrNumber.get(i).equals(arrNumber.get(j)))
                {
                    x = counterNumber.get(i);
                    x+=1;
                    counterNumber.set(i, x);
                }
                // ...how many times its colour is appeared.
                if (arrColour.get(i).equals(arrColour.get(j)))
                {
                    x = counterColour.get(i);
                    x+=1;
                    counterColour.set(i, x);
                }
            }
        }
        
        // Check for:
        for (i=0;i<size;i++)
        {
            // doubles.
            if (counterNumber.get(i) == 2)
            {
                numberDoubles++;
                impArray.set(i, true);
            }
            // triples.
            else if (counterNumber.get(i) == 3)
            {
                numberTriples++;
                impArray.set(i, true);
            }
            // quadras.
            else if (counterNumber.get(i) == 4)
            {
                numberQuadras++;
                impArray.set(i, true);
            }
            // single cards.
            else
            {
                singleFound = true;
            }
            // 3 same colours.
            if (counterColour.get(i) == 3)
            {
                colourTriples++;
                impArray.set(i, true);
            }
            // 4 same colours 
            if (counterColour.get(i) == 4)
            {
                colourQuadras++;
                impArray.set(i, true);
            }
            // 5 same colours
            if (counterColour.get(i) == 5)
            {
                colourPentas++;
                impArray.set(i, true);
            }
        }
        
        /*
         * We divide with the respectively number each time so there will be
         * always 0 or 1.
         */
        numberDoubles = numberDoubles/2;
        // In case that 3 doubles are found, 2 are kept.
        if (numberDoubles == 3)
        {
            numberDoubles = 2;
        }
        // In case that 4 doubles are found, 2 are kept.
        if (numberDoubles == 4)
        {
            numberDoubles = 2;
        }
        numberTriples = numberTriples/3;
        numberQuadras = numberQuadras/4;
        colourTriples = colourTriples/3;
        //In case that 2 triples are found, 1 is kept.
        if (colourTriples == 2)
        {
            colourTriples = 1;
            numberDoubles++;
        }
        colourQuadras = colourQuadras/4;
        colourPentas = colourPentas/5;
        
        
        // In case that a single card was found...
        if (singleFound = true)
        {
            // ...make a copy of arrNumber...
            tempString.addAll(arrNumber);
            // ...and convert it to int...
            for(j=0;j<size;j++)
            {
                if (tempString.get(j).equals("J"))
                {
                    tempString.set(j, "10");
                    tempInt.set(j, Integer.parseInt(tempString.get(j)));
                }
                else if (tempString.get(j).equals("Q"))
                {
                    tempString.set(j, "11");
                    tempInt.set(j, Integer.parseInt(tempString.get(j)));
                }
                else if (tempString.get(j).equals("K"))
                {
                    tempString.set(j, "12");
                    tempInt.set(j, Integer.parseInt(tempString.get(j)));
                }
                else if (tempString.get(j).equals("A"))
                {
                    maxNumber=1;
                    maxId = j;
                }
                else
                {
                    tempInt.set(j, Integer.parseInt(tempString.get(j)));
                }
            }
            
            /*
             *  Sorting.
             */
            // Check for Straight in case that 1<K.
            Collections.sort(tempInt);
            // Check for Straight in case that 1>K.
            tempInt2.addAll(tempInt);
            for (i=0;i<size;i++)
            {
                if (tempInt2.get(i) == 1)
                {
                    tempInt2.set(i, 14);
                }
            }
            Collections.sort(tempInt2);
            
            /*
             * A procedure that tries to find 5 consecutive cards.
             */
            if (size == 5)
            {
                if (tempInt.get(4) - tempInt.get(0) == 4)
                {
                    straightFound = true;
                    first = 0;
                    last = 4;
                }
                if (tempInt2.get(4) - tempInt2.get(0) == 4)
                {
                    straightFound = true;
                    first = 0;
                    last = 4;
                }
            }
            if (size == 6)
            {
                if (tempInt.get(4) - tempInt.get(0) == 4)
                {
                    straightFound = true;
                    first = 4;
                    last = 0;
                }
                if (tempInt.get(5) - tempInt.get(1) == 4)
                {
                    straightFound = true;
                    first = 5;
                    last = 1;
                }
                if (tempInt2.get(4) - tempInt2.get(0) == 4)
                {
                    straightFound = true;
                    first = 4;
                    last = 0;
                }
                if (tempInt2.get(5) - tempInt2.get(1) == 4)
                {
                    straightFound = true;
                    first = 5;
                    last = 1;
                }
            }
            if (size == 7)
            {
                if (tempInt.get(4) - tempInt.get(0) == 4)
                {
                    straightFound = true;
                    first = 4;
                    last = 0;
                }
                if (tempInt.get(5) - tempInt.get(1) == 4)
                {
                    straightFound = true;
                    first = 5;
                    last = 1;
                }
                if (tempInt.get(6) - tempInt.get(2) == 4)
                {
                    straightFound = true;
                    first = 6;
                    last = 2;
                }
                if (tempInt2.get(4) - tempInt2.get(0) == 4)
                {
                    straightFound = true;
                    first = 4;
                    last = 0;
                }
                if (tempInt2.get(5) - tempInt2.get(1) == 4)
                {
                    straightFound = true;
                    first = 5;
                    last = 1;
                }
                if (tempInt2.get(6) - tempInt2.get(2) == 4)
                {
                    straightFound = true;
                    first = 6;
                    last = 2;
                }
            }
            if (size == 8)
            {
                if (tempInt.get(4) - tempInt.get(0) == 4)
                {
                    straightFound = true;
                    first = 4;
                    last = 0;
                }
                if (tempInt.get(5) - tempInt.get(1) == 4)
                {
                    straightFound = true;
                    first = 5;
                    last = 1;
                }
                if (tempInt.get(6) - tempInt.get(2) == 4)
                {
                    straightFound = true;
                    first = 6;
                    last = 2;
                }
                if (tempInt.get(7) - tempInt.get(3) == 4)
                {
                    straightFound = true;
                    first = 7;
                    last = 3;
                }
                if (tempInt2.get(4) - tempInt2.get(0) == 4)
                {
                    straightFound = true;
                    first = 4;
                    last = 0;
                }
                if (tempInt2.get(5) - tempInt2.get(1) == 4)
                {
                    straightFound = true;
                    first = 5;
                    last = 1;
                }
                if (tempInt2.get(6) - tempInt2.get(2) == 4)
                {
                    straightFound = true;
                    first = 6;
                    last = 2;
                }
                if (tempInt2.get(7) - tempInt2.get(3) == 4)
                {
                    straightFound = true;
                    first = 7;
                    last = 3;
                }
            }
            
            
            /*
             * Check for Straight Flush (when we have a Straight Flush, we no
             * longer have a Straight).
             */
            if (straightFound == true)
            {
                boolean test = true;
                for (i=first;i<last;i++)
                {
                    if (arrColour.get(i).equals(arrColour.get(first)))
                    {
                        // Do nothing.
                    }
                    else
                    {
                        test = false;
                    }
                }
                // If test is true that means that all consecutive cards are of the same colour.
                if (test == true)
                {
                    straightFlushFound = true;
                    straightFound = false;
                }
            }
            
            /*
             * Check for Royal Flush (when we have a Royal Flush, we no
             * longer have a Straight Flush).
             */
            if ( (straightFlushFound == true) && (tempInt2.get(last) == 14))
            {
                straightFlushFound = false;
                royalFlushFound = true;
            }
            
            /*
             * Find the highest card.
             */
            secondMaxId = -1;
            maxId = 0;
            if (maxNumber!=1)
            {
                for(j=0;j<size;j++)
                {
                    if ((tempInt.get(j)>maxNumber) || (tempInt2.get(j)>maxNumber))
                    {
                        maxNumber = tempInt.get(j);
                        secondMaxId = maxId;
                        maxId = j;
                    }
                }
            }
            else
            {
                for(j=0;j<size;j++)
                {
                    if ((tempInt.get(j)!=maxNumber) || (tempInt2.get(j)!=maxNumber))
                    {
                        if ((tempInt.get(j)>secondMaxNumber) || (tempInt2.get(j)>secondMaxNumber))
                        {
                            secondMaxNumber = tempInt.get(j);
                            secondMaxId = j;
                        }
                        
                    }
                }
            }
            imArray.set(maxId, true);
            imArray.set(secondMaxId, true);
            
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
            for (i=0;i<size;i++)
            {
                imArray.set(i, true);
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
            for (i=0;i<size;i++)
            {
                imArray.set(i, true);
            }
            //System.out.println("Straight!");
        }
        else if (royalFlushFound == true)
        {
            importance = 1;
            for (i=0;i<size;i++)
            {
                imArray.set(i, true);
            }
            //System.out.println("Royal Flush!");
        }
        else if (straightFlushFound == true)
        {
            importance = 2;
            for (i=0;i<size;i++)
            {
                imArray.set(i, true);
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
    public ArrayList<Boolean> cardsToGiveBack()
    {
        theImportance = results();
        return imArray;
    }
}
