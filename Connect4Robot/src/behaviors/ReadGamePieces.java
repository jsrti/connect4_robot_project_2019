package behaviors;


import connect4.GameLogic;
import connect4.MotorFunctions;
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
	private final int sensorMotorSpeed = 20;
	private final int movementSpeed = 30;

	private ColorTester colorTester;
	private MotorFunctions motorFunctions;
	private GameLogic gameLogic;

	public ReadGamePieces(ColorTester colorTester, MotorFunctions motorFunctions, GameLogic gameLogic) {
		this.colorTester = colorTester;
		this.motorFunctions = motorFunctions;
		this.gameLogic = gameLogic;
	}

	@Override
	public boolean takeControl() {
		// otetaan kontrolli, kun robotin vuoro ja laudan luku kesken (pelattu nappula
		// löytämättä)
		if (gameLogic.getIsRobotsTurn() && !gameLogic.getGameBoardReadComplete()) {
			return true;
		}
		return true;
	}

	@Override
	public void action() {
		while (!suppressed) {
			
			motorFunctions.rotateLifterMotor(sensorMotorSpeed, true);
			Delay.msDelay(5000);
			motorFunctions.stopLifter();
			suppressed = true;
			
			
			// haetaan tieto stepeistä seuraavaan tyhjään slottiin (gameLogic)
			Point stepsToNextEmpty = gameLogic.stepsToNextEmpty();

			// sensorin liikutus haluttuun paikkaan
			moveSensor(stepsToNextEmpty);
			// read color
			// if!=empty -> update gameLogic+gameBoardReadComplete, suppress
		}

	}

	@Override
	public void suppress() {
		// TODO: paluu alkupisteeseen (behaviour)
		suppressed = true;
	}

	private void moveSensor(Point steps) {
		int stepsMovedY = 0; // pidetään ylhäällä liikutus stepit, parametrina saadaan kokonaismäärä
		int stepsMovedX = 0;

		// tarkistetaan x-liikkumissuunta
		boolean xDirectionForward = true;
		if (steps.x > 0) {
			xDirectionForward = true;
		} else {
			xDirectionForward = false;
		}
		// x-suunnassa liikkuminen (pyörät)
		motorFunctions.rotateMovementMotor(movementSpeed, xDirectionForward);

		int lastColor = colorTester.testColor(); // edellinen väri, johon uutta tunnistettua väriä verrataan (toiminto
													// värin vaihtuessa)
		for (int i = 0; i < Math.abs(steps.x); i++) {
			// luetaan väriä, kunnes tunnistetaan uusi väri
			boolean foundNewPiece = false;
			while (!foundNewPiece) {
				int color = colorTester.testColor();
				if (color != lastColor) {
					Sound.twoBeeps(); //piippaa, kun tunnistetaan uusi väri
					switch (color) {
					case ColorTester.COLOR_BOARD:
						lastColor = color; //laudan tunnistuksessa tallennetaan tieto värimuutoksesta ja jatketaan
						break;
					case ColorTester.COLOR_PLAYERPIECE:
					case ColorTester.COLOR_ROBOTPIECE:
						foundNewPiece = true;
						if(xDirectionForward) {
							stepsMovedX += 1;
						}else {
							stepsMovedX -= 1;
						}
						
						break;
					default:
						break;
					}
				}
			}
			// luetaan väri, pysähdytään
		}

		/* jos stepit miinuksella, moottorin pyörintäsuunta sn mukaan (laskeutuminen)
		if (steps.y < 0) {
			sensorMotor.backward();
		} else {
			sensorMotor.forward();
		}
		
		*/
	}

}
