package cleanerBot;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.Port;

public class ColorCalibration {
	private SensorModes colorSensor;
	private SampleProvider colorProvider;
	private float[] colorSample;
	
	private float[][] colors = new float[3][3];

	private void calibrateFloor(float[] floor) {
		colors[0] = floor.clone();
	}

	private void calibrateLine1(float[] line1) {
		colors[1] = line1.clone();
	}

	private void calibrateLine2(float[] line2) {
		colors[2] = line2.clone();
	}
	
	public ColorCalibration(Port colorPort) {
		colorSensor = new EV3ColorSensor(colorPort);
		colorProvider = ((EV3ColorSensor) colorSensor).getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
	}
	
	public void startCalibration() {
		System.out.println("Show the color of the floor, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		calibrateFloor(colorSample);

		System.out.println("Show the color of the first line, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		calibrateLine1(colorSample);

		System.out.println("Show the color of the second line, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		calibrateLine2(colorSample);
	}
	
	public int testColor() {
		colorProvider.fetchSample(colorSample, 0);
		

		float[] results = new float[3];
		double[] endResults = new double[3];

		for (int i = 0; i < colors.length; i++) {
			for (int n = 0; n < colors[i].length; n++) {
				results[n] = colorSample[n] - colors[i][n];
				results[n] *= results[n];
			}
			endResults[i] = Math.sqrt(results[0] + results[1] + results[2]);
		}

		int closest = 0;

		for (int i = 1; i < endResults.length; i++) {

			if (endResults[i] < endResults[closest]) {
				closest = i;
			}
		}

		return closest;
	}
}
