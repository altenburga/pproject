package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;

import protocol.Protocol;
/**
 * Our server class that handles the lobby, creates clienthandlers and manages the clienthandlers and their games.
 * @author Lieke
 *
 */
public class Server {
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private ServerSocket serverSock;
	private ArrayList<ClientHandler> threads;
	private int port;
	private Map<Integer, List<ClientHandler>> toPlay = new HashMap<Integer, List<ClientHandler>>();
	private List <ClientHandler> inGame;
	private HashMap<GameHandler, List<ClientHandler>> gameHandlers = new HashMap<GameHandler, List<ClientHandler>>();
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println(USAGE);
			return;
		}

		// parse port.
		int port;
		try {
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("ERROR: no valid portnummer!");
			return;
		}

		// create socket.
		Server server;
		server = new Server(port);
		server.run();

	}


	/**
	 *  Constructs a new Server object
	 * @param portArg
	 */
	public Server(int portArg) {
		try {
			serverSock = new ServerSocket(portArg);
			threads = new ArrayList<ClientHandler>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * returns the port of the server
	 * @return this.port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * Returns a list of clientHandlers that are currently in the lobby.
	 * @return list of ClientHandlers named threads
	 */
	public List<ClientHandler> getThreads() {
		return threads;
	}
/**
 * Keeps track of all the clientPlayers 
 * that have connected and creates a clientHandler for each client that connects
 */
	public void run() {
		System.out.print("Server started");
		while(true){
		try {
			Socket s = serverSock.accept();
			System.out.println("Client connected!");
			addHandler(new ClientHandler(this, s));
			}
		 catch (IOException e) {
			System.out.println("Server is terminated!");
			System.exit(0);
		}
		}
	}
	/**
	 * add a clientHandler to the list threads aka our lobby and starts it.
	 * @param clientHandler
	 */
	private void addHandler(ClientHandler clientHandler) {
		threads.add(clientHandler);
		clientHandler.start();

	}
	/**
	 * Deletes the handler out of the list aka our lobby.
	 * @param clientHandler
	 */
	public void deleteHandler(ClientHandler clientHandler) {
		if (threads.contains(clientHandler)) {
			threads.remove(clientHandler);
		}

	}
	public List<ClientHandler> getInGame(){
		return inGame;
	}
	/**
	 * Adds a given ClientHandler to a Playlist
	 * It searches for a value that is a list of 1 and add the clientHandler and prepares a game
	 * If there is no such list, it adds the ClientHandler to a key index with no value of a list yet. 
	 * @param clientHandler
	 */
	public void addToPlayList(ClientHandler clientHandler) {
		for (int i = 0; i < 30; i++) {
			if (toPlay.get(i) != null && toPlay.get(i).size() == 1) {
				List<ClientHandler> temp = toPlay.get(i);
				temp.add(clientHandler);
				toPlay.put(i, temp);
				prepareGame(temp);
				break;

			}
			else if(toPlay.get(i) == null) {
				List<ClientHandler> temp = new ArrayList<ClientHandler>();
				temp.add(clientHandler);
				toPlay.put(i, temp);
				System.out.println(Protocol.SERVER_WAITFORCLIENT);
				break;
			}


		}

	}
	/**
	 * Gives the parameter list temp to a gameHandler and lets the gameHandler start.
	 * @param temp
	 */

	private void prepareGame(List<ClientHandler> temp) {
		GameHandler one = new GameHandler(temp);
		gameHandlers.put(one, temp);
		one.start();

	}
	
	public Map<GameHandler, List<ClientHandler>> getGameHandler(){
		return gameHandlers;
	}
	/**
	 * Sends a message to every clientHandler in the lobby.
	 * @param msg
	 */

	public synchronized void broadcast(String msg) {
		for (ClientHandler c : threads) {
			c.sendMessage(msg);
		}
	}
	
	/**
	 * Finds the ClientHandler according to the name that is given as parameter
	 * @param name
	 * @return the ClientHandler with the name of the parameter
	 */
	public ClientHandler getClientByName(String name) {
		ClientHandler result = null;
		for (ClientHandler c : threads) {
			if (c.getName() == name) {
				result = c;
				break;
			}
		}
		return result;
	}

}
