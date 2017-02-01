package exceptions;

public class IncorrectNoOfplayersException extends Throwable {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "The number of players is incorrect. Please play Qwirkle with 2 to 4 players. ";
	}

}
