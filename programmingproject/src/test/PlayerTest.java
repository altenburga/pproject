package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.Game;
import exceptions.FieldNotExistingException;
import exceptions.OutOfBoundsException;
import model.Board;
import model.Color;
import model.Field;
import model.Humanplayer;
import model.Player;

public class PlayerTest {
	Player one;
	Player two;
	Game battle;
	Board board;

	@Before
	public void setUp() throws Exception {
		one = new Humanplayer("Lieke");
		two = new Humanplayer("Amber");
		board = new Board();
	}

	@Test
	public void testGetName() {
		assertEquals(one.getName(), "Lieke");
		assertEquals(two.getName(), "Amber");
	}

	@Test
	public void testGetSetColor() {
		one.setColor(Color.RED);
		assertTrue(one.getColor() == Color.RED);
		two.setColor(Color.YEL);
		assertTrue(two.getColor() == Color.YEL);
		two.setColor(Color.EMP);
		assertTrue(two.getColor() == Color.EMP);
		assertFalse(two.getColor() == Color.YEL);
	}

	@Test
	public void testSetName() {
		assertEquals(one.getName(), "Lieke");
		assertEquals(two.getName(), "Amber");
		one.setName("Lisa");
		two.setName("Anna");
		assertEquals(one.getName(), "Lisa");
		assertEquals(two.getName(), "Anna");
	}


	@Test
	public void testMakeMove() throws OutOfBoundsException, FieldNotExistingException {
		one.setColor(Color.RED);
		assertTrue(board.getField(0, 0, 0) == Color.EMP);
		System.out.println("Use column 0 and row 0!");
		one.makeMove(board);
		assertTrue(board.getField(0, 0, 0) == Color.RED);
		System.out.println(" Use column 1 and row 0!");
		one.makeMove(board);
		assertTrue(board.getField(1, 0, 0) == Color.RED);
	}
}
