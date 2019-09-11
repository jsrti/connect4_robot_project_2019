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
		final double HALKAISIJA = 3.2f;
		final double RAIDELEVEYS = 17.4f;
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilotti = new DifferentialPilot(HALKAISIJA, RAIDELEVEYS, mA, mC);

		//pilotti.rotate(3 * 360);
		//pilotti.travel(100);
		//Button.ENTER.waitForPressAndRelease();
		//pilotti.rotate(3 * 360);
		//Button.ENTER.waitForPressAndRelease();

		// luodaan kartta (LineMap), alkusijainti (Pose) ja kohde (Waypoint)
		Rectangle suorakulmio = new Rectangle(0, 0, 86, 133);
		Line[] janat = new Line[15];
		// rajaavan suorakulmion sivut
		janat[0] = new Line(0, 0, 27, 0); // alareuna vasen
		janat[1] = new Line(55, 0, 86, 0); // alareuna oikea
		janat[2] = new Line(0, 0, 0, 133); // Vasen pitkä seinä
		janat[3] = new Line(86, 0, 86, 133); // Oikea pitkä seinä
		janat[4] = new Line(0, 133, 86, 133); // yläreuna seinä
		
		// väliseinät
		janat[5] = new Line(27, 0, 27, 33); // vasen lähtö seinä
		janat[6] = new Line(32, 0, 32, 33); 
		
		janat[7] = new Line(54, 30, 86, 30); // oikea väliseinä
		janat[8] = new Line(54, 36, 86, 36); 
		
		janat[9] = new Line(0, 62, 55, 62); // pitkä keskiseinä
		janat[10] = new Line(0, 68, 55, 68); 
		
		janat[11] = new Line(51, 68, 51, 94); // lyhyt pystyseinä
		janat[12] = new Line(57, 68, 57, 94);
		
		janat[13] = new Line(22, 94, 55, 94); // lyhyt yläkeskiseinä
		janat[14] = new Line(22, 102, 55, 102); 
		

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
