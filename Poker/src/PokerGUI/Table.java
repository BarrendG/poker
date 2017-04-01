/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerGUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class Table extends JFrame {
    
    /**
     * The maximum number of players per table.
     */
    private static int maxSeats;
    
    /**
     * The amount of the chips that each player has at the begining.
     */
    private static int startChips;
    
    /**
     * The list of the players sitting on the table.
     */
    private ArrayList<Player> players; 
    
    /**
     * A variable for the creation of random numbers.
     */
    private Random randomGenerator;
    
    /**
     * An auxiliary int type variable.
     */
    public int i, rand1, rand2, dealerIs, choice, choice2;
    
    /**
     * The slave window to the first frame that the user sees.
     */
    public JDialog frameDialog;
    
    /**
     * The master window.
     */
    public JFrame frameTable;
 
    /**
     * Auxiliary label.
     */
    public JLabel label, label2;
  
    
    /**
     * The Constructor of the Table Class.
     * @param seats The maximum number of players per table.
     * @param mC The amount of the chips that each player has at the begining.
     */
    public Table (int seats, int mC, JFrame frameMenu)
    {
        maxSeats = seats;
        startChips = mC;
        players = new ArrayList();
        randomGenerator = new Random();
        frameTable = frameMenu;
    }
   
   /**
    * A method that return the value of maxSeats.
    * @return The maximum number of players per table.
    */
   public int getMaxSeats()
   {
       return maxSeats;
   }
   
   /**
    * A method that changes the value of maxSeats.
    * @param s The new value of maxSeats.
    */
   public void setMaxSeats(int s)
   {
       maxSeats = s;
   }
   
   /**
    * A method that returns the value of startChips.
    * @return The amount of the chips that each player has at the begining.
    */
   public int getStartChips()
   {
       return startChips;
   }
   
   /**
    * A method that changes the value of startChips.
    * @param st The new value of startChips.
    */
   public void setStartChips(int st)
   {
       startChips = st;
   }
   
   /**
    * A method that initializes the list with the number of players per table,
    * giving them an amount of starting chips, naming the player who will be
    * the first dealer and naming which player (randomly) will the user be.
    */
   public void initialize()
   {
       rand2 = Randomizer(maxSeats);
       // In every element of the list...
       for (i=0; i<maxSeats;i++)
       {
           // ...we add a player with full starting chips.
           players.add(new Player(startChips));
           // If we are mentioning the first player...
           if (i==0)
           {
               // ...we make him/her the dealer.
               players.get(i).setIsDealer(true);
           }
           if (i==rand2) 
           {
               players.get(i).setIsUser(true);
           }
       }
   }
   
   /**
    * A method that return a random number between 0 and n.
    * @param n The field of numbers.
    * @return A random number.
    */
   public int Randomizer(int n)
   {
       rand1 = randomGenerator.nextInt(n);
       return rand1;
   }
   
   /**
    * A method that returns a random number between 0 and the size of the list
    * of players.
    * @return A random number.
    */
   public int Randomizer()
   {
       rand1 = randomGenerator.nextInt(players.size());
       return rand1;
   }
   
   /**
    * A method that prints each player's id, the user's id and which player is
    * the dealer.
    */
   public void displayPlayers()
   {
       System.out.println("\nThe players:");
       for (i=0; i<players.size();i++)
       {
           Player p = players.get(i);
           if (p.getIsUser() == true)
           {
               System.out.println("Player " + (i+1) + " (You)");
           }
           else
           {
               System.out.println("Player " + (i+1));
           }
           if (p.getIsDealer() == true)
           {
               dealerIs = i;
           }
           System.out.println("Chips: " + p.getCurrentChips());
       }
       System.out.println("The Dealer is Player " + (dealerIs+1) + ".\n");
   }
   
   /**
    * A method that starts the game.
    */
   public void start()
   {
       // Create the slave window.
       frameDialog = new JDialog(frameTable, "Poker 2", true);
       
       // Set the frame's size.
       frameDialog.setSize(700, 500);
       
       // Put the frame in the center.
       frameDialog.setLocationRelativeTo(null);
       
       // Make it visible.
       frameDialog.setVisible(true);
       
       // Allow the user to resize it.
       frameDialog.setResizable(true);
       
       // Close.
       frameDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
       // Create a label.
       label = new JLabel("Which version of the game will be played? Make a choice"
               + " and go to the console to keep playing.");
       
       // Create a manager.
       FlowLayout layout = new FlowLayout();
       layout.setHgap(20);
       layout.setVgap(10);
       
       // Create the radio buttons with the options for the user.
       JRadioButton fiveCardDrawButton = new JRadioButton("5 Card Draw");
       JRadioButton sevenCardStudButton = new JRadioButton("7 Card Stud");
       JRadioButton texasHoldEmButton = new JRadioButton("Texas Hold'Em");
       
       // Group up the buttons.
       ButtonGroup group = new ButtonGroup();
       group.add(fiveCardDrawButton);
       group.add(sevenCardStudButton);
       group.add(texasHoldEmButton);
       
       // Where does the user go after he picks a button.
       fiveCardDrawButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed (ActionEvent e)
           {
               /*
                * We create a new GameOfPoker object, by sending to the constructor the
                * type of poker that will be played and the list of the players in the 
                * table.
                */
               GameOfPoker g = new GameOfPoker(1, players);
               // We call the begin method.
               g.begin();
           }
       });
       
       sevenCardStudButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed (ActionEvent e)
           {
               /*
                * We create a new GameOfPoker object, by sending to the constructor the
                * type of poker that will be played and the list of the players in the 
                * table.
                */
               GameOfPoker g = new GameOfPoker(2, players);
               // We call the begin method.
               g.begin();
           }
       });
       
       texasHoldEmButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed (ActionEvent e)
           {
               /*
                * We create a new GameOfPoker object, by sending to the constructor the
                * type of poker that will be played and the list of the players in the 
                * table.
                */
               GameOfPoker g = new GameOfPoker(3, players);
               // We call the begin method.
               g.begin();
           }
       });
       
       JPanel radioPanel = new JPanel(new GridLayout(0, 1));
       radioPanel.add(fiveCardDrawButton);
       radioPanel.add(sevenCardStudButton);
       radioPanel.add(texasHoldEmButton);
       
       // Add all elements in the frame.
       frameDialog.add(label);
       frameDialog.add(radioPanel);
       frameDialog.add(fiveCardDrawButton);
       frameDialog.add(sevenCardStudButton);
       frameDialog.add(texasHoldEmButton);
       frameDialog.setLayout(layout);
       frameDialog.add(label);
       frameDialog.setVisible(true);
       
       
   }
           
}


