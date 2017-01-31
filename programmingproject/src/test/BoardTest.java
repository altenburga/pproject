package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exceptions.OutOfBoundsException;
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
	public void testSetAndGetField() throws OutOfBoundsException {
		board.setField(0, 0, 0, Color.RED);
		assertEquals(Color.RED, board.getField(0, 0, 0));
		assertEquals(Color.EMP, board.getField(1, 1, 1));
	}

	@Test
	public void testSetField() throws OutOfBoundsException {
		board.setField(0, 0, 0, Color.RED);
		assertEquals(Color.RED, board.getField(0, 0, 0));
		assertEquals(Color.EMP, board.getField(1, 0, 0));
		assertEquals(Color.EMP, board.getField(0, 1, 0));
		assertEquals(Color.EMP, board.getField(0, 0, 1));
		assertEquals(Color.EMP, board.getField(1, 0, 0));
		assertEquals(Color.EMP, board.getField(1, 0, 1));
		assertEquals(Color.EMP, board.getField(1, 1, 0));
		assertEquals(Color.EMP, board.getField(1, 1, 1));
	}

	@Test
	public void testReset() throws OutOfBoundsException {
		board.reset();
		assertEquals(Color.EMP, board.getField(0, 0, 0));
	}

	@Test
	public void testDeepCopy() throws OutOfBoundsException {
		board.setField(0, 0, 0, Color.RED);
		Board deepCopyBoard = board.deepCopy();
		assertEquals(Color.RED, deepCopyBoard.getField(0, 0, 0));
		deepCopyBoard.setField(0, 0, 0, Color.YEL);
		assertEquals(Color.RED, board.getField(0, 0, 0));
		assertEquals(Color.YEL, deepCopyBoard.getField(0, 0, 0));
	}

	@Test
	public void testIsEmptyField() throws OutOfBoundsException {
		board.setField(0, 0, 0, Color.RED);
		assertFalse(board.isEmpty(0, 0, 0));
		assertTrue(board.isEmpty(1, 1, 1));
		board.setField(1, 1, 1, Color.RED);
		assertFalse(board.isEmpty(1, 1, 1));
	}

	@Test
	public void testHasRowX() {

		board.setField(0, 0, 0, Color.RED);
		board.setField(1, 0, 0, Color.RED);
		assertFalse(board.getXRow(Color.RED));
		assertFalse(board.getXRow(Color.RED));

		board.setField(2, 0, 0, Color.RED);
		board.setField(3, 0, 0, Color.RED);
		board.toString();
		assertTrue(board.getXRow(Color.RED));

	}

	@Test
	public void testHasRowZ() {

		board.setField(0, 0, 0, Color.RED);
		board.setField(0, 0, 1, Color.RED);
		Field one = new Field(0, 0, 0, Color.RED);
		Field two = new Field(0, 0, 1, Color.RED);
		Field three = new Field(0, 0, 1, Color.YEL);
		assertFalse(board.getZRow(Color.RED));

		board.setField(0, 0, 2, Color.RED);
		board.setField(0, 0, 3, Color.RED);
		assertTrue(board.getZRow(Color.RED));
		assertFalse(board.getZRow(Color.YEL));

	}

	@Test
	public void testHasColumn() {

		board.setField(0, 0, 0, Color.RED);
		board.setField(0, 1, 0, Color.RED);
		Field one = new Field(0, 0, 0, Color.RED);
		Field two = new Field(0, 1, 0, Color.RED);
		Field three = new Field(0, 1, 0, Color.YEL);
		assertFalse(board.getCol(Color.RED));
		assertFalse(board.getCol(Color.YEL));

		board.setField(0, 2, 0, Color.RED);
		board.setField(0, 3, 0, Color.RED);
		assertTrue(board.getCol(Color.RED));
		assertFalse(board.getCol(Color.YEL));
	}

	@Test
	public void testGetDiagDiag() {

		board.setField(0, 0, 0, Color.RED);
		board.setField(1, 1, 1, Color.RED);
		board.setField(2, 2, 2, Color.RED);
		assertFalse(board.getDiagDiag(Color.RED));
		assertFalse(board.getDiagDiag(Color.YEL));

		board.setField(3, 3, 3, Color.RED);
		assertTrue(board.getDiagDiag(Color.RED));
		assertFalse(board.getDiagDiag(Color.YEL));

	}

	@Test
	public void testGetXdiag1() {

		board.setField(0, 0, 3, Color.RED);
		board.setField(0, 3, 0, Color.RED);
		assertFalse(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YEL));

		board.setField(0, 1, 2, Color.RED);
		board.setField(0, 2, 1, Color.RED);
		assertTrue(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YEL));

	}

	@Test
	public void testGetXdiag2() {

		board.setField(0, 0, 0, Color.RED);
		board.setField(0, 3, 3, Color.RED);
		assertFalse(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YEL));

		board.setField(0, 2, 2, Color.RED);
		board.setField(0, 1, 1, Color.RED);
		assertTrue(board.getXdiag(Color.RED));
		assertFalse(board.getXdiag(Color.YEL));

	}

	@Test
	public void testGetZdiag1() {

		board.setField(0, 3, 0, Color.RED);
		board.setField(3, 0, 0, Color.RED);
		assertFalse(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YEL));

		board.setField(1, 2, 0, Color.RED);
		board.setField(2, 1, 0, Color.RED);
		assertTrue(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YEL));

	}

	@Test
	public void testGetZdiag2() {

		board.setField(0, 0, 0, Color.RED);
		board.setField(3, 3, 0, Color.RED);
		assertFalse(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YEL));

		board.setField(2, 2, 0, Color.RED);
		board.setField(1, 1, 0, Color.RED);
		assertTrue(board.getZdiag(Color.RED));
		assertFalse(board.getZdiag(Color.YEL));

	}

	@Test
	public void testValidMove() throws OutOfBoundsException {
		Board one = new Board();
		one.reset();
		Field place = new Field(0, 0, 0, Color.RED);
		assertTrue(one.validMove(place));
		one.setField(place);
		Field place1 = new Field(0, 1, 0, Color.RED);
		assertTrue(one.validMove(place1));
		one.setField(place1);
		assertFalse(one.validMove(place));
		Field place2 = new Field(0, 2, 0, Color.RED);
		assertTrue(one.validMove(place2));
		one.setField(place2);
		Field place3 = new Field(0, 3, 0, Color.RED);
		assertTrue(one.validMove(place3));
		one.setField(place3);
		Field place5 = new Field(0, 1, 0, Color.YEL);
		assertFalse(one.validMove(place5));
		Field place6 = new Field(1, 3, 1, Color.YEL);
		assertFalse(one.validMove(place6));
		Field place7 = new Field(5, 5, 5, Color.YEL);
		assertFalse(one.validMove(place7));
	}
}