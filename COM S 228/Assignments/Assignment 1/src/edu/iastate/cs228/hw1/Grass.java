package edu.iastate.cs228.hw1;

/**
 *  
 * @author Justin Soberano
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	
	public Grass (Plain p, int r, int c) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
		
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		
		int[] population = new int[NUM_LIFE_FORMS];
		census(population);
		
		if(population[RABBIT] >= (population[GRASS] * 3)) {
			return pNew.grid[row][column] = new Empty(pNew, row, column);
		} else if (population[RABBIT] >= 3) {
			return pNew.grid[row][column] = new Rabbit(pNew, row, column, 0);
		} else {
			return pNew.grid[row][column] = new Grass(pNew, row, column);
		}
	}
}
