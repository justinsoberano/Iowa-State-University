package edu.iastate.cs228.hw1;

/**
 * @author Justin Soberano
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnimalTest {

	@Test
	void test() {
		Plain p = new Plain(3);
		p.grid[0][0] = new Fox(p, 0, 0, 0);
		assertEquals(0, ((Animal)p.grid[0][0]).age);
	}

}
