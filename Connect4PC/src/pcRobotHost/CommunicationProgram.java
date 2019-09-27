package pcRobotHost;

import java.util.Scanner;

/**
 * PC main class
 * @author Pietari J�rvi, Jetro Saarti, Kim Widberg, Olli Kaivola
 *
 */
public class CommunicationProgram {

	public static void main(String[] args) {
		CommunicationPC communication = new CommunicationPC();
		Scanner userInput = new Scanner(System.in);
		
		communication.openConnection();
		
		System.out.println("Press enter to start");
		userInput.nextLine();
		communication.go();
		
		userInput.close();
		communication.closeConnection();
	}
}
