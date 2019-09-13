package pcRobotHost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

/**
 * PC COMMUNICATION!!!!!!1!!1K1K1o!Ko!K
 * @author Pietari
 *
 */
public class CommunicationPC {
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private Socket s = null;
	private int luku = 0;
	
	public void openConnection() {
		try {
			s = new Socket("10.0.1.1", 1111);
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendWaypoint(Waypoint point){
		try {
			point.dumpObject(out);
			System.out.println("Waypoint dumped");
		}catch(IOException e) {
			System.err.println(e);
			System.out.println("Couldn't dump waypoint");
		}
	}
	
	public void sendLineMap(LineMap map) {
		try {
			map.dumpObject(out);
		}catch(IOException e) {
			System.out.println("Couldn't send map");
			System.err.println(e);
		}
	}
	
	public void sendPose(Pose startingPose) {
		try {
			startingPose.dumpObject(out);
			System.out.println("Starting pose dumped");
		}catch(IOException e) {
			System.out.println("Couldn't dump starting pose");
			System.err.println(e);
		}
	}
}
