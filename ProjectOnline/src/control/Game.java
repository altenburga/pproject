package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

import exceptions.OutOfBoundsException;
import model.*;
import protocol.Protocol;
import view.*;
/**
 * represents a game within the Connect Four 3D.
 * @author Lieke and Amber
 */
public class Game extends Observable{

	private Player[] players;
	private Board board;
	public static final int NUMBER_OF_PLAYERS = 2;
	public Player currentPlayer;
	public TUIView view;
	private int current;
	private static final int DIM = 4;

	/** 
	 * Creates a new Game with 2 players, gives them their colors and creates a new view. 
	 */
	public Game(Player s0, Player s1) {
		board = new Board();
		players = new Player[NUMBER_OF_PLAYERS];
		players[0] = s0;
		players[1] = s1;
		players[0].setColor(Color.RED);
		players[1].setColor(Color.YEL);
		view = new TUIView();

	}
	/**
	 * returns the currentPlayer.
	 * @return currentPlayer
	 */

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Sets the current player to the given parameter one. 
	 * @param one
	 */
	public void setCurrentPlayer(Player one){
		currentPlayer = one;
	}
	/**
	 * changes the currentPlayer to the next player. 
	 * Used when a turn is over.
	 */
	public void changePlayer() {
		if (currentPlayer == players[0]) {
			currentPlayer = players[1];
		} else {
			currentPlayer = players[0];
		}

	}
	/**
	 * returns the currently used view.
	 * @return view
	 */
	public TUIView getView(){
		return view;
	}
	/**
	 * sets the current view to the given param.
	 * @param nview
	 */
	public void setView(TUIView nview){
		view = nview;
	}
	/**
	 * A method that gives you a hint for a field you can place your stone. 
	 * @param p
	 * @return a string with the coordinates where you can put your stone. 
	 * @throws OutOfBoundsException 
	 */
	public String getHint(Humanplayer p) throws OutOfBoundsException{
		Field place = new Field(0, 0, 0, null);
		for (int i = 0; i < DIM ; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM ; z++) {
					Field choice = new Field(i, j, z, p.getColor());
					while (board.validMove(choice)) {
						place = choice;
						break;
					}
				}
			}
		}
		return "Place your tile on x = " + place.getX() + "and z = " + place.getZ();
	}
	/**
	 * determines the firstPlayer randomly to start the game.
	 */
	public Player firstPlayer() {
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if (temp == 1) {
			currentPlayer = players[0];
		}
		if (temp == 2) {
			currentPlayer = players[1];

		}
		return currentPlayer;

	}

	/**
	 * Given a color determines if that color has a row of 4 somewhere that it is a winner. 
	 * @param bor
	 * @param c
	 * @return true if bor.getXdiag(c) or bor.getDiagDiag(c) or bor.getZdiag(c) or 
	 * 		bor.getCol(c) or bor.getZRow(c)
				or bor.getXRow(c).
	 */
	public Boolean isWinner(Board bor, Color c) {
		return bor.getXdiag(c) || bor.getDiagDiag(c) || bor.getZdiag(c) || bor.getCol(c) || bor.getZRow(c)
				|| bor.getXRow(c);

	}
	/**
	 * Given a board, determines if there is any winner on that current board. 
	 * @param b
	 * @return true if isWinner(b, Color.RED) == true or isWinner(b, Color.YEL) == true.
	 */
	public boolean hasWinner(Board b) {
		return isWinner(b, Color.RED) || isWinner(b, Color.YEL);

	}
	/**
	 * Resets the entire game by resetting the board.
	 */
	public void reset() {
		current = 0;
		board.reset();
	}
	/**
	 * Checks if there is a winner or the board is full thus having a gameover.
	 * @param b
	 * @return true if the board is full or there is a winner. 
	 */
	public boolean gameOver(Board b) {
		return board.isFull(b) || hasWinner(b);

	}
	/**
	 * This returns the player that is the winner.
	 * @param b
	 * @return the player that has won.
	 */
	public Player winner(Board b){
		return this.isWinner(b, players[0].getColor()) ? players[0] : players[1];
	}
	/**
	 * This prints the current situation with the view and the current player's 
	 * name with their color. 
	 * @throws OutOfBoundsException 
	 */

	public void update() throws OutOfBoundsException {
		System.out.println("\ncurrent game situation: \n\n" + view.toString(board) + "\n");
		System.out.println(
				"Player: " + this.currentPlayer.getName() + "\n" + "Color: " + this.getCurrentPlayer().getColor());
	}
	/**
	 * Returns the board which this game is currently using.
	 * @return
	 */
	public Board getBoard(){
		return board;
	}
	/**
	 * Let's players do a move on the given board.
	 * Once the move is set returns the move to the server. 
	 * @param board
	 * @throws OutOfBoundsException 
	 */
	public void setMove(Board board) throws OutOfBoundsException {
		currentPlayer = getCurrentPlayer();
		if (currentPlayer instanceof Computerplayer) {
			Field move =currentPlayer.determineMove(board);
			currentPlayer.makeMove(board, move);
		}
		
		else if(currentPlayer instanceof Humanplayer) {
			Field move =currentPlayer.determineMove(board);
			currentPlayer.makeMove(board, move);
			currentPlayer.getClientHandler().handleInput(Protocol.CLIENT_SETMOVE + " " + move.getX() + " " + move.getZ());
			
		}
		
		setChanged();
		notifyObservers("move");
	}


}
