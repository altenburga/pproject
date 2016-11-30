package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private static final int DIM = 4;
	public Field[][][] place = new Field[DIM][DIM][DIM];
	// * private int MinX = 1;
	// * private int MaxX = 4;
	// * private int MinY = 1;
	// * private int MaxY = 4;
	// * private int MinZ = 1;
	// * private int MaxZ = 4;

	public Board() {
		place = new Field[Board.DIM][Board.DIM][Board.DIM];
	}

	public Board Deepcopy() {
		Board newBoard = new Board();
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					newBoard.place[i][j][z] = this.place[i][j][z];
				}
			}
		}
		return newBoard;
	}

	public boolean isField(int x, int y, int z) {
		return x <= DIM && x > 0 && y <= DIM && y > 0 && z <= DIM && z > 0;
	}

	public Field getField(int x, int y, int z) {
		if (isField(x, y, z)) {
			return place[x][y][z];
		} else {
			return null;
		}

	}

	public void setField(int x, int y, int z, Tile choice) {
		place[x][y][z].setTile(choice);

	}

	public void setField(Field move) {
		Integer x = move.getX();
		Integer y = move.getY();
		Integer z = move.getZ();
		Tile one = move.getTile();
		place[x][y][z].setTile(one);
		;

	}

	public void reset() {
		Tile choice = new Tile(0);
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					choice = place[i][j][z].getTile();
					choice.setTile(0);
				}

			}

		}
	}

	public boolean isEmpty() {
		Tile choice = new Tile(1);
		boolean result = true;
		for (int x = 0; x < (DIM); x++) {
			for (int y = 0; y < (DIM); y++) {
				for (int z = 0; z < DIM; z++) {
					choice = place[x][y][z].getTile();
					if (choice.getColor() != 0) {
						result = false;
					}
				}
			}
		}
		return result;
	}

	public boolean boardEmpty() {
		boolean empty = true;
		for (int x = 0; x < (DIM); x++) {
			for (int y = 0; y < (DIM); y++) {
				for (int z = 0; z < DIM; z++) {
					Tile t = place[x][y][z].getTile();
					if (t.getColor() != 0) {
						empty = false;
					} else {
						empty = true;
					}

				}

			}

		}
		return empty;

	}

	public boolean isEmpty(int x, int y, int z) {
		return ((place[x][y][z].getTile().getColor()).equals(0));
	}

	public boolean getCol(Field choice) {
		boolean column = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (choice.getTile().getColor() != 0) {
			int color = choice.getTile().getColor();
			if (place[x][1][z].getTile().getColor() == color && place[x][2][z].getTile().getColor() == color
					&& place[x][3][z].getTile().getColor() == color && place[x][4][z].getTile().getColor() == color) {
				column = true;

			}
		}
		return column;

	}

	public boolean getRow(Field choice) {
		boolean Row = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (choice.getTile().getColor() != 0) {
			int col = choice.getTile().getColor();
			if (place[1][y][z].getTile().getColor() == col && place[2][y][z].getTile().getColor() == col
					&& place[3][y][z].getTile().getColor() == col && place[4][y][z].getTile().getColor() == col) {
				Row = true;
			}
			if (place[x][y][1].getTile().getColor() == col && place[x][y][2].getTile().getColor() == col
					&& place[x][y][3].getTile().getColor() == col && place[x][y][4].getTile().getColor() == col) {
				Row = true;
			}
			if (place[1][1][1].getTile().getColor() == col && place[2][2][2].getTile().getColor() == col
					&& place[3][3][3].getTile().getColor() == col && place[4][4][4].getTile().getColor() == col) {
				Row = true;
			}
			if (place[4][1][1].getTile().getColor() == col && place[3][2][2].getTile().getColor() == col
					&& place[2][2][3].getTile().getColor() == col && place[1][4][4].getTile().getColor() == col) {
				Row = true;
			}
		}
		return Row;

	}

	public boolean getDiagDiag(Tile tile) {
		boolean row = false;
		int color = tile.getColor();
		if (place[1][1][1].getTile().getColor() == color && place[2][2][2].getTile().getColor() == color
				&& place[3][3][3].getTile().getColor() == color && place[4][4][4].getTile().getColor() == color
				|| place[1][1][4].getTile().getColor() == color && place[2][2][3].getTile().getColor() == color
						&& place[3][3][2].getTile().getColor() == color
						&& place[4][4][1].getTile().getColor() == color) {
			row = true;

		}
		return row;

	}

	public boolean getXdiag(Tile tile) {
		boolean row = false;
		int color = tile.getColor();
		for (int x = 1; x < (DIM); x++) {
			if (place[x][1][4].getTile().getColor().equals(color)
					&& place[x][4][1].getTile().getColor().equals(color)) {
				if (place[x][2][3].getTile().getColor().equals(color)
						&& place[x][3][2].getTile().getColor().equals(color)) {
					row = true;
				}
			}
			if (place[x][1][1].getTile().getColor().equals(color)
					&& place[x][4][4].getTile().getColor().equals(color)) {
				if (place[x][3][3].getTile().getColor().equals(color)
						&& place[x][2][2].getTile().getColor().equals(color)) {
					row = true;
				}

			}
		}
		return row;
	}

	public boolean getZdiag(Tile tile) {
		boolean row = false;
		int color = tile.getColor();
		for (int z = 1; z <= (DIM); z++) {
			if (place[1][4][z].getTile().getColor().equals(color)
					&& place[4][1][z].getTile().getColor().equals(color)) {
				if (place[2][3][z].getTile().getColor().equals(color)
						&& place[3][2][z].getTile().getColor().equals(color)) {
					row = true;
				}
			}
			if (place[1][1][z].getTile().getColor().equals(color)
					&& place[4][4][z].getTile().getColor().equals(color)) {
				if (place[3][3][z].getTile().getColor().equals(color)
						&& place[2][2][z].getTile().getColor().equals(color)) {
					row = true;
				}

			}
		}
		return row;
	}
}
