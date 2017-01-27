package model;

public class Field {
	private int x;
	private int y;
	private int z;
	private Color color;
	
	public Field(int x, int y, int z, Color one){
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = one;
	}
	
	public Color getColor(){
		System.out.print(color);
		return color;
		
	}
	public void setColor(Color choice){
		if(color == Color.EMP){
			color = choice;
		}
		else{
			System.out.println("Dat vakje is al vol");
		}
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
