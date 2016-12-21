package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;
import view.*;


public class Game {
	private Board board;
	
	private Player[] players;
	public Player currentPlayer;
	boolean finished = false;
	private boolean finishedturn;
	private Color winningColor;
	public static final int NUMBER_PLAYERS = 2;

	public Game(Player s0, Player s1) {
		board = new Board();
        players = new Player[NUMBER_PLAYERS];
        players[0] = s0;
        players[1] = s1;
	}

	public void namePlayers() {
		boolean read = false;
		Scanner in = new Scanner(System.in);
		while (!read) {
			System.out.println(
					"What is the name of player one?(If you want to be a computerPlayer, let your name start with a C.)");
			String i = in.next();
			players[0].setName(i);
			System.out.println(
					"What is the name of player one?(If you want to be a computerPlayer, let your name start with a C.)");
			String o = in.next();
			players[1].setName(o);
			read = true;
			in.close();
		}

	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean turnFinished() {
		return finishedturn;
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
		Player newCurrentPlayer = players[0];
		if (players[0] == currentPlayer) {
			 newCurrentPlayer = players[1];
		} else {
			newCurrentPlayer = players[0];
		}
		currentPlayer = newCurrentPlayer;
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

	public String isWinner() {
		String winner = new String();
		if(players[0].getColor() == winningColor){
			winner = players[0].getName() + players[0].getColor();
			
		}else {
			winner = players[1].getName() + players[1].getColor();
			
		}
		return winner;
	}

	public boolean hasWinner() {
		boolean fourrow = false;
		winningColor = last.getColor();
		if(board.getCol(last) == true || board.getRow(last) == true){
			fourrow = true;			
		}
		if(board.getXdiag(currentPlayer.getColor()) == true || board.getZdiag(currentPlayer.getColor()) || board.getDiagDiag(currentPlayer.getColor())){
			fourrow = true;
		}
		
		return fourrow;
	}

	public void reset() {
		board.reset();
		players[0].reset();
		players[1].reset();
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
		finishedturn = false;
		while (!finished && !finishedturn) {
			if (board.boardEmpty()) {
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
							finishedturn = true;
								}


				
			else {
				currentPlayer.determineMove(board);
				finishedturn =true;

			}
			this.hasWinner();
			if (gameOver()) {
					System.out.println("The winner is : " + isWinner() + "!");
					finished = true;
				}
			}
			if (!gameOver()) {
				this.newCurrentPlayer();
				update();
			}
		}

	}

	}

	public boolean gameOver() {
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
