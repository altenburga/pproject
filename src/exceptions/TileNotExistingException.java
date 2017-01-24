package exceptions;

public class TileNotExistingException extends Throwable {

	public String getMessage() {
		return "This tile doesn't exist.";
	}

}
