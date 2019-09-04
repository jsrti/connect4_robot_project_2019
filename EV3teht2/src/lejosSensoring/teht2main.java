package lejosSensoring;

import lejos.hardware.Button;
import lejos.hardware.Device;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class teht2main {
	public static void main(String[] args) {
		
		RegulatedMotor motor1 = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.C);
		motor1.synchronizeWith(new RegulatedMotor[] { motor2 });
		
		Port wallPort = LocalEV3.get().getPort("S4");
		SensorModes wallSensor = new EV3IRSensor(wallPort);
		SampleProvider distance = ((EV3IRSensor)wallSensor).getDistanceMode();
		float[] wallSample = new float[distance.sampleSize()];
		
		Port colorPort = LocalEV3.get().getPort("S1");
		SensorModes colorSensor = new EV3ColorSensor(colorPort);
		SampleProvider colorProvider = ((EV3ColorSensor)colorSensor).getRGBMode();
		float[] colorSample = new float[colorProvider.sampleSize()];
		
		int colorMultiplier = 765;
		boolean isWallSeen = false;
		boolean isLooping = true;
		ColorCalibration colorTester = new ColorCalibration();
		
		System.out.println("Show the color of the floor, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		colorTester.calibrateFloor(colorSample);
		
		System.out.println("Show the color of the first line, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		colorTester.calibrateLine1(colorSample);
		
		System.out.println("Show the color of the second line, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		colorTester.calibrateLine2(colorSample);
		
		System.out.println("Press the button to start");
		Button.ENTER.waitForPressAndRelease();
		move(motor1, motor2, 400, 400, 0);
		
		while(isLooping) {
			
			Delay.msDelay(1000);
			colorProvider.fetchSample(colorSample, 0);
			distance.fetchSample(wallSample, 0);
			
			int testResult = colorTester.testColor(colorSample);
			
			switch(testResult) {
			case 1: 
				if (isWallSeen) {
					stopMoving(motor1, motor2);
					isLooping = false;
				}
				System.out.println("First line seen");
				break;
			case 2:
				System.out.println("Second line seen");
			}
			
			if(!isWallSeen && wallSample[0] <= 10) {
				isWallSeen = true;
				move(motor1, motor2, 400, 400, 1);
			}
			
			Delay.msDelay(100);
		}
		((Device) wallSensor).close();
		((Device) colorSensor).close();	
		motor1.close();
		motor2.close();
	}
	
	private static void move(RegulatedMotor m1, RegulatedMotor m2, int leftSpeed, int rightSpeed, int changeDirection) {
		m1.setSpeed(leftSpeed);
		m2.setSpeed(rightSpeed);
		m1.startSynchronization();
		if(changeDirection == 0) {
			m1.forward();
			m2.forward();
		}else {
			m1.backward();
			m2.backward();
		}
		
		m1.endSynchronization();
	}
	
	private static void stopMoving(RegulatedMotor m1, RegulatedMotor m2) {
		m1.startSynchronization();
		m1.stop();
		m2.stop();
		m1.endSynchronization();
	}
}
