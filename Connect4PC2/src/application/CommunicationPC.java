package application;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Pietari J�rvi, Jetro Saarti, Kim Widberg, Olli Kaivola
 *
 */
public class CommunicationPC {
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private ObjectInputStream oIn = null;
	private ObjectOutputStream oos = null;
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
			oos = new ObjectOutputStream(out);
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

	public boolean receiveTurnChange() {
		boolean turnChange = false;
		try {
			turnChange = in.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return turnChange;
	}

	/**
	 * Sends a point object to the robot
	 * 
	 * @param point
	 */

	public void sendDropPoint(Point point) {
		try {
			oos.writeObject(point);
			oos.flush();
			System.out.println("Point sent");
		} catch (IOException e) {
			System.out.println("Couldn't send point");
		}
	}

}
