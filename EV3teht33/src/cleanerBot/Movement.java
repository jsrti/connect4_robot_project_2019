package cleanerBot;

import lejos.hardware.Device;
import lejos.hardware.device.DeviceIdentifier;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * Class that controls the motor functions (left and right motor); moving,
 * turning and stopping the motors.
 * 
 * @author jetro, kim, pietari, olli
 *
 */
public class Movement {
	private RegulatedMotor motorLeft; // left motor
	private RegulatedMotor motorRight; // right motor
	private Port motorPortLeft; // left motor port
	private Port motorPortRight; // right motor port
	private boolean movingForward = true; // true when the robot is currently moving forward

	/**
	 * Requires the references to the left and right motors. Activating the motor
	 * synchronisation
	 * 
	 * @param motorLeft  left motor
	 * @param motorRight right motor
	 */
	public Movement(Port motorPortLeft, Port motorPortRight) {
		this.motorPortLeft = motorPortLeft;
		this.motorPortRight = motorPortRight;
		this.motorLeft = new EV3LargeRegulatedMotor(motorPortLeft);
		this.motorRight = new EV3LargeRegulatedMotor(motorPortRight);
		motorLeft.synchronizeWith(new RegulatedMotor[] { motorRight });
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
		motorLeft.setSpeed(leftSpeed);
		motorRight.setSpeed(rightSpeed);

		// motor controls between startSynchronization() and endSynchronization() are
		// synchronised.
		motorLeft.startSynchronization();
		if (changeDirection) {
			movingForward = !movingForward;
		}

		if (movingForward) {
			motorLeft.forward();
			motorRight.forward();
		} else {
			motorLeft.backward();
			motorRight.backward();
		}

		// motors start after the synchronisation ends
		motorLeft.endSynchronization();
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

		motorLeft.setSpeed(leftSpeed);
		motorRight.setSpeed(rightSpeed);
		motorLeft.startSynchronization();

		if (leftTurn) {
			motorLeft.backward();
			motorRight.forward();
		} else {
			motorLeft.forward();
			motorRight.backward();
		}

		motorLeft.endSynchronization();
	}

	/**
	 * Stops both motors (left and right) at the same time.
	 */
	public void stop() {
		motorLeft.startSynchronization();
		motorLeft.stop();
		motorRight.stop();
		motorLeft.endSynchronization();
	}

	/**
	 * Checks if DeviceIdentifier detects motors connected to ports.
	 * 
	 * @return true if motor ports are connected
	 */
	public boolean testMotorPorts() {
		// Message that is returned if no motors are connected to ports
		String message = "NONE:NONE";
		// Closing motors so DeviceIdentifier can access the ports
		motorLeft.close();
		motorRight.close();
		DeviceIdentifier motorID1 = new DeviceIdentifier(motorPortLeft);
		DeviceIdentifier motorID2 = new DeviceIdentifier(motorPortRight);
		boolean testOk = false;

		if (motorID1.getDeviceSignature(false).equals(message) || motorID2.getDeviceSignature(false).equals(message)) {
			System.out.print("not connected");
		} else {
			testOk = true;
		}
		motorID1.close();
		motorID2.close();
		// Reopening motors for movement
		motorLeft = new EV3LargeRegulatedMotor(motorPortLeft);
		motorRight = new EV3LargeRegulatedMotor(motorPortRight);
		return testOk;
	}
}
