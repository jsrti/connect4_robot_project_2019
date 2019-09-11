package pathFindingBot;

import java.util.ArrayList;
import java.util.List;
import lejos.hardware.Sound;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;

public class Navigointi {

	private List<Waypoint> wayPoints = new ArrayList<>();
	private Navigator navi;
	private LineMap kartta;
	private float robotRadius;
	private boolean isNavigating = false;

	public Navigointi(LineMap map, DifferentialPilot pilotti, float robotRadius) {
		navi = new Navigator(pilotti);
		this.kartta = map;
		this.robotRadius = robotRadius;
	}

	public boolean isNavigating() {
		return isNavigating;
	}

	public void lisaaPiste(int x, int y) {
		wayPoints.add(new Waypoint(x, y));
	}

	public void stopNavigating() {
		navi.stop();
		isNavigating = false;
	}

	public void jatkaNavigointia() {
		navi.followPath();
		isNavigating = true;
	}

	public void aloitaNavigointi() {
		isNavigating = true;
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
				// naytteenOtto.otaNayte();
			} catch (DestinationUnreachableException e) {
				System.out.println("Destination unreachable");
			}
		}
	}

}
