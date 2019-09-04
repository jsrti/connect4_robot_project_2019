package lejosSensoring;

public class ColorCalibration {
	private float[][] colors = new float[3][3];
	
	public void calibrateFloor(float[] floor) {
		colors[0][0] = floor[0];
		colors[0][1] = floor[1];
		colors[0][2] = floor[2];
	}
	
	public void calibrateLine1(float[] line1) {
		colors[1][0] = line1[0];
		colors[1][1] = line1[1];
		colors[1][2] = line1[2];
	}
	
	public void calibrateLine2(float[] line2) {
		colors[2][0] = line2[0];
		colors[2][1] = line2[1];
		colors[2][2] = line2[2];
	}
	
	public int testColor(float[] color) {
		
		float[] results = new float[3];
		double[] endResults = new double[3];
		
		for (int i = 0; i < colors.length; i++) {
			for (int n = 0; n < colors[i].length; n++) {
				results[n] = color[n] - colors[i][n];			
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
