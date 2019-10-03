package model;

public class Board {
	private int[][] grid;
	
	public Board() {
		grid = new int[7][6];
	}
	
	public int[] getNextFreeSpaces() {
		int[] indexes = new int[7];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				if (grid[x][y] == 0) {
					indexes[x] = y;
					break;
				}
			}
		}
		return indexes;
	}

	public int[][] getGrid() {
		return grid;
	}
	
	public void setPiece(int x, int player) {
		for (int y = 0; y < grid[x].length; y++) {
			if (grid[x][y] == 0) {
				grid[x][y] = player;
				break;
			}
		}
	}
}
