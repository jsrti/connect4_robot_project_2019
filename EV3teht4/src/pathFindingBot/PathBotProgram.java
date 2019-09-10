package pathFindingBot;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class PathBotProgram {
	public static void main(String[] args) {
		final double HALKAISIJA = 3.1f;
		final double RAIDELEVEYS = 20.3f;
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilotti = new DifferentialPilot(HALKAISIJA, RAIDELEVEYS, mA, mC);
		// metri eteenp‰in
		pilotti.travel(80);
		// k‰‰nny kolme kierrosta oikealle
		pilotti.rotate(4*360);
		// kaarta pitkin: 50 cm s‰de, 90 asteen keskuskulma
		pilotti.arc(50, 90);
	}
}
