package control;

import java.util.Scanner;

import model.*;
import view.*;
/**
 * represents a game within the Connect Four 3D.
 * @author Lieke and Amber
 */
public class Game {

	private Player[] players;
	private Board board;
	public static final int NUMBER_OF_PLAYERS = 2;
	public Player currentPlayer;
	public TUIView view;
	private boolean hint;
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
	 * returns the currently used view.
	 * @return view
	 */
	public TUIView getView() {
		return view;
	}
	/**
	 * sets the current view to the given param.
	 * @param nview
	 */
	public void setView(TUIView nview) {
		view = nview;
	}
	public String getHint(Humanplayer p) {
		Field place = new Field(0, 0, 0, null);
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
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
	 * returns the currentPlayer.
	 * @return currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
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
	 * determines the firstPlayer randomly to start the game.
	 */

	public void firstPlayer() {
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if (temp == 1) {
			currentPlayer = players[0];
		}
		if (temp == 2) {
			currentPlayer = players[1];

		}

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
		return bor.getXdiag(c) || bor.getDiagDiag(c) || bor.getZdiag(c) || 
				bor.getCol(c) || bor.getZRow(c) || bor.getXRow(c);

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
		board.reset();
	}
	/**
	 * Starts the game
	 * Asks the player if they want to read the rules
	 * After a game they ask if the Player wants to play another game and keeps the game running.
	 */
	public void start() {
		System.out.println("Welcome to this game of Connect Four, 3D");
		if (readBoolean("Would you like to know the rules of the game?", "y", "n") == true) {
			printRules();
		}
		if (players[0] instanceof Humanplayer || players[1] instanceof Humanplayer) {
			hint = readBoolean("Would you like the hint function on during the game?", "y", "n");
		}
		boolean doorgaan = true;
		while (doorgaan) {
			reset();
			play(board);
			doorgaan = readBoolean("\n> Play another game? (y/n)?", "y", "n");
		}

	}
	/**
	 * Takes three parameters and uses the user input to determine the result.
	 * Asks a question, the prompt and returns true if answer is yes, false if answer is no.
	 * @param prompt
	 * @param yes
	 * @param no
	 * @return true if answer equals "y" or "yes".
	 */
	public boolean readBoolean(String prompt, String yes, String no) {
		String answer;
		boolean conti = false;
		System.out.print(prompt);
		Scanner in = new Scanner(System.in);
		answer = in.nextLine();
//		answer = in.toString();
		if (answer.equals("y") || answer.equals("yes")) {
			conti = true;
		}
		return conti;
	}
	/**
	 * plays a game on a given board
	 * Makes sure that the players turn switch, they can make a move and the view gets updated.
	 * @param board
	 */
	public void play(Board board) {
		this.firstPlayer();
		update();
		while (!this.gameOver(board)) {
			if (currentPlayer instanceof Humanplayer && hint) {
				if (readBoolean("Would you like a hint?", "yes", "no")) {
					getHint((Humanplayer) currentPlayer);
				}
			}
			currentPlayer.makeMove(board);
			this.changePlayer();
			update();

		}
		printResult();

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
	 * If there is a winner, this method prints the winner with their name and color.
	 */
	private void printResult() {
		if (this.hasWinner(board)) {
			Player winner = this.winner(board);
			System.out.println("Speler " + winner.getName() + " (" + 
					winner.getColor().toString() + ") has won!");
		} else {
			System.out.println("Draw. There is no winner!");
		}
	}
	/**
	 * This returns the player that is the winner.
	 * @param b
	 * @return the player that has won.
	 */
	
	public Player winner(Board b) {
		return this.isWinner(b, players[0].getColor()) ? players[0] : players[1];
	}
	/**
	 * This prints the current situation with the view and the current player's 
	 * name with their color. 
	 */

	public void update() {
		System.out.println("\ncurrent game situation: \n\n" + view.toString(board) + "\n");
		System.out.println(
				"Player: " + this.currentPlayer.getName() + "\n" + "Color: " + 
						this.getCurrentPlayer().getColor());
	}
	/** 
	 * This method prints the rules at the beginning of the game if the player wants to. 
	 */

	public void printRules() {
		System.out.println(
				" Help  \n To start the game, give the names of the players one and two. "
				+ "If you want a computerplayer, let the name start with a 'C'.\n "
				+ "To play, choose first which location you want for your tile..\n "
				+ "You can place tiles by picking a row and a column.\n "
				+ "After picking, your tile will be placed and your turn is finished. \n "
				+ "Rules: \n All played tiles must above another placed tile or at the "
				+ "ground level.\n You can only play one tile at each turn. \n The maximun "
				+ "height of tiles is 4. \n "
				+ "Scoring: \n You can win one you have a row of 4 tiles with your color. "
				+ "This can be a row, column, or a diagonal. \n "
				+ "After you have finished your first game, you can play another game.");

	}

}
