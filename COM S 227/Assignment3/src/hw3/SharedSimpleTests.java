package hw3;

import static api.CellType.*;

import static api.Orientation.*;
import static api.Direction.*;

import java.util.ArrayList;
import java.util.Arrays;

import api.Cell;
import api.Move;

public class SharedSimpleTests {
	public static final String[][] testDescription1 =
		{ { "*", "*", "*", "*", "*" },
		  { "*", ".", ".", ".", "*" },
		  { "*", "[", "]", ".", "e" },
		  { "*", ".", ".", ".", "*" },
		  { "*", "*", "*", "*", "*" } };

	public static final String[][] testDescription2 =
			{	{ "*", "*", "*", "*", "*", "*", "*", "*", "*" },
				{ "*", ".", ".", ".", "^", ".", "[", "]", "*" },
				{ "*", "^", ".", ".", "v", ".", ".", "^", "*" },
				{ "*", "#", ".", ".", "[", "]", ".", "#", "*" },
				{ "*", "#", ".", "[", "]", ".", ".", "#", "*" },
				{ "*", "v", ".", ".", "[", "#", "]", "v", "*" },
				{ "*", ".", ".", ".", ".", ".", ".", ".", "*" },
				{ "*", ".", ".", ".", ".", "[", "#", "]", "*" },
				{ "*", "*", "*", "*", "e", "*", "*", "*", "*" } };

	private static final Cell[][] testGrid1 = {
			{ new Cell(WALL, 0, 0), new Cell(WALL, 0, 1), new Cell(WALL, 0, 2), new Cell(WALL, 0, 3), new Cell(WALL, 0, 4) },
			{ new Cell(WALL, 1, 0), new Cell(FLOOR, 1, 1), new Cell(FLOOR, 1, 2), new Cell(FLOOR, 1, 3), new Cell(WALL, 1, 4) },
			{ new Cell(WALL, 2, 0), new Cell(FLOOR, 2, 1), new Cell(FLOOR, 2, 2), new Cell(FLOOR, 2, 3), new Cell(EXIT, 2, 4) },
			{ new Cell(WALL, 3, 0), new Cell(FLOOR, 3, 1), new Cell(FLOOR, 3, 2), new Cell(FLOOR, 3, 3), new Cell(WALL, 3, 4) },
			{ new Cell(WALL, 4, 0), new Cell(WALL, 4, 1), new Cell(WALL, 4, 2), new Cell(WALL, 4, 3), new Cell(WALL, 4, 4) } };



	private static ArrayList<Block> makeTest1Blocks() {
		ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.add(new Block(2, 1, 2, HORIZONTAL));
		return blocks;
	}

