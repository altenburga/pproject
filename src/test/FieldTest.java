package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.Field;

public class FieldTest {

	private Field place;
	private Field please;
	private Color choice = Color.EMPTY;
	private Color press;
	private Color zero = Color.RED;

	@Before
	public void setUp() throws Exception {
		place = new Field(1,1,1,choice);
        please = new Field(1,1,1,choice);
	}

	@Test
	public void test() {
		assertTrue(place.getColor()== choice);
		place.setColor(press);
		assertTrue(place.getColor() == press);
		assertTrue(place.getX() == 1);
		assertTrue(place.getY() == 1);
		assertTrue(place.getZ() == 1);
		place.setColor(zero);
		assertFalse(place.getColor() == zero);
		please.setColor(zero);
		assertTrue(please.getColor() == zero);
	}

}
