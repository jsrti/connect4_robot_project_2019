package cleanerBot;

import lejos.robotics.subsumption.Behavior;

public class DriveForward implements Behavior {
	private volatile boolean suppressed = false;

	private Movement movement;
	
	public DriveForward(Movement movement) {
		this.movement = movement;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		System.out.println("Moving forward");
		suppressed = false;
		movement.move(300, 300, false);
		while (!suppressed)Thread.yield();
		movement.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}