	public static void main(String args[]) {
		Block block = new Block(2, 1, 2, HORIZONTAL);
		System.out.println("Block is " + block);
		block.move(DOWN);
		System.out.println("After move DOWN, block is " + block);
		System.out.println("Expected block at (row=2, col=1).");
		block.move(RIGHT);
		System.out.println("After move RIGHT, block is " + block);
		System.out.println("Expected block at (row=2, col=2).");
		System.out.println();

		Cell[][] cells = GridUtil.createGrid(testDescription1);
		System.out.println("Using testDescription1, cell (2, 4) is an exit is "
					+ cells[2][4].isExit() + ", expected is true.");

		ArrayList<Block> blocks = GridUtil.findBlocks(testDescription1);
		System.out.println("Using testDescription1, number of blocks is "
					+ blocks.size() + ", expected is 1.");
		System.out.println("Using testDescription1, first block is length "
					+ blocks.get(0).getLength() + ", expected is 2.");
		System.out.println();

		System.out.println("Making board with testGrid1.");
		Board board2 = new Board(testGrid1, makeTest1Blocks());
		System.out.println(board2.toString());
		System.out.println(board2.getMoveHistory());
		System.out.println();

		board2.grabBlockAtCell(2, 1);
		System.out.println("Grabbed block " + board2.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board2.getGrabbedCell().getRow()
					+ ", " + board2.getGrabbedCell().getCol() + ") " + ", expected (2, 1).");
		System.out.println();

		board2.moveGrabbedBlock(RIGHT);
		System.out.println("After moving block right one time game over is " + board2.isGameOver()
				+ ", expected is false.");
		System.out.println(board2.getMoveCount());
		System.out.println(board2.toString());
		System.out.println();
		System.out.println(board2.getMoveHistory());

		board2.moveGrabbedBlock(RIGHT);
		System.out.println("After moving block right two times game over is " + board2.isGameOver()
				+ ", expected is true.");
		System.out.println(board2.toString());
		System.out.println();
		System.out.println(board2.getMoveHistory());

		board2.reset();
		System.out.println("After reset:");
		System.out.println(board2.toString());
		System.out.println();

		ArrayList<Move> moves = board2.getAllPossibleMoves();
		System.out.println("All possible moves: " + Arrays.toString(moves.toArray()));





		//TEST TWO
		Cell[][] cells1 = GridUtil.createGrid(testDescription2);
		System.out.println("Using testDescription1, cell (2, 4) is an exit is "
				+ cells[2][4].isExit() + ", expected is true.");

		ArrayList<Block> blocks1 = GridUtil.findBlocks(testDescription2);
		System.out.println("Using testDescription2, number of blocks is "
		+ blocks1.size() + ", expected is 8.");
		System.out.println("Using testDescription2, first block is length "
				+ blocks1.get(0).getLength() + ", expected is 2");
		System.out.println("Using testDescription2, second block is length "
				+ blocks1.get(1).getLength() + ", expected is 2");
		System.out.println("Using testDescription2, third block is length "
				+ blocks1.get(2).getLength() + ", expected is 4");
		System.out.println("Using testDescription2, fourth block is length "
				+ blocks1.get(3).getLength() + ", expected is 4");
		System.out.println("Using testDescription2, fifth block is length "
				+ blocks1.get(4).getLength() + ", expected is 2");
		System.out.println("Using testDescription2, sixth block is length "
				+ blocks1.get(5).getLength() + ", expected is 2");
		System.out.println("Using testDescription2, seventh block is length "
				+ blocks1.get(6).getLength() + ", expected is 3");
		System.out.println("Using testDescription2, eighth block is length "
				+ blocks1.get(7).getLength() + ", expected is 3");

		System.out.println("Making board with testDescription2");
		Cell[][] cellsSeth = GridUtil.createGrid(testDescription2);
		ArrayList<Block> blocksSeth = GridUtil.findBlocks(testDescription2);

		Board board1 = new Board(cellsSeth, blocksSeth);
		System.out.println(board1.toString());
		System.out.println();

		board1.grabBlockAtCell(1,7);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (1, 7)");
		System.out.println();

		board1.moveGrabbedBlock(LEFT);
		System.out.println("After moving block left one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(2,7);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (2, 7)");
		System.out.println();

		board1.moveGrabbedBlock(UP);
		System.out.println("After moving block up one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(3,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (3, 4)");
		System.out.println();

		board1.moveGrabbedBlock(RIGHT);
		System.out.println("After moving block right one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(4,3);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (4, 3)");
		System.out.println();

		board1.moveGrabbedBlock(LEFT);
		System.out.println("After moving block left one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(5,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (5, 4)");
		System.out.println();

		board1.moveGrabbedBlock(RIGHT);
		System.out.println("After moving block right one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(1,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (1, 4)");
		System.out.println();

		board1.moveGrabbedBlock(DOWN);
		System.out.println("After moving block down one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(2,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (2, 4)");
		System.out.println();

		board1.moveGrabbedBlock(DOWN);
		System.out.println("After moving block down one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(3,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (3, 4)");
		System.out.println();

		board1.moveGrabbedBlock(DOWN);
		System.out.println("After moving block down one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(4,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (4, 4)");
		System.out.println();

		board1.moveGrabbedBlock(DOWN);
		System.out.println("After moving block down one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(5,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (5, 4)");
		System.out.println();

		board1.moveGrabbedBlock(DOWN);
		System.out.println("After moving block down one time, game over is " + board1.isGameOver() + ", expected is false");
		System.out.println(board1.toString());
		System.out.println(board1.getMoveHistory());
		System.out.println();

		board1.grabBlockAtCell(6,4);
		System.out.println("Grabbed block " + board1.getGrabbedBlock());
		System.out.println("Location of grab is at (" + board1.getGrabbedCell().getRow()
				+ ", " + board1.getGrabbedCell().getCol() + ") " + ", expected (6, 4)");
		System.out.println();

		board1.moveGrabbedBlock(DOWN);
		System.out.println("After moving block down one time, game over is " + board1.isGameOver() + ", expected is true");
		System.out.println(board1.toString());
		System.out.println();


	}

}

