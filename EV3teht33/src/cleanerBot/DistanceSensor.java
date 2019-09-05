package cleanerBot;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class DistanceSensor {
	
	private SensorModes wallSensor;
	private SampleProvider distance;
	private float[] wallSample;

	public DistanceSensor(Port IRPort) {
		wallSensor = new EV3IRSensor(IRPort);
		distance = ((EV3IRSensor) wallSensor).getDistanceMode();
		wallSample = new float[distance.sampleSize()];
	}
	
	public float distance() {
		distance.fetchSample(wallSample, 0);
		return wallSample[0];
	}
}
