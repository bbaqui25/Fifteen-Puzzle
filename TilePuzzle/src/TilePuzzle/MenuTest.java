/* The MenuTest class creates the GUI and the menu and sub menus
 * It contains the main
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuTest extends JFrame {
    public static game gm = new game();
	grid g = gm.getGrid();
	 
	// set up GUI
	public MenuTest()
	{
		super( "Using JMenus" );     

		// set up File menu and its menu items
		JMenu fileMenu = new JMenu( "File" );
		fileMenu.setMnemonic( 'F' );
		
		// set up About... menu item
		JMenuItem aboutItem = new JMenuItem( "About..." );
		aboutItem.setMnemonic( 'A' );
		fileMenu.add( aboutItem );
		aboutItem.addActionListener (
				
			new ActionListener() {  // anonymous inner class
		
				// display message dialog when user selects About...
				public void actionPerformed( ActionEvent event )
				{
					JOptionPane.showMessageDialog( MenuTest.this,
						"Bushra Baqui" + "\nThe 2nd programming assignment for CS 342" + "\nDate: Oct 4 2017 \n Written and run on Eclipse IDE - compatible with JDK8",
					"About", JOptionPane.PLAIN_MESSAGE );
				}
		
			}  // end anonymous inner class
		
		); // end call to addActionListener
		 
		// set up Exit menu item
		JMenuItem exitItem = new JMenuItem( "Quit" );
		exitItem.setMnemonic( 'Q' );
		fileMenu.add( exitItem );
		exitItem.addActionListener(
		
		     new ActionListener() {  // anonymous inner class
		
		    	 	// terminate application when user clicks exitItem
		    	 	public void actionPerformed( ActionEvent event )
		    	 	{
		    	 		System.exit( 0 );
		    	 	}
		
		     }  // end anonymous inner class
		
		); // end call to addActionListener
		
		// create menu bar and attach it to MenuTest window
		JMenuBar bar = new JMenuBar();  
		setJMenuBar( bar );  
		bar.add( fileMenu );  
		
		// create shuffle menu
		JMenu shuffle = new JMenu( "Shuffle" );  
		// set up description menu item
		JMenuItem inShuffle = new JMenuItem( "New Game" );
		shuffle.add(inShuffle);
		inShuffle.addActionListener (
				
				new ActionListener() {  // anonymous inner class
					public void actionPerformed ( ActionEvent event )
		    	 		{
						gm.newGame(g);
						gm.repaint();
		    	 		}
		    	 		
				} // end anonymous inner class
				
		); // end call to addActionListener
		  
		// create help menu
		JMenu helpMenu = new JMenu( "Help" );  
		helpMenu.setMnemonic( 'H' );
		
		// set up undo menu item
		JMenuItem undo = new JMenuItem( "Undo ");
		helpMenu.add( undo );
		undo.addActionListener (
				
				new ActionListener() {  // anonymous inner class
					
					public void actionPerformed ( ActionEvent event )
		    	 		{
						gm.undo(g);
						gm.repaint();
		    	 		}
					
				} // end anonymous inner class
				
		); // end call to addActionListener
		
		// set up all undo menu item
		JMenuItem allundo = new JMenuItem( "All Undo ");
		helpMenu.add( allundo );
		allundo.addActionListener (
				
				new ActionListener() {  // anonymous inner class
					
					public void actionPerformed ( ActionEvent event )
		    	 		{
						gm.allUndo(g);
						gm.repaint();
		    	 		}
					
				}// end anonymous inner class
				
		); // end call to addActionListener
		
		// set up description menu item
		JMenuItem description = new JMenuItem( "Description" );
		description.setMnemonic( 'D' );
		helpMenu.add( description );
		description.addActionListener (
		
		     new ActionListener() {  // anonymous inner class
		
		    	 	// display message dialog when user selects About...
		    	 	public void actionPerformed( ActionEvent event )
		    	 	{
		    	 		JOptionPane.showMessageDialog( MenuTest.this,
		    	 			"\" Program Basics \" \nThis is a fifteen tile puzzle. The puzzle consists of a 4x4 grid with the numbers " +
		    	 			"from 1 to 15 on the grid leaving one grid position empty." + 
		    	 			"\nTo solve the puzzle, a number that is next to the empty position is moved " +
		    	 			"into the empty position. \nBy \"next to\", the number can be above, below, " +
		    	 			"to the left or to the right of the empty position. \nThe empty position will now " +
		    	 			"occupy the grid position were the number had been." + 
		    	 			"\n \" Description of all menu operations \" \nThe File menu includes About and Quit submenus." +
		    	 			"\n  Quit the program: This menu button is in addition the quitting your program by clicking " +
		    	 			"on the X on the right side of the title bar. \n  Display an About box: The About box " +
		    	 			"is a simple dialog box that gives the name of the program's author, when the program was written," +
		    	 			"\nThe Help menu gives the description of the program and also contains undo option." +
		    	 			"\n  Undo Previous Move: This put the puzzle back into the previous positioning of the values." +
		    	 			"\n  Undo All Moves: Undo all of the moves done by the user, restoring the puzzle to its state after its last randomization." +
		    	 			"\nThe Shuffle menu rearrange the numbers in the grid into some random but solvable order. ",
		    	 		"Description", JOptionPane.PLAIN_MESSAGE );
		    	 	}
		
		     }  // end anonymous inner class
		
		); // end call to addActionListener
		
		// add help and shuffle menu to menu bar
		bar.add( helpMenu );
		bar.add( shuffle );
		
	} // end constructor
	   
	public static void main (String[] args) {
		MenuTest f = new MenuTest();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("15 Puzzle Game");
		f.add(gm, BorderLayout.CENTER);
		f.setSize(460,500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
	} // end of main

} // end of MenuTest class
