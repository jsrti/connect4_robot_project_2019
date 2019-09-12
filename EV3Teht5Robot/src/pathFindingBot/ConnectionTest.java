package pathFindingBot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class ConnectionTest {
	public static void main(String[] args) {

		ServerSocket serveri;
		Socket s;
		DataInputStream in = null;
		DataOutputStream out = null;
		int luku = 0;

		try {
			serveri = new ServerSocket(1111);
			System.out.println("waiting for connection");
			s = serveri.accept();
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("CONNECTED");
		
		try {
			do{
				luku = in.readInt(); // odottaa, että saadaan jotain'
				System.out.println("Saatiin luku " + luku);
		
			}while (luku != 5);
		}catch(IOException e) {
			System.err.println(e);
			//System.out.println("fail haha");
		}
		Button.ENTER.waitForPressAndRelease();
		
	}
}
