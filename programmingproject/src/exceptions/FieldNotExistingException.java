package exceptions;

import model.Field;

public class FieldNotExistingException extends Throwable {

	private int x;
	private int y;
	private int z;

	public FieldNotExistingException(Field choice){
		this.x = choice.getX();
		this.y = choice.getY();
		this.z = choice.getZ();
	}
	
	public String getMessage() {
		return "This field " + x + " "+ y + " "+ z + " does not exist.";
	}

}
