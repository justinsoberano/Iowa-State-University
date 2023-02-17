package edu.iastate.cs228.hw1;

/**
 *  
 * @author Justin Soberano
 *
 */

/**
 * 
 * Living refers to the life form occupying a square in a plain grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living 
{
	protected Plain plain; 
	protected int row;     
	protected int column;  
	
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a plain. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[])
	{		
		int sizeOfArray = this.plain.getWidth() - 1;
		
        for(int i = - 1; i < 2; i++) {								
            for(int j = - 1; j < 2; j++) {	
                if(!((row + j) > sizeOfArray) && !((row + j) < 0) 		
                && !((column + i) > sizeOfArray) && !((column + i) < 0)) {	
            		if(this.plain.grid[row + j][column + i].who() == State.BADGER) {
            			population[BADGER]++;
            		} else if (this.plain.grid[row + j][column + i].who() == State.EMPTY) {
            			population[EMPTY]++;
            		} else if (this.plain.grid[row + j][column + i].who() == State.FOX) {
            			population[FOX]++;
            		} else if (this.plain.grid[row + j][column + i].who() == State.GRASS) {
            			population[GRASS]++;
            		} else if (this.plain.grid[row + j][column + i].who() == State.RABBIT) {
            			population[RABBIT]++;
            		}
                }
            }
        }
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  pNew  plain of the next cycle
	 * @return Living 
	 */
	public abstract Living next(Plain pNew); 
	
}
