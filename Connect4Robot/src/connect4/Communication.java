package connect4;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Sound;

/**
 * Class contains methods for controlling the data streams (sending and
 * receiving data) between the robot and PC (robot side)
 * 
 * @author Pietari J�rvi, Olli Kaivola, Jetro Saarti, Kim Widberg
 *
 */
public class Communication {

	private ServerSocket serveri;
	private Socket s;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream oin = null;

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
			oin = new ObjectInputStream(in);
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
	 * Sends gameboard array to PC
	 * 
	 * @param gameBoardArray
	 */
	/*
	public void sendGameboardArray(int[][] gameBoardArray) {
		try {
			oos.writeObject(gameBoardArray);
			oos.flush();
			System.out.println("Gameboard sent");
		} catch (IOException e) {
			System.out.println("Couldn't send gameboard");
		}
	}
	*/
	/**
	 * Receives point coordinates sent from the PC
	 * 
	 * @return
	 */
	/*
	public Point receiveDropPoint() {
		Point point = new Point(0, 0);
		try {
			point = (Point) oin.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return point;
	}
	*/

	/**
	 * 
	 * @return
	 */
	public int receiveGamePieceX() {
		int gamePieceX = 0;

		try {
			gamePieceX = in.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gamePieceX;
	}

	public void sendGamePieceX(int gamePieceX) {
		try {
			out.writeInt(gamePieceX);
			out.flush();
		} catch (IOException e) {
			System.err.println(e);
			System.out.println("Failed to send game piece");
		}
	}
}
