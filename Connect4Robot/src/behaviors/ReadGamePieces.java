package behaviors;

import connect4.Communication;
import connect4.GameLogic;
import connect4.MotorFunctions;
import connect4.PieceXYReadMove;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import sensors.ColorTester;
import util.Point;

public class ReadGamePieces implements Behavior {

	private volatile boolean suppressed = false;
	

	private ColorTester colorTester;
	private MotorFunctions motorFunctions;
	private GameLogic gameLogic;
	private PieceXYReadMove pieceXYReadMove;
	private Communication comm = new Communication();

	public ReadGamePieces(PieceXYReadMove pieceXYReadMove, GameLogic gameLogic, Communication comm) {
		this.pieceXYReadMove = pieceXYReadMove;
		this.gameLogic = gameLogic;
		this.comm = comm;
	}

	@Override
	public boolean takeControl() {
		// otetaan kontrolli, kun robotin vuoro ja laudan luku kesken (pelattu nappula
		// löytämättä)
		if (gameLogic.getIsRobotsTurn() && !gameLogic.getGameBoardReadComplete() && gameLogic.inStartPosition()) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		while (!suppressed) {

			boolean newPieceFound = false;
			
			while(!newPieceFound) {
				// haetaan tieto stepeistä seuraavaan tyhjään slottiin (gameLogic)
				Point stepsToNextEmpty = gameLogic.stepsToNextEmpty();

				// siirrytään seuraavan edellisellä vuorolla tyhjänä olleeseen kohtaan, lopetetaan haku, kun löytyy pelattu nappula
				int destinationColor = pieceXYReadMove.moveSensor(stepsToNextEmpty);
				//gameLogic.locationChange(stepsToNextEmpty);
				if(destinationColor == ColorTester.COLOR_PLAYERPIECE||destinationColor == ColorTester.COLOR_ROBOTPIECE) {
					newPieceFound = true; //lopetetaan haku, kun kohdepisteessä nappula
					//ilmoitetaan löydetty nappula ja sijainti gameLogicille, muuten jatketaan hakemalla uusi kohde
					gameLogic.setPieceToCurrentLocation(destinationColor);
					Point currentLocation = gameLogic.getLocation();

					gameLogic.setGameBoardReadComplete(true);
					comm.sendDropPoint(currentLocation);
					gameLogic.setDropPointReceived(false);
					suppressed = true;
				}
			}
		}
	}

	@Override
	public void suppress() {
		motorFunctions.stopLifter();
		suppressed = true;
	}

	

}
