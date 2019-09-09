package cleanerBot;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

/**
 * Handles the IRSensor
 * @author Kim, Jetro, Pietari
 *
 */
public class DistanceSensor {

	private SensorModes wallSensor;
	private SampleProvider distance;
	private float[] wallSample;

	public DistanceSensor(Port IRPort) {
		wallSensor = new EV3IRSensor(IRPort);
		distance = ((EV3IRSensor) wallSensor).getDistanceMode();
		wallSample = new float[distance.sampleSize()];
	}

	/**
	 * Fetches the distance from the IR sensor
	 * @return a float indicating the distance measured
	 */
	public float distance() {
		distance.fetchSample(wallSample, 0);
		return wallSample[0];
	}
	
	/**
	 * Testing if the sensor measurement is inside the defined range
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean testSensorRange(float min, float max) {
		float distance = distance();
		if(distance>max||distance<min) {
			System.out.println("Range check failed, check distance.");
			System.out.printf("Sensor output: %.1f", distance);
			return false;
		}
		return true;
	}
}
