package exceptions;

public class IncorrectNoOfplayersException extends Throwable {
	public String getMessage() {
		return "The number of players is incorrect. Please play Qwirkle with 2 to 4 players. ";
	}

}
