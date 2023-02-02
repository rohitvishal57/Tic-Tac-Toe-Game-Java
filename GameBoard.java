package com.monocept.model;

import java.util.Scanner;

public class GameBoard {
	static Scanner sc = new Scanner(System.in);
	private int[][] board = new int[3][3];
	public static final int cross = 1;
	public static final int nought = 0;
	public static final int emptyCell = -1;
	GameStatus currentState = GameStatus.playing;

	public void startGame() {
		// filling all blocks of board with -1 to show board is empty initially
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				board[i][j] = emptyCell;
			}
		}
	}

	public void playGame() {
		int currentPlayer = cross;

		while (currentState == GameStatus.playing) {
			boolean validInput = false;
			do {
				if (currentPlayer == cross) {
					System.out.println("Player 'X', enter your move (row[1-3] column[1-3]): ");
				} else {
					System.out.println("Player 'O', enter your move (row[1-3] column[1-3]): ");
				}
				int row, col;
				row = sc.nextInt() - 1;
				col = sc.nextInt() - 1;
				if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == emptyCell) {
					// Update board[][] and return the new game state after the move
					currentState = updateBoard(currentPlayer, row, col);
					printBoard();
					validInput = true; // input okay, exit loop
				} else {
					System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...");
				}

			} while (!validInput);
			currentPlayer = (currentPlayer == cross) ? nought : cross;
		}
	}

	private GameStatus updateBoard(int currentPlayerMove, int row, int col) {
		board[row][col] = currentPlayerMove;
		if (board[row][0] == currentPlayerMove // 3-in-the-row
				&& board[row][1] == currentPlayerMove && board[row][2] == currentPlayerMove
				|| board[0][col] == currentPlayerMove // 3-in-the-column
						&& board[1][col] == currentPlayerMove && board[2][col] == currentPlayerMove
				|| row == col // 3-in-the-diagonal
						&& board[0][0] == currentPlayerMove && board[1][1] == currentPlayerMove
						&& board[2][2] == currentPlayerMove
				|| row + col == 2 // 3-in-the-opposite-diagonal
						&& board[0][2] == currentPlayerMove && board[1][1] == currentPlayerMove
						&& board[2][0] == currentPlayerMove) {
			return (currentPlayerMove == cross) ? GameStatus.crossWon : GameStatus.noughtWon;
		} else {
			// Case of Draw we check for all blocks are filled or not.
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					if (board[i][j] == emptyCell) {
						return GameStatus.playing; // still have empty cells Game continues
					}
				}
			}
			return GameStatus.draw;
		}
	}

	public void printBoard() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				//System.out.print(" ");
				if (board[i][j] == cross)
					System.out.print(" X ");
				else if (board[i][j] == nought)
					System.out.print(" O ");
				else
					System.out.print("  ");
				if (j < 2)
					System.out.print("|");
			}
			System.out.println();
			if (i < 2) {
				System.out.println("-----------");
			}
		}
		System.out.println();
	}

	public void printWinner() {
		if (currentState == GameStatus.crossWon) {
			System.out.println("'X' won!\nBye!");
		} else if (currentState == GameStatus.noughtWon) {
			System.out.println("'O' won!\nBye!");
		} else if (currentState == GameStatus.draw) {
			System.out.println("It's a Draw!\nBye!");
		}
	}

}
