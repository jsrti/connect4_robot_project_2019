package cleanerBot;

import lejos.robotics.RegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;


public class Movement {
	private RegulatedMotor motor1;
	private RegulatedMotor motor2;

	public Movement(RegulatedMotor motor1, RegulatedMotor motor2) {
		this.motor1 = motor1;
		this.motor2 = motor2;
		motor1.synchronizeWith(new RegulatedMotor[] { motor2 });
	}
	
	public void move(int leftSpeed, int rightSpeed, int changeDirection) {
		motor1.setSpeed(leftSpeed);
		motor2.setSpeed(rightSpeed);
		motor1.startSynchronization();
		if (changeDirection == 0) {
			motor1.forward();
			motor2.forward();
		} else {
			motor1.backward();
			motor2.backward();
		}

		motor1.endSynchronization();
	}

	public void stop() {
		motor1.startSynchronization();
		motor1.stop();
		motor2.stop();
		motor1.endSynchronization();
	}
}
