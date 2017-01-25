package server;

import model.Board;
import model.Field;
import model.Player;

public class ClientPlayer extends Player{
	
	private ClientHandler h;
	
	public ClientPlayer(ClientHandler client){
		super(client.getName());
		h = client;
		
	}
	@Override
	public void makeMove(Board board){
		h.moveRequest();
		try{
			h.WaitForMove();
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
			h.shutdown();
		}
		
	}
	@Override
	public Field determineMove(Board board) {
		return null;
	}
	

}
