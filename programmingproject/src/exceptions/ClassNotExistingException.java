package exceptions;

public class ClassNotExistingException extends Throwable {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "This field doesn't exist.";
	}

}
