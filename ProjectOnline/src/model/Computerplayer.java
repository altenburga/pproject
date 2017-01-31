package model;

import exceptions.OutOfBoundsException;
import server.ClientHandler;
/**
 * A class that represents a ComputerPlayer and determines a random field.
 * @author Lieke en Amber
 *
 */
public class Computerplayer extends Player {
	private static final int DIM = 4;

	public Computerplayer(String name, ClientHandler client) {
		super(name, client);
	}
	

	/**
	*Checks for a random field if it is a valid move.
	*@return the first field it finds which is a valid move. 
	*/
	@Override
	public Field determineMove(Board board) throws OutOfBoundsException {
		Field place = new Field(0, 0, 0, null);
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					Field choice = new Field(i, j, z, this.getColor());
					while (board.validMove(choice)) {
						place = choice;
						break;
					}
				}
			}
		}
		return place;
	}
}
