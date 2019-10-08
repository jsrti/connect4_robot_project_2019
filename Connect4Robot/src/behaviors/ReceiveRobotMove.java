package behaviors;

import connect4.Communication;
import connect4.GameLogic;
import lejos.robotics.subsumption.Behavior;
import util.Point;

public class ReceiveRobotMove implements Behavior {

	private Communication comm = new Communication();
	private GameLogic gameLogic;

	public ReceiveRobotMove(GameLogic gameLogic, Communication comm) {
		this.gameLogic = gameLogic;
		this.comm = comm;
	}

	@Override
	public boolean takeControl() {
		if (gameLogic.getIsRobotsTurn() && !gameLogic.getDropPointReceived()) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		Point p = comm.receiveDropPoint();
		gameLogic.setPlayerMove();
		gameLogic.setDropPointReceived(true);
		//TODO: falseksi jossain
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
