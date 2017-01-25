package control;

import client.Computerplayer;
import model.*;

public class ThreeDRow {
	public static void main(String[] args) {
		Player p = new Computerplayer("Lieke");
		Player q = new Computerplayer("Amber");
		Game battle = new Game(p, q);
		
		battle.start();
	}
}
