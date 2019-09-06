package cleanerBot;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
	private volatile boolean suppressed = false;
	private Movement movement;
	
	public EmergencyStop(Movement movement) {
		this.movement = movement;
	}
	
	@Override
	public boolean takeControl() {
		return Button.ENTER.isDown();
	}

	@Override
	public void action() {
		System.out.print("EMERGENCY STOP");
		suppressed = false;
		movement.stop();
		System.out.print("Press ENTER to continue");
		Button.ENTER.waitForPressAndRelease();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
