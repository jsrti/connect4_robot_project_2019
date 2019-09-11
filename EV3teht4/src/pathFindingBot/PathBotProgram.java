package pathFindingBot;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class PathBotProgram {
	public static void main(String[] args) {
		final double HALKAISIJA = 3.2f;
		final double RAIDELEVEYS = 17.4f;
		final float robotRadius = 10f;
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilotti = new DifferentialPilot(HALKAISIJA, RAIDELEVEYS, mA, mC);
		Kartta kartta = new Kartta();
		// NaytteenOtto naytteenOtto = new NaytteenOtto();
		Navigointi navigointi = new Navigointi(kartta.createDefaultMap(), pilotti, robotRadius);

		navigointi.lisaaPiste(12, 11);
		navigointi.lisaaPiste(78, 11);
		navigointi.lisaaPiste(39, 84);
		navigointi.lisaaPiste(43, 0);

		navigointi.aloitaNavigointi();

	}
}
