/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class Table {
    
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
     * The Constructor of the Table Class.
     * @param seats The maximum number of players per table.
     * @param mC The amount of the chips that each player has at the begining.
     */
    public Table (int seats, int mC)
    {
        maxSeats = seats;
        startChips = mC;
        players = new ArrayList();
        randomGenerator = new Random();
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
       // Asking which version will be played.
       do {
           
           System.out.println("Which version of the game will be played?");
           System.out.println("Press 1 for 5 Card Draw.");
           System.out.println("Press 2 for 7 Card Stud");
           System.out.println("Press 3 for Texas Hold'Em.");
           Scanner scanner = new Scanner(System.in);
           choice2 = scanner.nextInt();
           switch (choice2)
           {
               case 1:
                   System.out.println("\n" + maxSeats + " players per table will "
                           + "be playing 5 Card Draw with " + startChips + " chips "
                           + "each.\n");
                   break;
               case 2:
                   System.out.println("\n" + maxSeats + " players per table will "
                           + "be playing 7 Card Stud with " + startChips + " chips "
                           + "each.\n");
                   break;
               case 3:
                   System.out.println("\n" + maxSeats + " players per table will "
                           + "be playing Texas Hold'Em with " + startChips + " chips "
                           + "each.\n");
                   break;
               default:
                   System.out.println("Pick an integer between 1 and 3.");
           }
       } while (choice2 < 1 || choice2 > 3);
       
       /*
        * We create a new GameOfPoker object, by sending to the constructor the
        * type of poker that will be played and the list of the players in the 
        * table.
        */
       GameOfPoker g = new GameOfPoker(choice2, players);
       // We call the begin method.
       g.begin();
   }
           
}


