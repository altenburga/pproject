package controller;

import java.util.Scanner;

import exceptions.FieldNotExistingException;
import exceptions.OutOfBoundsException;
import exceptions.WrongInputException;
import model.Color;
import model.Computerplayer;
import model.Humanplayer;
import model.Player;

public class ThreeDRow {
	
	private static Game battle;
	public int index;
/**
 * create a game of Four in a row in 3D
 * @param args
 * @throws FieldNotExistingException 
 * @throws OutOfBoundsException 
 * @throws WrongInputException 
 */
	public static void main(String[] args) throws OutOfBoundsException, FieldNotExistingException, WrongInputException {
		Player p = determinePlayer(Color.RED);
		Player q = determinePlayer(Color.YEL);
		battle = new Game(p, q);
		
		battle.start();
	}
/**
 * 	Asks the user whether they want to be a computerplayer or humanplayer and what their name is. 
 * @param color
 * @return a human- or computerplayer with the given name
 */
	public static Player determinePlayer(Color color) {
		Player player = null;
		boolean human = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Would you like to be a HumanPlayer? (yes or no)");
		if (scanner.nextLine().equals("yes")) {
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
