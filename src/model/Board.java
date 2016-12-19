package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private static final int DIM = 4;
	private Color[][][] fields;

	public Board() {
		fields = new Color[4][4][4];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					fields[i][j][z] = Color.EMPTY;
				}

			}
		}
	}

	public Board Deepcopy() {
		Board newBoard = new Board();
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					newBoard.fields[i][j][z] = this.fields[i][j][z];
				}
			}
		}
		return newBoard;
	}

	public boolean isField(int x, int y, int z) {
		return x < DIM && x >= 0 && y < DIM && y >= 0 && z < DIM && z >= 0;
	}

	public Color getField(int x, int y, int z) {
		if (isField(x, y, z)) {
			return fields[x][y][z];
		} else {
			return null;
		}

	}

	public void setField(int x, int y, int z, Color choice) {
		fields[x][y][z] = choice;

	}

	/*
	 * public void setField(Field move) { Integer x = move.getX(); Integer y =
	 * move.getY(); Integer z = move.getZ(); Color one = move.getColor;
	 * fields[x][y][z].setTile(one); ;
	 * 
	 * }
	 */

	public void reset() {
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					fields[i][j][z] = Color.EMPTY;
				}

			}

		}
	}

	public boolean isEmpty(int i, int j, int z) {
		return getField(i, j, z) == Color.EMPTY;

	}

	public boolean boardEmpty() {
		boolean empty = false;
		for (int i = 0; i < (DIM); i++) {
			for (int j = 0; j < (DIM); j++) {
				for (int z = 0; z < DIM; z++) {
					if (fields[i][j][z] == Color.EMPTY) {
						empty = true;
					}

				}

			}

		}
		return empty;

	}

	public boolean getCol(Field choice) {
		boolean column = false;
		int x = choice.getX();
//		int y = choice.getY();
		int z = choice.getZ();
		if (choice.getColor() != Color.EMPTY) {
			Color color = choice.getColor();
			if (fields[x][0][z] == color && fields[x][1][z] == color && fields[x][2][z] == color
					&& fields[x][3][z] == color) {
				column = true;
			}
		}
		return column;

	}

	public boolean getRow(Field choice) {
		boolean row = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (choice.getColor() != Color.EMPTY) {
			Color col = choice.getColor();
			if (fields[0][y][z] == col && fields[1][y][z] == col && fields[2][y][z] == col && fields[3][y][z] == col) {
				row = true;
			}
			if (fields[x][y][0] == col && fields[x][y][1] == col && fields[x][y][2] == col && fields[x][y][3] == col) {
				row = true;
			}
		}
		return row;

	}

	public boolean getDiagDiag(Color color) {
		boolean row = false;
		if (fields[0][0][0] == color && fields[1][1][1] == color && fields[2][2][2] == color && fields[3][3][3] == color
				|| fields[0][0][3] == color && fields[1][1][2] == color && fields[2][2][1] == color
						&& fields[3][3][0] == color) {
			row = true;
		}
		return row;

	}

	public boolean getXdiag(Color color) {
		boolean row = false;
		for (int x = 0; x < (DIM); x++) {
			if (fields[x][0][3] == (color) && fields[x][3][0] == (color)) {
				if (fields[x][1][2] == (color) && fields[x][2][1] == (color)) {
					row = true;
				}
			}
			if (fields[x][0][0] == (color) && fields[x][3][3] == (color)) {
				if (fields[x][2][2] == (color) && fields[x][1][1] == (color)) {
					row = true;
				}

			}
		}
		return row;
	}

	public boolean getZdiag(Color color) {
		boolean row = false;
		for (int z = 0; z < (DIM); z++) {
			if (fields[0][3][z] == (color) && fields[3][0][z] == (color)) {
				if (fields[1][2][z] == (color) && fields[2][1][z] == (color)) {
					row = true;
				}
			}
			if (fields[0][0][z] == (color) && fields[3][3][z] == (color)) {
				if (fields[2][2][z] == (color) && fields[1][1][z] == (color)) {
					row = true;
				}

			}
		}
		return row;
	}

	public void showBoard() {
		System.out.println(" 1 2 3");
		System.out.println("A" + fields[1][1][1] + "|" + fields[2][1][1] + "|" + fields[3][1][1]);
		System.out.println("-----");
		System.out.println("B" + fields[1][2][1] + "|" + fields[2][2][1] + "|" + fields[3][2][1]);
		System.out.println("-----");
		System.out.println("C" + fields[1][3][1] + "|" + fields[2][3][1] + "|" + fields[3][3][1]);
	}

	public static void main(String[] args) {
		Board one = new Board();
		one.reset();
		one.showBoard();

	}
}
