package exceptions;

public class WrongInputException extends Exception {
	
	private String input;

	public WrongInputException(String input){
		this.input = input;
	}
	
	public String getMessage(){
		return "The message you typed: " + input + " is invalid.";
	}

}
