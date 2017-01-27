package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import control.Game;
import model.Color;
import model.Field;
import model.Player;
import protocol.Protocol;

public class ClientHandler {
	private BufferedWriter out;
	private BufferedReader in;
	private Server server;
	private String clientName;
	private Game battle;
	private Player one;
	private Field moveMade;
	private boolean moveHandled = false;

	public ClientHandler(Server serverArg, Socket sockArg) throws IOException {
		this.server = serverArg;
		this.in = new BufferedReader(new InputStreamReader(sockArg.getInputStream()));
		this.out = new BufferedWriter(new OutputStreamWriter(sockArg.getOutputStream()));

	}

	public String getName() {
		return clientName;
	}
    public void handled() {
    	moveHandled = false;
    }
    public boolean moveHandled() {
    	return moveHandled;
    }
    public void run() {
    	while(true){
    		String line;
			try {
				line = in.readLine();
				handleInput(line);
			} catch (IOException e) {
				System.out.println("System is down");
				e.printStackTrace();
				
			}
    		
    	}
    }

	public void handleInput(String input) {
		String[] inp = input.split(" ");
		switch (inp[0]) {
		case Protocol.CLIENT_JOINREQUEST:
			handleJoinReq(inp);
			break;
		case Protocol.CLIENT_GAMEREQUEST:
			handleJoinGameReq(inp);
			break;
		case Protocol.CLIENT_SETMOVE:
			handleSetMove(inp);
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
		}

	}

	private void handleJoinReq(String[] inp) {
		clientName = inp[1];
		try {
			out.write(Protocol.SERVER_ACCEPTREQUEST + " " + getName() + " " + inp[2] + " " + inp[3] + " " + inp[4] + " "
					+ inp[5]);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void handleJoinGameReq(String[] inp) {
		server.addToPlayList(this);

	}

	private void handleSetMove(String[] inp) {
		boolean set = false;
		int x = Integer.valueOf(inp[1]);
		int z = Integer.valueOf(inp[2]);
		for (int y = 0; y < 4; y++) {
			if (battle.getBoard().getField(x, y, z) == Color.EMP && set == false) {
				battle.getBoard().setField(x, y, z, one.getColor());
				moveMade = new Field(x,y,z, one.getColor());
				set = true;
				moveHandled = true;
			}
		}

	}
	private Field lastMove(){
		return moveMade;
	}
	public void sendMessage(String message) {
		try {
			out.write(message);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			System.out.println("Shut down client");
			shutdown();
		}
	

}

	private void shutdown() {
		server.deleteHandler(this);
		server.broadcast("[" + clientName + " has left]");
	}
		
	}
