package exceptions;

public class TileNotExistingException extends Throwable {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "This tile doesn't exist.";
	}

}
