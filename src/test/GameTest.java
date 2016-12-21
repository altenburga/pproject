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

public class GameTest {
	public Player s0;
	public Player s1;
	public Game battle;
	
	@Before
	public void setUp() throws Exception {
		battle = new Game(s0, s1);
		s0 = new Humanplayer("Lieke", battle);
		s1 = new Humanplayer("Amber", battle);
		
	}

	

/*	@Test
	public void testFirstPlayer() {
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testValidMove() {
		Board one = new Board();
		one.reset();
		Field place = new Field(0,0,0,Color.RED);
		assertTrue(battle.validMove(place, one));
		one.setField(place);
		Field place1 = new Field(0,1,0,Color.RED);
		assertTrue(battle.validMove(place1, one));
		one.setField(place1);
		Field place2 = new Field(0,2,0,Color.RED);
		assertTrue(battle.validMove(place2, one));
		one.setField(place2);
		Field place3 = new Field(0,3,0,Color.RED);
		assertTrue(battle.validMove(place3, one));
		one.setField(place3);
		Field place5 = new Field(0,1,0,Color.YELLOW);
		assertFalse(battle.validMove(place5, one));
		Field place6 = new Field(1,3,1,Color.YELLOW);
		assertFalse(battle.validMove(place6, one));
		Field place7 = new Field(5,5,5,Color.YELLOW);
		assertFalse(battle.validMove(place7, one));
	}
	@Test
	public void testisWinner() {
		 s0.setColor(Color.RED);
		 Color col = s1.getColor();
		 Board bor = new Board();
		 Field place = new Field(0, 0, 0, Color.RED);
		 bor.setField(0, 0, 0, Color.RED);
		 bor.setField(1, 0, 0, Color.RED);
		 bor.setField(2, 0, 0, Color.RED);
		 bor.setField(3, 0, 0, Color.RED);
		 bor.getRow(place);
		 assertEquals(battle.isWinner(Color.RED), s0.getName() + s0.getColor());
		 
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
		 bor.getRow(place);
		 assertTrue(battle.hasWinner() == true);
	}
	/*
	@Test
	public void testStartgame() {
		fail("Not yet implemented");
	}
	@Test
	public void testPlay() {
		fail("Not yet implemented");
	}
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
		 s0.makeMove(bor);
		 s0.makeMove(bor);
		 s0.makeMove(bor);
		 s0.makeMove(bor); 
//		 bor.setField(0, 0, 0, Color.RED);
//		 bor.setField(1, 0, 0, Color.RED);
//		 bor.setField(2, 0, 0, Color.RED);
//		 bor.setField(3, 0, 0, Color.RED);
		 assertTrue(bor.getRow(place) == true);
		 assertTrue(battle.hasWinner() == true);
		 assertTrue(battle.gameOver() == true);
	}
	/*
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	*/
	
}
