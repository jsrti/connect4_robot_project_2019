package cleanerBot;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class ObstacleCheck implements Behavior {
	private volatile boolean suppressed = false;
	DistanceSensor distanceSensor;
	private Movement movement;

	public ObstacleCheck(DistanceSensor distanceSensor, Movement movement) {
		this.distanceSensor = distanceSensor;
		this.movement = movement;
	}
	
	@Override
	public boolean takeControl() {
		if(distanceSensor.distance()<10) {
			return true;
		}
		return false;	
	}

	@Override
	public void action() {
		System.out.print("Obstacle detected");
		suppressed = false;
		movement.move(300, 300, true);
		Delay.msDelay(1000);
		movement.tankTurn(300, 300, true);
		Delay.msDelay(1000);
		movement.move(300, 300, true);
		if(suppressed) {
			movement.stop();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
