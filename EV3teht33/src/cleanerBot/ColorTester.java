package cleanerBot;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.Port;
/**
 * For color calibration and testing
 * @author Kim, Jetro, Pietari
 *
 */
public class ColorTester {
	private SensorModes colorSensor;
	private SampleProvider colorProvider;
	private float[] colorSample;
	
	// An array for the calibrated colors
	private float[][] colors = new float[3][2];

	/**
	 * Saves the floor color so it can be compared to the color fetched in colorTest along with the line color
	 * @param floor
	 */
	private void calibrateFloor(float[] floor) {
		//Clones the array so that the calibrated color wont change with the given array
		colors[0] = floor.clone();
		System.out.println("- ok");
	}
	
	/**
	 * Saves the line color so it can be compared to the color fetched in colorTest along with the floor color
	 * @param line1
	 */
	private void calibrateLine1(float[] line1) {
		//Clones the array so that the calibrated color wont change with the given array
		colors[1] = line1.clone();
		System.out.println("- ok");
	}
	
	public ColorTester(Port colorPort) {
		// Setting up the color sensor for use
		colorSensor = new EV3ColorSensor(colorPort);
		colorProvider = ((EV3ColorSensor) colorSensor).getRGBMode();
		colorSample = new float[colorProvider.sampleSize()];
	}
	
	/**
	 * Runs the calibration sequence for the color sensor
	 */
	public void startCalibration() {
		// After the button is pressed takes the current color the sensor sees and
		// calls the calibration method for the floor
		System.out.print("Calibrate floor ");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		calibrateFloor(colorSample);

		// After the button is pressed takes the current color the sensor sees and
		// calls the calibration method for the line
		System.out.print("Calibrate line ");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		calibrateLine1(colorSample);
	}
	
	/**
	 * Compares the given color to the 2 colors given during calibration.
	 * @return integer, either 0 indicating that the color fetched is closer to the floor 
	 *  and 1 indicating it was closer to the line
	 */
	public int testColor() {
		colorProvider.fetchSample(colorSample, 0);

		// Arrays needed to save intermediate values
		float[] results = new float[3];
		double[] endResults = new double[3];
		
		// Repeats for every color value in each calibrated color
		for (int i = 0; i < colors.length; i++) {
			for (int n = 0; n < colors[i].length; n++) {
				// Calculations for checking which color is the closest
				results[n] = colorSample[n] - colors[i][n];
				results[n] *= results[n];
			}
			endResults[i] = Math.sqrt(results[0] + results[1] + results[2]);
		}

		int closest = 0;

		// Checks which one of the colors was the closest and returns the index indicating it
		for (int i = 1; i < endResults.length; i++) {

			if (endResults[i] < endResults[closest]) {
				closest = i;
			}
		}

		return closest;
	}
}
