/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerGUI;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class Betting {
    
    /**
     * The value of ante.
     */
    private int ante;
    
    /**
     * The value of the small blind.
     */
    private int smallBlind;
    
    /**
     * The value of the big blind.
     */
    private int bigBlind;
    
    /**
     * The betting limit in a betting round.
     */
    private int fixedLimit;
    
    /**
     * The betting limit in a turn.
     */
    private int capLimit;
    
    /**
     * The Class Constructor.
     */
    Betting()
    {
        ante = 0;
        smallBlind = 1;
        bigBlind = 2;
        fixedLimit = 2;
        capLimit = 20;
    }
    
    /**
     * A method that returns the ante's value.
     * @return The ante's value.
     */
    public int getAnte()
    {
        return ante;
    }
    
    /**
     * A method that changes the ante's value.
     * @param a The new value of ante.
     */
    public void setAnte(int a)
    {
        ante = a;
    }
    
    /**
     * A method that returns the small blind's value.
     * @return The small blind's value.
     */
    public int getSmallBlind()
    {
        return smallBlind;
    }
    
    /**
     * A method that changes the small blind's value.
     * @param s The new value of smallBlind.
     */
    public void setSmallBlind(int s)
    {
        smallBlind = s;
    }
    
    /**
     * A method that returns the big blind's value.
     * @return The big blind's value.
     */
    public int getBigBlind()
    {
        return bigBlind;
    }
    
    /**
     * A method that changes the big blind's value.
     * @param b The new value of bigBlind.
     */
    public void setBigBlind(int b)
    {
        bigBlind = b;
    }
    
    /**
     * A method that returns the betting limit in a round.
     * @return The value of Fixed Limit.
     */
    public int getFixedLimit()
    {
        return fixedLimit;
    }
    
    /**
     * A method that changes the betting limit in a round.
     * @param f The new value of Fixed Limit.
     */
    public void setFixedLimit(int f)
    {
        fixedLimit = f;
    }
    
    /**
     * A method that returns the betting limit in a turn.
     * @return The value of Cap Limit.
     */
    public int getCapLimit()
    {
        return capLimit;
    }
    
    /**
     * A method that changes the betting limit in a turn.
     * @param c The new value of Cap Limit.
     */
    public void setCapLimit(int c)
    {
        capLimit = c;
    }
}
