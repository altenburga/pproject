package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.Game;
import model.Player;

public class GameReq {
	// number of clients that accepted this request. For us this will always be
	// two.
	// private List<ClientHandler> clients;
	private ClientHandler one;
	private ClientHandler two;

	public GameReq(ClientHandler c, ClientHandler h) {
		one = c;
		two = h;
	}

	public Game createGame() {
		ClientPlayer p = new ClientPlayer(one);
		ClientPlayer c = new ClientPlayer(two);
		Game battle = new Game(p, c);
		one.setCurrentGame(battle);
		two.setCurrentGame(battle);
		one.setCurrentPlayer(p);
		two.setCurrentPlayer(c);
		one.startGame(one,two);
		two.startGame(one,two);

		return battle;
	}

}
