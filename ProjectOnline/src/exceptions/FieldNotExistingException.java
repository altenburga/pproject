package exceptions;

public class FieldNotExistingException extends Throwable {

	public String getMessage() {
		return "This field doesn't exist.";
	}

}
