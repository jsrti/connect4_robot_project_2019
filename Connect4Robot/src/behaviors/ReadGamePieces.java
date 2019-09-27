package behaviors;

import java.awt.Point;

import connect4.GameLogic;
import connect4.Movement;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import sensors.ColorTester;

public class ReadGamePieces implements Behavior {

	private final int motorSpeed = 50;
	private RegulatedMotor motor;
	
	private ColorTester colorCalibrator;
	private Movement movement;
	private GameLogic gameLogic;

	public ReadGamePieces(ColorTester colorCalibrator, Movement movement, Port motorPort, GameLogic gameLogic) {
		this.colorCalibrator = colorCalibrator;
		this.movement = movement;
		this.motor = new EV3MediumRegulatedMotor(motorPort);
		motor.setSpeed(motorSpeed);
		this.gameLogic = gameLogic;
	}

	@Override
	public boolean takeControl() {
		return false;
	}

	@Override
	public void action() {
		//moveToNextEmpty
		//read color
		//if!=empty -> update gameLogic+gameBoardReadComplete, suppress

	}

	@Override
	public void suppress() {
		//paluu alkupisteeseen
	}
	
	private void moveSensor(boolean moveUpwards, Point steps) {
		
		if (moveUpwards) {
			motor.forward();
		}else {
			motor.backward();
		}
	}
	
}
