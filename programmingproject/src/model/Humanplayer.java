package model;

import java.util.Scanner;

import exceptions.OutOfBoundsException;

public class Humanplayer extends Player {
	private Scanner in;

	public Humanplayer(String name) {
		super(name);
		in = new Scanner(System.in);
	}

	/**
	 * Asks the user at what coordinates they want to place their tile and
	 * calculates the Y-coordinate
	 * 
	 * @return the field the user wants
	 */
	@Override
	public Field determineMove(Board board) throws OutOfBoundsException {
		boolean valid = false;
		Field choice = new Field(0, 0, 0, color);
		int col = askColumn();
		int row = askRow();
		Color tile = this.getColor();
		Field place = new Field(0, 0, 0, null);
		for (int j = 0; j < 4; j++) {
			Field tried = new Field(col, j, row, this.getColor());
			while (board.validMove(tried)) {
				choice = tried;
				break;

			}
		}
		return choice;

	}

	/**
	 * Asks a user in what column they want to place their tile.
	 * 
	 * @return the column the user gives.
	 */
	public int askColumn() {
		System.out.println("In which column would you like to place your tile?");
		int col = in.nextInt();
		return col;
	}

	/**
	 * Asks the user in what row they want to place their tile.
	 * 
	 * @return the row the user gives.
	 */
	public int askRow() {
		System.out.println("In which row would you like to place your tile?");
		int row = in.nextInt();
		return row;

	}

}
