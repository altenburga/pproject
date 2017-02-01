package exceptions;

public class WrongInputException extends Exception {

	private static final long serialVersionUID = 1L;
	private String input;

	public WrongInputException(String input) {
		this.input = input;
	}

	public String getMessage() {
		return "The message you typed: " + input + " is invalid.";
	}

}
