package pcRobotHost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import dataClasses.Sample;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

/**
 * 
 * @author Pietari Järvi, Jetro Saarti, Kim Widberg, Olli Kaivola
 *
 */
public class CommunicationPC {
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private ObjectInputStream oIn = null;
	private Socket s = null;
	
	/**
	 * Opens socket connection and data streams
	 */
	public void openConnection() {
		try {
			s = new Socket("10.0.1.1", 1111);
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());
			oIn = new ObjectInputStream(in); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes socket connection
	 */
	public void closeConnection() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start command for robot
	 */
	public void go() {
		try {
			out.writeInt(1);
			out.flush();
		} catch (IOException e) {
			System.err.println(e);
			System.out.println("Failed to send waypoint count");
		}
	}
	
	/**
	 * Robot expects to get the number of waypoints that this method sends as parameter
	 * @param count
	 */
	public void sendWaypointCount(int count) {
		try {
			out.writeInt(count);
			out.flush();
		} catch (IOException e) {
			System.err.println(e);
			System.out.println("Failed to send waypoint count");
		}
	}
	
	/**
	 * Sends a waypoint object to the robot
	 * @param point
	 */
	public void sendWaypoint(Waypoint point){
		try {
			System.out.println(point.x + " " + point.y);
			point.dumpObject(out);
			System.out.println("Waypoint dumped");
		}catch(IOException e) {
			System.err.println(e);
			System.out.println("Couldn't dump waypoint");
		}
	}
	
	/**
	 * Sends a LineMap object to the robot
	 * @param map
	 */
	public void sendLineMap(LineMap map) {
		try {
			map.dumpObject(out);
		}catch(IOException e) {
			System.out.println("Couldn't send map");
			System.err.println(e);
		}
	}
	
	/**
	 * Sends a Pose object to the robot
	 * @param startingPose
	 */
	public void sendPose(Pose startingPose) {
		try {
			startingPose.dumpObject(out);
			System.out.println("Starting pose dumped");
		}catch(IOException e) {
			System.out.println("Couldn't dump starting pose");
			System.err.println(e);
		}
	}
	
	/**
	 * Receives a sample from the robot when the robot reaches a waypoint
	 * @return
	 */
	public Sample receiveSample() {
		Sample s = null;
		try {
			s = (Sample)oIn.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
