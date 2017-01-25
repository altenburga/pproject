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
	private List<ClientHandler> clients;

	public GameReq() {
		this.clients = new ArrayList<ClientHandler>();
	}

	public boolean isComplete() {
		return clients.size() == 2;
	}

	public Game createGame() {
		Map<String, Player> result = new HashMap<String, Player>();
		for (ClientHandler c : clients) {
			result.put(c.getName(), new Player());

		}
		Game battle = new Game(one, two);
		for (ClientHandler c : clients) {
			c.setCurrentGame(battle);
			c.setCurrentPlayer(result.get(client.getClientName()));
			c.startGame();

		}
		return battle;
	}

}
