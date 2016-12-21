package control;

import model.*;

public class ThreeDRow {
	private static Player p;
	private static Player q;

	public static void main(String[] args) {
		Player p = new Humanplayer("Lieke", Color.RED );
		Player q = new Humanplayer("Amber", Color.YEL);
		Game battle = new Game(p, q);
		
		battle.start();
	}
}
