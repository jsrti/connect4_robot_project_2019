package cleanerBot;

import lejos.robotics.RegulatedMotor;

/**
 * Class that controls the motor functions (left and right motor); moving,
 * turning and stopping the motors.
 * 
 * @author jetro, kim, pietari, olli
 *
 */
public class Movement {
	private RegulatedMotor motor1; // left motor
	private RegulatedMotor motor2; // right motor
	private boolean movingForward = true; // true when the robot is currently moving forward

	/**
	 * Requires the references to the left and right motors. Activating the motor
	 * synchronisation
	 * 
	 * @param motor1 left motor
	 * @param motor2 right motor
	 */
	public Movement(RegulatedMotor motor1, RegulatedMotor motor2) {
		this.motor1 = motor1;
		this.motor2 = motor2;
		motor1.synchronizeWith(new RegulatedMotor[] { motor2 });
	}

	/**
	 * Robot starts moving. Motor speeds are set separately so the robot can drive
	 * forward (same motor speeds) or turn. Current moving direction can be changed.
	 * 
	 * @param leftSpeed       left motor speed
	 * @param rightSpeed      right motor speed
	 * @param changeDirection robot changes direction if true
	 */
	public void move(int leftSpeed, int rightSpeed, boolean changeDirection) {
		motor1.setSpeed(leftSpeed);
		motor2.setSpeed(rightSpeed);

		// motor controls between startSynchronization() and endSynchronization() are
		// synchronised.
		motor1.startSynchronization();
		if (changeDirection) {
			movingForward = !movingForward;
		}

		if (movingForward) {
			motor1.forward();
			motor2.forward();
		} else {
			motor1.backward();
			motor2.backward();
		}

		// motors start after the synchronisation ends
		motor1.endSynchronization();
	}

	/**
	 * The robot turns in place by rotating the left and right motors to the
	 * opposite directions. Turn direction is defined with the parameter.
	 * 
	 * @param leftSpeed  left motor speed
	 * @param rightSpeed right motor speed
	 * @param leftTurn   turns left if true, turns right if false
	 */
	public void tankTurn(int leftSpeed, int rightSpeed, boolean leftTurn) {

		motor1.setSpeed(leftSpeed);
		motor2.setSpeed(rightSpeed);
		motor1.startSynchronization();

		if (leftTurn) {
			motor1.backward();
			motor2.forward();
		} else {
			motor1.forward();
			motor2.backward();
		}

		motor1.endSynchronization();
	}

	/**
	 * Stops both motors (left and right) at the same time.
	 */
	public void stop() {
		motor1.startSynchronization();
		motor1.stop();
		motor2.stop();
		motor1.endSynchronization();
	}
}
