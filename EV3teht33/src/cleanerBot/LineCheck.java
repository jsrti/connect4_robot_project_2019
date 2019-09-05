package cleanerBot;

import lejos.robotics.subsumption.Behavior;

public class LineCheck implements Behavior {
	private volatile boolean suppressed = false;
	ColorCalibration colorSensor;
	private Movement movement;

	public LineCheck(ColorCalibration colorSensor, Movement movement) {
		this.colorSensor = colorSensor;
		this.movement = movement;
	}
	
	@Override
	public boolean takeControl() {
		int color = colorSensor.testColor();
		if(color>0) {
			return true;
		}
		return false;	
	}

	@Override
	public void action() {
		suppressed = false;
		movement.move(300, 300, 1);
		while(!suppressed) Thread.yield();
		if(suppressed) {
			movement.stop();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
