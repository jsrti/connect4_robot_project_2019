package pcRobotHost;

import java.io.IOException;
import java.util.Scanner;

import dataClasses.Sample;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
/**
 * PC main class
 * @author Pietari Järvi, Jetro Saarti, Kim Widberg, Olli Kaivola
 *
 */
public class CommunicationProgram {

	public static void main(String[] args) {
		CommunicationPC communication = new CommunicationPC();
		Scanner userInput = new Scanner(System.in);
		MapPC map = new MapPC();
		Pose startingPose = new Pose(43,0,90);
		
		communication.openConnection();
		communication.sendLineMap(map.createDefaultMap());
		
		boolean correct = false;
		while(!correct)
			try {
				System.out.println("Home X: (Enter empty to use default)");
				String xS = userInput.nextLine();
				if (!xS.equals("")) {
					int x = Integer.parseInt(xS);
					System.out.println("Home Y:");
					int y = userInput.nextInt();
					System.out.println("Starting angle:");
					int angle = userInput.nextInt();
					startingPose = new Pose(x, y, angle);
				}
				communication.sendPose(startingPose);
				correct = true;
			} catch(Exception e) {
				System.out.println("nuh uh, try again");
			}
		
		System.out.println("How many waypoints?");
		int waypointCount;
		try {
			waypointCount = userInput.nextInt();
		} catch(Exception e) {
			System.out.println("What did you just enter? You get 1");
			waypointCount = 1;
		}
		
		communication.sendWaypointCount(waypointCount);
		
		for (int i = 0; i < waypointCount; i++) {
			correct = false;
			while(!correct)
			try {
				System.out.println("#" + (i + 1) +" X:");
				int x = userInput.nextInt();
				System.out.println("#" + (i + 1) +" Y:");
				int y = userInput.nextInt();
				communication.sendWaypoint(new Waypoint(x,y));
				correct = true;
			} catch(Exception e) {
				System.out.println("no, try again");
			}
		}
		
		System.out.println("Press enter to start");
		userInput.nextLine();
		communication.go();
		
		Sample s;
		for (int i = 0; i < waypointCount; i++) {
			s = communication.receiveSample();
			System.out.println(s);
		}
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userInput.close();
		communication.closeConnection();
	}
}
