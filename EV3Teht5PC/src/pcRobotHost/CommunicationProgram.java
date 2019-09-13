package pcRobotHost;

import java.io.IOException;

import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class CommunicationProgram {

	public static void main(String[] args) {
		CommunicationPC communication = new CommunicationPC();
		MapPC map = new MapPC();
		Pose startingPose = new Pose(43,0,90);
		Waypoint waypoint = new Waypoint(39,84);
		
		communication.openConnection();
		communication.sendLineMap(map.createDefaultMap());
		communication.sendPose(startingPose);
		communication.sendWaypoint(waypoint);
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		communication.closeConnection();
	}
}
