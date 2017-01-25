package client;

import model.Board;
import model.Field;
import model.Player;

public class Computerplayer extends Player {

	public Computerplayer(String name) {
		super(name);
	}

	private static final int DIM = 4;

	@Override
	public Field determineMove(Board board) {
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
