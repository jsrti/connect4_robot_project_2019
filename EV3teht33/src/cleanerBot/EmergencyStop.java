package cleanerBot;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EmergencyStop implements Behavior {
	private volatile boolean suppressed = false;
	private Movement movement;
	
	public EmergencyStop(Movement movement) {
		this.movement = movement;
	}
	
	@Override
	public boolean takeControl() {
		if(Button.ENTER.isDown()) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		System.out.print("EMERGENCY STOP");
		suppressed = false;
		movement.stop();
		Delay.msDelay(10);
		System.out.print("Press ENTER to continue");
		Button.ENTER.waitForPressAndRelease();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
