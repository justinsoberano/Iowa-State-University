package edu.iastate.cs228.hw1;

/**
 *  
 * @author Justin Soberano
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	
	public Rabbit (Plain p, int r, int c, int a) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
		this.age = a;
		
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		int[] population = new int[NUM_LIFE_FORMS];
		census(population);
		
		if(this.age >= RABBIT_MAX_AGE) {
			return pNew.grid[row][column] = new Empty(pNew, row, column);
		} else if (population[GRASS] == 0) {
			return pNew.grid[row][column] = new Empty(pNew, row, column);
		} else if (population[BADGER] + population[FOX] >= population[RABBIT] && population[BADGER] < population[FOX]) {
			return pNew.grid[row][column] = new Fox(pNew, row, column, 0);
		} else if (population[BADGER] > population[RABBIT]) {
			return pNew.grid[row][column] = new Badger(pNew, row, column, 0);
		} else {
			return pNew.grid[row][column] = new Rabbit(pNew, row, column, age + 1);
		}
	}
}
