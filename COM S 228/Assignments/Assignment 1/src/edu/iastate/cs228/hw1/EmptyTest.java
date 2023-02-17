package edu.iastate.cs228.hw1;

/**
 * @author Justin Soberano
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptyTest {

	@Test
	void test() {
		Plain p = new Plain(3);
		p.grid[0][0] = new Empty(p, 0, 0);
		p.grid[0][1] = new Grass(p, 0, 1);
		p.grid[1][0] = new Fox(p, 1, 0, 0);
		p.grid[1][1] = new Rabbit(p, 1, 1, 0);
		p.grid[0][0].next(p);
		assertEquals(State.GRASS, p.grid[0][0].who());
	}

}
