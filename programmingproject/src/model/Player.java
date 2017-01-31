package model;

import controller.Game;
import exceptions.FieldNotExistingException;
import exceptions.OutOfBoundsException;
/**
 * An abstract class that represents a Player with a name and clientHandler
 * @author Lieke en Amber
 *
 */
public abstract class Player {
	private String name;
	public Color color;
	protected Game game;
	private Field last;

	public Player(String name) {
		this.name = name;
	}
	/**
	 * Returns the name of the player
	 * @return name of the player
	 */
	public String getName() {
		return name;
	}
	/**
	 * returns the color of the player
	 * @return the color of the player
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Sets the color of the player to the given parameter col
	 * @param col
	 */
	public void setColor(Color col) {
		color = col;

	}
	/**
	 * Sets the name of a player to the given parameter choice
	 * @param choice
	 */
	public void setName(String choice) {
		name = choice;
	}


	/**
	 * Lets the player decide on which field it wants to place a tile.
	 * @param board
	 * @return a field to place on the board
	 * @throws OutOfBoundsException
	 */
	public abstract Field determineMove(Board board) throws OutOfBoundsException;

	/**
	 * returns the game of the player
	 * @return the game of the player
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * Places the field the player wants on the board.
	 * @param board
	 * @throws OutOfBoundsException
	 */
	public void makeMove(Board board) throws OutOfBoundsException, FieldNotExistingException {
		Field choice = determineMove(board);
		if (choice != null) {
			int x = choice.getX();
			int y = choice.getY();
			int z = choice.getZ();
			Color one = choice.getColor();
			board.setField(x, y, z, one);
			last = choice;
		} else {
			throw new FieldNotExistingException(choice);
		}
	}
	/**
	 * returns the last move this player made.
	 * @return the last move this player made.
	 */
	public Field getLastMove() {
		return last;
	}

}
