package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import protocol.Protocol;


public class Client extends Thread {
	private static final String USAGE
        = "usage: <address> <port>";

	/** Starts a Client-application. */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println(USAGE);
			System.exit(0);
		}
		
		InetAddress host = null;
		int port = 0;

		try {
			host = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			print("ERROR: no valid hostname!");
			System.exit(0);
		}

		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			print("ERROR: no valid portnummer!");
			System.exit(0);
		}

		try {
			Client client = new Client(args[0], host, port);
			client.sendMessage(Protocol.CLIENT_JOINREQUEST + " Amber 0 0 0 0");
			client.sendMessage(Protocol.CLIENT_GAMEREQUEST);
			client.sendMessage("Dit is een client");
			client.start();
			
		} catch (IOException e) {
			print("ERROR: couldn't construct a client object!");
			System.exit(0);
		}

	}
	
	private String clientName;
	private Socket sock;
	private BufferedReader in;
	private BufferedWriter out;

	/**
	 * Constructs a Client-object and tries to make a socket connection.
	 */
	public Client(String name, InetAddress host, int port)
			throws IOException {
		clientName = name;
    	sock = new Socket(host, port);
    	in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	}

	/**
	 * Reads the messages in the socket connection. Each message will
	 * be forwarded to the MessageUI
	 */
	public void run() {
		while (true) {
			try {
				String messageReceived = in.readLine();
				if (!messageReceived.startsWith("[" + clientName + "]")) {
					print(messageReceived);
				} 
			} catch (IOException e) {
					
			}
		}
	}
	

	/** send a message to a ClientHandler. */
	public void sendMessage(String msg) {
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** close the socket connection. */
	public void shutdown() {
		print("Closing socket connection...");
		try {
			out.close();
			in.close();
			sock.close();
		} catch (IOException e) {
			
		}
	}

	/**
	 * @return name of the client
	 */
	public String getClientName() {
		return clientName;
	}
	/**
	 * Prints out a message to System.out.
	 * @param message
	 * 				who needs to be printed.
	 */
	private static void print(String message) {
		System.out.println(message);
	}
	
	/**
	 * Reads a String from the input console.
	 * @param tekst
	 * 				what is asked from the user.
	 * @return  input of the Client.
	 */
	public static String readString(String tekst) {
		System.out.print(tekst);
		String antw = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			antw = in.readLine();
		} catch (IOException e) {
		}

		return (antw == null) ? "" : antw;
	}
}
