package exceptions;

public class FieldTakenException extends Throwable {

	public String getMessage(){
		return "The field you are trying to place your tile in is already taken.";
	}
}
