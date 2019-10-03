package behaviors;

import connect4.GameLogic;
import connect4.MotorFunctions;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import sensors.ColorTester;
import sensors.TouchSensor;
import util.Point;

public class DispenseGamePieces implements Behavior {
	private volatile boolean suppressed = false;
	private MotorFunctions motorFunctions; // movement reference (motor functions)
	private TouchSensor feederEndButton;
	private GameLogic gameLogic;
	private ColorTester colorTester;
	
	
	public DispenseGamePieces(MotorFunctions motorFunctions, TouchSensor feederEndButton, GameLogic gameLogic, ColorTester colorTester) {
		this.motorFunctions = motorFunctions;
		this.feederEndButton = feederEndButton;
		this.gameLogic = gameLogic;
		this.colorTester = colorTester;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		/*
		System.out.println("Starting dispenser");
		suppressed = false;
		motorFunctions.rotateDispenserMotor(20, true);
		Delay.msDelay(3000);
		motorFunctions.stopDispenser();
		motorFunctions.rotateDispenserMotor(20, false);
		Delay.msDelay(3000);
		motorFunctions.stopDispenser();
		suppressed = true;
		*/
		
		
		//TODO: siirtyy oikeaan kohtaan (pudotuspaikan Point(x,y) saadaan tietokoneelta, liikutaan kohdalle ja nostetaan
		// anturi tarkkailemaan pudotusta. Liikutetaan dispencerMotoria, kunnes huomataan värin vaihtuneen, vuoro päättyy (suppress)
		// HUOM: jos osuu kosketusanturiin ääripäässä, ilmoitetaan lataustarve ja jatketaan pelaajan kuittauksen jälkeen
		// default behavior: ReturnToStart
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

}
