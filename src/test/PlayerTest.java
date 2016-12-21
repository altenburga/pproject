package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import control.Game;
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
		one = new Humanplayer("Lieke", Color.RED);
		two = new Humanplayer("Amber", Color.YEL);
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
	public void testShowHand() {
		one.initHand();
		assertTrue(one.showHand() == 32);
		Field move = new Field(0,0,0,one.getColor());
//		one.makeMove(board, move);
//		assertTrue(one.showHand() == 31);
		}
	@Test
	public void testInitHand() {
		one.initHand();
		assertTrue(one.showHand() == 32);
	}
	@Test
	public void testClearHand() {
		one.initHand();
		assertTrue(one.showHand() == 32);
		one.clearHand();
		assertTrue(one.showHand() == 0);
	}
	@Test
	public void testMakeMove() {
		one.setColor(Color.RED);
		Field move = new Field(0,0,0, Color.RED);
		assertTrue(board.getField(0, 0, 0) == Color.EMP);
		one.makeMove(board);
		assertTrue(board.getField(0, 0, 0) == Color.RED);
		Field move1 = new Field(1, 0, 0, null);
		one.makeMove(board);
		assertFalse(board.getField(1, 0, 0) == Color.RED);
		
	}
}
