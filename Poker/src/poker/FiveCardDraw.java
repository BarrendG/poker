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
public class FiveCardDraw extends GameOfPoker {
    
    /**
     * A new object of the CheckHand Class.
     */
    private CheckHand b;
    
    
    /**
     * The Class Constructor.
     */
    public FiveCardDraw(int type, ArrayList<Player> pl)
    {
        super(type,pl);
        stillPlayingInGame = pl.size();
        arrayNumber = new String[5];
        arrayColour = new String[5];
        importanceArray = new boolean[5];
        bet = new Betting();
        betSoFar = new ArrayList();
        finalResult = new ArrayList();
        maxNumCard = new ArrayList();
    }
    
    /**
     * A method that returns a random number between 0 and n.
     * @param n The field of the numbers.
     * @return A random number.
     */
    public int Randomizer1(int n)
    {
        rand1 = randomGenerator.nextInt(n);
        return rand1;
    }
   
    /**
     * A method that returns a random number between 0 and the size of the list
     * of players.
     * @return A random number.
     */
    public int Randomizer2()
    {
        rand1 = randomGenerator.nextInt(100);
        return rand1;
    }
    
    /**
     * The method that starts the game.
     */
    public void Play()
    {
        // We initialize betSoFar.
        initializeBetSoFar();
        
        while (stillPlayingInGame!=1)
        { 
            // An starting turn message.
            System.out.println("\n---------------------------------");
            System.out.println("A NEW TURN STARTS.");
            System.out.println("---------------------------------");
        
            // We create a new deck of cards.
            tempDeck = new ArrayList(deck.getCards());
            // We shuffle the deck.
            Collections.shuffle(tempDeck);
            
            /*
             * A procedure that deals the cards to the players.
             */
            counter = 51;
            // Till every player has 5 cards...
            for (i=0;i<5;i++)
            {
                // ...for each player...
                for (j=0;j<p.size();j++)
                {
                    //...create a card and...
                    card2 = tempDeck.get(counter);
                    //...give it to the player and then...
                    p.get(j).addCard(card2);
                    // ...remove it from the deck.
                    tempDeck.remove(counter);
                    counter--;
                    //System.out.println(card2);
                }
            }
            
            // Print the values of ante and of the 2 blinds.
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
             * A method that says who is the dealer and which players must
             * place the 2 blinds
             */
            initialBetting();
            
            /*
             * A method that prints the chips and the cards of the players.
             */
            showChipsAndHand();
            
            /*
             * A method that finds out how many players compete in this turn.
             */
            calculatePlayersInTurn();
            
            
            /*
             * FIRST ROUND OF BETTING.
             */
            System.out.println("---------------------------------");
            System.out.println("1ST ROUND OF BETTING.");
            System.out.println("---------------------------------");
            
            BettingRound();
            
            System.out.println("\n---------------------------------\n");
            
            /*
             * A procedure in which each player returns the cards that he doesnt
             * want and takes new ones.
             */
            // For each player still in the game...
            for (i=0;i<stillPlayingInGame;i++)
            {
                // ...check if he is in this turn...
                if (p.get(i).getIsInTurn() == true)
                {
                    // ...and if he is the user...
                    if (p.get(i).getIsUser() == false)
                    {
                        for (j=0;j<5;j++)
                        {
                            x = p.get(i).getCard(j).indexOf(' ');
                            number = p.get(i).getCard(j).substring(0, x);
                            colour = p.get(i).getCard(j).substring(x+1);
                            arrayNumber[j] = number;
                            arrayColour[j] = colour;
                        }
                        //System.out.println("\nPlayer " + (i+1) + ":");
                        b = new CheckHand(arrayNumber, arrayColour);
                        
                        // We take a table that says which cards are important.
                        importanceArray = b.cardsToGiveBack();
                        
                        /*
                         * We remove every non important card from the player.
                         * This procedure must be implemented backwards.
                         */
                        cardsGivenAway = 0;
                        for (j=4;j>-1;j--)
                        {
                            if (importanceArray[j] == false)
                            {
                                p.get(i).removeCard(j);
                                // We calculate how many cards the player has returned back.
                                cardsGivenAway++;
                            }
                        }
                        
                        // We deal to the player as many new cards as the ones he returned. 
                        for (j=0;j<cardsGivenAway;j++)
                        {
                            card2 = tempDeck.get(counter);
                            p.get(i).addCard(card2);
                            tempDeck.remove(counter);
                            counter--;
                            //System.out.println(card2);
                        }
                        //System.out.println("Player " + "'s hand:");
                        //p.get(i).displayCardsOnHand();
                        
                    }
                    else
                    {
                        cardsGivenAway = 0;
                        currentCards = 5;
                        // We make a menu with 6 choices for the user.
                        do
                        {
                            System.out.println("\nWhich card do you want to return?");
                            System.out.println("Press:");
                            for(j=0;j<currentCards;j++)
                            {
                                System.out.println((j+1) + " for " + p.get(i).getCard(j));
                            }
                            System.out.println("If you don't want to return any more cards, press 0.");
                            do
                            {
                                Scanner scanner = new Scanner(System.in);
                                choice1 = scanner.nextInt();
                                if (choice1 < 0 || choice1 > currentCards)
                                {
                                    System.out.println("Pick a choice between 0 and " + currentCards + ".");
                                }
                            } while (choice1 < 0 || choice1 > currentCards);
                            
                            // We remove the card that the user doesn't want.
                            if (choice1 != 0)
                            {
                                card3 = choice1 - 1;
                                p.get(i).removeCard(card3);
                                
                                // We update the counters.
                                currentCards--;
                                cardsGivenAway++;
                            }
                        }while (choice1 != 0);
                        
                        // We deal to the user as many new cards as the ones he returned.
                        for (j=0;j<cardsGivenAway;j++)
                        {
                            card2 = tempDeck.get(counter);
                            p.get(i).addCard(card2);
                            tempDeck.remove(counter);
                            counter--;
                            //System.out.println(card2);
                        }
                        System.out.println("\nYour hand:");
                        p.get(i).displayCardsOnHand();
                        
                    }
                }
            }
            
            /*
             * We find out how many players compete in this turn.
             */
            calculatePlayersInTurn();
            
            // We check if there is a winner.
            if (stillPlayingInTurn == 1)
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
            else
            {
                // If there is not a winner yet, we go to the 2nd round of betting.
                
                System.out.println("\n---------------------------------");
                System.out.println("2ND ROUND OF BETTING.");
                System.out.println("---------------------------------");
                
                BettingRound();
                
                System.out.println("\n---------------------------------\n");
                System.out.println("\n---------------------------------");
                System.out.println("TIME TO OPEN THE CARDS.");
                System.out.println("---------------------------------");
                
                min = 100;
                minNumber = 1;
                minIndex = -1;
                // The time of the winner.
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
                        
                        for (j=0;j<5;j++)
                        {
                            x = p.get(i).getCard(j).indexOf(' ');
                            number = p.get(i).getCard(j).substring(0, x);
                            colour = p.get(i).getCard(j).substring(x+1);
                            arrayNumber[j] = number;
                            arrayColour[j] = colour;
                        }
                        b = new CheckHand(arrayNumber, arrayColour);
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
                        System.out.println("Congratz! You are the winner of this turn! You won " + bank + " chips!");
                    }
                    else
                    {
                        System.out.println("Player " + (i+1) + " is the winner of the turn! He/she won " + bank + " chips!");
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
                        System.out.println("\nCongratz! You are the winner of this turn! You won " + bank + " chips!");
                    }
                    else
                    {
                        System.out.println("\nPlayer " + (maxIndex+1) + " is the winner of the turn! He/She won " + bank + " chips!");
                    }
                    chips = p.get(maxIndex).getCurrentChips();
                    chips = chips + bank;
                    p.get(maxIndex).setCurrentChips(chips);
                    
                    
                }
            }
            
            
            
            /*
             * We call the method that initializes the players.
             */
            initializePlayers();
            
            
        }// END while (stillPlayingInGame!=1)
        
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
                // ... check if he is in this turn and if he is not ready...
                if ((p.get(i).getIsInTurn() == true) && (p.get(i).getIsReady() == false))
                {
                    // ...and check if he is the user.
                    if (p.get(i).getIsUser() == false)
                    {
                        /*
                         * We create 2 tables, one with the values of the cards
                         * (arrayNumber) and one with the colours of the cards
                         * (arrayColour).
                         */
                        for (j=0;j<5;j++)
                        {
                            x = p.get(i).getCard(j).indexOf(' ');
                            number = p.get(i).getCard(j).substring(0, x);
                            colour = p.get(i).getCard(j).substring(x+1);
                            arrayNumber[j] = number;
                            arrayColour[j] = colour;
                        }
                        //System.out.println("Player " + (i+1) + ":");
                        b = new CheckHand(arrayNumber, arrayColour);
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