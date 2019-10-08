package behaviors;

import connect4.Communication;
import connect4.GameLogic;
import lejos.robotics.subsumption.Behavior;

public class ReceiveTurnChange implements Behavior {

	private GameLogic gameLogic;
	private Communication comm;
	
	public ReceiveTurnChange(GameLogic gameLogic, Communication comm) {
		this.gameLogic = gameLogic;
		this.comm = comm;
	}

	@Override
	public boolean takeControl() {
		if(!gameLogic.getIsRobotsTurn() && gameLogic.inStartPosition()) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		comm.receiveTurnChange();
		System.out.println("Turn change received");
		gameLogic.setIsRobotsTurn(true);
	}

	@Override
	public void suppress() {
		
	}

}
