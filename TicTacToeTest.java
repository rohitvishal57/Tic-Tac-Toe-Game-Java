package com.monocept.test;

import com.monocept.model.GameBoard;

public class TicTacToeTest {

	public static void main(String[] args) {
		GameBoard game1 = new GameBoard();
		game1.startGame();
		game1.playGame();
		//game1.printBoard();
		game1.printWinner();

	}

}
