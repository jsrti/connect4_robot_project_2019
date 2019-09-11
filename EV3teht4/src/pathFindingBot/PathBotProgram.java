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
		final double HALKAISIJA = 3.15f;
		final double RAIDELEVEYS = 17.9f;
		RegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor mC = new EV3LargeRegulatedMotor(MotorPort.C);
		DifferentialPilot pilotti = new DifferentialPilot(HALKAISIJA, RAIDELEVEYS, mA, mC);
		// metri eteenp�in
		//pilotti.travel(80);
		// k��nny kolme kierrosta oikealle
		//pilotti.rotate(3 * 360);
		// kaarta pitkin: 50 cm s�de, 90 asteen keskuskulma
		//pilotti.arc(50, 90);
		
		// luodaan kartta (LineMap), alkusijainti (Pose) ja kohde (Waypoint)
		Rectangle suorakulmio = new Rectangle(0, 0, 88, 135);
		Line[] janat = new Line[10];
		// rajaavan suorakulmion sivut
		janat[0] = new Line(0, 0, 30, 0); // alareuna vasen
		janat[1] = new Line(53, 0, 88, 0); // alareuna oikea
		janat[2] = new Line(0, 0, 0, 135); // Vasen pitk� sein�
		janat[3] = new Line(88, 0, 88, 135); // Oikea pitk� sein�
		janat[4] = new Line(0, 135, 88, 135); // yl�reuna sein�
		// v�lisein�t
		janat[5] = new Line(28, 0, 28, 33); // vasen l�ht� sein�
		janat[6] = new Line(56, 33, 88, 33); // oikea v�lisein�
		janat[7] = new Line(0, 64, 53, 64); // pitk� keskisein�
		janat[8] = new Line(53, 63, 53, 102); // lyhyt pystysein�
		janat[9] = new Line(22, 96, 56, 96); // lyhyt yl�keskisein�


		LineMap kartta = new LineMap(janat, suorakulmio);

		Navigator navi = new Navigator(pilotti);
		//navi.addWaypoint(new Waypoint(12,12));
		//navi.addWaypoint(new Waypoint(50,0));
		navi.addWaypoint(new Waypoint(43,80));
		//navi.addWaypoint(new Waypoint(43,0));

		ShortestPathFinder polunEtsija = new ShortestPathFinder(kartta);
		polunEtsija.lengthenLines(10); // pidennet��n kartan viivoja joka suuntaan,jotta
		// robotti mahtuu liikkumaan alueella (oletus: robotti tarvitsee 10 cm
		// tilaa keskipisteens� ulkopuolelle)
		Pose alkupiste = new Pose(43, 0, 90);
		navi.getPoseProvider().setPose(alkupiste);
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
