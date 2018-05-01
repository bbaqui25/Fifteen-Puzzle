/*
 * The grid class contains all the required getters and setters functions for the tile, margin, blank tile etc
*/

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class grid extends JPanel {
	 private final int side = 4;
	 
	 // getter & setter functions for creating the grid
	 public int getSide() {
	        return side;
	 }

	 private int tileSize;
	 public void setTileSize(int ts) {
        tileSize = ts;
	 }
	 public int getTileSize() {
        return tileSize;
	 }

	 private int gridSize;
	 public void setGridSize(int gs) {
        gridSize = gs;
	 }
	 public int getGridSize() {
        return gridSize;
	 }

	 private int margin;
	 public void setMargin(int m) {
        margin = m;
	 }
	 public int getMargin() {
        return margin;
	 }

	 private final int numTiles = side * side - 1;
	 public int getNumTiles() {
        return numTiles;
	 }

	 private final Random rand = new Random();
	 public Random getRandom() {
        return rand;
	 }

	 private int[] tiles = new int[numTiles + 1];
	 
	 public int getTilePos(int tl) {
		 int index = 0;
		 while(index!= this.tileSize) {
			 if (tiles[index]==tl)
				 return index;
			 index++;
		 }
		return -1;
	 }
	 
	 public void setTiles(int index, int val) {
        tiles[index] = val;
	 }
	 
	 public int[] getTiles() {
		 return tiles;
	 }
	 public void setTile(int[] tl) {
		 tiles = tl;
	 }
	 
	 public int getTilesValue(int index) {
        return tiles[index];
	 }
	 public int getTilesLength() {
        return tiles.length;
	 }

	 private int blankPos;
	 public void setBlankPos(int bp) {
        blankPos = bp;
	 }
	 
	 public int getBlankPos() {
        return blankPos;
	 }

	 private boolean gameOver = false;
	 public void setGameOver(boolean b) {
        gameOver = b;
	 }
	 
	 public boolean getGameOver() {
        return gameOver;
	 }

} // end of grid class
