package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Field;
import model.Tile;

public class FieldTest {

	private Field place;
	private Field please;
	private Tile choice;
	private Tile press;

	@Before
	public void setUp() throws Exception {
		place = new Field(1,1,1,choice);
	}

	@Test
	public void test() {
		assertTrue(place.getTile() == choice);
		place.setTile(press);
		assertTrue(place.getTile() == press);
		assertTrue(place.getX() == 1);
		assertTrue(place.getY() == 1);
		assertTrue(place.getZ() == 1);
	}

}
