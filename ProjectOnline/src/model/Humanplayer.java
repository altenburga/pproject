package model;

import java.util.Scanner;

import exceptions.OutOfBoundsException;
import server.ClientHandler;

/**
 * A class that asks a Humanplayer for input to determine their move.
 * @author Lieke en Amber
 *
 */
public class Humanplayer extends Player {
	// x = column, y = height, z = row;
	private static final int DIM = 4;
	private Scanner in;

	public Humanplayer(String name, ClientHandler client) {
		super(name, client);
		in = new Scanner(System.in);
	}
/**
 * Asks the user at what coordinates they want to place their tile and calculates the Y-coordinate
 * @return the field the user wants
 */
	@Override
	public Field determineMove(Board board) throws OutOfBoundsException {
		boolean valid = false;
		Field choice = new Field(0, 0, 0, color);
		int col = askQuestion("In what column would you like to place your tile?");
		System.out.println(col);
		int row = askQuestion("In what row would you like to place your tile?");
		Color tile = this.getColor();
		Field place = new Field(0, 0, 0, null);
		for (int j = 0; j < DIM; j++) {
			if (board.isEmpty(col, j, row)) {
				place = new Field(col, j, row, tile);
				break;
			}
		}

		if (board.validMove(choice)) {
			valid = true;
		}
		if (valid = true) {
			choice = place;
		}
		return choice;

	}
/**
 * Asks a client in what column they want to place their tile.
 * @return the column the user gives.
 */

/**
 * Asks the client in what row they want to place their tile.
 * @return the row the user gives.
 */
	public int askQuestion(String question) {
		int answer;
		boolean conti = false;
		System.out.print(question);
		answer = in.nextInt();
		return answer;
	}

}
