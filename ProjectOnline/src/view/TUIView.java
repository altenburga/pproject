package view;

import java.util.Scanner;

import exceptions.OutOfBoundsException;
import exceptions.WrongInputException;
import model.Board;
import model.Color;
/**
 * A class that builds the board to string for the user to see.
 * @author Lieke en Amber
 *
 */
public class TUIView {
	private Color[][][] fields;
	

	
	
/**
 * Builds the board to a string for the user to view.
 * @param board
 * @return a visible representation of the board in a string
 * @throws OutOfBoundsException
 */
	public String toString(Board board) throws OutOfBoundsException {
		String s = "" + System.lineSeparator();
		s = s + ("       Z = 0" + "                 Z = 1                 Z = 2                 Z = 3") + System.lineSeparator();
		s = s + ("  " + board.getField(0,3,0) + "|" + board.getField(1,3,0) + "|" + board.getField(2,3,0) + "|" + System.lineSeparator()
				+ board.getField(3,3,0) + "       " + board.getField(0,3,1) + "|" + board.getField(1,3,1) + "|" + board.getField(2,3,1) + "|" 
				+ board.getField(3,3,1) + "       " + board.getField(0,3,2) + "|" + board.getField(1,3,2) + "|" + board.getField(2,3,2) + "|"
				+ board.getField(3,3,2) + "       " + board.getField(0,3,3) + "|" + board.getField(1,3,3) + "|" + board.getField(2,3,3) + "|"
				+ board.getField(3,3,3)) + System.lineSeparator();
		s = s + ("Y " + board.getField(0,2,0) + "|" + board.getField(1,2,0) + "|" + board.getField(2,2,0) + "|"
				+ board.getField(3,2,0) + "     Y " + board.getField(0,2,1) + "|" + board.getField(1,2,1) + "|" + board.getField(2,2,1) + "|"
				+ board.getField(3,2,1) + "     Y " + board.getField(0,2,2) + "|" + board.getField(1,2,2) + "|" + board.getField(2,2,2) + "|"
				+ board.getField(3,2,2) + "     Y " + board.getField(0,2,3) + "|" + board.getField(1,2,3) + "|" + board.getField(2,2,3) + "|"
				+ board.getField(3,2,3)) + System.lineSeparator();
		s = s + ("  " + board.getField(0,1,0) + "|" + board.getField(1,1,0) + "|" + board.getField(2,1,0) + "|"
				+ board.getField(3,1,0) + "       " + board.getField(0,1,1) + "|" + board.getField(1,1,1) + "|" + board.getField(2,1,1) + "|"
				+ board.getField(3,1,1) + "       " + board.getField(0,1,2) + "|" + board.getField(1,1,2) + "|" + board.getField(2,1,2) + "|"
				+ board.getField(3,1,2) + "       " + board.getField(0,1,3) + "|" + board.getField(1,1,3) + "|" + board.getField(2,1,3) + "|"
				+ board.getField(3,1,3)) + System.lineSeparator();
		s = s + ("  " + board.getField(0,0,0) + "|" + board.getField(1,0,0) + "|" + board.getField(2,0,0) + "|"
				+ board.getField(3,0,0) + "       " + board.getField(0,0,1) + "|" + board.getField(1,0,1) + "|" + board.getField(2,0,1) + "|"
				+ board.getField(3,0,1) + "       " + board.getField(0,0,2) + "|" + board.getField(1,0,2) + "|" + board.getField(2,0,2) + "|"
				+ board.getField(3,0,2) + "       " + board.getField(0,0,3) + "|" + board.getField(1,0,3) + "|" + board.getField(2,0,3) + "|"
				+ board.getField(3,0,3)) + System.lineSeparator();
		s = s + ("         X                     X                     X                     X") + System.lineSeparator();
		s = s + ("") + System.lineSeparator();
		s = s + ("       X = 0" + "                 X = 1                 X = 2                 X = 3") + System.lineSeparator();
		s = s + ("  " + board.getField(0,3,3) + "|" + board.getField(0,3,2) + "|" + board.getField(0,3,1) + "|"
				+ board.getField(0,3,0) + "       " + board.getField(1,3,3) + "|" + board.getField(1,3,2) + "|" + board.getField(1,3,1) + "|"
				+ board.getField(1,3,0) + "       " + board.getField(2,3,3) + "|" + board.getField(2,3,2) + "|" + board.getField(2,3,1) + "|"
				+ board.getField(2,3,0) + "       " + board.getField(3,3,3) + "|" + board.getField(3,3,2) + "|" + board.getField(3,3,1) + "|"
				+ board.getField(3,3,0)) + System.lineSeparator();
		s = s + ("Y " + board.getField(0,2,3) + "|" + board.getField(0,2,2) + "|" + board.getField(0,2,1) + "|"
				+ board.getField(0,2,0) + "     Y " + board.getField(1,2,3) + "|" + board.getField(1,2,2) + "|" + board.getField(1,2,1) + "|"
				+ board.getField(1,2,0) + "     Y " + board.getField(2,2,3) + "|" + board.getField(2,2,2) + "|" + board.getField(2,2,1) + "|"
				+ board.getField(2,2,0) + "     Y " + board.getField(3,2,3) + "|" + board.getField(3,2,2) + "|" + board.getField(3,2,1) + "|"
				+ board.getField(3,2,0)) + System.lineSeparator();
		s = s + ("  " + board.getField(0,1,3) + "|" + board.getField(0,1,2) + "|" + board.getField(0,1,1) + "|"
				+ board.getField(0,1,0) + "       " + board.getField(1,1,3) + "|" + board.getField(1,1,2) + "|" + board.getField(1,1,1) + "|"
				+ board.getField(1,1,0) + "       " + board.getField(2,1,3) + "|" + board.getField(2,1,2) + "|" + board.getField(2,1,1) + "|"
				+ board.getField(2,1,0) + "       " + board.getField(3,1,3) + "|" + board.getField(3,1,2) + "|" + board.getField(3,1,1) + "|"
				+ board.getField(3,1,0))+ System.lineSeparator();
		s = s + ("  " + board.getField(0,0,3) + "|" + board.getField(0,0,2) + "|" + board.getField(0,0,1) + "|"
				+ board.getField(0,0,0) + "       " + board.getField(1,0,3) + "|" + board.getField(1,0,2) + "|" + board.getField(1,0,1) + "|"
				+ board.getField(1,0,0) + "       " + board.getField(2,0,3) + "|" + board.getField(2,0,2) + "|" + board.getField(2,0,1) + "|"
				+ board.getField(2,0,0) + "       " + board.getField(3,0,3) + "|" + board.getField(3,0,2) + "|" + board.getField(3,0,1) + "|"
				+ board.getField(3,0,0))+ System.lineSeparator();
		s = s + ("         Z                     Z                     Z                     Z") + System.lineSeparator();
		s = s + ("") + System.lineSeparator();
		s = s + ("  " + board.getField(0,3,0) + "|" + board.getField(1,3,1) + "|" + board.getField(2,3,2) + "|"
				+ board.getField(3,3,3) + "       " + board.getField(0,3,3) + "|" + board.getField(1,3,2) + "|" + board.getField(2,3,1) + "|"
				+ board.getField(3,3,0)) + System.lineSeparator();
		s = s + ("Y " + board.getField(0,2,0) + "|" + board.getField(1,2,1) + "|" + board.getField(2,2,2) + "|"
				+ board.getField(3,2,3) + "     Y " + board.getField(0,2,3) + "|" + board.getField(1,2,2) + "|" + board.getField(2,2,1) + "|"
				+ board.getField(3,2,0)) + System.lineSeparator();
		s = s + ("  " + board.getField(0,1,0) + "|" + board.getField(1,1,1) + "|" + board.getField(2,1,2) + "|"
				+ board.getField(3,1,3) + "       " + board.getField(0,1,3) + "|" + board.getField(1,1,2) + "|" + board.getField(2,1,1) + "|"
				+ board.getField(3,1,0)) + System.lineSeparator();
		s = s + ("  " + board.getField(0,0,0) + "|" + board.getField(1,0,1) + "|" + board.getField(2,0,2) + "|"
				+ board.getField(3,0,3) + "       " + board.getField(0,0,3) + "|" + board.getField(1,0,2) + "|" + board.getField(2,0,1) + "|"
				+ board.getField(3,0,0)) + System.lineSeparator();
		return s;

	}
}
