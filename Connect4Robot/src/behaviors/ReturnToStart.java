package behaviors;

import connect4.GameLogic;
import connect4.MotorFunctions;
import lejos.robotics.subsumption.Behavior;
import sensors.TouchSensor;

public class ReturnToStart implements Behavior {

	private volatile boolean suppressed = false;
	MotorFunctions motorFunctions;
	private TouchSensor startPositionButton;
	private GameLogic gameLogic;
	
	public ReturnToStart(MotorFunctions motorFunctions, TouchSensor startPositionButton, GameLogic gameLogic) {
		this.motorFunctions = motorFunctions;
		this.startPositionButton = startPositionButton;
		this.gameLogic = gameLogic;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		
		while(!suppressed) {
			// TODO: robotti liikkuu aloitusasemaan (x- siirtymä anturiin asti, joka merkkinä pisteestä -1)
			// jos värianturi liian alhaalla (y 1-2), nostetaan sitä (jotta mahtuu liikkumaan pelilaudan ohi)
			// -> päivitetään gameLogic sijainti
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
