package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;
import view.*;


public class Game {
	private Board board;
	public Player one;
	public Player two;
	public Player currentPlayer;
	boolean finished = false;

	public Game() {
		board = new Board();
		if (one.getName().startsWith("C")) {
			one = new Computerplayer(one.getName(), this);
		} else {
			one = new Humanplayer(one.getName(), this);
		}

		if (two.getName().startsWith("C")) {
			one = new Computerplayer(two.getName(), this);
		} else {
			two = new Humanplayer(two.getName(), this);
		}
	}

	public void namePlayers() {
		boolean read = false;
		Scanner in = new Scanner(System.in);
		while (!read) {
			System.out.println(
					"What is the name of player one?(If you want to be a computerPlayer, let your name start with a C.)");
			String i = in.next();
			one.setName(i);
			System.out.println(
					"What is the name of player one?(If you want to be a computerPlayer, let your name start with a C.)");
			String o = in.next();
			two.setName(o);
			read = true;
			in.close();
		}

	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean turnFinished() {
		return finished;
	}

	public Player firstPlayer() {
		Player p = one;
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if (temp == 1) {
			p = one;
		}
		if (temp == 2) {
			p = two;

		}
		return p;
	}

	public Player newCurrentPlayer() {
		Player newCurrentPlayer = null;
		if (one == currentPlayer) {
			newCurrentPlayer = two;
		} else {
			newCurrentPlayer = one;
		}
		return newCurrentPlayer;
	}

	public boolean validMove(Field choice, Board board) {
		boolean valid = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (board.isEmpty() && y == 1) {
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

	public String isWinner() {
		return one.getName();
	}

	public boolean hasWinner() {
		boolean fourrow = false;
		Field last = currentPlayer.lastTile();
		Tile tile = last.getTile();
		if(board.getCol(last) == true || board.getRow(last) == true){
			fourrow = true;			
		}
		if(board.getXdiag(tile) == true || board.getZdiag(tile) || board.getDiagDiag(tile)){
			fourrow = true;
		}
		
		return fourrow;
	}

	public void reset() {
		board.reset();
		one.reset();
		two.reset();
	}

	public void startGame() {
		boolean doorgaan = true;
		while (doorgaan) {
			System.out.println("Do you want to see the game rules?");
			System.out.println("Yes ------------------ 1");
			System.out.println("No ------------------- 0");
			int k = new Scanner(System.in).nextInt();
			if (k == 1) {
				printRules();
			}
			reset();
			play();
			System.out.println("Do you want to play another game of 4 in a row 3D? ");
			System.out.println("Yes ------------------ 1");
			System.out.println("No ------------------- 0");
			int i = new Scanner(System.in).nextInt();
			if (i == 0) {
				System.out.println("Thank you for playing 4 in a row 3D!");
				doorgaan = false;
			}
		}
	}

	public void play(){
		finished = false;
		while (!finished) {
			if (board.isEmpty()) {
				currentPlayer = firstPlayer();
				board.showBoard();
				System.out.println(currentPlayer.getName() + currentPlayer.showHand());

			}

			if (!currentPlayer.getName().startsWith("C")) {

				boolean placed = false;
				while (!placed) {
						Board cboard = board.Deepcopy();
						currentPlayer.determineMove(board);
						Board dboard = board.Deepcopy();
						int i = 0;
						int j = 0;
						int z = 0;
						if (!cboard.getField(i, j, z).equals(dboard.getField(i, j, z))) {
							placed = true;
								}


				
			else {
				currentPlayer.determineMove(board);

			}
			this.hasWinner();
			if (gameOver()) {
					System.out.println("The winner is : " + isWinner() + "!");
					finished = true;
				}
			}
			if (!gameOver()) {
				currentPlayer = newCurrentPlayer();
				update();
			}
		}

	}

	}

	private boolean gameOver() {
		boolean finished = false;
		if(this.hasWinner() == true){
			finished = true;
		}
		return finished;
	}

	public void update() {
		board.showBoard();
		System.out.println(currentPlayer.getName() + currentPlayer.showHand());
	}

	public void printRules() {
		System.out.println(
				" Help  \n To start the game, give the names of the players one and two. If you want a computerplayer, let the name start with a 'C'.\n To play, choose first which location you want for your tile..\n You can place tiles by picking a row, colum, and height.\n After picking, your tile will be placed and your turn is finished. \n Rules: \n All played tiles must above another placed tile or at the ground level.\n You can only play one tile at each turn. \n The maximun height of tiles is 4. \n Scoring: \n You can win one you have a row of 4 tiles with your color. This can be a row, column, or a diagonal. \n After you have finished your first game, you can play another game.");

	}

}
