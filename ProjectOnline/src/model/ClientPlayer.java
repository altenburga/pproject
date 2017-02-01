package model;

import java.util.Scanner;

import exceptions.OutOfBoundsException;
import protocol.Protocol;
import server.ClientHandler;

public class ClientPlayer {
	private static final int DIM = 4;
	protected Scanner in;
	private String name;
	public Color color;

	public ClientPlayer(String name) {
		this.name = name;
		in = new Scanner(System.in);
	}

	public Color getColor(){
		return color;
	}
	/**
	 * Asks the user at what coordinates they want to place their tile and
	 * calculates the Y-coordinate
	 * 
	 * @return the field the user wants
	 */
	
	public Field determineMove() throws OutOfBoundsException {
		boolean valid = false;
		Field choice = new Field(0, 0, 0, color);
		int col = askQuestion("In what column do you want to place your tile?");
		int row = askQuestion("In what row do you want to place your tile?");
		Color tile = this.getColor();
		Field place = new Field(0, 0, 0, null);
		for (int j = 0; j < 4; j++) {
			Field tried = new Field(col, j, row, this.getColor());{
			choice = tried;
			valid = true;
			break;

			}
		}
		if (valid = false) {
			System.out.println("This tile is invalid");
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
		System.out.println(question);
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

