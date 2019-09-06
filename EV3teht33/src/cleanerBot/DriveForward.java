package cleanerBot;

import lejos.robotics.subsumption.Behavior;

/**
 * Default behavior for the robot - it keeps driving forward when there are no
 * other active behaviors
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
	 * Always ready to take control (default behavior)
	 */
	@Override
	public boolean takeControl() {
		return true;
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
		movement.move(300, 300, false);
		while (!suppressed)
			Thread.yield();
		movement.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
