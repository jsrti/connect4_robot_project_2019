package model;

public class Determinator {
	private static Board game;
	private static int[][] grid;
	private static int[] nextMoves;
	private static int bot;
	private static int player;
	
	public Determinator(Board game, int bot, int player) {
		this.game = game;
		this.bot = bot;
		this.player = player;
	}
	
	public static void main(String[] args) {
		game = new Board();
		grid = game.getGrid();
		bot = 1;
		player = 2;
		game.setPiece(0, 1);
		game.setPiece(1, 2);
		game.setPiece(3, 1);
		
		System.out.println(getHorizontalValue(2, 0));
	}
	
	private static int getHorizontalValue(int x, int y) {
		
		int points = 0;
		boolean noEmpty = true;
		
		//Left side
		int read = x;
		System.out.println(read);
		if (read > 3) {
			read = 3;
		}
		
		int leftPoints = 0;
		int leftPeg = -1;
		int leftStreak = 0;
		for (int a = 1; a <= read; a++) {
			System.out.println("hi");
			
			if (grid[x-a][y] != 0 && leftPeg == -1) {
				leftPeg = grid[x-a][y];
			}
			else if (grid[x-a][y] == 0) {
				noEmpty = false;
			}
			
			if (grid[x-a][y] == leftPeg) {
				if (leftPeg == bot) {
					leftPoints += 3;
					System.out.println("3 left");
				}
				else if (leftPeg == player) {
					leftPoints += 2;
					System.out.println("2 left");
				}
				
				if (noEmpty) {
					leftStreak++;
					System.out.println("1 left");
				}
			}
			else if (grid[x-a][y] == 0) {
				leftPoints++;
			}
			else { break; }
			
		}
		read = grid.length - 1 - x;
		System.out.println(read);
		if (read > 3) {
			read = 3;
		}
		
		//Right side
		int rightPoints = 0;
		int rightPeg = -1;
		int rightStreak = 0;
		for (int a = 1; a <= read; a++) {
			System.out.println("ho");
			
			if (grid[x+a][y] != 0 && rightPeg == -1) {
				rightPeg = grid[x-a][y];
			}
			else if (grid[x+a][y] == 0) {
				noEmpty = false;
			}
			
			if (grid[x+a][y] == rightPeg) {
				if (rightPeg == bot) {
					rightPoints += 3;
					System.out.println("3 right");
				}
				else if (rightPeg == player) {
					rightPoints += 2;
					System.out.println("2 right");
				}
				
				if (noEmpty) {
					rightStreak++;
					System.out.println("1 right");
				}
			}
			else if (grid[x+a][y] == 0) {
				rightPoints++;
			}
			else { break; }
			
		}
		
		if (rightPeg == leftPeg && (noEmpty || rightStreak + leftStreak >= 3)) {
			points = (int)Math.pow(2, rightPoints + leftPoints);
		}
		else {
			points = (int)(Math.pow(2, leftPoints) + Math.pow(2, rightPoints));
		}
		return points;
	}
}
