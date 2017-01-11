package model;

import java.util.ArrayList;
import java.util.List;

import control.Game;

public abstract class Player {
	private String name;
	public List<Color> pHand;
	public Color color;
	protected Game game;
	
	public Player(String name) {
		this.name = name;
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
	
	public void reset() {
		initHand();
	}
	public void initHand() {
		Color e = color;
		for (int i = 0; i < 32; i++) {
			pHand.add(e);
		}	
		}

		
	public abstract Field determineMove(Board board);
	
	
	public void clearHand() {
		pHand = new ArrayList<Color>();

	}
	public Game getGame(){
		return game;
	}
	public void makeMove(Board board) {
    	Field choice = determineMove(board);
		if (choice != null) {
			int x = choice.getX();
			int y = choice.getY();
			int z = choice.getZ();
			Color one = choice.getColor();
			board.setField(x, y, z, one);
		} 
		else {
			System.out.print("Move is empty, try again." + System.lineSeparator());
		}
	}
	

}
