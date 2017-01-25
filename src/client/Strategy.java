package client;

import model.Board;
import model.Field;

public interface Strategy {
	
	public void getName();
	
	public Field determineMove(Computerplayer p, Board b);

	void makeMove(Computerplayer p, Board b);


}
