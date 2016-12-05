package model;

import java.util.ArrayList;
import java.util.List;

import control.Game;

public abstract class Player {
	private String name;
	public List<Tile> pHand;
	public Integer color;
	protected Game game;
	private Field lastMove;
	
	public Player(String name, Game game) {
		this.name = name;
		this.game = game;
		pHand = new ArrayList<Tile>();
	}
	public String getName() {
		return name;
	}
	public int getColor(){
		return color;
	}
	
	public void setColor(int col){
		color = col;
	
	}
	public void setName(String choice) {
		name = choice;
	}
	public Integer showHand() {
		Integer result = 0;
		for (Tile tile : pHand) {
			result = result + 1 ;
		}
		return result;
	}
	public boolean CheckTile(Tile choice){
		boolean available = true;
		if( choice.getColor().equals(color) && this.showHand() != 0){
			available = true;
		}
		else {
			available = false;
		}
		return available; 
	}
	public void reset() {
		initHand();
	}
	public void initHand() {
		Tile e = new Tile(color);
		for (int i = 0; i < 32; i++) {
			pHand.add(e);
		}	
		}

		
	public abstract void determineMove(Board board);
	
	
	public void clearHand() {
		pHand = new ArrayList<Tile>();

	}
	public void makeMove(Board board, Field move) {
		Board bcopy = board.Deepcopy();
		if (move != null) {
			lastMove = move;
			bcopy.setField(move);
		} 
		else {
			System.out.print("Move is empty, try again." + System.lineSeparator());
		}
	}
	
	public Field lastTile(){
		return lastMove;
	}

}
