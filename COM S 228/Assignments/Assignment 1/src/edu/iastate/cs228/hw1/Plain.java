package edu.iastate.cs228.hw1;

/**
 *  
 * @author Justin Soberano
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
		
		File file = new File(inputFileName);			
		Scanner scanner = new Scanner(file);			
		
		while(scanner.hasNextLine()) {					
			String plainLine = scanner.nextLine();		
			if(!plainLine.trim().equals(" ")) {			
				width++;
			}
		}
		scanner.close();								
		
		this.grid = new Living[width][width];
		String[][] stringGrid = new String[width][width];		
		scanner = new Scanner(file);							
		
		for(int i = 0; i < width; i++) {
			if(scanner.hasNextLine()) {	
				for(int j = 0; j < width; j++) {
					stringGrid[i][j] = scanner.next();		
				}											
			}
		}
		scanner.close();	
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < width; j++) {
				if(stringGrid[i][j].charAt(0) == 'B') {
					grid[i][j] = new Badger(this, i, j, Character.getNumericValue(stringGrid[i][j].charAt(1)));
				} else if (stringGrid[i][j].charAt(0) == 'E') {
					grid[i][j] = new Empty(this, i, j);
				} else if (stringGrid[i][j].charAt(0) == 'F') {
					grid[i][j] = new Fox(this, i, j, Character.getNumericValue(stringGrid[i][j].charAt(1)));
				} else if (stringGrid[i][j].charAt(0) == 'G') {
					grid[i][j] = new Grass(this, i, j);
				} else if (stringGrid[i][j].charAt(0) == 'R') {
					grid[i][j] = new Rabbit(this, i, j, Character.getNumericValue(stringGrid[i][j].charAt(1)));
				}
			}
		}			
	}

	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		width = w;
		grid = new Living[w][w];
	}
	
	public int getWidth()
	{ 
		return width;
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		int random = 0;
		 
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				random = generator.nextInt(5);
				if(random == 0) {
					grid[i][j] = new Badger(this, i, j, 0);
				} else if (random == 1) {
					grid[i][j] = new Empty(this, i, j);
				} else if (random == 2) {
					grid[i][j] = new Fox(this, i, j, 0);
				} else if (random == 3) {
					grid[i][j] = new Grass(this, i, j);
				} else if (random == 4) {
					grid[i][j] = new Rabbit(this, i, j, 0);
				}
			}
		}
	}
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String plain = "";
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(grid[i][j].who() == State.BADGER) {
					plain += "B" + ((Animal)grid[i][j]).myAge() + " ";
				} else if (grid[i][j].who() == State.FOX) {
					plain += "F" + ((Animal)grid[i][j]).myAge() + " ";
				} else if (grid[i][j].who() == State.RABBIT) {
					plain += "R" + ((Animal)grid[i][j]).myAge() + " ";
				} else if (grid[i][j].who() == State.EMPTY) {
					plain += "E  ";
				} else if (grid[i][j].who() == State.GRASS) {
					plain += "G  ";
				}
			}
			plain += "\n";
		}
		return plain;
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(outputFileName);
		output.print(this.toString());
		output.close();
	}			
}
