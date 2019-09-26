package pathFindingBot;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import dataClasses.Sample;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Robot that finds the shortest path to waypoints/goals and tries to avoid obstacles(walls).
 * @author Pietari J�rvi, Jetro Saarti, Kim Widberg, Olli Kaivola 12.9.2019
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
		
		Communication communication = new Communication();
		communication.openConnection();
		
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilot = new DifferentialPilot(DIAMETER, TRACKWIDTH, mA, mC);
		
		//Map map = new Map();
		LineMap lineMap = communication.receiveLineMap();
		Pose startingPose = new Pose();
		startingPose = communication.receivePose();
		
		PathNavigating navigate = new PathNavigating(lineMap, pilot, ROBOTRADIUS, startingPose);

		/*
		(12, 11);
		(78, 11);
		(39, 84);
		(43, 0);
		*/
		
		int waypointCount = communication.receiveWaypointCount();
		System.out.println("count: " + waypointCount);
		
		for(int i = 0; i<waypointCount; i++) {
			navigate.addWaypoint(communication.receiveWaypoint());
		}
		navigate.addWaypoint(new Waypoint(startingPose.getX(), startingPose.getY())); // Returns home

		// Button check before starting pathfinding
		System.out.println("Waiting for start command");
		communication.waitForGoCommand();
		System.out.println("Starting...");

		for (int i = 0; i < navigate.getWaypointCount(); i++) {
			Sample s = navigate.startNavigating(i);
			communication.sendSample(s);
			System.out.println("Found waypoint: " + s.getWaypointNumber());
		}
		
		System.out.println("Press ENTER to exit");
		Button.ENTER.waitForPressAndRelease();
		
	}
}