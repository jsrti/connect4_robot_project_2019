package cleanerBot;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
	private volatile boolean suppressed = false;

	public EmergencyStop() {
	}

	@Override
	public boolean takeControl() {
		return Button.DOWN.isDown();
	}

	@Override
	public void action() {
		System.out.print("EMERGENCY STOP");
		suppressed = false;
		System.out.print("Press ENTER to continue");
		Button.ENTER.waitForPressAndRelease();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
