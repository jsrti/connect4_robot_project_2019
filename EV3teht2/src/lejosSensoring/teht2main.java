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
		/*
		Port port = LocalEV3.get().getPort("S4");
		SensorModes sensor = new EV3IRSensor(port);
		SampleProvider distance = ((EV3IRSensor)sensor).getDistanceMode();
		float[] sample = new float[distance.sampleSize()];
		while(true) {
			distance.fetchSample(sample, 0);
			System.out.println(sample[0]);
		}
		*/
		int colorMultiplier = 765;
		int max = 255;
		
		Port colorPort = LocalEV3.get().getPort("S1");
		SensorModes colorSensor = new EV3ColorSensor(colorPort);
		SampleProvider colorProvider = ((EV3ColorSensor)colorSensor).getRGBMode();
		float[] colorSample = new float[colorProvider.sampleSize()];
		while(true) {
			System.out.println("Waiting for a button press");
			Button.ENTER.waitForPressAndRelease();
			colorProvider.fetchSample(colorSample, 0);

			int r = Math.round(colorSample[0]*colorMultiplier);
			int g = Math.round(colorSample[1]*colorMultiplier);
			int b = Math.round(colorSample[2]*colorMultiplier);

			int rv = max - r;
			int gv = max - g;
			int bv = max - b;
			
			System.out.println(r + " " + g + " " + b);
			if (rv < gv && rv < bv) {
				System.out.println("red");
			}
			else if (gv < rv && gv < bv) {
				System.out.println("green");
			}
			else if (bv < rv && bv < gv) {
				System.out.println("blue");
			}
			
			
			Delay.msDelay(1000);
		}
	}
}
