package exceptions;

public class OutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1L;
	private int y;
	private int x;
	private int z;

	public OutOfBoundsException(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String sendMessage() {
		return "This field" + x + y + z + "is not in our board";
	}
}
