/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.Scanner;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 * @version 1
 */
public class Poker {

    /**
     * @param choice1 The amount of players per table.
     * @param choice2 The amount of starting chips for a player.
     * @param t An object from the Table Class.
     * @param scanner A variable to help us read.
     */
    public static void main(String[] args) {
        
        int choice1, choice2;

        // A simple welcome message to the game.
        Welcome();

        // Asking how many players does a table have.
        do {
            System.out.println("How many players does a table have?");
            Scanner scanner = new Scanner(System.in);
            choice1 = scanner.nextInt();
            if (choice1 < 4 || choice1 > 8) {
                System.out.println("The number must be between 4 and 8.");
            }
        } while (choice1 < 4 || choice1 > 8);

        // Asking how many starting chips does a player have.
        do {
            System.out.println("How many chips does a player have?");
            Scanner scanner = new Scanner(System.in);
            choice2 = scanner.nextInt();
            if (choice2 < 10 || choice2 > 20) {
                System.out.println("The number must be between 10 and 20.");
            }
        } while (choice2 < 10 || choice2 > 30);
        
        
        /*
         * In the future, in a tournament with more than one tables, we will
         * have an array list of tables. In the present we are adressing the
         * issue of a single table.
         */
        Table t = new Table(choice1, choice2);

        // Initialization.
        t.initialize();
        // Display the state of the players.
        t.displayPlayers();
        // Start the game.
        t.start();

        
    }

    /**
     * Mia apli methodos pou vgazei ena minima ipodoxis tou xristi sto paixnidi.
     */
    public static void Welcome() {
        System.out.println("Welcome to our game!! Are you ready to bluff?\n");
    }
}
