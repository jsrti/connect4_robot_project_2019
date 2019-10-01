package connect4;

import util.Point;

public class GameLogic {
	int columns = 7;
	int rows = 6;

	private int[][] gameGrid = new int[columns][rows];

	private Point currentPos = new Point(0, 0);

	private boolean isRobotsTurn = false; // Player's turn by default
	private boolean gameBoardReadComplete = false;

	private boolean turnCalculationReceived = false;
	private boolean hasDroppedPiece = false;
	
	public boolean getIsRobotsTurn() {
		return isRobotsTurn;
	}
	
	public boolean getGameBoardReadComplete() {
		return gameBoardReadComplete;
	}

	public GameLogic() {
		gameGrid = new int[columns][rows];
	}

	public void increaseCurrentVerticalPos() {
		currentPos.y++;
	}

	public void decreaseCurrentVerticalPos() {
		currentPos.y--;
	}

	public void increaseCurrentHorizontalPos() {
		currentPos.x++;
	}

	public void decreaseCurrentHorizontalPos() {
		currentPos.x--;
	}

	// etsii seuraavan tyhj�n slotin nykyisest� sijainnista eteenp�in. l�hdett�v�
	// t�ss� vaiheessa nollasta, eli nollaus ennen t�t�. palautetaan ensimmäinen löydetty tyhjä
	private Point checkNextEmptySlot() {
		Point nextEmptyPoint = null;
		for (int i = currentPos.x; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				if (gameGrid[i][j] == 0) {
					nextEmptyPoint = new Point(i, j);
					System.out.println("Steps to next empty slot: " + nextEmptyPoint);
					return nextEmptyPoint;
				}
			}
		}
		return nextEmptyPoint;
	}
	
	//"askeleet" seuraavaan tarkistettavaan pisteeseen anturin nykyisest� sijainnista
	public Point stepsToNextEmpty() {
		Point nextEmpty = checkNextEmptySlot();
		Point xySteps = new Point();
		
		xySteps.x = nextEmpty.x-currentPos.x;
		xySteps.y = nextEmpty.y-currentPos.y;
		
		return xySteps;
	}

}
