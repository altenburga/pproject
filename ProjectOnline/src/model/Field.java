package model;

import exceptions.FieldTakenException;

/**
 * A class that represents a field which can be taken(RED or YEL) or can be empty
 * @author Lieke en Amber
 *
 */
public class Field {
	private int x;
	private int y;
	private int z;
	private Color color;

	public Field(int x, int y, int z, Color one) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = one;
	}
	/**
	 * Asks for the color of a field.
	 * @return the color of the field
	 */
	public Color getColor() {
		return color;

	}
	/**
	 * Sets the color of a field to a given color.
	 * @param choice
	 * @throws FieldTakenException 
	 */
	public void setColor(Color choice) throws FieldTakenException {
		if (color == Color.EMP) {
			color = choice;
		} else {
			throw new FieldTakenException();
		}
	}

	/**
	 * gives the y coordinate where the player wants to place his tile during
	 * his move.
	 * 
	 * @return y coordinate of Move
	 */
	public int getY() {
		return y;

	}

	/**
	 * gives the x coordinate where the player wants to place his tile during
	 * his move.
	 * 
	 * @return x coordinate of Move
	 */
	public int getX() {
		return x;
	}

	/**
	 * gives the y coordinate where the player wants to place his tile during
	 * his move.
	 * 
	 * @return y coordinate of Move
	 */
	public int getZ() {
		return z;
	}

}

