package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import model.*;
import protocol.Protocol;
import view.*;
import control.*;

public class GameHandler extends Thread implements Observer {
	private List<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private Player one;
	private Player two;
	private Game battle;
	private TUIView view;

	public GameHandler(List<ClientHandler> temp) {
		clientHandlers = temp;
		one = new Computerplayer(temp.get(0).getName(), temp.get(0));
		two = new Computerplayer(temp.get(1).getName(), temp.get(1));
		battle = new Game(one, two);
	}

	public void run() {
		battle = new Game(one, two);
		battle.setCurrentPlayer(battle.firstPlayer());
		;
		battle.addObserver(this);
		sendMessageStartGame();
		sendMessageMoveRequest(battle.getCurrentPlayer());
		view = new TUIView();
		while (!battle.gameOver(battle.getBoard())) {
			if (!battle.getCurrentPlayer().getClientHandler().moveHandled()) {
				setMove();
				battle.getCurrentPlayer().getClientHandler().handled();
				battle.changePlayer();
				sendMessageMoveRequest(battle.getCurrentPlayer());
			}
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

	private void setMove() {
		battle.setMove(battle.getBoard());

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == "move") {
			battle.getCurrentPlayer().getClientHandler().sendMessage(Protocol.SERVER_NOTIFYMOVE + " " + battle.getCurrentPlayer().getClientHandler() + " "
							+ battle.getCurrentPlayer().getClientHandler().lastMove().getX() + " " + " " + battle.getCurrentPlayer().getClientHandler().lastMove().getZ());
			view.toString(battle.getBoard());
		}

	}

}
