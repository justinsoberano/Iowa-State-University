package hw3;

import static api.Orientation.*;
import static api.CellType.*;
import java.util.ArrayList;
import api.Cell;

/**
 * Utilities for parsing string descriptions of a grid.
 * 
 * @author justin
 * 
 */
public class GridUtil {
	
	/**
	 * Constructs a 2D grid of Cell objects given a 2D array of cell descriptions.
	 * String descriptions are a single character and have the following meaning.
	 * <ul>
	 * <li>"*" represents a wall.</li>
	 * <li>"e" represents an exit.</li>
	 * <li>"." represents a floor.</li>
	 * <li>"[", "]", "^", "v", or "#" represent a part of a block. A block is not a
	 * type of cell, it is something placed on a cell floor. For these descriptions
	 * a cell is created with CellType of FLOOR. This method does not create any
	 * blocks or set blocks on cells.</li>
	 * </ul>
	 * The method only creates cells and not blocks. See the other utility method
	 * findBlocks which is used to create the blocks.
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a 2D array of cells the represent the grid without any blocks present
	 */
	
	public static Cell[][] createGrid(String[][] desc) {
		
		Cell[][] gameGrid = new Cell[desc.length][desc[0].length];
		
			for (int i = 0; i < desc.length; i++) {
				for (int j = 0; j < desc[0].length; j++) {

					if (desc[i][j].equals("*")) {
						gameGrid[i][j] = new Cell(WALL, i, j);
					}
					
					if (desc[i][j].equals("e")) {
						gameGrid[i][j] = new Cell(EXIT, i, j);
					}
					
					if (desc[i][j].equals(".") || desc[i][j].equals("[") || desc[i][j].equals("]") || desc[i][j].equals("#") || desc[i][j].equals("^") || desc[i][j].equals("v")) {
						gameGrid[i][j] = new Cell(FLOOR, i, j);
					}
				}
			}
		return gameGrid;
	}
	
	/**
	 * Returns a list of blocks that are constructed from a given 2D array of cell
	 * descriptions. String descriptions are a single character and have the
	 * following meanings.
	 * <ul>
	 * <li>"[" the start (left most column) of a horizontal block</li>
	 * <li>"]" the end (right most column) of a horizontal block</li>
	 * <li>"^" the start (top most row) of a vertical block</li>
	 * <li>"v" the end (bottom most column) of a vertical block</li>
	 * <li>"#" inner segments of a block, these are always placed between the start
	 * and end of the block</li>
	 * <li>"*", ".", and "e" symbols that describe cell types, meaning there is not
	 * block currently over the cell</li>
	 * </ul>
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a list of blocks found in the given grid description
	 */
	public static ArrayList<Block> findBlocks(String[][] desc) {
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		int i = 0, j = 0, k = 0, blockLength = 2;
		
		for (i = 0; i < desc.length; i++) {
			for (j = 0; j < desc[0].length; j++) {
				
				if (desc[i][j].equals("[")) {
					for(k = j + 1; k < desc.length - 1; k++) {
						if (desc[i][k].equals("#")) {      
							blockLength++;
						}
						else if (desc[i][k].equals("]")) {
							Block newBlock = new Block(i, j, blockLength, HORIZONTAL);
							blocks.add(newBlock);
							blockLength = 2;
							break;
						}
					}
				}
			
				if (desc[i][j].equals("^")) {
					for(k = i + 1; k < desc[i].length; k++) {
						
						if (desc[k][j].equals("#")) {
							blockLength++;
						}
						
						else if (desc[k][j].equals("v")) {
							Block newBlock = new Block(i, j, blockLength, VERTICAL);
							blocks.add(newBlock);
							blockLength = 2;
							break;
						}
					}
				}
			}
		}
		return blocks;	
	}
}