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
	public Player currentPlayer;
	
	public Game(Player s0, Player s1) {
		board = new Board();
		players = new Player[NUMBER_OF_PLAYERS];
		players[0] = s0;
		players[1] = s1;
		players[0].setColor(Color.RED);
		players[1].setColor(Color.YEL);
		System.out.println(players[0].getColor());
		System.out.println(players[1].getColor());
	}

	public void namePlayers() {
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public void changePlayer(){
		if(currentPlayer == players[0]){
			currentPlayer = players[1];	
		}
		else {
			currentPlayer = players[0];
		}
		
	}

	public void firstPlayer() {
		currentPlayer =  players[1];
	}

	public void newCurrentPlayer() {
	}
/*	public boolean validMove(Field choice, Board board) {
		boolean valid = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (board.boardEmpty() && y == 0) {
			valid = true;
		}
		if (board.isEmpty(x, y, z) && y == 0) {
			valid = true;
		}
		if (y >= 4) {
			valid = false;
		}
		if (board.isEmpty(x, y, z) && y != 0) {
			if (board.isEmpty(x, y - 1, z)) {
				valid = false;
			} else {
				valid = true;
			}
		}

		return valid;

	}
*/
	public Boolean isWinner(Board bor, Color c) {
		return bor.getXdiag(c) || bor.getDiagDiag(c) || bor.getZdiag(c) || bor.getCol(c) || bor.getZRow(c) || bor.getXRow(c) ;

	}

	public boolean hasWinner(Board b) {
		return isWinner(b, Color.RED) || isWinner(b, Color.YEL);

	}

	public void reset() {
		current = 0;
		board.reset();
	}

	public void start() {
		boolean doorgaan = true;
		while (doorgaan) {
			reset();
			play(board);
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

	public void play(Board board) {
		this.firstPlayer();
		update();
		while (!this.gameOver(board)) {
			currentPlayer.makeMove(board);
			this.changePlayer();
			update();

		}
		printResult();

	}

	public boolean gameOver(Board b) {
		return board.isFull(b) || hasWinner(b);

	}

	private void printResult() {
		if (this.hasWinner(board)) {
			Player winner = this.isWinner(board, players[0].getColor()) ? players[0] : players[1];
			System.out.println("Speler " + winner.getName() + " (" + winner.getColor().toString() + ") has won!");
		} else {
			System.out.println("Draw. There is no winner!");
		}
	}

	public void update() {
		System.out.println("\ncurrent game situation: \n\n" + board.toString() + "\n");
		System.out.println("Player: "+ this.currentPlayer.getName() + "\n" + "Color: " + this.getCurrentPlayer().getColor());
	}

	public void printRules() {
		System.out.println(
				" Help  \n To start the game, give the names of the players one and two. If you want a computerplayer, let the name start with a 'C'.\n To play, choose first which location you want for your tile..\n You can place tiles by picking a row, colum, and height.\n After picking, your tile will be placed and your turn is finished. \n Rules: \n All played tiles must above another placed tile or at the ground level.\n You can only play one tile at each turn. \n The maximun height of tiles is 4. \n Scoring: \n You can win one you have a row of 4 tiles with your color. This can be a row, column, or a diagonal. \n After you have finished your first game, you can play another game.");

	}

}
