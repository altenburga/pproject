package exceptions;

public class ListEmptyException extends Throwable {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Your choice was empty.";
	}

}
