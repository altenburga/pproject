package exceptions;

public class ClassNotExistingException extends Throwable {


	public String getMessage() {
		return "This field doesn't exist.";
	}

}

