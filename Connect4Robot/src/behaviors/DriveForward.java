package behaviors;

import connect4.Movement;
import lejos.robotics.subsumption.Behavior;

/**
 * 
 * @author jetro, kim, pietari, olli
 *
 */
public class DriveForward implements Behavior {
	private volatile boolean suppressed = false;
	private Movement movement; // movement reference (motor functions)

	public DriveForward(Movement movement) {
		this.movement = movement;
	}

	/**
	 *
	 */
	@Override
	public boolean takeControl() {
		return false;
	}

	/**
	 * The robot drives forward (left and right motors) and stops the motor when
	 * suppressed
	 */
	@Override
	public void action() {
		System.out.println("Moving forward");
		suppressed = false;

		/*
		 * robot moves forward (predefined speed) until the behavior is suppressed and
		 * then stops the motors
		 */
		movement.move(300, false);
		while (!suppressed)
			Thread.yield();
		movement.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
