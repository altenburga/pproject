package exceptions;

public class ListEmptyException extends Throwable {

	public String getMessage() {
		return "Your choice was empty.";
	}

}
