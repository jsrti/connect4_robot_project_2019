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
import lejos.utility.Delay;

public class Main {
	public static void main(String[] args) {
		Arbitrator arbitrator;
		ArrayList<Behavior> behaviors = new ArrayList<>();

		// Gets the sensors and motors from the ports
		Port colorPort = LocalEV3.get().getPort("S1");
		Port IRPort = LocalEV3.get().getPort("S4");
		Port motorPortLeft = MotorPort.B;
		Port motorPortRight = MotorPort.C;

		// Initializes the needed classes
		ColorTester colorCalibrator = new ColorTester(colorPort);
		DistanceSensor distanceSensor = new DistanceSensor(IRPort);

		// Testing that the IR sensor works properly
		int rangeMin = 1;
		int rangeMax = 10;
		boolean testOK = false;
		while (!testOK) {
			System.out.printf("Press ENTER near a wall (%d-%d cm)", rangeMin, rangeMax);
			Button.ENTER.waitForPressAndRelease();
			testOK = distanceSensor.testSensorRange(rangeMin, rangeMax);
		}
		if (testOK) {
			System.out.println("IR range test OK");
		}

		Movement movement = new Movement(motorPortLeft, motorPortRight);
		// Testing if motors are connected to the ports. Loops if one or both of the
		// motors are not detected.
		boolean motorPortTest = false;
		while (!motorPortTest) {
			System.out.println("Testing motor ports");
			if (movement.testMotorPorts()) {
				System.out.println("\nMotor port test ok\n");
				motorPortTest = true;
			} else {
				System.out.println("Check connection of motors");
				Delay.msDelay(2000);
			}
		}
		DriveForward driveForward = new DriveForward(movement);
		LineCheck lineCheck = new LineCheck(colorCalibrator, movement);
		ObstacleCheck obstacleCheck = new ObstacleCheck(distanceSensor, movement);
		EmergencyStop emergencyStop = new EmergencyStop();

		// Adds the behaviors in the order of importance from least to most
		behaviors.add(driveForward);
		behaviors.add(lineCheck);
		behaviors.add(obstacleCheck);
		behaviors.add(emergencyStop);

		Behavior[] behaviorArray = behaviors.toArray(new Behavior[behaviors.size()]);

		arbitrator = new Arbitrator(behaviorArray);

		// Starting sequence
		colorCalibrator.startCalibration();
		System.out.println("Please test the emergency stop button. (Down)");
		Button.DOWN.waitForPressAndRelease();
		System.out.println("yep, it works");
		System.out.println("Press ENTER to start");
		Button.ENTER.waitForPressAndRelease();
		// Starts the bot's default cycle
		arbitrator.go();

		((Device) colorPort).close();
		((Device) IRPort).close();
		((Device) motorPortLeft).close();
		((Device) motorPortRight).close();
	}
}
