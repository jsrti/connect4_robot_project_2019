package pathFindingBot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;

import dataClasses.Sample;
import lejos.hardware.Sound;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

/**
 * Class contains methods for controlling the data streams (sending and
 * receiving data) between the robot and PC (robot side)
 * 
 * @author Pietari Järvi, Olli Kaivola, Jetro Saarti, Kim Widberg
 *
 */
public class Communication {

	private ServerSocket serveri;
	private Socket s;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private ObjectOutputStream oos = null;

	/**
	 * Opens server socket connection and data streams and waits for the PC to
	 * connect
	 */
	public void openConnection() {
		try {
			serveri = new ServerSocket(1111);
			System.out.println("waiting for connection");
			Sound.beep();
			s = serveri.accept();
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			oos = new ObjectOutputStream(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("CONNECTED");
	}

	/**
	 * Waits for the start command from PC
	 */
	public void waitForGoCommand() {
		try {
			in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sends sample from waypoint to PC
	 * 
	 * @param s
	 */
	public void sendSample(Sample s) {
		try {
			oos.writeObject(s);
			oos.flush();
			System.out.println("Sample sent");
		} catch (IOException e) {
			System.out.println("Couldn't send sample");
		}
	}

	/**
	 * Receives the amount of waypoints that PC is going to send
	 * 
	 * @return
	 */
	public int receiveWaypointCount() {
		int count = 0;

		try {
			count = in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * Receives waypoint coordinates sent from the PC
	 * 
	 * @return
	 */
	public Waypoint receiveWaypoint() {
		Waypoint point = new Waypoint(0, 5);
		try {
			point.loadObject(in);
			System.out.println("X " + point.x);
			System.out.println("Y " + point.y);
		} catch (IOException e) {
			System.out.println("Couldn't load waypoints");
			System.err.println(e);
		}
		return point;
	}

	/**
	 * Receives the LineMap object sent from the PC
	 * 
	 * @return
	 */
	public LineMap receiveLineMap() {
		LineMap map = new LineMap();
		try {
			map.loadObject(in);
			System.out.println("Map loaded");
		} catch (IOException e) {
			System.out.println("Couldn't load map");
			System.err.println(e);
		}
		return map;
	}

	/**
	 * Receives the Pose object sent from the PC
	 * 
	 * @return
	 */
	public Pose receivePose() {
		Pose startingPose = new Pose();
		try {
			startingPose.loadObject(in);
			System.out.println("Starting pose loaded");
		} catch (IOException e) {
			System.out.println("Couldn't load starting pose");
			System.err.println(e);
		}
		return startingPose;
	}
}
