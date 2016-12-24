package control;

import model.*;

public class ThreeDRow {
	public static void main(String[] args) {
		Player p = new Humanplayer("Lieke");
		Player q = new Humanplayer("Myrte");
		Game battle = new Game(p, q);
		
		battle.start();
	}
}
