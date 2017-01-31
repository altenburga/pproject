package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.Game;
import model.Board;
import model.Color;
import model.Field;
import model.Humanplayer;
import model.Player;
import view.TUIView;

public class GameTest {
	public Player s0;
	public Player s1;
	public Game battle;
	public Board bor;
	private Player[] player;

	@Before
	public void setUp() {
		s0 = new Humanplayer("Lieke");
		s1 = new Humanplayer("Amber");
		battle = new Game(s0, s1);
		bor = new Board();
	}

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
		battle.reset();
		bor.setField(0, 0, 0, Color.YEL);
		bor.setField(1, 0, 0, Color.YEL);
		bor.setField(2, 0, 0, Color.YEL);
		bor.setField(3, 0, 0, Color.YEL);
		assertTrue(bor.getXRow(Color.YEL) == true);
		assertEquals(battle.isWinner(bor, Color.YEL), true);
	}

	@Test
	public void testHasWinner() {
		s0.setColor(Color.RED);
		s1.setColor(Color.YEL);
		Field place = new Field(0, 0, 0, Color.RED);
		bor.setField(0, 0, 0, Color.RED);
		bor.setField(1, 0, 0, Color.RED);
		bor.setField(2, 0, 0, Color.RED);
		bor.setField(3, 0, 0, Color.RED);
		bor.toString();
		bor.getXRow(Color.RED);
		assertTrue(battle.hasWinner(bor) == true);
		battle.reset();
		bor.setField(0, 0, 0, Color.YEL);
		bor.setField(1, 0, 0, Color.YEL);
		bor.setField(2, 0, 0, Color.YEL);
		bor.setField(3, 0, 0, Color.YEL);
		bor.getXRow(Color.YEL);
		assertTrue(battle.hasWinner(bor) == true);
	}


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
	
	@Test
	public void testSetGetGame() {
		TUIView view = new TUIView();
		battle.setView(view);
		assertEquals(battle.getView(), view);
	}
	
	@Test
	public void testSetGetCurrentPlayer() {
		Player currentPlayer = s0;
		battle.setCurrentPlayer(currentPlayer);
		assertEquals(battle.getCurrentPlayer(), currentPlayer);
	}
	
	@Test
	public void testChangePlayer() {
		Player currentPlayer = s0;		
		battle.setCurrentPlayer(currentPlayer);
		Player currentPlayer2 = s1;
		battle.changePlayer();
		assertEquals(battle.getCurrentPlayer(), currentPlayer2);
		battle.changePlayer();
		assertEquals(battle.getCurrentPlayer(), currentPlayer);
	}

}
