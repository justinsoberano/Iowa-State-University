package hw3;

import static api.Direction.*;

import static api.Orientation.*;
import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Move;
import api.Orientation;

/**
 * Represents a board in the Block Slider game. A board contains a 2D grid of
 * cells and a list of blocks that slide over the cells.
 *
 * @author justin
 * 
 */
public class Board {
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of blocks that are positioned on the board.
	 */
	private ArrayList<Block> blocks;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of blocks on the board.
	 */
	private ArrayList<Move> moveHistory = new ArrayList<Move>();
	
	/**
	 * Stores the grabbed block
	 */
	private Block grabbedBlock;
	
	/**
	 * Stores the grabbed cell
	 */
	private Cell grabbedCell;
	
	/**
	 * Stores the amount of moves made
	 */
	private int moveCount;
	
	/**
	 * Stores if the game is over
	 */
	private boolean gameOver;
	
	/**
	 * Constructs a new board from a given 2D array of cells and list of blocks. The
	 * cells of the grid should be updated to indicate which cells have blocks
	 * placed over them (i.e., setBlock() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param blocks list of blocks already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Block> blocks) {
		
		this.grid = grid;
		this.blocks = blocks;
		
		for(int i = 0; i < blocks.size(); i++) {
			
			int col = blocks.get(i).getFirstCol();
			int row = blocks.get(i).getFirstRow();
			
			if(blocks.get(i).getOrientation() == HORIZONTAL) {
				for(int j = 0; j < blocks.get(i).getLength(); j++) {
					this.grid[row][col + j].setBlock(blocks.get(i));
				}
			}
			
			if(blocks.get(i).getOrientation() == VERTICAL) {
				for(int j = 0; j < blocks.get(i).getLength(); j++) {
					this.grid[row + j][col].setBlock(blocks.get(i));
				}
			}
		}
	}

	/**
	 * Constructs a new board from a given 2D array of String descriptions.
	 * <p>
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBlocks(desc));
	}

	/**
	 * Models the user grabbing a block over the given row and column. The purpose
	 * of grabbing a block is for the user to be able to drag the block to a new
	 * position, which is performed by calling moveGrabbedBlock(). This method
	 * records two things: the block that has been grabbed and the cell at which it
	 * was grabbed.
	 * 
	 * @param row row to grab the block from
	 * @param col column to grab the block from
	 */
	public void grabBlockAtCell(int row, int col) {

		grabbedCell = grid[row][col];		
		grabbedBlock = this.grid[row][col].getBlock();
	
	}
	
	/**
	 * Set the currently grabbed block to null.
	 */
	public void releaseBlock() { 
		grabbedBlock = null;
		grabbedCell = null;
	}

	/**
	 * Returns the currently grabbed block.
	 * 
	 * @return the current block
	 */
	public Block getGrabbedBlock() {
		return grabbedBlock;
	}

