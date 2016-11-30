package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private static final int DIM = 4;
	public Field[][][] place = new Field[DIM][DIM][DIM];
//*	private int MinX = 1;
//*	private int MaxX = 4;
//*	private int MinY = 1;
//*	private int MaxY = 4;
//*	private int MinZ = 1;
//*	private int MaxZ = 4; 

	public Board() {
		place = new Field[Board.DIM][Board.DIM][Board.DIM];
	}

	public Board Deepcopy() {
		Board newBoard = new Board();
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					newBoard.place[i][j][z] = this.place[i][j][z];
				}
			}
		}
		return newBoard;
	}

	public boolean isField(int x, int y, int z) {
		return x <= DIM && x > 0 && y <= DIM && y > 0 && z <= DIM && z > 0;
	}

	public Field getField(int x, int y, int z) {
		if (isField(x, y, z)) {
			return place[x][y][z];
		} else {
			return null;
		}

	}

	public void setField(int x, int y, int z, Tile choice) {
		place[x][y][z].setTile(choice);

	}
	public void setField(Field move) {
		Integer x = move.getX();
		Integer y = move.getY();
		Integer z = move.getZ();
		Tile one = move.getTile();
		place[x][y][z].setTile(one);
;
		

	}

	public void reset() {
		Tile choice = new Tile(0);
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				for (int z = 0; z < DIM; z++) {
					choice = place[i][j][z].getTile();
					choice.setTile(0);
				}

			}

		}
	}
	public boolean isEmpty() {
		Tile choice = new Tile(1);
		boolean result = true;
		for (int x = 0; x < (DIM); x++) {
			for (int y = 0; y < (DIM); y++) {
				for (int z = 0; z < DIM; z++) {
					choice = place[x][y][z].getTile();
					if (choice.getColor() != 0) {
					result = false;
				}
				}
			}
		}
		return result;
	}
	public boolean boardEmpty(){
		boolean empty = true;
		for (int x = 0; x < (DIM); x++) {
			for (int y = 0; y < (DIM); y++) {
				for (int z = 0; z < DIM; z++) {
					Tile t = place[x][y][z].getTile();
					if(t.getColor() != 0){
						empty = false;
					}
					else {
						empty = true;
					}
					
					}
				
			}
			
		}
		return empty;
				
	}
	
	public boolean isEmpty(int x, int y, int z) {
		return ((place[x][y][z].getTile().getColor()).equals(0));
	}
	
	public boolean getYrow(Field choice){
		boolean Yrow = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if(choice.getTile().getColor()!=0){
			int col = choice.getTile().getColor();
			if (place[x][1][z].getTile().getColor() == col && place[x][2][z].getTile().getColor() == col && place[x][3][z].getTile().getColor() == col && place[x][4][z].getTile().getColor() == col){
				Yrow = true;
			
		}
		}
		return Yrow;
		
	}
	public boolean getXrow(Field choice){
		boolean Xrow = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if(choice.getTile().getColor()!=0){
			int col = choice.getTile().getColor();
			if (place[1][y][z].getTile().getColor() == col && place[2][y][z].getTile().getColor() == col && place[3][y][z].getTile().getColor() == col && place[4][y][z].getTile().getColor() == col){
				Xrow = true;
			
		}
		}
		return Xrow;
		
	}
	public boolean getZrow(Field choice){
		boolean Zrow = false;
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if(choice.getTile().getColor()!=0){
			int col = choice.getTile().getColor();
			if (place[x][y][1].getTile().getColor() == col && place[x][y][2].getTile().getColor() == col && place[x][y][3].getTile().getColor() == col && place[x][y][4].getTile().getColor() == col){
				Zrow = true;
			
		}
		}
		return Zrow;
		
	}

}