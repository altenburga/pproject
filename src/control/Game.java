package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;
import view.*;

public class Game {

	private Player[] players;
	private Board board;
	private int current;
	public static final int NUMBER_OF_PLAYERS = 2;

	public Game(Player s0, Player s1) {
		board = new Board();
		players = new Player[NUMBER_OF_PLAYERS];
		players[0] = s0;
		players[1] = s1;
		current = 0;
	}

	public void namePlayers() {
	}

	public int getCurrentPlayer() {
		return current;
	}

	public Player firstPlayer() {
		Player p = players[0];
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if (temp == 1) {
			p = players[0];
		}
		if (temp == 2) {
			p = players[1];

		}
		return p;
	}

	public void newCurrentPlayer() {
	}
	public boolean validMove(Field choice, Board board) {
		boolean valid = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (board.boardEmpty() && y == 1) {
			valid = true;
		}
		if (board.isEmpty(x, y, z) && y == 1) {
			valid = true;
		}
		if (y >= 5) {
			valid = false;
		}
		if (board.isEmpty(x, y, z) && y != 1) {
			if (board.isEmpty(x, y - 1, z)) {
				valid = false;
			} else {
				valid = true;
			}
		}

		return valid;

	}

	public Boolean isWinner(Color c) {
		Boolean waar = false;
		Color one = players[1].getColor();
		Color zero = players[0].getColor();
		if (one == c) {
			waar = board.getXdiag(c) || board.getDiagDiag(c) || board.getZdiag(c) || board.getCol(players[1].lastTile())
					|| board.getRow(players[1].lastTile());
		} else if (zero == c) {
			waar = board.getXdiag(c) || board.getDiagDiag(c) || board.getZdiag(c) || board.getCol(players[0].lastTile())
					|| board.getRow(players[0].lastTile());
		}
		return waar;

	}

	public boolean hasWinner() {
		return isWinner(Color.RED) || isWinner(Color.YELLOW);

	}

	public void reset() {
		current = 0;
		board.reset();
		players[0].reset();
		players[1].reset();
	}

	public void start() {
		boolean doorgaan = true;
		while (doorgaan) {
			reset();
			play();
			doorgaan = readBoolean("\n> Play another game? (y/n)?", "y", "n");
		}

	}

	private boolean readBoolean(String prompt, String yes, String no) {
		String answer;
		do {
			System.out.print(prompt);
			try (Scanner in = new Scanner(System.in)) {
				answer = in.hasNextLine() ? in.nextLine() : null;
			}
		} while (answer == null || (!answer.equals(yes) && !answer.equals(no)));
		return answer.equals(yes);
	}

	public void play() {
		update();
		int moveNr = 0;
		while (!this.gameOver()) {
			players[moveNr % 2].makeMove(board);
			update();
			moveNr++;
		}
		printResult();

	}

	public boolean gameOver() {
		return board.isFull() || hasWinner();

	}

	private void printResult() {
		if (this.hasWinner()) {
			Player winner = this.isWinner(players[0].getColor()) ? players[0] : players[1];
			System.out.println("Speler " + winner.getName() + " (" + winner.getColor().toString() + ") has won!");
		} else {
			System.out.println("Draw. There is no winner!");
		}
	}

	public void update() {
		System.out.println("\ncurrent game situation: \n\n" + board.toString() + "\n");
	}

	public void printRules() {
		System.out.println(
				" Help  \n To start the game, give the names of the players one and two. If you want a computerplayer, let the name start with a 'C'.\n To play, choose first which location you want for your tile..\n You can place tiles by picking a row, colum, and height.\n After picking, your tile will be placed and your turn is finished. \n Rules: \n All played tiles must above another placed tile or at the ground level.\n You can only play one tile at each turn. \n The maximun height of tiles is 4. \n Scoring: \n You can win one you have a row of 4 tiles with your color. This can be a row, column, or a diagonal. \n After you have finished your first game, you can play another game.");

	}

}
