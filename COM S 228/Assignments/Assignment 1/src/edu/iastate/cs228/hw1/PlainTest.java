package edu.iastate.cs228.hw1;

/**
 * @author Justin Soberano
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlainTest {

	@Test
	void test() {
		Plain p = new Plain(3);
		assertEquals(3, p.getWidth());
	}

}