	/**
	 * Returns the currently grabbed cell.
	 * 
	 * @return the current cell
	 */
	public Cell getGrabbedCell() {
		return grabbedCell;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a block
	 * to be placed over it. Blocks can only be placed over floors and exits. A
	 * block cannot be placed over a cell that is occupied by another block.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a block, otherwise false
	 */
	public boolean canPlaceBlock(int row, int col) {
		
		if(this.grid[row][col].isFloor() == true || this.grid[row][col].isExit() == true) {
			return true;
		} else {		
			return false;
		}
	}

	/**
	 * Returns the number of moves made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		return moveCount;
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		return this.grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		return this.grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCell(int row, int col) {
		return this.grid[row][col];
	}

	/**
	 * Returns a list of all blocks on the board.
	 * 
	 * @return a list of all blocks
	 */
	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a block
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() { 
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				
				if(grid[i][j].isExit() == true) {
					
					if(grid[i][j].hasBlock() == true) {
						gameOver = true;
						return gameOver;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Moves the currently grabbed block by one cell in the given direction. A
	 * horizontal block is only allowed to move right and left and a vertical block
	 * is only allowed to move up and down. A block can only move over a cell that
	 * is a floor or exit and is not already occupied by another block. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No block is currently grabbed by the user.</li>
	 * <li>A block is currently grabbed by the user, but the block is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does the following:
	 * <ul>
	 * <li>Moves the block object by calling its move method.</li>
	 * <li>Sets the block for the grid cell that the block is being moved into.</li>
	 * <li>For the grid cell that the block is being moved out of, sets the block to
	 * null.</li>
	 * <li>Moves the currently grabbed cell by one cell in the same moved direction.
	 * The purpose of this is to make the currently grabbed cell move with the block
	 * as it is being dragged by the user.</li>
	 * <li>Adds the move to the end of the moveHistory list.</li>
	 * <li>Increment the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBlock(Direction dir) {  
		
		int row = 0;
		int col = 0;
		int len = getGrabbedBlock().getLength();
		
		
		int gRow = getGrabbedBlock().getFirstRow();
		int gCol = getGrabbedBlock().getFirstCol();
		Orientation ori = getGrabbedBlock().getOrientation();
		
		if(isGameOver() == false && getGrabbedBlock() != null) {
				
				if(dir == RIGHT && ori == HORIZONTAL && this.grid[gRow][gCol + len].isWall() == false && this.grid[gRow][gCol + len].hasBlock() == false) {
					
					moveHistory.add(new Move(getGrabbedBlock(), RIGHT));
					
					getGrabbedBlock().move(RIGHT);
					
					row = getGrabbedBlock().getFirstRow();
					col = getGrabbedBlock().getFirstCol();
					
					this.grid[row][col - 1].clearBlock();
					
					for(int j = 0; j < len; j++) {
						this.grid[row][col + j].setBlock(getGrabbedBlock());
					}
					
					moveCount++;
				}
				
				if(dir == LEFT && ori == HORIZONTAL && this.grid[gRow][gCol - 1].isWall() == false && this.grid[gRow][gCol - 1].hasBlock() == false) {
					
					moveHistory.add(new Move(getGrabbedBlock(), LEFT));
					
					getGrabbedBlock().move(LEFT);
					
					row = getGrabbedBlock().getFirstRow();
					col = getGrabbedBlock().getFirstCol();
					
					this.grid[row][col + len].clearBlock();
					
					for(int j = 0; j < len; j++) {
						this.grid[row][col + j].setBlock(getGrabbedBlock());
					}
					
					moveCount++;
					
				}
				
				if(dir == DOWN && ori == VERTICAL && this.grid[gRow + len][gCol].isWall() == false && this.grid[gRow + len][gCol].hasBlock() == false) {
					
					moveHistory.add(new Move(getGrabbedBlock(), DOWN));
					
					getGrabbedBlock().move(DOWN);
					
					row = getGrabbedBlock().getFirstRow();
					col = getGrabbedBlock().getFirstCol();
					
					
					this.grid[row - 1][col].clearBlock();
					
					for(int j = 0; j < len; j++) {
						this.grid[row + j][col].setBlock(getGrabbedBlock());
					}
					
					moveCount++;
				}
				
				if(dir == UP && ori == VERTICAL && this.grid[gRow - 1][gCol].isWall() == false && this.grid[gRow - 1][gCol].hasBlock() == false) {
					
					moveHistory.add(new Move(getGrabbedBlock(), UP));
					
					getGrabbedBlock().move(UP);
					
					row = getGrabbedBlock().getFirstRow();
					col = getGrabbedBlock().getFirstCol();
					
					this.grid[row + len][col].clearBlock();
					
					for(int j = 0; j < len; j++) {
						this.grid[row + j][col].setBlock(getGrabbedBlock());
					}
					
					moveCount++;
				}
		}
		
	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each block object. It also updates each grid cells by calling
	 * their setBlock method to either set a block if one is located over the cell
	 * or set null if no block is located over the cell.
	 */
	public void reset() {
		
		moveCount = 0;
		moveHistory = new ArrayList<Move>();
		gameOver = false;
		
		for(int j = 0; j < this.grid.length; j++) {
			for(int k = 0; k < this.grid[0].length; k++) {
				this.grid[j][k].clearBlock();
			}
		}

		for(int i = 0; i < blocks.size(); i++) {
			
			blocks.get(i).reset();
			
			int col = blocks.get(i).getFirstCol();
			int row = blocks.get(i).getFirstRow();
		
			if(blocks.get(i).getOrientation() == HORIZONTAL) {
				for(int j = 0; j < blocks.get(i).getLength(); j++) {
					this.grid[row][col + j].setBlock(blocks.get(i));
				}
			}
			
			if(blocks.get(i).getOrientation() == VERTICAL) {
				for(int j = 0; j < blocks.get(i).getLength(); j++) {
					this.grid[row + j][col].setBlock(blocks.get(i));
				}
			}
		}
	}

	/**
	 * Returns a list of all legal moves that can be made by any block on the
	 * current board. If the game is over there are no legal moves.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		
		ArrayList<Move> allPossibleMoves = new ArrayList<Move>();
		
		Move newPossibleMove = new Move(null, null);
		
		for(int i = 0; i < blocks.size(); i++) {
			
			if(blocks.get(i).getOrientation() == HORIZONTAL) {
				
				int row = blocks.get(i).getFirstRow();
				int col = blocks.get(i).getFirstCol();
				int len = blocks.get(i).getLength();
				
				if(!(this.grid[row][col - 1].isWall()) && !(this.grid[row][col - 1].hasBlock()))  {
					newPossibleMove = new Move(blocks.get(i), LEFT);
					allPossibleMoves.add(newPossibleMove);
					newPossibleMove = null;
					
				}
				
				if(!(this.grid[row][col + len].isWall()) && !(this.grid[row][col + len].hasBlock()))  {
					newPossibleMove = new Move(blocks.get(i), RIGHT);
					allPossibleMoves.add(newPossibleMove);
					newPossibleMove = null;
				}
			}
			
			if(blocks.get(i).getOrientation() == VERTICAL) {
				
				int row = blocks.get(i).getFirstRow();
				int col = blocks.get(i).getFirstCol();
				int len = blocks.get(i).getLength();
				
				if(!(this.grid[row - 1][col].isWall()) && !(this.grid[row - 1][col].hasBlock()))  {
					newPossibleMove = new Move(blocks.get(i), UP);
					allPossibleMoves.add(newPossibleMove);
					newPossibleMove = null;
					
				}
				
				if(!(this.grid[row + len][col].isWall()) && !(this.grid[row + len][col].hasBlock()))  {
					newPossibleMove = new Move(blocks.get(i), DOWN);
					allPossibleMoves.add(newPossibleMove);
					newPossibleMove = null;
				}
			}
		}
		return allPossibleMoves;
	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return moveHistory;
	}
	
	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>grabs the moved block and calls moveGrabbedBlock passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBlock twice</li>
	 * <li>if required, sets is game over to false</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
		// TODO
	}
	

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
