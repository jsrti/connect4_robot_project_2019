package cleanerBot;

import lejos.hardware.Button;
import lejos.hardware.Device;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Main {
	public static void main(String[] args) {		
		Port colorPort = LocalEV3.get().getPort("S1");
		Port IRPort = LocalEV3.get().getPort("S4");
		RegulatedMotor motor1 = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.C);
		
		ColorCalibration colorCalibrator = new ColorCalibration(colorPort);
		DistanceSensor distanceSensor = new DistanceSensor(IRPort);
		
		System.out.println("Press the button to start");
		Button.ENTER.waitForPressAndRelease();
		
		colorCalibrator.startCalibration();
		int testedColor = colorCalibrator.testColor();
		float distance = distanceSensor.distance();
		
		((Device) colorPort).close();
		((Device) IRPort).close();
		motor1.close();
		motor2.close();
	}
}
