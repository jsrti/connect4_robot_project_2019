package pcRobotHost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
}
