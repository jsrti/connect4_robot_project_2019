package pathFindingBot;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;

/**
 * Creates a map for navigation
 * @author Pietari Järvi, Kim Widberg, Olli Kaivola, Jetro Saarti 12.9.2019
 *
 */
public class Map {

	/**
	 * Creates a map using an array of lines (walls) with x and y coordinates.
	 * @return predefined map
	 */
	public LineMap createDefaultMap() {
		Rectangle rectangle = new Rectangle(0, 0, 86, 133);
		Line[] lines = new Line[18];
		
		// Outer rectangle walls
		lines[0] = new Line(0, 0, 27, 0);
		lines[1] = new Line(55, 0, 86, 0); 
		lines[2] = new Line(0, 0, 0, 133); 
		lines[3] = new Line(86, 0, 86, 133); 
		lines[4] = new Line(0, 133, 86, 133); 

		// Middle walls
		lines[5] = new Line(27, 0, 27, 35); 
		lines[6] = new Line(32, 0, 32, 35);
		lines[16] = new Line(26, 35, 32, 35);

		lines[7] = new Line(54, 30, 86, 30); 
		lines[8] = new Line(54, 36, 86, 36);
		lines[17] = new Line(56, 31, 56, 36);

		lines[9] = new Line(0, 62, 60, 62);
		lines[10] = new Line(0, 68, 60, 68);

		lines[11] = new Line(51, 68, 51, 97);
		lines[12] = new Line(57, 68, 57, 97);

		lines[13] = new Line(22, 94, 60, 94); 
		lines[14] = new Line(22, 106, 60, 106);
		lines[15] = new Line(22, 94, 22, 106);

		LineMap map = new LineMap(lines, rectangle);
		return map;
	}
}
