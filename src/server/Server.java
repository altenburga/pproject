package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import control.Game;
import model.Field;
import protocol.Protocol;

/**
 * P2 prac wk5. <br>
 * Server. A Thread class that listens to a socket connection on a specified
 * port. For every socket connection with a Client, a new ClientHandler thread
 * is started.
 * 
 * @author Theo Ruys
 * @version 2005.02.21
 */
public class Server extends Thread {
	private int port;
	private List<ClientHandler> threads;
	private List<ClientHandler> toPlay = threads;
	private ServerSocket serverSock;
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private List<Game> currentGames;
	private static final int NUMBER_OF_PLAYERS = 2;
	private List<Game> currentgames;

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

		try {
			server.run();
		} finally {
			server.close();
		}
	}

	/** Constructs a new Server object */
	public Server(int portArg) {
		try {
			serverSock = new ServerSocket(portArg);
			threads = new ArrayList<ClientHandler>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public int getPort(){
		return port;
	}

	/**
	 * Listens to a port of this Server if there are any Clients that would like
	 * to connect. For every new socket connection a new ClientHandler thread is
	 * started that takes care of the further communication with the Client.
	 */
	public void run() {
		System.out.print("Server started");
		Socket s;
		try {
			s = serverSock.accept();
			System.out.println("Client connected!");
			addHandler(new ClientHandler(this, s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server is terminated!");
			System.exit(10);
		}

	}
	

	/**
	 * Sends a message using the collection of connected ClientHandlers to all
	 * connected Clients.
	 * 
	 * @param msg
	 *            message that is send
	 */
	// niet zeker
	public void broadcast(String msg) {
		for (ClientHandler client : threads) {
			client.sendMessage(msg);
		}
	}
	
	public void broadcastMove(ClientHandler one, Field choice){
		Game battle = one.getCurrentgame();
		for(ClientHandler c : threads){
			if(c.getCurrentgame() == battle){
				c.notifyMove(c.getName(), choice);
			}
		}
	}
	
	public void broadcastLostConnection(ClientHandler one){
		Game battle = one.getCurrentgame();
		for(ClientHandler c : threads){
			if(c.getCurrentgame() == battle){
			c.notifyLost(one);
		}
		}
		threads.remove(one);
		currentGames.remove(battle);
		
	}

	/**
	 * Add a ClientHandler to the collection of ClientHandlers.
	 * 
	 * @param handler
	 *            ClientHandler that will be added
	 */
	public void addHandler(ClientHandler handler) {
		threads.add(handler);
	}

	/**
	 * Remove a ClientHandler from the collection of ClientHanlders.
	 * 
	 * @param handler
	 *            ClientHandler that will be removed
	 */
	public void removeHandler(ClientHandler handler) {
		if (threads.contains(handler)) {
			threads.remove(handler);
		}
	}

	public void requestGame(ClientHandler c) {
		threads.add(c);
		ClientHandler one = (toPlay.get(0));
		ClientHandler two = toPlay.get(1);
		GameReq r = new GameReq(one,two);
		toPlay.remove(0);
		toPlay.remove(1);

	}

	public Object getClientName(String name) {
		ClientHandler one = null;
		for(ClientHandler c : threads){
			if(c.getName().equals(name)){
				one = c;
			}
		}
		return one;
	
	}

	/**
	 * Closes the server.
	 */

	public void close() {
		try {
			serverSock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
