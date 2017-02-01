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
	private boolean set;
	private int index;

	public GameHandler(List<ClientHandler> temp) {
		clientHandlers = temp;
		one = new Humanplayer(temp.get(0).name(), temp.get(0));
		two = new Humanplayer(temp.get(1).name(), temp.get(1));
		battle = new Game(one, two);
	}

	public void run() {
		set = false;
		battle = new Game(one, two);
		battle.setCurrentPlayer(battle.firstPlayer());
		battle.addObserver(this);
		sendMessageStartGame();
		view = new TUIView();
		while (!battle.gameOver(battle.getBoard())) {
			if (index == 0) {
				sendMessageMoveRequest(battle.getCurrentPlayer());
				index = index +1;
			}
			if (battle.getCurrentPlayer().getClientHandler().moveHandled()) {
				if (battle.getCurrentPlayer() instanceof Humanplayer && set == false) {
					this.setField(battle.getCurrentPlayer().getClientHandler().lastMove());
					update(battle, "move");
				} else if (battle.getCurrentPlayer() instanceof Computerplayer && set == false) {
					try {
						setMove();
						set = true;
					} catch (OutOfBoundsException e) {
						battle.getCurrentPlayer().getClientHandler().sendMessage(Protocol.SERVER_DENYMOVE);
					}
				}
				battle.getCurrentPlayer().getClientHandler().handled();
				battle.changePlayer();
				set = false;
				index = index-1;
			}
		}
		if (battle.gameOver(battle.getBoard())) {
			if (battle.hasWinner(battle.getBoard())) {
				String over = Protocol.SERVER_GAMEOVER + " " + battle.winner(battle.getBoard()).getName();
				clientHandlers.get(0).sendMessage(over);
				clientHandlers.get(1).sendMessage(over);
			} else {
				String over = Protocol.SERVER_GAMEOVER;
				clientHandlers.get(0).sendMessage(over);
				clientHandlers.get(1).sendMessage(over);
			}
		}
	}

	public Game getGame() {
		return battle;
	}

	private void sendMessageMoveRequest(Player currentPlayer) {
		currentPlayer.getClientHandler().sendMessage(Protocol.SERVER_MOVEREQUEST);

	}

	public List<ClientHandler> getClientHandlers() {
		return clientHandlers;
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

	private void setField(Field choice) {
		battle.getBoard().setField(choice);
		set = true;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == "move") {
			battle.getCurrentPlayer().getClientHandler().sendMessage(
					Protocol.SERVER_NOTIFYMOVE + " " + battle.getCurrentPlayer().getClientHandler().getName() + " "
							+ battle.getCurrentPlayer().getClientHandler().lastMove().getX() + " " + " "
							+ battle.getCurrentPlayer().getClientHandler().lastMove().getZ());
			try {
				clientHandlers.get(0).sendMessage(view.toString(battle.getBoard()));
				clientHandlers.get(1).sendMessage(view.toString(battle.getBoard()));
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
			}
			clientHandlers.get(0).sendMessage("| Current player: " + battle.getCurrentPlayer().getName()
					+ "  |  Color of the Player:  " + battle.getCurrentPlayer().getColor() + "| ");
			clientHandlers.get(1).sendMessage("| Current player: " + battle.getCurrentPlayer().getName()
					+ "  |  Color of the Player:  " + battle.getCurrentPlayer().getColor() + "| ");
		}

	}

}
