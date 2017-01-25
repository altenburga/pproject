package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import control.Game;
import model.Board;
import model.Player;
import protocol.Protocol;

public class ClientHandler extends Thread {

	private Server server;
	private Socket sock;
	private BufferedReader in;
	private BufferedWriter out;
	private String clientName;
	private Game current;
	private Board board;
	private Player one;

	/**
	 * Constructs a ClientHandler object Initialises both Data streams. @
	 * requires server != null && sock != null;
	 */
	public ClientHandler(Server serverArg, Socket sockArg) throws IOException {
		sock = sockArg;
		server = serverArg;
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	}

	/**
	 * Reads the name of a Client from the input stream and sends a broadcast
	 * message to the Server to signal that the Client is participating in the
	 * chat. Notice that this method should be called immediately after the
	 * ClientHandler has been constructed.
	 */
	public void announce() throws IOException {
		clientName = in.readLine();
		server.broadcast("[" + clientName + " has entered]");
	}

	public void messageHandler(String msg) {
		try (Scanner s = new Scanner(msg)) {
			switch (s.next()) {
			case Protocol.CLIENT_JOINREQUEST:
				handleJoinReq(s);
				break;
			case Protocol.CLIENT_GAMEREQUEST:
				handleJoinGameReq(s);
				break;
			case Protocol.CLIENT_SETMOVE:
				handleSetMove(s);
				break;
			case Protocol.CLIENT_SETLEADERBOARD:
				System.out.print("Not available!");
				break;
			case Protocol.CLIENT_ANSWERCHALLENGE:
				System.out.print("Not available!");
				break;
			case Protocol.CLIENT_REQUESTCHALLENGE:
				System.out.print("Not available!");
				break;
			case Protocol.CLIENT_REQUESTCHALLENGELIST:
				System.out.print("Not available!");
				break;
			case Protocol.CLIENT_REQUESTLEADERBOARD:
				System.out.print("Not available!");
				break;
			case Protocol.CLIENT_SENDMESSAGE:
				sendMessage(s);
				break;
			}

		}
	}

	private void handleJoinGameReq(Scanner s) {
		server.requestGame(this);
	}

	private void handleSetMove(Scanner s) {
		boolean set = false;
		int x = s.nextInt();
		int z = s.nextInt();
		for(int y = 0; y< 4; y++){
			if(board.getField(x, y, z) == null && set == false){
				board.setField(x, y, z, one.getColor());
				set = true;
			}
		}
		
		
	}

	private void handleJoinReq(Scanner s) {
		String name = s.nextLine();
		if (server.getClientName(name) == null) {
			clientName = name;
			try {
				out.write(Protocol.SERVER_ACCEPTREQUEST + " "+ getClientName()+ " " + "0 0 0 0");
				out.newLine();
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			} 
		}
		else {
			System.out.println("This " + name + " already exists. Choose another one.");
		}
		
		
		

	}

	private String getClientName() {
		return clientName;
	}

	/**
	 * This method takes care of sending messages from the Client. Every message
	 * that is received, is preprended with the name of the Client, and the new
	 * message is offered to the Server for broadcasting. If an IOException is
	 * thrown while reading the message, the method concludes that the socket
	 * connection is broken and shutdown() will be called.
	 */
	public void run() {
		String msg;
		try {
			while ((msg = in.readLine()) != null) {
				System.out.println(String.format("[%s (%s)]: %s", 
								getClientName(), 
								sock.getInetAddress(),
								msg));
				messageHandler(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			shutdown();
		}
 	}

	/**
	 * This method can be used to send a message over the socket connection to
	 * the Client. If the writing of a message fails, the method concludes that
	 * the socket connection has been lost and shutdown() is called.
	 */
	public void sendMessage(Scanner scan) {
        try {
        	String s = scan.nextLine(); 
        	out.write(s);
        	out.newLine();
        	out.flush();
        } catch (IOException e) {
        	System.out.println("Shut down client");
        	shutdown();
        }
	}

	public void sendMessage(String msg) {
        try {
        	out.write(msg);
        	out.newLine();
        	out.flush();
        } catch (IOException e) {
        	System.out.println("Shut down client");
        	shutdown();
        }
	}
	
	public Game getCurrentgame(){
		return current;
	}
	/**
	 * This ClientHandler signs off from the Server and subsequently sends a
	 * last broadcast to the Server to inform that the Client is no longer
	 * participating in the chat.
	 */
	private void shutdown() {
		server.removeHandler(this);
		server.broadcast("[" + clientName + " has left]");
	}

}
