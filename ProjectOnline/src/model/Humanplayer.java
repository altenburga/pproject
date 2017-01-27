package model;

import java.util.Scanner;

import server.ClientHandler;

public class Humanplayer extends Player {
// x = column, y = height, z = row;
	private Scanner in;

	public Humanplayer(String name, ClientHandler client) {
		super(name, client);
		in = new Scanner(System.in);
	}

	@Override
	public Field determineMove(Board board) {
		boolean valid = false;
		Field choice = new Field(0, 0, 0, color);
		int col = askColumn();
		int row = askRow();
		Color tile = this.getColor();
		Field place = new Field(0, 0, 0, null);
		for(int j = 0; j < 4; j++){
			if(board.isEmpty(col, j, row)){
				place = new Field(col, j, row, tile);
				break;
			}
		}
		
		if(board.validMove(choice)){
			valid = true;
		}
		if(valid = true){
			choice = place;
		}
		return choice;

	}
	
		
		
		
		
		
		
/*		if (game.validMove(place, board)) {
			choice = place;
		} else {
			System.out.println("This is not a valid move. Try again.");
		}
		return choice;
		


	} */

	public int askColumn() {
		System.out.println("In which column would you like to place your tile?");
		int col = in.nextInt();
		return col;
	}

	public int askRow() {
		System.out.println("In which row would you like to place your tile?");
		int row = in.nextInt();
		return row;

	}

/*	public int askHeight() {
		System.out.println("At what height would you like to place your tile?");
		int height = in.nextInt();
		return height;

	}
	*/

}
