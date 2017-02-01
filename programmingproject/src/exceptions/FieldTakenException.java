package exceptions;

public class FieldTakenException extends Throwable {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The field you are trying to place your tile in is already taken.";
	}
}
