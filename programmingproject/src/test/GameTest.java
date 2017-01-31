package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.Game;
import model.Board;
import model.Color;
import model.Field;
import model.Humanplayer;
import model.Player;

public class GameTest {
	public Player s0;
	public Player s1;
	public Game battle;
	public Board bor;

	@Before
	public void setUp() {
		s0 = new Humanplayer("Lieke");
		s1 = new Humanplayer("Amber");
		battle = new Game(s0, s1);
		bor = new Board();
	}

	/*
	 * @Test public void testFirstPlayer() { fail("Not yet implemented"); }
	 */

	@Test
	public void testisWinner() {
		s0.setColor(Color.RED);
		s1.setColor(Color.YEL);
		bor.setField(0, 0, 0, Color.RED);
		bor.setField(1, 0, 0, Color.RED);
		bor.setField(2, 0, 0, Color.RED);
		bor.setField(3, 0, 0, Color.RED);
		assertTrue(bor.getXRow(Color.RED) == true);
		assertEquals(battle.isWinner(bor, Color.RED), true);

	}

	@Test
	public void testHasWinner() {
		s0.setColor(Color.RED);
		Color col = s0.getColor();
		Board bor = new Board();
		Field place = new Field(0, 0, 0, Color.RED);
		bor.setField(0, 0, 0, Color.RED);
		bor.setField(1, 0, 0, Color.RED);
		bor.setField(2, 0, 0, Color.RED);
		bor.setField(3, 0, 0, Color.RED);
		bor.toString();
		bor.getXRow(Color.RED);
		assertTrue(battle.hasWinner(bor) == true);
	}
	/*
	 * @Test public void testStartgame() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testPlay() { fail("Not yet implemented"); }
	 */

	@Test
	public void testGameOver() {
		s0.setColor(Color.RED);
		Color col = s0.getColor();
		Board bor = new Board();
		Field place = new Field(0, 0, 0, Color.RED);
		Field place1 = new Field(1, 0, 0, Color.RED);
		Field place2 = new Field(2, 0, 0, Color.RED);
		Field place3 = new Field(3, 0, 0, Color.RED);
		Field place4 = new Field(1, 1, 0, Color.RED);
		bor.setField(place1);
		bor.setField(place2);
		bor.setField(place3);
		bor.setField(place);
		assertTrue(bor.getXRow(Color.RED) == true);
		assertTrue(battle.hasWinner(bor) == true);
		assertTrue(battle.gameOver(bor));
	}

}
