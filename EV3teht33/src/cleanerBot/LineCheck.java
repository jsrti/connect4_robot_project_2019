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
		if (color > 0) { //If the color is closer to the color of the line returns true
			return true;
		}
		return false;
	}

	@Override
	public void action() {
		// Gets the time so that the turn can be timed in a while sentence instead of a using Delay
		startTime = System.currentTimeMillis(); 
		System.out.println("Line detected"); // Feedback for the user
		suppressed = false;
		movement.tankTurn(300, 300, true); // Turns so that it wont go over the line
		// Loops until arbitrator suppresses it or 2.6 seconds which is the time the bot takes to turn almost 180 degrees
		while (!suppressed && (System.currentTimeMillis() - startTime) < 2600) Thread.yield();
		if (suppressed) {
			movement.stop();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
