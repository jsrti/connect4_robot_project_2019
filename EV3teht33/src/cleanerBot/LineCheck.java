package cleanerBot;

import lejos.robotics.subsumption.Behavior;

public class LineCheck implements Behavior {
	private volatile boolean suppressed = false;
	private ColorTester colorSensor;
	private Movement movement;
	private long startTime;

	public LineCheck(ColorTester colorSensor, Movement movement) {
		this.colorSensor = colorSensor;
		this.movement = movement;
	}

	@Override
	public boolean takeControl() {
		int color = colorSensor.testColor();
		if (color > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		startTime = System.currentTimeMillis();
		System.out.print("Line detected");
		suppressed = false;
		movement.tankTurn(300, 300, true);
		while (!suppressed && (System.currentTimeMillis() - startTime) < 2600) {
			Thread.yield();
		}
		if (suppressed) {
			movement.stop();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
