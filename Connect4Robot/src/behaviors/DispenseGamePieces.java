package behaviors;

import connect4.GameLogic;
import connect4.MotorFunctions;
import connect4.PieceXYReadMove;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import sensors.ColorTester;
import sensors.TouchSensor;
import util.Point;

public class DispenseGamePieces implements Behavior {
	private volatile boolean suppressed = false;
	private PieceXYReadMove pieceXYReadMove;
	private MotorFunctions motorFunctions; // movement reference (motor functions)
	private TouchSensor feederEndButton;
	private GameLogic gameLogic;
	private ColorTester colorTester;
	
	
	public DispenseGamePieces(PieceXYReadMove pieceXYReadMove, MotorFunctions motorFunctions, TouchSensor feederEndButton, GameLogic gameLogic, ColorTester colorTester) {
		this.pieceXYReadMove = pieceXYReadMove;
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
		
		Point targetEmpty = new Point(3,3);
		pieceXYReadMove.moveSensor(targetEmpty);
		motorFunctions.rotateDispenserMotor(10, false);
		while(colorTester.testColor()==ColorTester.COLOR_EMPTY);
		motorFunctions.stopDispenser();
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

}
