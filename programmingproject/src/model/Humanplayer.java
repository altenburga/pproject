package model;

import java.util.Scanner;

public class Humanplayer extends Player {
	private Scanner in;

	public Humanplayer(String name) {
		super(name);
		in = new Scanner(System.in);
	}

	@Override
	public Field determineMove(Board board) {
		boolean valid = false;
		Field choice = new Field(0, 0, 0, color);
		int col = askColumn();
		int row = askRow();
		Color tile = this.getColor();
		Field place = new Field(0, 0, 0, null);
		for (int j = 0; j < 4; j++) {
			if (board.isEmpty(col, j, row)) {
				place = new Field(col, j, row, tile);
				break;
			}
		}

		if (board.validMove(choice)) {
			valid = true;
		}
		if (valid) {
			choice = place;
		}
		return choice;

	}

	public int askColumn() {
		System.out.println("In which column would you like to place your tile?");
		int col = in.nextInt();
		return col;
	}

	public int askRow() {
		System.out.println("In which row would you like to place your tile?");
		int row = in.nextInt();
		return row;

	}

}
