package control;

import model.*;

public class ThreeDRow {
	public static void main(String[] args) {
		Player p = new Computerplayer("Lieke", null);
		Player q = new Computerplayer("Amber", null);
		Game battle = new Game(p, q);
		
		battle.start();
	}
}
