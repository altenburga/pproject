package control;

import java.util.Scanner;

import model.Color;
import model.Computerplayer;
import model.Humanplayer;
import model.Player;

public class ThreeDRow {
	
	public static void main(String[] args) {
		Player p = determinePlayer(args[0], Color.RED);
		Player q = determinePlayer(args[1], Color.YEL);
		Game battle = new Game(p, q);
		
		battle.start();
	}
	
	public static Player determinePlayer(String arg, Color color) {
		Player player = null;
		String argument = arg.toUpperCase();
		Scanner scanner = new Scanner(System.in);
		if (argument.equals("-H")) {
			System.out.println("What is your name?");
			String name = scanner.nextLine();
			player = new Humanplayer(name);
		} else if (argument.equals("-C")) {
			System.out.println("What is the name of the computer player?");
			String compName = scanner.nextLine();
			player = new Computerplayer(compName);
		}
		return player;
	}
}
