package pcRobotHost;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommunicationProgram {

	public static void main(String[] args) {
		DataOutputStream out = null;
		DataInputStream in = null;
		Socket s;
		try {
			s = new Socket("10.0.1.1", 1111);
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (int i = 1; i <= 5; i++) { // Kirjoitetaan output-virtaan luvut 1-5.
				out.writeInt(i);
				out.flush(); // aina kirjoittamisen jälkeen
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			
		}
	}
}
