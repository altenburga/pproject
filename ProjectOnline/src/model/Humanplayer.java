package model;

import java.util.Scanner;

import exceptions.OutOfBoundsException;
import protocol.Protocol;
import server.ClientHandler;

/**
 * A class that asks a Humanplayer for input to determine their move.
 * 
 * @author Lieke en Amber
 *
 */
public class Humanplayer extends Player {
	// x = column, y = height, z = row;
	private static final int DIM = 4;
	protected Scanner in;

	public Humanplayer(String name, ClientHandler client) {
		super(name, client);
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
		int col = askQuestion("In what column do you want to place your tile?");
		int row = askQuestion("In what row do you want to place your tile?");
		Color tile = this.getColor();
		Field place = new Field(0, 0, 0, null);
		for (int j = 0; j < 4; j++) {
			Field tried = new Field(col, j, row, this.getColor());
			while(board.validMove(tried)) {
				choice = tried;
				valid = true;
				break;

			}
		}
		if (valid = false) {
			this.getClientHandler().sendMessage(Protocol.SERVER_DENYMOVE);
		}
		return choice;

	}

	/**
	 * Asks the client a question and returns the answer.
	 * 
	 * @return the input the user gives.
	 */
	public int askQuestion(String question) {
		int answer = -1;
		boolean conti = false;
		this.getClientHandler().sendMessage(question);
		while (answer == -1) {
			String line = in.nextLine();
			try {
				answer = Integer.parseInt(line);
			} catch(NumberFormatException e) {
				System.out.println("Please provide valid number.");
			}
		}
		return answer;
	}

}
