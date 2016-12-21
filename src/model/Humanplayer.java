package model;

import java.util.Scanner;

public class Humanplayer extends Player {
// x = column, y = height, z = row;
	private Scanner in;

	public Humanplayer(String name, Color color) {
		super(name, color);
		in = new Scanner(System.in);
	}

	@Override
	public Field determineMove(Board board) {
		boolean valid = false;
		Field end = new Field(0, 0, 0, null);
		Field choice = new Field(0, 0, 0, color);
		int col = askColumn();
		int row = askRow();
		int height = askHeight();
		Color tile = this.getColor();
		Field place = new Field(col, height, row, tile);
		if (board.boardEmpty() && height == 0) {
			valid = true;
		}
		if (board.isEmpty(col, row, height) && height == 0) {
			valid = true;
		}
		if (height >= 4) {
			valid = false;
		}
		if (board.isEmpty(col, row, height) && height != 0) {
			if (board.isEmpty(col, row - 1, height)) {
				valid = false;
			} else {
				valid = true;
			}
		}
		if(valid == false){
			System.out.println("That is not a valid place, try again!");
		}
		else{
			end = choice;
		}
		return end;

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

	public int askHeight() {
		System.out.println("At what height would you like to place your tile?");
		int height = in.nextInt();
		return height;

	}

}