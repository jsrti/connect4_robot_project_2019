package pathFindingBot;

import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;

public class Kartta {

	public LineMap createDefaultMap() {
		Rectangle suorakulmio = new Rectangle(0, 0, 86, 133);
		Line[] lines = new Line[15];
		// rajaavan suorakulmion sivut
		lines[0] = new Line(0, 0, 27, 0); // alareuna vasen
		lines[1] = new Line(55, 0, 86, 0); // alareuna oikea
		lines[2] = new Line(0, 0, 0, 133); // Vasen pitk� sein�
		lines[3] = new Line(86, 0, 86, 133); // Oikea pitk� sein�
		lines[4] = new Line(0, 133, 86, 133); // yl�reuna sein�

		// v�lisein�t
		lines[5] = new Line(27, 0, 27, 33); // vasen l�ht� sein�
		lines[6] = new Line(32, 0, 32, 33);

		lines[7] = new Line(54, 30, 86, 30); // oikea v�lisein�
		lines[8] = new Line(54, 36, 86, 36);

		lines[9] = new Line(0, 62, 55, 62); // pitk� keskisein�
		lines[10] = new Line(0, 68, 55, 68);

		lines[11] = new Line(51, 68, 51, 94); // lyhyt pystysein�
		lines[12] = new Line(57, 68, 57, 94);

		lines[13] = new Line(22, 94, 55, 94); // lyhyt yl�keskisein�
		lines[14] = new Line(22, 106, 55, 106);

		LineMap map = new LineMap(lines, suorakulmio);
		return map;
	}
}
