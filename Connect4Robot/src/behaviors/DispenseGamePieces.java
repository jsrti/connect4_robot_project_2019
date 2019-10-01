package behaviors;

import connect4.MotorFunctions;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class DispenseGamePieces implements Behavior {
	private volatile boolean suppressed = false;
	private MotorFunctions motorFunctions; // movement reference (motor functions)
	
	
	public DispenseGamePieces(MotorFunctions motorFunctions) {
		this.motorFunctions = motorFunctions;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		System.out.println("Starting dispenser");
		suppressed = false;
		motorFunctions.rotateDispenserMotor(20, true);
		Delay.msDelay(3000);
		motorFunctions.stopDispenser();
		motorFunctions.rotateDispenserMotor(20, false);
		Delay.msDelay(3000);
		motorFunctions.stopDispenser();
		suppressed = true;
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

}
