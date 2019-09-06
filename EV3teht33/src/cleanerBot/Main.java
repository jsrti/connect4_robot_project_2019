package cleanerBot;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.Device;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	public static void main(String[] args) {		
		Arbitrator arbitrator;
		ArrayList<Behavior> behaviors = new ArrayList<>();
		
		Port colorPort = LocalEV3.get().getPort("S1");
		Port IRPort = LocalEV3.get().getPort("S4");
		RegulatedMotor motor1 = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.C);
		
		ColorTester colorCalibrator = new ColorTester(colorPort);
		DistanceSensor distanceSensor = new DistanceSensor(IRPort);
		Movement movement = new Movement(motor1, motor2);
		
		DriveForward driveForward = new DriveForward(movement);
		LineCheck lineCheck = new LineCheck(colorCalibrator, movement);
		
		behaviors.add(driveForward);
		behaviors.add(lineCheck);
		
		Behavior[]behaviorArray = behaviors.toArray(new Behavior[behaviors.size()]); 
		
		arbitrator = new Arbitrator(behaviorArray);
		
		colorCalibrator.startCalibration();
		System.out.println("Press the button to start");
		Button.ENTER.waitForPressAndRelease();
		arbitrator.go();
		//int testedColor = colorCalibrator.testColor();
		//float distance = distanceSensor.distance();
		
		((Device) colorPort).close();
		((Device) IRPort).close();
		motor1.close();
		motor2.close();
	}
}
