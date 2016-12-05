package model;

public class Field {
	private int x;
	private int y;
	private int z;
	private Tile tile;
	
	public Field(int x, int y, int z, Tile one){
		this.x = x;
		this.y = y;
		this.z = z;
		this.tile = one;
	}
	
	public Tile getTile(){
		return tile;
		
	}
	public void setTile(Tile choice){
		tile = choice;
	}
	/**
	 * gives the y coordinate where the player wants to place his tile during
	 * his move.
	 * 
	 * @return y coordinate of Move
	 */
	public int getY() {
		return y;

	}

	/**
	 * gives the x coordinate where the player wants to place his tile during
	 * his move.
	 * 
	 * @return x coordinate of Move
	 */
	public int getX() {
		return x;
	}

	/**
	 * gives the y coordinate where the player wants to place his tile during
	 * his move.
	 * 
	 * @return y coordinate of Move
	 */
	public int getZ() {
		return z;
	}


}
