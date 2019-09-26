package connect4;

import java.util.Arrays;

public class GameLogic {
	private int[][] gameGrid = new int[7][6];
	
	private boolean isRobotsTurn = false; //Player's turn by default
	private boolean gameBoardReadComplete = false;
	
	private boolean turnCalculationReceived = false;
	private boolean hasDroppedPiece = false;
	
	public GameLogic() {
		Arrays.fill(gameGrid, 0);
	}
	
}
