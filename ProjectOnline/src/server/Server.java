package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import protocol.Protocol;

public class Server {
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private ServerSocket serverSock;
	private ArrayList<ClientHandler> threads;
	private int port;
	private Map<Integer, List<ClientHandler>> toPlay = new HashMap<Integer, List<ClientHandler>>();

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

	public int getPort() {
		return port;
	}

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

	private void addHandler(ClientHandler clientHandler) {
		threads.add(clientHandler);
		clientHandler.run();

	}

	public void deleteHandler(ClientHandler clientHandler) {
		if (threads.contains(clientHandler)) {
			threads.remove(clientHandler);
		}

	}

	public void addToPlayList(ClientHandler clientHandler) {
		if (threads.contains(clientHandler.getName())) {
			System.out.print(Protocol.SERVER_DENYREQUEST + " " + clientHandler.getName());
		} else {
			for (int i = 0; i < 30; i++) {
				if (toPlay.get(i) == null) {
					List<ClientHandler> temp = new ArrayList<ClientHandler>();
					temp.add(clientHandler);
					toPlay.put(i, temp);
					System.out.println(Protocol.SERVER_WAITFORCLIENT);
					break;
				}
				if (toPlay.get(i).size() == 1) {
					System.out.println("addToPlayList met al 1 client");
					List<ClientHandler> temp = toPlay.get(i);
					temp.add(clientHandler);
					toPlay.put(i, temp);
					prepareGame(temp);
					break;

				}
			}

		}
	}

	private void prepareGame(List<ClientHandler> temp) {
		GameHandler one = new GameHandler(temp);
		one.start();

	}

	public synchronized void broadcast(String msg) {
		for (ClientHandler c : threads) {
			c.sendMessage(msg);
		}
	}

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
