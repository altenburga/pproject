package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Board;

public class BoardTest {

	private Board oneb;

	@Before
	public void setUp() throws Exception {
		oneb = new Board();
	}

	@Test
	public void test() {
		oneb.reset();
		Board twob = oneb.Deepcopy();
		assertTrue(oneb.equals(twob));
		
	}
}
