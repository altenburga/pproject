package control;

import model.*;

public class ThreeDRow {
	private static Player p;
	private static Player q;

	public static void main(String[] args) {
		Player p = new Computerplayer("Lieke");
		Player q = new Computerplayer("Amber");
		Game battle = new Game(p, q);
		
		battle.start();
	}
}
