package pathFindingBot;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Robot that finds the shortest path to waypoints/goals and tries to avoid obstacles(walls).
 * @author Pietari Järvi, Jetro Saarti, Kim Widberg, Olli Kaivola
 *
 */
public class PathBotProgram {
	public static void main(String[] args) {
		// Diameter of the robot's wheel used in movement calibration
		final double DIAMETER = 3.2f;
		// Robot's track width used in movement calibration
		final double TRACKWIDTH = 18.0f;
		// defines the area around the robot's middle point (approximately)
		final float ROBOTRADIUS = 10f;
		// Sets the robots starting position and angle
		Pose startingPose = new Pose(43, 0, 90);

		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilot = new DifferentialPilot(DIAMETER, TRACKWIDTH, mA, mC);
		Map map = new Map();
		PathNavigating navigate = new PathNavigating(map.createDefaultMap(), pilot, ROBOTRADIUS, startingPose);

		// Adding waypoints to the map
		navigate.addWaypoint(12, 11);
		navigate.addWaypoint(78, 11);
		navigate.addWaypoint(39, 84);
		navigate.addWaypoint(43, 0);

		// Button check before starting pathfinding
		System.out.println("Press ENTER to start");
		Button.ENTER.waitForPressAndRelease();

		navigate.startNavigating();
	}
}
