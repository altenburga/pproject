package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Board;
import model.Computerplayer;
import model.Field;
import model.Humanplayer;
import model.Player;

public class Game {
	private Board board;
	public Player one;
	public Player two;
	public Player currentPlayer;
	boolean finished = false;
	
	public Game(){
		board = new Board();
		if(one.getName().startsWith("C")){
				one = new Computerplayer(one.getName(), this);
			} 
		else {
				one = new Humanplayer(one.getName(), this);
			}
		
		if(two.getName().startsWith("C")){
				one = new Computerplayer(two.getName(), this);
			}
		else{
			two=new Humanplayer(two.getName(),this);
			}
		}
	public void namePlayers(){
		boolean read = false;
		Scanner in = new Scanner(System.in);
		while (!read) {
			System.out.println("What is the name of player one?(If you want to be a computerPlayer, let your name start with a C.)");
			String i = in.next();
			one.setName(i);
			System.out.println("What is the name of player one?(If you want to be a computerPlayer, let your name start with a C.)");
			String o = in.next();
			two.setName(o);
			read = true;
			in.close();
			}

	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public boolean turnFinished() {
		return finished;
	}
	public Player firstPlayer() {
		Player p = one;
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if(temp == 1){
			p = one;
		}
		if(temp ==2){
			p = two;
			
		}
		return p;
	}
	
	public Player newCurrentPlayer() {
		Player newCurrentPlayer = null;
		if(one == currentPlayer){
			newCurrentPlayer = two;
		}
		else {
			newCurrentPlayer = one;
		}
		return newCurrentPlayer;
		}
	
	public boolean validMove(Field choice, Board board){
		boolean valid = false; 
		int x = choice.getX();
		int y = choice.getY();
		int z = choice.getZ();
		if (board.isEmpty() && y == 1){
			valid = true;
		}		
		if(board.isEmpty(x, y, z) && y==1){
			valid = true;
		}
		if(y >= 5){
			valid = false;
		}
		if(board.isEmpty(x, y, z) &&  y!= 1){
			if(board.isEmpty(x, y-1, z)){
				valid = false;
			}
			else{
				valid = true;
			}
		}
		
		return valid;
		
	}
	public String isWinner(){
		return one.getName();
	}
	public boolean hasWinner(){
		return true;
		
	}
	public void reset() {
		board.reset();
		one.reset();
		two.reset();
	}
	public void startGame(){
		
	}
	public void play(){
		
	}
	public void update(){
		
	}

}
