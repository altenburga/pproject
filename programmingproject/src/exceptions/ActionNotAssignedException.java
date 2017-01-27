package exceptions;

public class ActionNotAssignedException extends Throwable {

	public String getMessage() {
		return "This integer doesn't start an action. Please pick 1, 2, 3, 4.";
	}

}
