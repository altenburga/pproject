package model;

import java.util.Observable;

import exceptions.OutOfBoundsException;

/**
 * Represents a board in the Connect Four 3D game.
 * 
 * @author Lieke en Amber
 *
 */

public class Board extends Observable {
	private static final int DIM = 4;
	private Color[][][] fields;

	/**
	 * Creates an empty board.
	 */
	public Board() {
		fields = new Color[DIM][DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					fields[i][j][z] = Color.EMP;
				}

			}
		}
	}

	/**
	 * Creates a deep copy of the board.
	 */
	/*
	 * @ensures \result.equals(this.reset());
	 */
	public Board deepCopy() {
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

	/**
	 * Returns true if the (x, y, z) pair refers to a valid field on the board.
	 * 
	 * @param x
	 *            the x-coordinate of the field
	 * @param y
	 *            the y-coordinate of the field
	 * @param z
	 *            the z-coordinate of the field
	 * @return true if 0 <= x < DIM && 0 <= y < DIM && 0 <= z < DIM
	 */
	/* pure */
	public boolean isField(int x, int y, int z) {
		return x < DIM && x >= 0 && y < DIM && y >= 0 && z < DIM && z >= 0;
	}

	/**
	 * Returns the content of the field referred to the (x, y, z) coordinates.
	 * 
	 * @param x
	 *            the x-coordinate of the field
	 * @param y
	 *            the y-coordinate of the field
	 * @param z
	 *            the z-coordinate of the field
	 * @return the color on the field
	 */
	/* pure */
	// @ requires isField(x,y,z);
	// @ ensures \result == fields[x][y][z];
	public Color getField(int x, int y, int z) throws OutOfBoundsException {
		if (isField(x, y, z)) {
			return fields[x][y][z];
		} else {
			throw new OutOfBoundsException(x, y, z);
		}

	}

	/**
	 * Sets the content of the field represented by the (x, y, z) coordinates to
	 * the color choice.
	 * 
	 * @param x
	 *            the x-coordinate of the field
	 * @param y
	 *            the y-coordinate of the field
	 * @param z
	 *            the z-coordinate of the field
	 * @param choice
	 *            the color to be placed
	 */
	// @requires isField(x,y,z);
	// @ensures this.getField(x,y,z).getColor().equals(choice);
	public void setField(int x, int y, int z, Color choice) {
		if (this.isField(x, y, z)) {
			fields[x][y][z] = choice;
		}

	}

	// @requires choice =! null && isField(choice.getX(), choice.getY(),
	// choice.getZ());
	// @ensures this.getField(choice.getX(), choice.getY(),
	// choice.getZ()).equals(choice.getColor());
	public void setField(Field choice) {
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		Color one = choice.getColor();
		fields[x][y][z] = one;
	}

	/**
	 * Empties all fields of the board (i.e., let them refer to the value
	 * Color.EMP).
	 */
	/*
	 * @ensures (\forall int x = 0; x < DIM; int y = 0; y < DIM; int z = 0; z <
	 * DIM; fields[x][y][z].equals(Color.EMP));
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

	/**
	 * Returns true if the field referred to by the (x, y, z) coordinates is
	 * empty.
	 * 
	 * @param x
	 *            the x-coordinate of the field
	 * @param y
	 *            the y-coordinate of the field
	 * @param z
	 *            the z-coordinate of the field
	 * @return true if the field is empty
	 * @throws OutOfBoundsException
	 */
	/* pure */
	public boolean isEmpty(int x, int y, int z) throws OutOfBoundsException {
		return getField(x, y, z) == Color.EMP;

	}

	/**
	 * Checks whether all the fields on the board are empty (i.e., all fields on
	 * the board equal Color.EMP).
	 * 
	 * @return true if the board is empty
	 */
	/* pure */
	public boolean boardEmpty() {
		boolean empty = false;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					if (fields[i][j][z] == Color.EMP) {
						empty = true;
					}

				}

			}

		}
		return empty;

	}

	/**
	 * Checks whether there is a column which is full and only contains the
	 * color color.
	 * 
	 * @param color
	 *            the color of interest
	 * @return true if there is a column controlled by color
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 * @ensures
	 */
	public boolean getCol(Color color) {
		boolean column = false;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (fields[i][3][j] == color) {
					column = fields[i][2][j] == color && fields[i][1][j] == color && 
							fields[i][0][j] == color;
				}
			}
		}
		return column;
	}

	/**
	 * Checks whether there is a row in the x-direction which is full and only
	 * contains the color choice.
	 * 
	 * @param choice
	 *            the color of interest
	 * @return true if there is a x-row controlled by choice
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean getXRow(Color choice) {
		boolean xRow = false;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (fields[0][i][j] == choice) {
					xRow = fields[1][i][j] == choice && fields[2][i][j] == choice && 
							fields[3][i][j] == choice;
				}
			}
		}
		return xRow;
	}

	/**
	 * Checks whether there is a row in the z-direction which is full and only
	 * contains the color choice.
	 * 
	 * @param choice
	 *            the color of interest
	 * @return true if there is a z-row controlled by choice
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean getZRow(Color choice) {
		boolean zRow = false;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (fields[i][j][0] == choice) {
					zRow = fields[i][j][1] == choice && fields[i][j][2] == choice && 
							fields[i][j][3] == choice;
				}
			}
		}
		return zRow;
	}

	/**
	 * Checks whether there is a diagonal in the diagonal direction which is
	 * full and only contains the color color.
	 * 
	 * @param color
	 *            the color of interest
	 * @return true if there is a diagonal controlled by color
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean getDiagDiag(Color color) {
		boolean row = false;
		if (fields[0][0][0] == color && fields[1][1][1] == color && fields[2][2][2] == color && 
				fields[3][3][3] == color || fields[0][0][3] == color && fields[1][1][2] == color
				&& fields[2][2][1] == color && fields[3][3][0] == color) {
			row = true;
		}
		return row;

	}

	/**
	 * Checks whether there is a diagonal in the x-direction which is full and
	 * only contains the color color.
	 * 
	 * @param color
	 *            the color of interest
	 * @return true if there is a x-diagonal controlled by color
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean getXdiag(Color color) {
		boolean row = false;
		for (int x = 0; x < DIM; x++) {
			if (fields[x][0][3] == color && fields[x][3][0] == color) {
				if (fields[x][1][2] == color && fields[x][2][1] == color) {
					row = true;
				}
			}
			if (fields[x][0][0] == color && fields[x][3][3] == color) {
				if (fields[x][2][2] == color && fields[x][1][1] == color) {
					row = true;
				}

			}
		}
		return row;
	}

	/**
	 * Checks whether there is a diagonal in the z-direction which is full and
	 * only contains the color color.
	 * 
	 * @param color
	 *            the color of interest
	 * @return true if there is a z-diagonal controlled by color
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean getZdiag(Color color) {
		boolean row = false;
		for (int z = 0; z < DIM; z++) {
			if (fields[0][3][z] == color && fields[3][0][z] == color) {
				if (fields[1][2][z] == color && fields[2][1][z] == color) {
					row = true;
				}
			}
			if (fields[0][0][z] == color && fields[3][3][z] == color) {
				if (fields[2][2][z] == color && fields[1][1][z] == color) {
					row = true;
				}

			}
		}
		return row;
	}

	/**
	 * Tests if the whole board is full.
	 * 
	 * @param b
	 * @return true if all fields are occupied
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean isFull(Board b) {
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					if (fields[i][j][z] == Color.EMP) {
						return false;
					}
				}
			}
		}
		return true;

	}

	/**
	 * Returns true if the chosen field choice refers to a valid field on the
	 * board. It also checks whether the y - 1 of the chosen field is filled.
	 * 
	 * @param choice
	 *            the field of interest
	 * @return true if choice refers to a valid field
	 * @throws OutOfBoundsException
	 */
	/* pure */
	/*
	 * @requires color!= emp && color!=null;
	 * 
	 */
	public boolean validMove(Field choice) throws OutOfBoundsException {
		boolean valid = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (this.isEmpty(x, y, z)) {
			if (this.boardEmpty() && y == 0) {
				valid = true;
			} else if (this.isEmpty(x, y, z) && y == 0) {
				valid = true;
			} else if (y >= 4) {
				valid = false;
			} else if (this.isEmpty(x, y, z) && y != 0) {
				if (this.isEmpty(x, y - 1, z)) {
					valid = false;
				} else {
					valid = true;
				}
			}
		}

		return valid;

	}

}
