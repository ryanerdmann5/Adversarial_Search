package simacogo;

import java.util.Arrays;

//class for the board object
public class Board {
	String[][] board = new String[9][9];

	// create a new board and populate with empty spaces
	public Board() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = "*";
			}
		}
	}

	// creates a board from a given array
	public Board(String[][] board) {
		this.board = board;
	}

	// places and O at the desired location
	public boolean humanPlace(int index) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][index].equals("X") == false && board[i][index].equals("O") == false) {
				board[i][index] = "O";
				return true;
			}
		}
		return false;
	}

	// Places an X at the desired location
	public boolean AIPlace(int index) {
		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][index].equals("X") == false && board[i][index].equals("O") == false) {
				board[i][index] = "X";
				return true;
			}
		}
		return false;
	}

	// Returns a new board with an O at the given location (for min max)
	public Board humanCanPlace(int index) {
		String[][] newBoard = new String[9][9];
		for (int i = 0; i < board.length; i++) {
			newBoard[i] = Arrays.copyOf(board[i], board[i].length);
		}

		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][index].equals("X") == false && board[i][index].equals("O") == false) {
				newBoard[i][index] = "O";
				return new Board(newBoard);
			}
		}
		return this;
	}

	// returns a new board with an x at the desired location (for min max)
	public Board AICanPlace(int index) {
		String[][] newBoard = new String[9][9];
		for (int i = 0; i < board.length; i++) {
			newBoard[i] = Arrays.copyOf(board[i], board[i].length);
		}

		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][index].equals("X") == false && board[i][index].equals("O") == false) {
				newBoard[i][index] = "X";
				return new Board(newBoard);
			}
		}
		return this;
	}

	// calculate the human score
	public int getHumanScore() {
		int score = 0;
		for (int i = board.length - 1; i > 0; i--) {
			for (int j = 1; j < board.length; j++) {
				if (board[i][j].equals("O") && board[i][j - 1].equals("O"))
					score = score + 2;
				if (board[i][j].equals("O") && board[i - 1][j].equals("O"))
					score = score + 2;
				if (board[i][j].equals("O") && board[i - 1][j - 1].equals("O"))
					score = score + 1;
			}
		}

		for (int i = 1; i <= 8; i++) {
			if (board[7][i].equals("O") && board[8][i - 1].equals("O"))
				score = score + 1;
		}
		return score;
	}

	// calculate the AI score
	public int getAIScore() {
		int score = 0;
		for (int i = board.length - 1; i > 0; i--) {
			for (int j = 1; j < board.length; j++) {
				if (board[i][j].equals("X") && board[i][j - 1].equals("X"))
					score = score + 2;
				if (board[i][j].equals("X") && board[i - 1][j].equals("X"))
					score = score + 2;
				if (board[i][j].equals("X") && board[i - 1][j - 1].equals("X"))
					score = score + 1;
			}
		}

		for (int i = 1; i <= 8; i++) {
			if (board[7][i].equals("X") && board[8][i - 1].equals("X"))
				score = score + 1;
		}
		return score;
	}

	// see if a row column is full
	public boolean canPlace(int index) {
		if (board[0][index] == "X" || board[0][index] == "O")
			return false;
		return true;
	}

	// print out the board
	public String toString() {
		for (int i = 0; i < this.board.length; i++) {
			System.out.println(Arrays.toString(this.board[i]));

		}
		System.out.println(" 1, 2, 3, 4, 5, 6, 7, 8, 9");
		return Arrays.deepToString(this.board);
	}

	// detect end game
	public boolean gameOver() {
		boolean flag = true;
		for (int i = 0; i <= 8; i++) {
			if (board[0][i].equals("*"))
				flag = false;
		}
		return flag;
	}

	// for testing
	public static void main(String[] args) {
		Board b = new Board();
		MinMaxAI MAX = new MinMaxAI(3);
		int k = 0;
		b.humanPlace(2);
		b.AIPlace(MAX.minMaxCall(b));
		b.humanPlace(3);
		Board b2 = b.humanCanPlace(3);
		for (int i = 0; i < b.board.length; i++) {
			System.out.println(Arrays.toString(b.board[i]));
		}
		System.out.println();
		b2.toString();
		System.out.println(b.getHumanScore());
		System.out.println(b.getAIScore());
		System.out.println(b2.getHumanScore());
	}
}
