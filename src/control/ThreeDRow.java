package control;

import model.*;

public class ThreeDRow {
	private static Player p;
	private static Player q;

	public static void main(String[] args) {
		Game battle = new Game(p, q);
		Player p = new Humanplayer("Lieke", battle, Color.RED );
		Player q = new Humanplayer("Amber", battle, Color.YEL);
		
		battle.start();
	}
}
