package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Color;
import model.Field;

public class BoardTest {
	private Board board;

	@Before
	public void setUp() {
		board = new Board();
	}

	@Test
	public void testIsField() {
		assertFalse(board.isField(-1, -1, -1));
		assertTrue(board.isField(0, 0, 0));
		assertTrue(board.isField(3, 3, 3));
		assertFalse(board.isField(4, 4, 4));
	}


	@Test
	public void testSetAndGetField() {
		board.setField(0, 0, 0, Color.RED);
		assertEquals(Color.RED, board.getField(0, 0, 0));
		assertEquals(Color.EMPTY, board.getField(1, 1, 1));
	}

	@Test
	public void testSetField() {
		board.setField(0, 0, 0, Color.RED);
		assertEquals(Color.RED, board.getField(0, 0, 0));
		assertEquals(Color.EMPTY, board.getField(1, 0, 0));
		assertEquals(Color.EMPTY, board.getField(0, 1, 0));
		assertEquals(Color.EMPTY, board.getField(0, 0, 1));
		assertEquals(Color.EMPTY, board.getField(1, 0, 0));
		assertEquals(Color.EMPTY, board.getField(1, 0, 1));
		assertEquals(Color.EMPTY, board.getField(1, 1, 0));
		assertEquals(Color.EMPTY, board.getField(1, 1, 1));
	}

	@Test
	public void testSetup() {
		assertEquals(Color.EMPTY, board.getField(0, 0, 0));
	}

	@Test
	public void testReset() {
		board.reset();
		assertEquals(Color.EMPTY, board.getField(0, 0, 0));
	}

	@Test
	public void testDeepCopy() {
		board.setField(0, 0, 0, Color.RED);
		Board deepCopyBoard = board.Deepcopy();
		deepCopyBoard.setField(0, 0, 0, Color.YELLOW);
		assertEquals(Color.RED, board.getField(0, 0, 0));
		assertEquals(Color.YELLOW, deepCopyBoard.getField(0, 0, 0));
	}

	@Test
	public void testIsEmptyField() {
		board.setField(0, 0, 0, Color.RED);
		assertFalse(board.isEmpty(0, 0, 0));
		assertTrue(board.isEmpty(1, 1, 1));
	}

	/*@Test
	public void testIsFull() {
		for (int i = 0; i < 8; i++) {
			board.setField(i, Mark.XX);
		}
		assertFalse(board.isFull());

		board.setField(8, Mark.XX);
		assertTrue(board.isFull());
	}*/

	/*@Test
	public void testGameOverFullBoard() {

		board.setField(0, 0, Mark.XX);
		board.setField(0, 1, Mark.XX);
		board.setField(0, 2, Mark.OO);
		board.setField(1, 0, Mark.OO);
		board.setField(1, 1, Mark.OO);
		board.setField(1, 2, Mark.XX);
		board.setField(2, 0, Mark.XX);
		board.setField(2, 1, Mark.OO);

		assertFalse(board.gameOver());
		board.setField(2, 2, Mark.XX);
		assertTrue(board.gameOver());
	} */

	@Test
	public void testHasRowX() {
		
		board.setField(0, 0, 0, Color.RED);
		board.setField(1, 0, 0, Color.RED);
		Field one = new Field(0, 0, 0, Color.RED);
		Field two = new Field(1, 0, 0, Color.RED);
		Field three = new Field(1, 0, 0, Color.YELLOW);
		assertFalse(board.getRow(one));
		assertFalse(board.getRow(three));
		
		board.setField(2, 0, 0, Color.RED);
		board.setField(3, 0, 0, Color.RED);
		assertTrue(board.getRow(one));
		assertTrue(board.getRow(two));
		assertFalse(board.getRow(three));

	}
	
	@Test
	public void testHasRowZ() {
		
		board.setField(0, 0, 0, Color.RED);
		board.setField(0, 0, 1, Color.RED);
		Field one = new Field(0, 0, 0, Color.RED);
		Field two = new Field(0, 0, 1, Color.RED);
		Field three = new Field(0, 0, 1, Color.YELLOW);
		assertFalse(board.getRow(one));
		assertFalse(board.getRow(three));
		
		board.setField(0, 0, 2, Color.RED);
		board.setField(0, 0, 3, Color.RED);
		assertTrue(board.getRow(one));
		assertTrue(board.getRow(two));
		assertFalse(board.getRow(three));

	}

	@Test
	public void testHasColumn() {
		
		board.setField(0, 0, 0, Color.RED);
		board.setField(0, 1, 0, Color.RED);
		Field one = new Field(0, 0, 0, Color.RED);
		Field two = new Field(0, 1, 0, Color.RED);
		Field three = new Field(0, 1, 0, Color.YELLOW);
		assertFalse(board.getCol(one));
		assertFalse(board.getCol(three));
		
		board.setField(0, 2, 0, Color.RED);
		board.setField(0, 3, 0, Color.RED);
		assertTrue(board.getCol(one));
		assertTrue(board.getCol(two));
		assertFalse(board.getCol(three));
	}

	@Test
	public void testGetDiagDiag() {
		
		board.setField(0, 0, 0, Color.RED);
		board.setField(1, 1, 1, Color.RED);
		board.setField(2, 2, 2, Color.RED);
		assertFalse(board.getDiagDiag(Color.RED));
		assertFalse(board.getDiagDiag(Color.YELLOW));
		
		board.setField(3, 3, 3, Color.RED);
		assertTrue(board.getDiagDiag(Color.RED));
		assertFalse(board.getDiagDiag(Color.YELLOW));

	}
	
	@Test
	public void testGetXdiag1() {
		
		board.setField(0, 0, 3, Color.RED);
		board.setField(0, 3, 0, Color.RED);
		assertFalse(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YELLOW));
		
		board.setField(0, 1, 2, Color.RED);
		board.setField(0, 2, 1, Color.RED);
		assertTrue(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YELLOW));
		
	}
	
	@Test
	public void testGetXdiag2() {
		
		board.setField(0, 0, 0, Color.RED);
		board.setField(0, 3, 3, Color.RED);
		assertFalse(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YELLOW));
		
		board.setField(0, 2, 2, Color.RED);
		board.setField(0, 1, 1, Color.RED);
		assertTrue(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YELLOW));
		
	}
	
	@Test
	public void testGetZdiag1() {
		
		board.setField(0, 3, 0, Color.RED);
		board.setField(3, 0, 0, Color.RED);
		assertFalse(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YELLOW));
		
		board.setField(1, 2, 0, Color.RED);
		board.setField(2, 1, 0, Color.RED);
		assertTrue(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YELLOW));
		
	}
	
	@Test
	public void testGetZdiag2() {
		
		board.setField(0, 0, 0, Color.RED);
		board.setField(3, 3, 0, Color.RED);
		assertFalse(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YELLOW));
		
		board.setField(2, 2, 0, Color.RED);
		board.setField(1, 1, 0, Color.RED);
		assertTrue(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YELLOW));
		
	}
}