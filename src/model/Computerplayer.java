package model;

public class Computerplayer extends Player {

	public Computerplayer(String name) {
		super(name);
	}

	private static final int DIM = 4;

	@Override
	public Field determineMove(Board board) {
		Field choice = new Field(0, 0, 0, color);
		for (int i = 0; i < DIM; i++) {
				for (int z = 0; z < DIM; z++) {
					if (board.isEmpty(i, 0, z)) {
						Field place = new Field(i, 0, z, this.getColor());
						choice = place;
				}
					if (board.isEmpty(i, 1, z)) {
						Field place = new Field(i, 0, z, this.getColor());
						choice = place;
				}
					if (board.isEmpty(i, 2, z)) {
						Field place = new Field(i, 0, z, this.getColor());
						choice = place;
				}
					if (board.isEmpty(i, 3, z)) {
						Field place = new Field(i, 0, z, this.getColor());
						choice = place;
				}
				

			}
		}
		return choice;
	}
}
