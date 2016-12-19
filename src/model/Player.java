package model;

import java.util.ArrayList;
import java.util.List;

import control.Game;

public abstract class Player {
	private String name;
	public List<Color> pHand;
	public Color color;
	protected Game game;
	private Field lastMove;
	
	public Player(String name, Game game) {
		this.name = name;
		this.game = game;
		pHand = new ArrayList<Color>();
	}
	public String getName() {
		return name;
	}
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color col){
		color = col;
	
	}
	public void setName(String choice) {
		name = choice;
	}
	public Integer showHand() {
		Integer result = 0;
		for (Color color : pHand) {
			result = result + 1 ;
		}
		return result;
	}
	public boolean CheckTile(Color choice){
		boolean available = true;
		if( choice == color && this.showHand() != 0){
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
		Color e = color;
		for (int i = 0; i < 32; i++) {
			pHand.add(e);
		}	
		}

		
	public abstract void determineMove(Board board);
	
	
	public void clearHand() {
		pHand = new ArrayList<Color>();

	}
	public void makeMove(Board board, Field move) {
		Board bcopy = board.Deepcopy();
		if (move != null) {
			lastMove = move;
			int x = move.getX();
			int y = move.getY();
			int z = move.getZ();
			Color one = move.getColor();
			bcopy.setField(x,y,z,one);
		} 
		else {
			System.out.print("Move is empty, try again." + System.lineSeparator());
		}
	}
	
	public Field lastTile(){
		return lastMove;
	}

}
