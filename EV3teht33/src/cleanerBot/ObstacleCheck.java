package cleanerBot;

import lejos.robotics.subsumption.Behavior;

/**
 * Behavior for the robot when it (IR sensor) detects an obstacle. The robot
 * moves backwards, turns and continues moving to the original direction
 * 
 * @author jetro, kim, pietari, olli 6.9.2019
 */
public class ObstacleCheck implements Behavior {
	private final int detectionDistance = 10; // obstacle detection distance in centimeters
	private volatile boolean suppressed = false;
	private DistanceSensor distanceSensor; // IR sensor that measures the distance to obstacles/wall
	private Movement movement; // robot movement controls
	private long backStartTime;
	private long turnStartTime;

	/**
	 * 
	 * @param distanceSensor - reference to the IR sensor that measures distance
	 * @param movement       - reference to the class that controls the motors
	 */
	public ObstacleCheck(DistanceSensor distanceSensor, Movement movement) {
		this.distanceSensor = distanceSensor;
		this.movement = movement;
	}

	/**
	 * Taking control when there is an obstacle closer than the defined distance
	 */
	@Override
	public boolean takeControl() {
		if (distanceSensor.distance() < detectionDistance) {
			return true;
		}
		return false;
	}

	/**
	 * Action that happens when the robot sees an obstacle. Robot moves backwards,
	 * turns and continues moving to the original direction
	 */
	@Override
	public void action() {
		System.out.print("Obstacle detected");
		suppressed = false;

		backStartTime = System.currentTimeMillis();

		// moving forward for the defined time
		movement.move(300, 300, true);
		while (!suppressed && (System.currentTimeMillis() - backStartTime) < 1000) {
			Thread.yield();
		}
		turnStartTime = System.currentTimeMillis();
		movement.tankTurn(300, 300, true);

		// turning for the defined time
		while (!suppressed && (System.currentTimeMillis() - turnStartTime) < 1000) {
			Thread.yield();
		}

		// moving to the original direction
		movement.move(300, 300, true);

		if (suppressed) {
			movement.stop();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
