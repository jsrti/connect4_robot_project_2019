package model;

public class Determinator {
	private Board game;
	private int[][] grid;
	private int[] nextMoves;
	private int bot;
	private int player;
	
	public Determinator(Board game, int bot, int player) {
		this.game = game;
		this.bot = bot;
		this.player = player;
	}
	
	public int getNextMove(int botSide, int playerSide) {
		bot = botSide;
		player = playerSide;
		grid = game.getGrid();
		
		nextMoves = game.getNextFreeSpaces();
		
		for (int i = 0; i < nextMoves.length; i++) {
			
		}
		
		return 0;
	}
	
	private int getHorizontalValue(int x, int y) {
		
		int points = 0;
		boolean noEmpty = true;
		
		//Left side
		int read = x;
		System.out.println("read " + read);
		if (read > 3) {
			read = 3;
		}
		
		int leftPoints = 0;
		int leftPeg = -1;
		int leftStreak = 0;
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x-a][y];
			
			if (spot != 0 && leftPeg == -1) {
				leftPeg = spot;
				System.out.println("leftPeg = " + leftPeg + " at X: " + (x-a) + " Y: " + y);
			}
			else if (spot == 0) {
				noEmpty = false;
			}
			
			if (spot == leftPeg) {
				if (spot == bot) {
					leftPoints += 3;
					System.out.println("3 left");
				}
				else if (spot == player) {
					leftPoints += 2;
					System.out.println("2 left");
				}
				
				if (noEmpty) {
					leftStreak++;
					System.out.println("left streak increased");
				}
			}
			else if (spot == 0) {
				leftPoints++;
					System.out.println("1 left");
			}
			else { System.out.println("left break at " + a + " " + spot); break; }
			
		}
		read = grid.length - 1 - x;
		System.out.println("read " + read);
		if (read > 3) {
			read = 3;
		}
		
		//Right side
		int rightPoints = 0;
		int rightPeg = -1;
		int rightStreak = 0;
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x+a][y];
			
			if (spot != 0 && rightPeg == -1) {
				rightPeg = spot;
				System.out.println("rightPeg = " + rightPeg + " at X: " + (x+a) + " Y: " + y);
			}
			else if (spot == 0) {
				noEmpty = false;
			}
			
			if (spot == rightPeg) {
				if (spot == bot) {
					rightPoints += 3;
					System.out.println("+3 right");
				}
				else if (spot == player) {
					rightPoints += 2;
					System.out.println("+2 right");
				}
				
				if (noEmpty) {
					rightStreak++;
					System.out.println("right streak increased");
				}
			}
			else if (spot == 0) {
				rightPoints++;
					System.out.println("+1 right");
			}
			else { System.out.println("right break at " + a + " " + spot); break; }
			
		}
		
		if (rightPeg == leftPeg && (noEmpty || rightStreak + leftStreak >= 3)) {
			points = (int)Math.pow(2, rightPoints + leftPoints);
		}
		else {
			points = (int)(Math.pow(2, leftPoints) + Math.pow(2, rightPoints));
		}
		return points;
	}
	
	private int getVerticalValue(int x, int y) {
		int points = 0;
		int firstPeg = -1;
		
		int read = y;
		if (read > 3) {
			read = 3;
		}
		
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x][y-a];
			
			if (spot != 0 && firstPeg == -1) {
				firstPeg = spot;
			}
			
			if (spot == firstPeg) {
				if (spot == bot) {
					points += 3;
					System.out.println("+3 right");
				}
				else if (spot == player) {
					points += 2;
					System.out.println("+2 right");
				}
			}
			else { System.out.println("down break at " + a + " " + spot); break; }
		}
		
		points = (int)Math.pow(2, points);
		return points;
	}
	
	private int getDiagonalRightValue(int x, int y) {
		
		int points = 0;
		boolean noEmpty = true;
		
		//Left side
		int read = y;
		if (x < y) {
			read = x;
		}
		if (read > 3) {
			read = 3;
		}
		
		int leftPoints = 0;
		int leftPeg = -1;
		int leftStreak = 0;
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x-a][y-a];
			
			if (spot != 0 && leftPeg == -1) {
				leftPeg = spot;
				System.out.println("leftPeg = " + leftPeg + " at X: " + (x-a) + " Y: " + (y-a));
			}
			else if (spot == 0) {
				noEmpty = false;
			}
			
			if (spot == leftPeg) {
				if (spot == bot) {
					leftPoints += 3;
					System.out.println("3 left");
				}
				else if (spot == player) {
					leftPoints += 2;
					System.out.println("2 left");
				}
				
				if (noEmpty) {
					leftStreak++;
					System.out.println("left streak increased");
				}
			}
			else if (spot == 0) {
				leftPoints++;
					System.out.println("1 left");
			}
			else { System.out.println("left break at " + a + " " + spot); break; }
			
		}
		read = grid[x].length - 1 - y;
		if (grid.length - 1 - x < grid[x].length - 1 - y) {
			System.out.println("aaaa");
			read = grid.length - 1 - x;
		}
		System.out.println("read " + read);
		if (read > 3) {
			read = 3;
		}
		
		//Right side
		int rightPoints = 0;
		int rightPeg = -1;
		int rightStreak = 0;
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x+a][y+a];
			
			if (spot != 0 && rightPeg == -1) {
				rightPeg = spot;
				System.out.println("rightPeg = " + rightPeg + " at X: " + (x+a) + " Y: " + (y+a));
			}
			else if (spot == 0) {
				noEmpty = false;
			}
			
			if (spot == rightPeg) {
				if (spot == bot) {
					rightPoints += 3;
					System.out.println("+3 right");
				}
				else if (spot == player) {
					rightPoints += 2;
					System.out.println("+2 right");
				}
				
				if (noEmpty) {
					rightStreak++;
					System.out.println("right streak increased");
				}
			}
			else if (spot == 0) {
				rightPoints++;
					System.out.println("+1 right");
			}
			else { System.out.println("right break at " + a + " " + spot); break; }
			
		}
		
		if (rightPeg == leftPeg && (noEmpty || rightStreak + leftStreak >= 3)) {
			points = (int)Math.pow(2, rightPoints + leftPoints);
		}
		else {
			points = (int)(Math.pow(2, leftPoints) + Math.pow(2, rightPoints));
		}
		return points;
	}

	private int getDiagonalLeftValue(int x, int y) {
		
		int points = 0;
		boolean noEmpty = true;
		
		//Left side
		int read = grid[x].length - 1 - y;
		if (x < read) {
			read = x;
		}
		if (read > 3) {
			read = 3;
		}
		
		int leftPoints = 0;
		int leftPeg = -1;
		int leftStreak = 0;
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x-a][y+a];
			
			if (spot != 0 && leftPeg == -1) {
				leftPeg = spot;
				System.out.println("leftPeg = " + leftPeg + " at X: " + (x-a) + " Y: " + (y+a));
			}
			else if (spot == 0) {
				noEmpty = false;
			}
			
			if (spot == leftPeg) {
				if (spot == bot) {
					leftPoints += 3;
					System.out.println("3 left");
				}
				else if (spot == player) {
					leftPoints += 2;
					System.out.println("2 left");
				}
				
				if (noEmpty) {
					leftStreak++;
					System.out.println("left streak increased");
				}
			}
			else if (spot == 0) {
				leftPoints++;
					System.out.println("1 left");
			}
			else { System.out.println("left break at " + a + " " + spot); break; }
			
		}
		read = grid.length - 1 - x;
		if (y < read) {
			read = y;
		}
		System.out.println("read " + read);
		if (read > 3) {
			read = 3;
		}
		
		//Right side
		int rightPoints = 0;
		int rightPeg = -1;
		int rightStreak = 0;
		for (int a = 1; a <= read; a++) {
			
			int spot = grid[x+a][y-a];
			
			if (spot != 0 && rightPeg == -1) {
				rightPeg = spot;
				System.out.println("rightPeg = " + rightPeg + " at X: " + (x+a) + " Y: " + (y-a));
			}
			else if (spot == 0) {
				noEmpty = false;
			}
			
			if (spot == rightPeg) {
				if (spot == bot) {
					rightPoints += 3;
					System.out.println("+3 right");
				}
				else if (spot == player) {
					rightPoints += 2;
					System.out.println("+2 right");
				}
				
				if (noEmpty) {
					rightStreak++;
					System.out.println("right streak increased");
				}
			}
			else if (spot == 0) {
				rightPoints++;
					System.out.println("+1 right");
			}
			else { System.out.println("right break at " + a + " " + spot); break; }
			
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
