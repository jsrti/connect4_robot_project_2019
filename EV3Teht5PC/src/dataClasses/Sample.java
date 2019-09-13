package dataClasses;

import java.io.Serializable;
import java.util.Date;

public class Sample implements Serializable {
	private int x;
	private int y;
	private long currentTimeMillis;
	private int waypointNumber;
	
	public Sample(int x, int y, int waypointNumber, long currentTimeMillis) {
		this.x = x;
		this.y = y;
		this.waypointNumber = waypointNumber;
		this.currentTimeMillis = currentTimeMillis;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public long getcurrentTimeMillis() {
		return currentTimeMillis;
	}

	public int getWaypointNumber() {
		return waypointNumber;
	}
	
	@Override
	public String toString() {
		Date date = new Date(currentTimeMillis);
		String returnable = "#" + waypointNumber + 1;
		returnable += " X: " + x;
		returnable += " Y: " + y;
		returnable += " Time: " + date;
		return returnable;
	}
}
