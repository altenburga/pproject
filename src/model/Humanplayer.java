package model;

import java.util.Scanner;

public class Humanplayer extends Player {
// x = column, y = height, z = row;
	private Scanner in;

	public Humanplayer(String name, control.Game game) {
		super(name, game);
		in = new Scanner(System.in);
	}

	@Override
	public void determineMove(Board board) {
		int col = askColumn();
		int row = askRow();
		int height = askHeight();
		Tile tile = new Tile(this.getColor());
		Field place = new Field(col, height, row, tile);
		if (game.validMove(place, board)) {
			makeMove(board, place);
		} else {
			System.out.println("This is not a valid move. Try again.");
		}


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

	public int askHeight() {
		System.out.println("At what height would you like to place your tile?");
		int height = in.nextInt();
		return height;

	}

}
