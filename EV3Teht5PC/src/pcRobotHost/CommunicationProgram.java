package pcRobotHost;

import java.io.IOException;

import dataClasses.Sample;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class CommunicationProgram {

	public static void main(String[] args) {
		CommunicationPC communication = new CommunicationPC();
		MapPC map = new MapPC();
		Pose startingPose = new Pose(43,0,90);
		Waypoint waypoint = new Waypoint(12,11);
		Waypoint waypoint1 = new Waypoint(78,11);
		
		communication.openConnection();
		communication.sendLineMap(map.createDefaultMap());
		communication.sendPose(startingPose);
		communication.sendWaypoint(waypoint);
		communication.sendWaypoint(waypoint1);
		
		Sample s = communication.receiveSample();
		System.out.println("Reached waypoint " + s.getWaypointNumber());
		System.out.println("X: " +  s.getX());
		
		s = communication.receiveSample();
		System.out.println("Reached waypoint " + s.getWaypointNumber());
		System.out.println("X: " +  s.getX());
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		communication.closeConnection();
	}
}
