/* ------------------------------------------------------------------------------
*  To Do: implement the Fifteen Puzzle using the Java Swing Library GUI elements. 
*  ------------------------------------------------------------------------------
*/

/* The game class creates the grid and its positions
 * It also contains all the functions associated with the menu
 * Keeps track of solvable puzzle using inversion 
 * And display the message "Solved!" when the puzzle is solved
 * Starts the new game
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.*;

public class game extends JPanel {
	final grid grid = new grid();
	private Stack<Integer> stack = new Stack<Integer>();
	
	// getter function to get stack
	public Stack<Integer> getStack() {
		return stack;
	}
	
	// getter function to get grid
	public grid getGrid() {
		return grid;
	}
	
    public game() {

    		// grid dimension
        final int dim = 460;	
    
        // sets the grid by margin, tile size, background & foreground color, and font
        grid.setMargin(10);
        grid.setTileSize ((dim - 2 * grid.getMargin()) / grid.getSide());
        grid.setGridSize(grid.getTileSize() * grid.getSide());
    
        grid.setPreferredSize(new Dimension(dim, dim + grid.getMargin()));
        grid.setBackground(Color.black);
        grid.setForeground(new Color(0x1AA5ED)) ;
        grid.setFont(new Font("Arial Black", Font.BOLD, 70));
        
        // setting boolean value to keep track of game - if its finished or not
        grid.setGameOver(false);

        addMouseListener(new MouseAdapter()	// anonymous inner class
        {
            @Override
            // display positions when user clicks
            public void mousePressed(MouseEvent e) {
                if (grid.getGameOver()) {
                    newGame(grid);

                } else {

                    int ex = e.getX() - grid.getMargin();
                    int ey = e.getY() - grid.getMargin();

                    if (ex < 0 || ex > grid.getGridSize() || ey < 0 || ey > grid.getGridSize()) {
                        return;
                    }

                    //divide the positions in the jpanel by the grid get side to get positions
                    int c1 = ex / grid.getTileSize();
                    int r1 = ey / grid.getTileSize();
                    int c2 = grid.getBlankPos() % grid.getSide();
                    int r2 = grid.getBlankPos() / grid.getSide();

                    int clickPos = r1 * grid.getSide() + c1;

                    int dir = 0;
                    if (c1 == c2 && Math.abs(r1 - r2) > 0) {
                        dir = (r1 - r2) > 0 ? 4 : -4;

                    } else if (r1 == r2 && Math.abs(c1 - c2) > 0) {
                        dir = (c1 - c2) > 0 ? 1 : -1;
                    }

                    if (dir != 0) {
                    		do {
                    			// get the index and store it in stack
                    			int index = grid.getTilesValue(grid.getBlankPos() + dir);
                    			stack.push(index);
                        	
                    			int newBlankPos = grid.getBlankPos() + dir;
                    			grid.setTiles(grid.getBlankPos(), grid.getTilesValue(newBlankPos));
                    			grid.setBlankPos(newBlankPos);
                        } while (grid.getBlankPos() != clickPos);
                        grid.setTiles(grid.getBlankPos(),  0);
                		}

                    grid.setGameOver(isSolved(grid));
                }
                repaint();
            }
        });

        newGame(grid);
    }
    
    // shuffle's the game when user clicks on new game menu option and when the game is solved
    public void newGame(grid g) {
        do {
            reset(g);
            shuffle(g);
        } while (!isSolvable(g));
        g.setGameOver(false);
    }

    // resets the grid after game is solved or when user click's shuffle menu
    public void reset(grid g) {
        for (int i = 0; i < g.getTilesLength(); i++) {
            g.setTiles(i , (i + 1) % g.getTilesLength());
        }
        g.setBlankPos(g.getTilesLength() - 1);
    }
    
    // function undo the previous move by popping the index from the stack and swapping it
    public void undo(grid g) {
    		int tl = stack.pop();
    		int blankPos = g.getTilePos(tl);
    	
    		g.setTiles(g.getBlankPos(), tl);
    		g.setTiles(blankPos, 0);
    		g.repaint();
          
        g.setBlankPos(blankPos);
        g.repaint();
    }
    
    // function undo's all the moves and get's back to the beginning of the game
    public void allUndo(grid g) {
    		while(!stack.isEmpty()) {
    			undo(g);
    		}
    }

    // shuffle's the grid
    public void shuffle(grid g) {
        int n = g.getNumTiles();
        while (n > 1) {
            int r = g.getRandom().nextInt(n--);
            int tmp = g.getTilesValue(r);
            g.setTiles(r, g.getTilesValue(n));
            g.setTiles(n, tmp);
        }
    }

    // makes sure if the puzzle is solvable using inversion
    private boolean isSolvable(grid g) {
        int countInversions = 0;
        for (int i = 0; i < g.getNumTiles(); i++) {
            for (int j = 0; j < i; j++) {
                if (g.getTilesValue(j) > g.getTilesValue(i)) {
                    countInversions++;
                }
            }
        }
        return countInversions % 2 == 0;
    }

    // keeps track when the puzzle is solved then prints the message
    private boolean isSolved(grid g) {
        if (g.getTilesValue(g.getTilesLength() - 1) != 0) {
            return false;
        }
        for (int i = g.getNumTiles() - 1; i >= 0; i--) {
            if (g.getTilesValue(i) != i + 1) {
                return false;
            }
        }
        return true;
    }

    // set the grid for 4*4 dimension and uses the space of the window. sets the window and grid color, font, font size
    private void drawGrid(Graphics2D g, grid gd) {
        for (int i = 0; i < gd.getTilesLength(); i++) {
            int r = i / gd.getSide();
            int c = i % gd.getSide();
            int x = gd.getMargin() + c * gd.getTileSize();
            int y = gd.getMargin() + r * gd.getTileSize();

            if (gd.getTilesValue(i) == 0) {
                if (gd.getGameOver()) {
                	    g.setFont(getFont().deriveFont(Font.BOLD, 18));
                	    g.setColor(Color.GREEN.darker());
                    drawCenteredString(g, "Solved!", x, y);
                }
                continue;
            }

            g.setColor(getForeground());
            g.fillRoundRect(x, y, gd.getTileSize(), gd.getTileSize(), 25, 25);
            g.setColor(Color.blue.darker());
            g.drawRoundRect(x, y, gd.getTileSize(), gd.getTileSize(), 25, 25);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(gd.getTilesValue(i)), x, y);
        }
    }

    // prints the message to start for starting the new game after solved
    private void drawStartMessage(Graphics2D g, grid gd) {
        if (gd.getGameOver()) {
            g.setFont(getFont().deriveFont(Font.BOLD, 18));
            g.setColor(getForeground());
            String s = "click to start a new game";
            int x = (getWidth() - g.getFontMetrics().stringWidth(s)) / 2;
            int y = getHeight() - gd.getMargin();
            g.drawString(s, x, y);
        }
    }

    //  draws the string in center of the grid
    private void drawCenteredString(Graphics2D g2, String s, int x, int y) {
        FontMetrics fm = g2.getFontMetrics();
        int asc = fm.getAscent();
        int des = fm.getDescent();

        x = x + (grid.getTileSize() - fm.stringWidth(s)) / 2;
        y = y + (asc + (grid.getTileSize() - (asc + des)) / 2);

        g2.drawString(s, x, y);
    }

    // helper function for repaint
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g2 = (Graphics2D) gg;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2,grid);
        drawStartMessage(g2,grid);
    }

} // end of game class
