package model;

import java.util.Scanner;

public class Humanplayer extends Player{
	
	private Scanner in;

	public Humanplayer(String name, control.Game game) {
		super(name, game);
		in = new Scanner(System.in);
	}

	@Override
	public void determineMove(Board board) {
		
		
	}

}
