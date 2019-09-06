package cleanerBot;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.motor.EV3LargeRegulatedMotor;


public class Movement {
	private RegulatedMotor motor1;
	private RegulatedMotor motor2;
	boolean movingForward = true;
	long startTime;

	public Movement(RegulatedMotor motor1, RegulatedMotor motor2) {
		this.motor1 = motor1;
		this.motor2 = motor2;
		motor1.synchronizeWith(new RegulatedMotor[] { motor2 });
	}
	
	public void move(int leftSpeed, int rightSpeed, boolean changeDirection) {
		motor1.setSpeed(leftSpeed);
		motor2.setSpeed(rightSpeed);
		motor1.startSynchronization();
		if (changeDirection) {
			movingForward = !movingForward;
		}
		
		if(movingForward) {
			motor1.forward();
			motor2.forward();
		}else {
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
