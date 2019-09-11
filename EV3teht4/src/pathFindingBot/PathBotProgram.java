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
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class PathBotProgram {
	public static void main(String[] args) {
		final double HALKAISIJA = 3.1f;
		final double RAIDELEVEYS = 20.3f;
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilotti = new DifferentialPilot(HALKAISIJA, RAIDELEVEYS, mA, mC);
		// metri eteenp�in
		//pilotti.travel(80);
		// k��nny kolme kierrosta oikealle
		//pilotti.rotate(4 * 360);
		// kaarta pitkin: 50 cm s�de, 90 asteen keskuskulma
		//pilotti.arc(50, 90);

		// luodaan kartta (LineMap), alkusijainti (Pose) ja kohde (Waypoint)
		Rectangle suorakulmio = new Rectangle(0, 0, 150, 100);
		Line[] janat = new Line[8];
		// rajaavan suorakulmion sivut
		janat[0] = new Line(0, 0, 150, 0);
		janat[1] = new Line(150, 0, 150, 100);
		janat[2] = new Line(0, 100, 150, 100);
		janat[3] = new Line(0, 0, 0, 100);
		// pystysuora este
		janat[4] = new Line(30, 20, 30, 60);
		// kolmionmuotoinen alue
		janat[5] = new Line(90, 50, 120, 20);
		janat[6] = new Line(120, 20, 130, 50);
		janat[7] = new Line(90, 50, 130, 50);
		LineMap kartta = new LineMap(janat, suorakulmio);

		Navigator navi = new Navigator(pilotti);
		navi.addWaypoint(new Waypoint(20,10));

		ShortestPathFinder polunEtsija = new ShortestPathFinder(kartta);
		polunEtsija.lengthenLines(10); // pidennet��n kartan viivoja joka suuntaan,jotta
		// robotti mahtuu liikkumaan alueella (oletus: robotti tarvitsee 10 cm
		// tilaa keskipisteens� ulkopuolelle)
		Pose alkupiste = new Pose(10, 10, 0);
		try {
			Path polku = polunEtsija.findRoute(alkupiste, navi.getWaypoint());
			navi.setPath(polku);
			navi.followPath();
			navi.waitForStop();
		} catch (DestinationUnreachableException e) {
			System.out.println("EI TOIMI!!!!!!");
			Button.ENTER.waitForPressAndRelease();
		}
	}
}
