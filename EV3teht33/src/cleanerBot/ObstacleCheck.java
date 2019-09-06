package cleanerBot;

import lejos.robotics.subsumption.Behavior;

public class ObstacleCheck implements Behavior {
	private volatile boolean suppressed = false;
	DistanceSensor distanceSensor;
	private Movement movement;
	long backStartTime;
	long turnStartTime;

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
		
		backStartTime = System.currentTimeMillis();
		movement.move(300, 300, true);
		while(!suppressed && (System.currentTimeMillis()-backStartTime)<1000) {
			Thread.yield();
		}
		turnStartTime = System.currentTimeMillis();
		movement.tankTurn(300, 300, true);
		while(!suppressed && (System.currentTimeMillis()-turnStartTime)<1000) {
			Thread.yield();
		}
		
		if(suppressed) {
			movement.stop();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
