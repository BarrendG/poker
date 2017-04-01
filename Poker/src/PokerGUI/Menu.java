/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PokerGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Michael Chatzakis (1717) - Panagiotis Ilias (1645)
 */
public class Menu extends JFrame {
    
    /**
     * A frame that displays the main window.
     */
    private JFrame frame;
    
    /**
     * A button to start the game.
     */
    private JButton button1;
    
    /**
     * The label for the Number of players.
     */
    private JLabel labelNumberOfPlayers;
    
    /**
     * The field for the user to entry the number of players.
     */
    private JTextField textNumberOfPlayers;
    
    /**
     * The label for the Starting Chips per player.
     */
    private JLabel labelStartingChips;
    
    /**
     * The field for the user to entry the Starting Chips per player.
     */
    private JTextField textStartingChips;
           
    /**
     * The Class Constructor.
     */
    public Menu()
    {
        // Call the method for the initiliazation.
        makeFrame();
    }
    
    /**
     * A method that implements the window.
     */
    private void makeFrame()
    {
        // Create a new frame with the name "Poker".
        frame = new JFrame("Poker");
        
        // Create a new container.
        Container contentPane = frame.getContentPane();
        // Create a label and add it to the container.
        final JLabel label = new JLabel("Welcome to our game!! Are you ready to bluff?");
        contentPane.add(label);
        
        // Make proper arrangements of the elements in the frame.
        frame.pack();
        
        // Configure the window size.
        frame.setSize(700, 500);
        
        // Set its location at the center.
        frame.setLocationRelativeTo(null);
        
        // Make the elements visible.
        frame.setVisible(true);
        
        // Allow the user to be able to resize the window.
        frame.setResizable(true);
        
        // Create the orizontal menu bar.
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // Create and add a choice "File" in the menubar.
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        // Create and add the "Quit" option in the File option of the menubar.
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                System.exit(0);
            }
        });
        fileMenu.add(quitItem);
        
        // Create a layout manager (GridLayout)
        final JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(0,1));
        
        // Create and add a button that starts the game.
        button1 = new JButton("Start");
        button1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                label.setText("Enter the number of players sitting per table "
                        + "and the number of starting chips for each player.");
                
                // Create the fields where the user entries his choices.
                labelNumberOfPlayers = new JLabel("Number of players:");
                toolbar.add(labelNumberOfPlayers);
                textNumberOfPlayers = new JTextField();
                toolbar.add(textNumberOfPlayers);
                
                labelStartingChips = new JLabel("Amount of starting chips");
                toolbar.add(labelStartingChips);
                textStartingChips = new JTextField();
                toolbar.add(textStartingChips);
                
                // Create and add a new button that sends you to the Table Class.
                JButton buttonEnter = new JButton("Enter the data");
                buttonEnter.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        int numberOfPlayers = Integer.parseInt(textNumberOfPlayers.getText());
                        int startingChips = Integer.parseInt(textStartingChips.getText());
                        /*
                         * In the future, in a tournament with more than one tables, we will
                         * have an array list of tables. In the present we are adressing the
                         * issue of a single table.
                         */
                        Table t = new Table(numberOfPlayers, startingChips, frame);
                        
                        // Initialization.
                        t.initialize();
                        // Display the state of the players.
                        t.displayPlayers();
                        // Start the game.
                        t.start();
                    }
                });
                toolbar.add(buttonEnter);
            }
        });
        toolbar.add(button1);
        JPanel flow = new JPanel();
        flow.add(toolbar);
        // Put the field in the bottom part of the window.
        contentPane.add(toolbar, BorderLayout.SOUTH);
    }
    
    
}
