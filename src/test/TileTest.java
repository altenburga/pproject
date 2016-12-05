package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Tile;

public class TileTest {

	private Tile one;

	@Before
	public void setUp() throws Exception {
		
		one = new Tile(1);
	}

	@Test
	public void test() {
		assertTrue(one.getColor()==1);
	}

}
