/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class SevenCardStud extends GameOfPoker {
    
    /**
     * An object of CheckHand2 Class.
     */
    private CheckHand2 b;
    /**
     * Auxiliary list String type.
     */
    public ArrayList<String> arrayListNumber, arrayListColour;
    
    /**
     * The class Constructor.
     */
    public SevenCardStud(int type, ArrayList<Player> pl)
    {
        super(type,pl);
        betSoFar = new ArrayList();
        stillPlayingInGame = p.size();
        bet = new Betting();
        arrayListNumber = new ArrayList();
        arrayListColour = new ArrayList();
        finalResult = new ArrayList();
        maxNumCard = new ArrayList();
    }
    
    /**
     * A method that simulates and 7 Card Stud game.
     */
    public void Play()
    {
        // Initialize betSoFar.
        initializeBetSoFar();
        
        while (stillPlayingInGame != 1)
        {
            System.out.println("\n---------------------------------");
            System.out.println("A NEW TURN STARTS.");
            System.out.println("---------------------------------");
            
            // Create a deck of cards.
            tempDeck = new ArrayList(deck.getCards());
            // Shuffle the deck.
            Collections.shuffle(tempDeck);
            
            // Print the Ante and 2 blinds values.
            currentAnte = bet.getAnte();
            System.out.println("Ante is " + currentAnte);
            smallBet = bet.getSmallBlind();
            System.out.println("Small Blind is " + smallBet);
            bigBet = bet.getBigBlind();
            System.out.println("Big Blind is " + bigBet);  
            System.out.println("---------------------------------");
            
            // Set bank as 0.
            bank = 0;
            
            /*
             * Call the method that finds out who the dealer is and which
             * players must place the 2 blinds.
             */
            initialBetting();
            
            /*
             * A procedure that deals 2 close and 1 open card to the players.
             */
            System.out.println("\n2 close and 1 open card are dealt to the players.");
            counter = 51;
            // Till each player has 2 cards...
            for (i=0;i<2;i++)
            {
                // ...for each player...
                for (j=0;j<p.size();j++)
                {
                    //...create a card...
                    card2 = tempDeck.get(counter);
                    // ...and give it to him...
                    p.get(j).addCard(card2);
                    // ...and remove it from the deck.
                    tempDeck.remove(counter);
                    counter--;
                    //System.out.println(card2);
                }
            }
            // Deal the open card.
            for (j=0;j<p.size();j++)
            {
                card2 = tempDeck.get(counter);
                p.get(j).addCard(card2);
                tempDeck.remove(counter);
                counter--;
                //System.out.println(card2);
                p.get(j).addOpenCard(card2);
            }
            
            /*
             * Call the method that prints all open cards.
             */
            DisplayOpenCards();
            
            /*
             * Call the method that prints the chips and cards of the players.
             */
            showChipsAndHand();
            
            /*
             * Call the procedure that finds out how many players are in this turn.
             */
            calculatePlayersInTurn();
            
            /*
             * 1ST ROUND OF BETTING.
             */
            System.out.println("\n---------------------------------");
            System.out.println("1ST ROUND OF BETTING.");
            System.out.println("---------------------------------");
            
            BettingRound();
            
            System.out.println("\n---------------------------------");
            
            
            // Check if there is a winner.
            if (stillPlayingInTurn == 1)
            {
                // Call the method to announce the winner.
                WinnerFound();
            }
            else
            {
                /*
                 * Procedure in which the dealer deals the 2nd open card to each
                 * player.
                 */
                System.out.println("\nThe second open card is dealt to the players.");
                for (j=0;j<p.size();j++)
                {
                    card2 = tempDeck.get(counter);
                    if (p.get(j).getIsInTurn() == true)
                    {
                        p.get(j).addCard(card2);
                        p.get(j).addOpenCard(card2);
                    }
                    tempDeck.remove(counter);
                    counter--;
                    // Telos, emfanizoume tin karta.
                    //System.out.println(card2);
                }
                
                /*
                 * Call the method that prints all open cards.
                 */
                DisplayOpenCards();
                
                /*
                 * Call the method that prints the chips and the cards of the players.
                 */
                showChipsAndHand();
                
                /*
                 * 2ND ROUND OF BETTING.
                 */
                System.out.println("\n---------------------------------");
                System.out.println("2ND ROUND OF BETTING.");
                System.out.println("---------------------------------");
                
                BettingRound();
                
                System.out.println("\n---------------------------------");
                
                
                // Check if there is a winner.
                if (stillPlayingInTurn == 1)
                {
                    // Call the method to announce the winner.
                    WinnerFound();
                }
                else
                {
                    /*
                     * The dealer deals the 3rd open card to the players.
                     */
                    System.out.println("\nThe third open card is dealt to the players.");
                    for (j=0;j<p.size();j++)
                    {
                        card2 = tempDeck.get(counter);
                        if (p.get(j).getIsInTurn() == true)
                        {
                            p.get(j).addCard(card2);
                            p.get(j).addOpenCard(card2);
                        }
                        tempDeck.remove(counter);
                        counter--;
                        // Telos, emfanizoume tin karta.
                        //System.out.println(card2);
                    }
                    
                    /*
                     * Call the method that prints all open cards.
                     */
                    DisplayOpenCards();
                    
                    /*
                     * Call the method that prints the chips and the cards of the players.
                     */
                    showChipsAndHand();  
            
                    /*
                     * 3os giros pontarismatos.
                     */
                    // Minima gia ton xristi.
                    System.out.println("\n---------------------------------");
                    System.out.println("3RD ROUND OF BETTING.");
                    System.out.println("---------------------------------");
                        
                    BettingRound();
                        
                    System.out.println("\n---------------------------------");
                        
                    
                    // Check if there is a winner.
                    if (stillPlayingInTurn == 1)
                    {
                        // Call the method to announce the winner.
                        WinnerFound();
                    }
                    else
                    {
                        /*
                         * The dealer deals the 4th open card to each player.
                         */
                        System.out.println("\nThe fourth open card is dealt to the players.");
                        for (j=0;j<p.size();j++)
                        {
                            card2 = tempDeck.get(counter);
                            if (p.get(j).getIsInTurn() == true)
                            {
                                p.get(j).addCard(card2);
                                p.get(j).addOpenCard(card2);
                            }
                            tempDeck.remove(counter);
                            counter--;
                            
                        }
                        
                        /*
                         * Call the method that prints all open cards.
                         */
                        DisplayOpenCards();
                        
                        /*
                         * Call the method that prints each player's chips and cards.
                         */
                        showChipsAndHand();
                        
                        /*
                         * 4TH ROUND OF BETTING.
                         */
                        System.out.println("\n---------------------------------");
                        System.out.println("4TH ROUND OF BETTING.");
                        System.out.println("---------------------------------");
                                
                        BettingRound();
                                
                        System.out.println("\n---------------------------------");
            
                        
                        // Check if there is a winner.
                        if (stillPlayingInTurn == 1)
                        {
                            // Call the method to announce the winner.
                            WinnerFound();
                        }
                        else
                        {
                            /*
                             * The dealer deals the 5th open card to the players.
                             */
                            System.out.println("\nThe fifth open card is dealt to the players.");
                            for (j=0;j<p.size();j++)
                            {
                                card2 = tempDeck.get(counter);
                                if (p.get(j).getIsInTurn() == true)
                                {
                                    p.get(j).addCard(card2);
                                    p.get(j).addOpenCard(card2);
                                }
                                tempDeck.remove(counter);
                                counter--;
                                // Telos, emfanizoume tin karta.
                                //System.out.println(card2);
                            }
                            
                            /*
                             * Call the method that prints all open cards.
                             */
                            DisplayOpenCards();
                            
                            /*
                             * Call the method that prints the chips and the cards.
                             */
                            showChipsAndHand();
                            
                            /*
                             * 5TH ROUND OF BETTING.
                             */
                            System.out.println("\n---------------------------------");
                            System.out.println("5TH ROUND OF BETTING.");
                            System.out.println("---------------------------------");
                            
                            BettingRound();
                            
                            System.out.println("\n---------------------------------");
                            
                            
                            // Check if there is a winner.
                            if (stillPlayingInTurn == 1)
                            {
                                // Call the method to announce the winner.
                                WinnerFound();
                            }
                            else
                            {
                                /*
                                 * The dealer deals the 6th open card.
                                 */
                                System.out.println("\nThe third close card is dealt to the players.");
                                for (j=0;j<p.size();j++)
                                {
                                    card2 = tempDeck.get(counter);
                                    if (p.get(j).getIsInTurn() == true)
                                    {
                                        p.get(j).addCard(card2);
                                        
                                    }
                                    tempDeck.remove(counter);
                                    counter--;
                                }
                                
                                /*
                                 * Call the method that prints all open cards.
                                 */
                                DisplayOpenCards();
                                
                                /*
                                 * Call the method that prints the chips and cards.
                                 */
                                showChipsAndHand();
                            
                                /*
                                 * 6TH ROUND OF BETTING.
                                 */
                                // Minima gia ton xristi.
                                System.out.println("\n---------------------------------");
                                System.out.println("6TH ROUND OF BETTING.");
                                System.out.println("---------------------------------");
                            
                                BettingRound();
                                
                                System.out.println("\n---------------------------------");
                            
                            
                                // Check if there is a winner.
                               if (stillPlayingInTurn == 1)
                               {
                                   // Call the method to announce the winner.
                                    WinnerFound();
                               }
                               else
                               {
                                    System.out.println("\n---------------------------------");
                                    System.out.println("TIME TO OPEN THE CARDS.");
                                    System.out.println("---------------------------------");
                                
                                    min = 100;
                                    minNumber = 1;
                                    minIndex = -1;
                                    // Winner's time!
                                    for (i=0;i<stillPlayingInGame;i++)
                                    {
                                        if (p.get(i).getIsInTurn() == true)
                                        {
                                            if (p.get(i).getIsUser() == false)
                                            {
                                                System.out.println("\nPlayer " + (i+1) + "'s hand:");
                                            }
                                            else
                                            {
                                                System.out.println("\nYour hand:");
                                            }
                                            p.get(i).displayCardsOnHand();
                                            
                                            /*
                                            for (j=0;j<p.get(i).getCardsOnHandSize();j++)
                                            {
                                                x = p.get(i).getCard(j).indexOf(' ');
                                                number = p.get(i).getCard(j).substring(0, x);
                                                colour = p.get(i).getCard(j).substring(x+1);
                                                arrayNumber[j] = number;
                                                arrayColour[j] = colour;
                                            }
                                            */
                                            
                                            b = new CheckHand2(arrayListNumber, arrayListColour);
                                            finalResult.add(i, b.results());
                                            if (finalResult.get(i) < min)
                                            {
                                                min = finalResult.get(i);
                                                minIndex = i;
                                            }   
                                            if (finalResult.get(i) == min)
                                            {
                                                minNumber++;
                                            }
                                            maxNumCard.add(i, b.maxNumber);
                                        }   
                                        else    
                                        {
                                            finalResult.add(i, 100);
                                            maxNumCard.add(i, 0);
                                        }
                                    }
                                
                                    if (minNumber == 1)
                                    {
                                        if (p.get(minIndex).getIsUser() == true)
                                        {
                                            System.out.println("Congratz! You are the winner of this turn! You won " + bank + " chips!\n");
                                        }
                                        else
                                        {
                                            System.out.println("Player " + (i+1) + " is the winner of the turn! He/she won " + bank + " chips!\n");
                                        }
                                        chips = p.get(i).getCurrentChips();
                                        chips = chips + bank;
                                        p.get(i).setCurrentChips(chips);
                                    }
                                    else
                                    {
                                        maxIndex = -1;
                                        max = 0;
                                        for (i=0;i<stillPlayingInGame;i++)
                                        {
                                            if ((p.get(i).getIsInTurn() == true) && (finalResult.get(i) == min))
                                            {
                                                if (maxNumCard.get(i) > max)
                                                {
                                                    max = maxNumCard.get(i);
                                                    maxIndex = i;
                                                }
                                            }
                                        }
                                    
                                        if (p.get(maxIndex).getIsUser() == true)
                                        {
                                            System.out.println("\nCongratz! You are the winner of this turn! You won " + bank + " chips!\n");
                                        }
                                        else
                                        {
                                            System.out.println("\nPlayer " + (maxIndex+1) + " is the winner of the turn! He/She won " + bank + " chips!\n");
                                        }
                                        chips = p.get(maxIndex).getCurrentChips();
                                        chips = chips + bank;
                                        p.get(maxIndex).setCurrentChips(chips);
                                    }
                                    
                                    
                               }// END IF AFTER THE 6TH ROUND OF BETTING
                            }// END IF AFTER 5TH ROUND OF BETTING
                        }// END IF AFTER 4TH ROUND OF BETTING
                    }// END IF AFTER 3RD ROUND OF BETTING
                }// END IF AFTER 2ND ROUND OF BETTING
            }// END IF AFTER 1ST ROUND OF BETTING
            
            /*
             * Call the method that initializes the players.
             */
            initializePlayers();
            
        } //END while (stillPlayingInGame != 1)
        
        if (p.get(0).getIsUser() == false)
        {
            System.out.println("You lost the game.");
        }
        else
        {
            System.out.println("Congratz!!! You are the winner of the table!.");
        }
        
        
        
        tempDeck.clear();
    }
    
    /**
     * A method that prints all open cards.
     */
    public void DisplayOpenCards()
    {
        for (i=0;i<p.size();i++)
        {
            System.out.println("\n");
            if (p.get(i).getIsUser() == true)
            {
                System.out.println("Your chips: " + p.get(i).getCurrentChips());
                System.out.println("Your open cards:");
            }
            else
            {
                System.out.println("Player " + (i+1) + "'s chips: " +
                        p.get(i).getCurrentChips());
                System.out.println("Player " + (i+1) + "'s open cards:");
            }
            p.get(i).displayOpenCards();
        }
    }
    
    /**
     * A method that simulates a betting round.
     */
    public void BettingRound()
    {
        playersReady = 0;
        while (playersReady < stillPlayingInTurn)
        {
            // For each player in the table...
            for (i=0;i<stillPlayingInGame;i++)
            {
                // ...check if he is in this turn and if he is not ready...
                if ((p.get(i).getIsInTurn() == true) && (p.get(i).getIsReady() == false))
                {
                    // ...and check if he is the user.
                    if (p.get(i).getIsUser() == false)
                    {
                        /*
                         * We create 2 array lists, one with the values of the
                         * cards (arrayNumber) and one with the colours of the
                         * cards (arrayColour).
                         */
                        for (j=0;j<p.get(i).getCardsOnHandSize();j++)
                        {
                            x = p.get(i).getCard(j).indexOf(' ');
                            number = p.get(i).getCard(j).substring(0, x);
                            colour = p.get(i).getCard(j).substring(x+1);
                            arrayListNumber.add(number);
                            arrayListColour.add(colour);
                        }
                        //System.out.println("Player " + (i+1) + ":");
                        b = new CheckHand2(arrayListNumber, arrayListColour);
                        result = b.results();
                        
                        // We create a new random variable.
                        Random r = new Random();
                        
                        // We assign a percentage of bluffing chance to a variable.
                        bluffChance = r.nextInt(100);
                        //System.out.println("Bluff chance: " + bluffChance + " %.");

                        // If the bluff chance is higher than 75%...
                        if (bluffChance > 75)
                        {
                            /*
                             * ...we set result to be lower than 10, so that the
                             * player will bluff.
                             */
                            result = 5;
                        }
                        
                        
                        /*
                         * If the most powerful hand is at least equal to 
                         * Two Pair (which is the number 9 in our list of the
                         * hands' importance - 1 is the most importand) the
                         * player takes part in this betting round.
                         */
                        // If no one has bet yet...
                        if (somebodyHasBet == 0)
                        {
                            // ...if the hand is good...
                            if (result < 10)
                            {
                                /*
                                 * If the total amount of chips bet so far by
                                 * the player is lower than the Cap Limit and if
                                 * the player has enough chips to place a bet...
                                 */
                                if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() > 0)
                                {
                                    // ...the player bets one chip...
                                    System.out.println("Player " + (i+1) + " bets " + "1 Chip.");
                                    // ...which is removed from his current chips.
                                    chips = p.get(i).getCurrentChips() - 1;
                                    p.get(i).setCurrentChips(chips);
                                    
                                    // We update the bank.
                                    bank = bank + 1;
                                    
                                    // We update betSoFar.
                                    testBet = betSoFar.get(i);
                                    testBet = testBet + 1;
                                    betSoFar.set(i, testBet);
                                    
                                    // We set as 1 the bet that someone placed.
                                    somebodyHasBet = 1;
                                    
                                    // We initialize playersReady.
                                    playersReady = 1;
                                    
                                    // And we consider the player as ready.
                                    p.get(i).setIsReady(true);
                                    
                                    // For each player...
                                    for (z=0;z<stillPlayingInGame;z++)
                                    {
                                        // ...not having the i index...
                                        if(z!=i)
                                        {
                                            // ...set him as not ready.
                                            p.get(z).setIsReady(false);
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Player " + (i+1) + ": \"Check.\"");
                                    playersReady++;
                                    p.get(i).setIsReady(true);
                                }
                            }
                            else
                            {
                                System.out.println("Player " + (i+1) + ": \"Check.\"");
                                playersReady++;
                                p.get(i).setIsReady(true);
                            }
                        }
                        else
                        {
                            // ...if the hand is good enough...
                            if (result < 10)
                            {
                                /*
                                 * If the total amount of chips bet so far by
                                 * the player is lower than the Cap Limit and if
                                 * the player has enough chips to place a bet...
                                 */
                                if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() >= somebodyHasBet)
                                {
                                    // ...the player calls the bet...
                                    System.out.println("Player " + (i+1) + " bets " + somebodyHasBet + " chips.");
                                    // ...which is removed from his chips.
                                    chips = p.get(i).getCurrentChips() - somebodyHasBet;
                                    p.get(i).setCurrentChips(chips);
                                    playersReady++;
                                    p.get(i).setIsReady(true);
                                    
                                    // We update the bank.
                                    bank = bank + somebodyHasBet;
                                    
                                    // We update betSoFar.
                                    testBet = betSoFar.get(i);
                                    testBet = testBet + somebodyHasBet;
                                    betSoFar.set(i, testBet);
                                }
                                else
                                {
                                    System.out.println("Player " + (i+1) + ": \"I'm out.\"");
                                    stillPlayingInTurn--;
                                    p.get(i).setIsInTurn(false);
                                }
                            }
                            else
                            {
                                System.out.println("Player " + (i+1) + ": \"I'm out.\"");
                                stillPlayingInTurn--;
                                p.get(i).setIsInTurn(false);
                            }
                        }
                    }
                    else
                    {
                        // ...if no one has bet yet...
                        if (somebodyHasBet == 0)
                        {
                            System.out.println("No one has bet yet.");
                            do
                            {
                                System.out.println("Press 1 to bet.");
                                System.out.println("Press 2 to check.");
                                Scanner scanner = new Scanner(System.in);
                                choice1 = scanner.nextInt();
                                if (choice1 < 1 || choice1 > 2)
                                {
                                    System.out.println("Incorrent input. Try again.");
                                }
                            } while (choice1 < 1 || choice1 > 2);
                            
                            // If the user wants to bet...
                            if (choice1 == 1)
                            {
                                // ...check if he can to.
                                if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() > 0)
                                {
                                    // Print a menu.
                                    System.out.println("How much do you wanna bet?");
                                    do
                                    {
                                        System.out.print("You can bet between 1 and " + bet.getFixedLimit() + " chips.");
                                        Scanner scanner = new Scanner(System.in);
                                        choice2 = scanner.nextInt();
                                        if (choice2 < 1 || choice2 > bet.getFixedLimit() || choice2 > p.get(i).getCurrentChips())
                                        {
                                            System.out.println("You can't bet that much. Try again.");
                                        }
                                    } while (choice2 < 1 || choice2 > bet.getFixedLimit() || choice2 > p.get(i).getCurrentChips());
                                    
                                    // Update his chips.
                                    chips = p.get(i).getCurrentChips() - choice2;
                                    p.get(i).setCurrentChips(chips);
                                    
                                    // Update the bet that someone placed.
                                    somebodyHasBet = choice2;
                                    System.out.println("You bet " + somebodyHasBet + " chips.");
                                    
                                    // Initialize playersReady.
                                    playersReady = 1;
                                    
                                    // Update the bank.
                                    bank = bank + somebodyHasBet;
                                    
                                    // Update betSoFar.
                                    testBet = betSoFar.get(i);
                                    testBet = testBet + somebodyHasBet;
                                    betSoFar.set(i, testBet);
                                    
                                    // Set the player as ready...
                                    p.get(i).setIsReady(true);
                                    
                                    // For each player in the game...
                                    for (z=0;z<stillPlayingInGame;z++)
                                    {
                                        // ...that he has not index i...
                                        if(z!=i)
                                        {
                                            // ...set him as not ready.
                                            p.get(z).setIsReady(false);
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Sorry, you can't bet.");
                                    playersReady++;
                                    // Set the player as ready.
                                    p.get(i).setIsReady(true);
                                }
                            }
                            else if (choice1 == 2)
                            {
                                System.out.println("You: \"Check.\"");
                                playersReady++;
                                
                                // Set the player as ready.
                                p.get(i).setIsReady(true);
                            }
                        }
                        else
                        {
                            // Print a menu.
                            System.out.println("Someone has bet " + somebodyHasBet + " chips.");
                            System.out.println("What do you wanna do?");
                            do
                            {
                                System.out.println("Press 1 to call.");
                                System.out.println("Press 2 to stop.");
                                System.out.println("Press 3 to raise.");
                                Scanner scanner = new Scanner(System.in);
                                choice1 = scanner.nextInt();
                                if (choice1 < 1 || choice1 > 3)
                                {
                                    System.out.println("Incorrent input. Try again.");
                                }
                            } while (choice1 < 1 || choice1 > 3);
                            
                            // If the user wants to call the bet...
                            if (choice1 == 1)
                            {
                                // ...check if he can to...
                                if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() >= somebodyHasBet)
                                {
                                    chips = p.get(i).getCurrentChips() - somebodyHasBet;
                                    p.get(i).setCurrentChips(chips);
                                    playersReady++;
                                    
                                    System.out.println("You called the bet.");
                                    
                                    // Update the bank.
                                    bank = bank + somebodyHasBet;
                                    
                                    // Update betSoFar.
                                    testBet = betSoFar.get(i);
                                    testBet = testBet + somebodyHasBet;
                                    betSoFar.set(i, testBet);
                                    
                                    // Set the player as ready.
                                    p.get(i).setIsReady(true);
                                }
                                else
                                {
                                    System.out.println("Sorry, you can't call. You are out.");
                                    stillPlayingInTurn--;
                                    p.get(i).setIsInTurn(false);
                                }
                            }
                            else if (choice1 == 2)
                            {
                                System.out.println("You: \"I'm out.\"");
                                stillPlayingInTurn--;
                                p.get(i).setIsInTurn(false);
                            }
                            else if (choice1 == 3)
                            {
                                // Check if someone else hasn't raised already.
                                if (somebodyHasRaised == 0)
                                {
                                    System.out.println("How much you wanna raise?");
                                    do
                                    {
                                        Scanner scanner = new Scanner(System.in);
                                        choice3 = scanner.nextInt();
                                        if (choice3 < 1 || choice3 > 5) // 5 is the upper limit.
                                        {
                                            System.out.println("Incorrent input. Try again.");
                                        }
                                    } while (choice3 < 1 || choice3 > 5);
                                    
                                    if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() >= choice3 )
                                    {
                                        chips = p.get(i).getCurrentChips() - choice3;
                                        p.get(i).setCurrentChips(chips);
                                        playersReady++;
                                        
                                        // Update the bet that someone placed.
                                        somebodyHasBet = choice3;
                                        System.out.println("You raised the bet to " + somebodyHasBet + " chips.");
                                        
                                        // Initialize playersReady.
                                        playersReady = 1;
                                        
                                        // Update the bank.
                                        bank = bank + somebodyHasBet;
                                        
                                        // Update betSoFar.
                                        testBet = betSoFar.get(i);
                                        testBet = testBet + somebodyHasBet;
                                        betSoFar.set(i, testBet);
                                        
                                        // Set the player as ready...
                                        p.get(i).setIsReady(true);
                                        
                                        // For each player in the game...
                                        for (z=0;z<stillPlayingInGame;z++)
                                        {
                                            // ...that he has not index i...
                                            if(z!=i)
                                            {
                                                // ...set him as not ready.
                                                p.get(z).setIsReady(false);
                                            }
                                        }
                                        somebodyHasRaised = somebodyHasBet;
                                    }
                                    else
                                    {
                                        System.out.println("Sorry, you can't bet that much.");
                                        System.out.println("What do you wanna do?");
                                        do
                                        {
                                            System.out.println("Press 1 to call.");
                                            System.out.println("Press 2 to stop.");
                                            Scanner scanner = new Scanner(System.in);
                                            choice4 = scanner.nextInt();
                                            if (choice4 < 1 || choice4 > 2)
                                            {
                                                System.out.println("Incorrent input. Try again.");
                                            }
                                        } while (choice4 < 1 || choice4 > 2);
                                        
                                        // If the user wants to call the bet...
                                        if (choice1 == 1)
                                        {
                                            // ...check if he can to...
                                            if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() >= somebodyHasBet)
                                            {
                                                chips = p.get(i).getCurrentChips() - somebodyHasBet;
                                                p.get(i).setCurrentChips(chips);
                                                playersReady++;
                                                
                                                // Update the bank.
                                                bank = bank + somebodyHasBet;
                                                
                                                // Update betSoFar.
                                                testBet = betSoFar.get(i);
                                                testBet = testBet + somebodyHasBet;
                                                betSoFar.set(i, testBet);
                                                
                                                // Set the player as ready.
                                                p.get(i).setIsReady(true);
                                            }
                                            else
                                            {
                                                System.out.println("Sorry, you can't call. You are out.");
                                                stillPlayingInTurn--;
                                                p.get(i).setIsInTurn(false);
                                            }
                                        }
                                        else if (choice1 == 2)
                                        {
                                            System.out.println("You: \"I'm out.\"");
                                            stillPlayingInTurn--;
                                            p.get(i).setIsInTurn(false);
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Someone has already raised");
                                    System.out.println("What do you wanna do?");
                                    do
                                    {
                                        System.out.println("Press 1 to call.");
                                        System.out.println("Press 2 to stop.");
                                        Scanner scanner = new Scanner(System.in);
                                        choice4 = scanner.nextInt();
                                        if (choice4 < 1 || choice4 > 2)
                                        {
                                            System.out.println("Incorrent input. Try again.");
                                        }
                                    } while (choice4 < 1 || choice4 > 2);
                                    
                                    // If the user wants to call the bet...
                                    if (choice1 == 1)
                                    {
                                        // ...check if he can to...
                                        if (betSoFar.get(i) < bet.getCapLimit() && p.get(i).getCurrentChips() >= somebodyHasBet)
                                        {
                                            chips = p.get(i).getCurrentChips() - somebodyHasBet;
                                            p.get(i).setCurrentChips(chips);
                                            playersReady++;
                                                
                                            // Update the bank.
                                            bank = bank + somebodyHasBet;
                                                
                                            // Update betSoFar.
                                            testBet = betSoFar.get(i);
                                            testBet = testBet + somebodyHasBet;
                                            betSoFar.set(i, testBet);
                                                
                                            // Set the player as ready.
                                            p.get(i).setIsReady(true);
                                        }
                                        else
                                        {
                                            System.out.println("Sorry, you can't call. You are out.");
                                            stillPlayingInTurn--;
                                            p.get(i).setIsInTurn(false);
                                        }
                                    }
                                    else if (choice1 == 2)
                                    {
                                        System.out.println("You: \"I'm out.\"");
                                        stillPlayingInTurn--;
                                        p.get(i).setIsInTurn(false);
                                    } // END IF if (choice1 == 1)
                                } // END if (somebodyHasRaised != 0)
                            } // END if (choice1 == 1)
                        } // END if (somebodyHasBet == 0)
                    } // END if (p.get(i).getIsUser() == false)
                } // END if ((p.get(i).getIsInTurn() == true) && (p.get(i).getIsReady() == false))
            } // END for (i=0;i<stillPlayingInGame;i++)
        } // END while (playersReady < stillPlayingInTurn)
        
        somebodyHasRaised = 0;
        somebodyHasBet = 0;
        /*
         * Set all players as not ready.
         */
        for(i=0;i<stillPlayingInGame;i++)
        {
            p.get(i).setIsReady(false);
        }
    }
}
