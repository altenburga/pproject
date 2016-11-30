package model;

public class Tile {
	private Integer color;
	public static final Integer[] colors = {0, 1,2};
	
	/* The 1 stands for white and the 2 stands for black, zero is no tile*/
	
	public Tile(Integer color) {
		this.color = color;
	}
	
	/**
	 * gives the color in integer form that was given to the tile.
	 * 
	 * @return color in integer form
	 */
	public Integer getColor() {
		return color;
	}
	/**
	 * gives a Tile in String form
	 * 
	 * @return String representation of the Tile
	 */
	public String toString() {
		String a = new String();
		if( this.color == 1){
			a = "White";
					
		}
		if(this.color == 0){
			a = "No tile";
		}
		else {
			a = "Black";
		}
		return a;
	}
	public void setTile(Integer color){
		Integer tileColor = this.getColor();
		tileColor = color;
	}
}
