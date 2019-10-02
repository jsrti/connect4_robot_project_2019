package model;

public class Board {
	private static int[][] board;
	
	public Board() {
		board = new int[7][6];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				System.out.println(x);
				System.out.println(y);
				board[x][y] = 0;
			}
		}
	}
	public static void main(String[] args) {
		board = new int[7][6];
		System.out.println(board.length);
		System.out.println(board[0].length);
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				System.out.println("X: " + x + " Y: " + y);
				System.out.println(board[x][y]);
				board[x][y] = 2;
			}
		}
	}
}
