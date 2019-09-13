package pcRobotHost;

import java.io.Serializable;

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
}
