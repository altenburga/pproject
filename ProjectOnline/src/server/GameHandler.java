package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import model.*;
import protocol.Protocol;
import view.*;
import control.*;
import exceptions.OutOfBoundsException;

public class GameHandler extends Thread implements Observer {
	private List<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private Player one;
	private Player two;
	private Game battle;
	private TUIView view;

	public GameHandler(List<ClientHandler> temp) {
		clientHandlers = temp;
		one = new Computerplayer(temp.get(0).name(), temp.get(0));
		two = new Computerplayer(temp.get(1).name(), temp.get(1));
		battle = new Game(one, two);
	}

	public void run() {
		battle = new Game(one, two);
		battle.setCurrentPlayer(battle.firstPlayer());;
		battle.addObserver(this);
		sendMessageStartGame();
		sendMessageMoveRequest(battle.getCurrentPlayer());
		view = new TUIView();
		while (!battle.gameOver(battle.getBoard())) {
			if (!battle.getCurrentPlayer().getClientHandler().moveHandled()) {
				try {
					setMove();
				} catch (OutOfBoundsException e) {
					e.printStackTrace();
				}
				battle.getCurrentPlayer().getClientHandler().handled();
				battle.changePlayer();
				sendMessageMoveRequest(battle.getCurrentPlayer());
			}
		}
		if(battle.gameOver(battle.getBoard())){
			String over = Protocol.SERVER_GAMEOVER + " " + battle.winner(battle.getBoard()).getName();
			clientHandlers.get(0).sendMessage(over);
			clientHandlers.get(1).sendMessage(over);
			
		}
	}

	private void sendMessageMoveRequest(Player currentPlayer) {
		currentPlayer.getClientHandler().sendMessage(Protocol.SERVER_MOVEREQUEST);

	}

	private void sendMessageStartGame() {
		String message = Protocol.SERVER_STARTGAME;
		message = message + " " + one.getName() + " " + two.getName();
		clientHandlers.get(0).sendMessage(message);
		clientHandlers.get(1).sendMessage(message);
	}

	private void setMove() throws OutOfBoundsException {
		battle.setMove(battle.getBoard());

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == "move") {
			battle.getCurrentPlayer().getClientHandler().sendMessage(Protocol.SERVER_NOTIFYMOVE + " " + battle.getCurrentPlayer().getClientHandler().getName() + " "
							+ battle.getCurrentPlayer().getLastMove().getX() + " " + " " + battle.getCurrentPlayer().getLastMove().getZ());
			try {
				clientHandlers.get(0).sendMessage(view.toString(battle.getBoard()));
				clientHandlers.get(1).sendMessage(view.toString(battle.getBoard()));
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
			}
			clientHandlers.get(0).sendMessage("| Current player: " + battle.getCurrentPlayer().getName() + "  |  Color of the Player:  " + battle.getCurrentPlayer().getColor() + "| ");
			clientHandlers.get(1).sendMessage("| Current player: " + battle.getCurrentPlayer().getName() + "  |  Color of the Player:  " + battle.getCurrentPlayer().getColor() + "| ");
		}

	}

}
