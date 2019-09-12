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

/**
 * pathfinding and navigation
 * @author Pietari Järvi, Jetro Saarti, Olli Kaivola, Kim Widberg 12.9.2019
 *
 */
public class PathNavigating {

	private List<Waypoint> wayPoints = new ArrayList<>();
	private Navigator navi;
	private ShortestPathFinder pathFinder;
	private Pose startingPose;

	/**
	 * 
	 * @param map LineMap that defines the area where the robot moves
	 * @param pilot control class for robot movement
	 * @param robotRadius defines the space that the robot needs around its center point
	 * @param startingPose
	 */
	public PathNavigating(LineMap map, DifferentialPilot pilot, float robotRadius, Pose startingPose) {
		navi = new Navigator(pilot);
		pathFinder = new ShortestPathFinder(map);
		pathFinder.lengthenLines(robotRadius);
		this.startingPose = startingPose;
	}

	/**
	 * Adds a waypoint to the map. The robot will calculate the shortest possible
	 * path to these waypoints. Uses x and y coordinates as parameters for waypoint
	 * locations.
	 * 
	 * @param x
	 * @param y
	 */
	public void addWaypoint(int x, int y) {
		wayPoints.add(new Waypoint(x, y));
	}

	/**
	 * Sets the starting point to the robot and navigates from waypoint to waypoint
	 * until the final waypoint is reached.
	 */
	public void startNavigating() {
		navi.getPoseProvider().setPose(startingPose);

		for (int i = 0; i < wayPoints.size(); i++) {
			try {
				Path path = pathFinder.findRoute(navi.getPoseProvider().getPose(), wayPoints.get(i));
				navi.setPath(path);
				navi.followPath();
				navi.waitForStop();
				Sound.twoBeeps(); // Taking a sample at a waypoint
			} catch (DestinationUnreachableException e) {
				System.out.println("Destination unreachable");
			}
		}
	}

}
