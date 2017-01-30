package control;

import java.util.Scanner;

import model.Color;
import model.Computerplayer;
import model.Humanplayer;
import model.Player;

public class ThreeDRow {
	
	private static Game battle;
	public int index;

	public static void main(String[] args) {
		Player p = determinePlayer(Color.RED);
		Player q = determinePlayer(Color.YEL);
		battle = new Game(p, q);
		
		battle.start();
	}
	
	public static Player determinePlayer(Color color) {
		Player player = null;
		boolean human = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Would you like to be a HumanPlayer? (yes or no)");
		if(scanner.nextLine().equals("yes")){
			human = true;
		}
		if (human) {
			System.out.println("What is your name?");
			String name = scanner.nextLine();
			player = new Humanplayer(name);
		} else if (!human) {
			System.out.println("What is the name of the computer player?");
			String compName = scanner.nextLine();
			player = new Computerplayer(compName);
		}
		return player;
	}
}
