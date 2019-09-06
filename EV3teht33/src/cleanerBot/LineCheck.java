package cleanerBot;

import lejos.robotics.subsumption.Behavior;

public class LineCheck implements Behavior {
	private volatile boolean suppressed = false;
	ColorTester colorSensor;
	private Movement movement;

	public LineCheck(ColorTester colorSensor, Movement movement) {
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
		System.out.print("Line detected");
		suppressed = false;
		movement.tankTurn(300, 300, true);
		Delay.msDelay(2000);
		if(suppressed) {
			movement.stop();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
