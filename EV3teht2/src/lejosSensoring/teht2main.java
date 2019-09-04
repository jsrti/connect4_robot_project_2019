package lejosSensoring;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class teht2main {
	public static void main(String[] args) {
		
		Port wallPort = LocalEV3.get().getPort("S4");
		SensorModes wallSensor = new EV3IRSensor(wallPort);
		SampleProvider distance = ((EV3IRSensor)wallSensor).getDistanceMode();
		float[] wallSample = new float[distance.sampleSize()];
		
		Port colorPort = LocalEV3.get().getPort("S1");
		SensorModes colorSensor = new EV3ColorSensor(colorPort);
		SampleProvider colorProvider = ((EV3ColorSensor)colorSensor).getRGBMode();
		float[] colorSample = new float[colorProvider.sampleSize()];
		System.out.println("Waiting for a button press");
		Button.ENTER.waitForPressAndRelease();
		
		int colorMultiplier = 765;
		boolean isWallSeen = false;
		ColorCalibration colorTester = new ColorCalibration();
		
		System.out.println("Show the color of the floor, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		colorTester.calibrateFloor(colorSample);
		
		System.out.println("Show the color of the first line, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		colorTester.calibrateLine1(colorSample);
		
		System.out.println("Show the color of the second line, and then press the button");
		Button.ENTER.waitForPressAndRelease();
		colorProvider.fetchSample(colorSample, 0);
		colorTester.calibrateLine2(colorSample);
		
		while(true) {
			
			colorProvider.fetchSample(colorSample, 0);
			distance.fetchSample(wallSample, 0);
			
			int testResult = colorTester.testColor(colorSample);
			
			switch(testResult) {
			
			case 1: 
				if (isWallSeen) {
					//stop
				}
				System.out.println("First line seen");
				break;
			case 2:
				System.out.println("Second line seen");
			}
			
			if(wallSample[0] <= 10) {
				isWallSeen = true;
			}
			
			Delay.msDelay(100);
		}
		
	}
}
