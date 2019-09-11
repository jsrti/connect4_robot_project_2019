package pathFindingBot;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Point;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;

import java.util.ArrayList;
import java.util.List;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class PathBotProgram {
	public static void main(String[] args) {
		final double HALKAISIJA = 3.15f;
		final double RAIDELEVEYS = 17.8f;
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilotti = new DifferentialPilot(HALKAISIJA, RAIDELEVEYS, mA, mC);

		// pilotti.rotate(3 * 360);
		// pilotti.travel(80);
		// pilotti.rotate(3 * 360);
		// Button.ENTER.waitForPressAndRelease();

		// luodaan kartta (LineMap), alkusijainti (Pose) ja kohde (Waypoint)
		Rectangle suorakulmio = new Rectangle(0, 0, 88, 135);
		Line[] janat = new Line[10];
		// rajaavan suorakulmion sivut
		janat[0] = new Line(0, 0, 30, 0); // alareuna vasen
		janat[1] = new Line(53, 0, 88, 0); // alareuna oikea
		janat[2] = new Line(0, 0, 0, 135); // Vasen pitkä seinä
		janat[3] = new Line(88, 0, 88, 135); // Oikea pitkä seinä
		janat[4] = new Line(0, 135, 88, 135); // yläreuna seinä
		// väliseinät
		janat[5] = new Line(28, 0, 28, 33); // vasen lähtö seinä
		janat[6] = new Line(56, 33, 88, 33); // oikea väliseinä
		janat[7] = new Line(0, 64, 53, 64); // pitkä keskiseinä
		janat[8] = new Line(53, 63, 53, 102); // lyhyt pystyseinä
		janat[9] = new Line(22, 96, 56, 96); // lyhyt yläkeskiseinä

		LineMap kartta = new LineMap(janat, suorakulmio);

		Navigator navi = new Navigator(pilotti);
		List<Waypoint> wayPoints = new ArrayList<>();
		wayPoints.add(new Waypoint(12, 12));
		wayPoints.add(new Waypoint(79, 11));
		wayPoints.add(new Waypoint(43, 80));
		wayPoints.add(new Waypoint(43, 0));

		ShortestPathFinder polunEtsija = new ShortestPathFinder(kartta);
		polunEtsija.lengthenLines(10);
		Pose alkupiste = new Pose(43, 0, 90);
		navi.getPoseProvider().setPose(alkupiste);
		for (int i = 0; i < wayPoints.size(); i++) {
			try {
				Path polku = polunEtsija.findRoute(navi.getPoseProvider().getPose(), wayPoints.get(i));
				navi.setPath(polku);
				navi.followPath();
				navi.waitForStop();
				Sound.twoBeeps();
			} catch (DestinationUnreachableException e) {
				System.out.println("Destination unreachable");
				Button.ENTER.waitForPressAndRelease();
			}
		}

	}
}
