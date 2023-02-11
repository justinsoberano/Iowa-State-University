package hw3;

import static api.Direction.*;

import static api.Orientation.*;

import api.Direction;
import api.Orientation;

/**
 * Represents a block in the Block Slider game.
 * 
 * @author justin
 * 
 */
public class Block {
	
	/**
	 * Stores the original row
	 */
	private int firstRowOriginal;
	
	/**
	 * Stores the original column
	 */
	private int firstColOriginal;
	
	/**
	 * Stores the row
	 */
	private int row;
	
	/**
	 * Stores the column
	 */
	private int col;
	
	/**
	 * Stores the length
	 */
	private int len;
	
	/**
	 * Stores the orientation
	 */
	private Orientation ori;
	
	/**
	 * Constructs a new Block with a specific location relative to the board. The
	 * upper/left most corner of the block is given as firstRow and firstCol. All
	 * blocks are only one cell wide. The length of the block is specified in cells.
	 * The block can either be horizontal or vertical on the board as specified by
	 * orientation.
	 * 
	 * @param firstRow    the first row that contains the block
	 * @param firstCol    the first column that contains the block
	 * @param length      block length in cells
	 * @param orientation either HORIZONTAL or VERTICAL
	 */
	public Block(int firstRow, int firstCol, int length, Orientation orientation) {
		
		firstRowOriginal = firstRow;
		firstColOriginal = firstCol;
		
		row = firstRow;
		col = firstCol;
		len = length;
		ori = orientation;
		
	}

	/**
	 * Resets the position of the block to the original firstRow and firstCol values
	 * that were passed to the constructor during initialization of the the block.
	 */
	public void reset() {
		row = firstRowOriginal;
		col = firstColOriginal;
	}

	/**
	 * Move the blocks position by one cell in the direction specified. The blocks
	 * first column and row should be updated. The method will only move VERTICAL
	 * blocks UP or DOWN and HORIZONTAL blocks RIGHT or LEFT. Invalid movements are
	 * ignored.
	 * 
	 * @param dir direction to move (UP, DOWN, RIGHT, or LEFT)
	 */
	public void move(Direction dir) {
		
		if (dir == UP && getOrientation() == VERTICAL) {
			row -= 1;
		}
		if (dir == RIGHT && getOrientation() == HORIZONTAL) {
			col += 1;
		}
		if (dir == DOWN && getOrientation() == VERTICAL) {
			row += 1;
		}
		if (dir == LEFT && getOrientation() == HORIZONTAL) {
			col -= 1;
		}
		
	}

	/**
	 * Gets the first row of the block on the board.
	 * 
	 * @return first row
	 */
	public int getFirstRow() {
		return row;
	}

	/**
	 * Sets the first row of the block on the board.
	 * 
	 * @param firstRow first row
	 */
	public void setFirstRow(int firstRow) {
		row = firstRow;
	}

	/**
	 * Gets the first column of the block on the board.
	 * 
	 * @return first column
	 */
	public int getFirstCol() {
		return col;
	}

	/**
	 * Sets the first column of the block on the board.
	 * 
	 * @param firstCol first column
	 */
	public void setFirstCol(int firstCol) {
		col = firstCol;
	}

	/**
	 * Gets the length of the block.
	 * 
	 * @return length measured in cells
	 */
	public int getLength() {
		return len;
	}

	/**
	 * Gets the orientation of the block.
	 * 
	 * @return either VERTICAL or HORIZONTAL
	 */
	public Orientation getOrientation() {
		return ori;
	}

	@Override
	public String toString() {
		return "(row=" + getFirstRow() + ", col=" + getFirstCol() + ", len=" + getLength()
				+ ", ori=" + getOrientation() + ")";
	}
}
