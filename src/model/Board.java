package model;

import java.util.ArrayList;
import java.util.List;

import control.Game;

public class Board {
	private static final int DIM = 4;
	private Color[][][] fields;

	public Board() {
		fields = new Color[4][4][4];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					fields[i][j][z] = Color.EMP;
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

	public void setField(Field choice) {
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		Color one = choice.getColor();
		fields[x][y][z] = one;
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
					fields[i][j][z] = Color.EMP;
				}

			}

		}
	}

	public boolean isEmpty(int i, int j, int z) {
		return getField(i, j, z) == Color.EMP;

	}

	public boolean boardEmpty() {
		boolean empty = false;
		for (int i = 0; i < (DIM); i++) {
			for (int j = 0; j < (DIM); j++) {
				for (int z = 0; z < DIM; z++) {
					if (fields[i][j][z] == Color.EMP) {
						empty = true;
					}

				}

			}

		}
		return empty;

	}

	/*
	 * public boolean getCol1(Field choice) { boolean column = false; int x =
	 * choice.getX(); // int y = choice.getY(); int z = choice.getZ(); if
	 * (choice.getColor() != Color.EMP) { Color color = choice.getColor(); if
	 * (fields[x][0][z] == color && fields[x][1][z] == color && fields[x][2][z]
	 * == color && fields[x][3][z] == color) { column = true; } } return column;
	 * 
	 * }
	 */

	public boolean getCol(Color color) {
		boolean column = false;
		for (int i = 0; i < (DIM); i++) {
			for (int j = 0; j < (DIM); j++) {
				if (fields[i][3][j] == color) {
					column = (fields[i][2][j] == color && fields[i][1][j] == color && fields[i][0][j] == color);
				}
			}
		}
		return column;
	}

	/*
	 * public boolean getRow(Field choice) { boolean row = false; int x =
	 * choice.getX(); int y = choice.getY(); int z = choice.getZ(); if
	 * (choice.getColor() != Color.EMP) { Color col = choice.getColor(); if
	 * (fields[0][y][z] == col && fields[1][y][z] == col && fields[2][y][z] ==
	 * col && fields[3][y][z] == col) { row = true; } if (fields[x][y][0] == col
	 * && fields[x][y][1] == col && fields[x][y][2] == col && fields[x][y][3] ==
	 * col) { row = true; } } return row;
	 * 
	 * }
	 */
	public boolean getXRow(Color choice) {
		boolean xRow = false;
		for (int i = 0; i < (DIM); i++) {
			for (int j = 0; j < (DIM); j++) {
				if (fields[0][i][j] == choice) {
					xRow = (fields[1][i][j] == choice && fields[2][i][j] == choice && fields[3][i][j] == choice);
				}
			}
		}
		return xRow;
	}

	public boolean getZRow(Color choice) {
		boolean zRow = false;
		for (int i = 0; i < (DIM); i++) {
			for (int j = 0; j < (DIM); j++) {
				if (fields[i][j][0] == choice) {
					zRow = (fields[i][j][1] == choice && fields[i][j][2] == choice && fields[i][j][3] == choice);
				}
			}
		}
		return zRow;
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

	public boolean isFull(Board b) {
		for (int i = 0; i < (DIM); i++) {
			for (int j = 0; j < (DIM); j++) {
				for (int z = 0; z < DIM; z++) {
					if (fields[i][j][z] == Color.EMP) {
						return false;
					}
				}
			}
		}
		return true;

	}
	public boolean validMove(Field choice, Game game) {
		boolean valid = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (this.boardEmpty() && y == 0) {
			valid = true;
		}
		if (this.isEmpty(x, y, z) && y == 0) {
			valid = true;
		}
		if (y >= 4) {
			valid = false;
		}
		if (this.isEmpty(x, y, z) && y != 0) {
			if (this.isEmpty(x, y - 1, z)) {
				valid = false;
			} else {
				valid = true;
			}
		}

		return valid;

	}

	public String toString() {
		String s = "";
		System.out.println("       Z = 0" + "                 Z = 1                 Z = 2                 Z = 3");
		System.out.println("  " + fields[0][3][0] + "|" + fields[1][3][0] + "|" + fields[2][3][0] + "|"
				+ fields[3][3][0] + "       " + fields[0][3][1] + "|" + fields[1][3][1] + "|" + fields[2][3][1] + "|"
				+ fields[3][3][1] + "       " + fields[0][3][2] + "|" + fields[1][3][2] + "|" + fields[2][3][2] + "|"
				+ fields[3][3][2] + "       " + fields[0][3][3] + "|" + fields[1][3][3] + "|" + fields[2][3][3] + "|"
				+ fields[3][3][3]);
		System.out.println("Y " + fields[0][2][0] + "|" + fields[1][2][0] + "|" + fields[2][2][0] + "|"
				+ fields[3][2][0] + "     Y " + fields[0][2][1] + "|" + fields[1][2][1] + "|" + fields[2][2][1] + "|"
				+ fields[3][2][1] + "     Y " + fields[0][2][2] + "|" + fields[1][2][2] + "|" + fields[2][2][2] + "|"
				+ fields[3][2][2] + "     Y " + fields[0][2][3] + "|" + fields[1][2][3] + "|" + fields[2][2][3] + "|"
				+ fields[3][2][3]);
		System.out.println("  " + fields[0][1][0] + "|" + fields[1][1][0] + "|" + fields[2][1][0] + "|"
				+ fields[3][1][0] + "       " + fields[0][1][1] + "|" + fields[1][1][1] + "|" + fields[2][1][1] + "|"
				+ fields[3][1][1] + "       " + fields[0][1][2] + "|" + fields[1][1][2] + "|" + fields[2][1][2] + "|"
				+ fields[3][1][2] + "       " + fields[0][1][3] + "|" + fields[1][1][3] + "|" + fields[2][1][3] + "|"
				+ fields[3][1][3]);
		System.out.println("  " + fields[0][0][0] + "|" + fields[1][0][0] + "|" + fields[2][0][0] + "|"
				+ fields[3][0][0] + "       " + fields[0][0][1] + "|" + fields[1][0][1] + "|" + fields[2][0][1] + "|"
				+ fields[3][0][1] + "       " + fields[0][0][2] + "|" + fields[1][0][2] + "|" + fields[2][0][2] + "|"
				+ fields[3][0][2] + "       " + fields[0][0][3] + "|" + fields[1][0][3] + "|" + fields[2][0][3] + "|"
				+ fields[3][0][3]);
		System.out.println("         X                     X                     X                     X");
		System.out.println("");
		System.out.println("       X = 0" + "                 X = 1                 X = 2                 X = 3");
		System.out.println("  " + fields[0][3][3] + "|" + fields[0][3][2] + "|" + fields[0][3][1] + "|"
				+ fields[0][3][0] + "       " + fields[1][3][3] + "|" + fields[1][3][2] + "|" + fields[1][3][1] + "|"
				+ fields[1][3][0] + "       " + fields[2][3][3] + "|" + fields[2][3][2] + "|" + fields[2][3][1] + "|"
				+ fields[2][3][0] + "       " + fields[3][3][3] + "|" + fields[3][3][2] + "|" + fields[3][3][1] + "|"
				+ fields[3][3][0]);
		System.out.println("Y " + fields[0][2][3] + "|" + fields[0][2][2] + "|" + fields[0][2][1] + "|"
				+ fields[0][2][0] + "     Y " + fields[1][2][3] + "|" + fields[1][2][2] + "|" + fields[1][2][1] + "|"
				+ fields[1][2][0] + "     Y " + fields[2][2][3] + "|" + fields[2][2][2] + "|" + fields[2][2][1] + "|"
				+ fields[2][2][0] + "     Y " + fields[3][2][3] + "|" + fields[3][2][2] + "|" + fields[3][2][1] + "|"
				+ fields[3][2][0]);
		System.out.println("  " + fields[0][1][3] + "|" + fields[0][1][2] + "|" + fields[0][1][1] + "|"
				+ fields[0][1][0] + "       " + fields[1][1][3] + "|" + fields[1][1][2] + "|" + fields[1][1][1] + "|"
				+ fields[1][1][0] + "       " + fields[2][1][3] + "|" + fields[2][1][2] + "|" + fields[2][1][1] + "|"
				+ fields[2][1][0] + "       " + fields[3][1][3] + "|" + fields[3][1][2] + "|" + fields[3][1][1] + "|"
				+ fields[3][1][0]);
		System.out.println("  " + fields[0][0][3] + "|" + fields[0][0][2] + "|" + fields[0][0][1] + "|"
				+ fields[0][0][0] + "       " + fields[1][0][3] + "|" + fields[1][0][2] + "|" + fields[1][0][1] + "|"
				+ fields[1][0][0] + "       " + fields[2][0][3] + "|" + fields[2][0][2] + "|" + fields[2][0][1] + "|"
				+ fields[2][0][0] + "       " + fields[3][0][3] + "|" + fields[3][0][2] + "|" + fields[3][0][1] + "|"
				+ fields[3][0][0]);
		System.out.println("         Z                     Z                     Z                     Z");
		System.out.println("");
		System.out.println("  " + fields[0][3][0] + "|" + fields[1][3][1] + "|" + fields[2][3][2] + "|"
				+ fields[3][3][3] + "       " + fields[0][3][3] + "|" + fields[1][3][2] + "|" + fields[2][3][1] + "|"
				+ fields[3][3][0]);
		System.out.println("Y " + fields[0][2][0] + "|" + fields[1][2][1] + "|" + fields[2][2][2] + "|"
				+ fields[3][2][3] + "     Y " + fields[0][2][3] + "|" + fields[1][2][2] + "|" + fields[2][2][1] + "|"
				+ fields[3][2][0]);
		System.out.println("  " + fields[0][1][0] + "|" + fields[1][1][1] + "|" + fields[2][1][2] + "|"
				+ fields[3][1][3] + "       " + fields[0][1][3] + "|" + fields[1][1][2] + "|" + fields[2][1][1] + "|"
				+ fields[3][1][0]);
		System.out.println("  " + fields[0][0][0] + "|" + fields[1][0][1] + "|" + fields[2][0][2] + "|"
				+ fields[3][0][3] + "       " + fields[0][0][3] + "|" + fields[1][0][2] + "|" + fields[2][0][1] + "|"
				+ fields[3][0][0]);
		return s;

	}

	/*public void showBoard() {
		System.out.println("       Z = 0" + "                 Z = 1                 Z = 2                 Z = 3");
		System.out.println("  " + fields[0][3][0] + "|" + fields[1][3][0] + "|" + fields[2][3][0] + "|"
				+ fields[3][3][0] + "       " + fields[0][3][1] + "|" + fields[1][3][1] + "|" + fields[2][3][1] + "|"
				+ fields[3][3][1] + "       " + fields[0][3][2] + "|" + fields[1][3][2] + "|" + fields[2][3][2] + "|"
				+ fields[3][3][2] + "       " + fields[0][3][3] + "|" + fields[1][3][3] + "|" + fields[2][3][3] + "|"
				+ fields[3][3][3]);
		System.out.println("Y " + fields[0][2][0] + "|" + fields[1][2][0] + "|" + fields[2][2][0] + "|"
				+ fields[3][2][0] + "     Y " + fields[0][2][1] + "|" + fields[1][2][1] + "|" + fields[2][2][1] + "|"
				+ fields[3][2][1] + "     Y " + fields[0][2][2] + "|" + fields[1][2][2] + "|" + fields[2][2][2] + "|"
				+ fields[3][2][2] + "     Y " + fields[0][2][3] + "|" + fields[1][2][3] + "|" + fields[2][2][3] + "|"
				+ fields[3][2][3]);
		System.out.println("  " + fields[0][1][0] + "|" + fields[1][1][0] + "|" + fields[2][1][0] + "|"
				+ fields[3][1][0] + "       " + fields[0][1][1] + "|" + fields[1][1][1] + "|" + fields[2][1][1] + "|"
				+ fields[3][1][1] + "       " + fields[0][1][2] + "|" + fields[1][1][2] + "|" + fields[2][1][2] + "|"
				+ fields[3][1][2] + "       " + fields[0][1][3] + "|" + fields[1][1][3] + "|" + fields[2][1][3] + "|"
				+ fields[3][1][3]);
		System.out.println("  " + fields[0][0][0] + "|" + fields[1][0][0] + "|" + fields[2][0][0] + "|"
				+ fields[3][0][0] + "       " + fields[0][0][1] + "|" + fields[1][0][1] + "|" + fields[2][0][1] + "|"
				+ fields[3][0][1] + "       " + fields[0][0][2] + "|" + fields[1][0][2] + "|" + fields[2][0][2] + "|"
				+ fields[3][0][2] + "       " + fields[0][0][3] + "|" + fields[1][0][3] + "|" + fields[2][0][3] + "|"
				+ fields[3][0][3]);
		System.out.println("         X                     X                     X                     X");
		System.out.println("");
		System.out.println("       X = 0" + "                 X = 1                 X = 2                 X = 3");
		System.out.println("  " + fields[0][3][3] + "|" + fields[0][3][2] + "|" + fields[0][3][1] + "|"
				+ fields[0][3][0] + "       " + fields[1][3][3] + "|" + fields[1][3][2] + "|" + fields[1][3][1] + "|"
				+ fields[1][3][0] + "       " + fields[2][3][3] + "|" + fields[2][3][2] + "|" + fields[2][3][1] + "|"
				+ fields[2][3][0] + "       " + fields[3][3][3] + "|" + fields[3][3][2] + "|" + fields[3][3][1] + "|"
				+ fields[3][3][0]);
		System.out.println("Y " + fields[0][2][3] + "|" + fields[0][2][2] + "|" + fields[0][2][1] + "|"
				+ fields[0][2][0] + "     Y " + fields[1][2][3] + "|" + fields[1][2][2] + "|" + fields[1][2][1] + "|"
				+ fields[1][2][0] + "     Y " + fields[2][2][3] + "|" + fields[2][2][2] + "|" + fields[2][2][1] + "|"
				+ fields[2][2][0] + "     Y " + fields[3][2][3] + "|" + fields[3][2][2] + "|" + fields[3][2][1] + "|"
				+ fields[3][2][0]);
		System.out.println("  " + fields[0][1][3] + "|" + fields[0][1][2] + "|" + fields[0][1][1] + "|"
				+ fields[0][1][0] + "       " + fields[1][1][3] + "|" + fields[1][1][2] + "|" + fields[1][1][1] + "|"
				+ fields[1][1][0] + "       " + fields[2][1][3] + "|" + fields[2][1][2] + "|" + fields[2][1][1] + "|"
				+ fields[2][1][0] + "       " + fields[3][1][3] + "|" + fields[3][1][2] + "|" + fields[3][1][1] + "|"
				+ fields[3][1][0]);
		System.out.println("  " + fields[0][0][3] + "|" + fields[0][0][2] + "|" + fields[0][0][1] + "|"
				+ fields[0][0][0] + "       " + fields[1][0][3] + "|" + fields[1][0][2] + "|" + fields[1][0][1] + "|"
				+ fields[1][0][0] + "       " + fields[2][0][3] + "|" + fields[2][0][2] + "|" + fields[2][0][1] + "|"
				+ fields[2][0][0] + "       " + fields[3][0][3] + "|" + fields[3][0][2] + "|" + fields[3][0][1] + "|"
				+ fields[3][0][0]);
		System.out.println("         Z                     Z                     Z                     Z");
		System.out.println("");
		System.out.println("  " + fields[0][3][0] + "|" + fields[1][3][1] + "|" + fields[2][3][2] + "|"
				+ fields[3][3][3] + "       " + fields[0][3][3] + "|" + fields[1][3][2] + "|" + fields[2][3][1] + "|"
				+ fields[3][3][0]);
		System.out.println("Y " + fields[0][2][0] + "|" + fields[1][2][1] + "|" + fields[2][2][2] + "|"
				+ fields[3][2][3] + "     Y " + fields[0][2][3] + "|" + fields[1][2][2] + "|" + fields[2][2][1] + "|"
				+ fields[3][2][0]);
		System.out.println("  " + fields[0][1][0] + "|" + fields[1][1][1] + "|" + fields[2][1][2] + "|"
				+ fields[3][1][3] + "       " + fields[0][1][3] + "|" + fields[1][1][2] + "|" + fields[2][1][1] + "|"
				+ fields[3][1][0]);
		System.out.println("  " + fields[0][0][0] + "|" + fields[1][0][1] + "|" + fields[2][0][2] + "|"
				+ fields[3][0][3] + "       " + fields[0][0][3] + "|" + fields[1][0][2] + "|" + fields[2][0][1] + "|"
				+ fields[3][0][0]);

	}

	public static void main(String[] args) {
		Board one = new Board();
		one.reset();
		one.showBoard();

	} */
}
