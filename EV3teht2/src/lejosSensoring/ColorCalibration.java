package lejosSensoring;

public class ColorCalibration {
	private float[][] colors = new float[3][3];
	
	public void calibrateLine1(float[] line1) {
		colors[1] = line1;
	}
	
	public void calibrateLine2(float[] line2) {
		colors[2] = line2;
	}
	
	public void calibrateFloor(float[] floor) {
		colors[0] = floor;
	}
	
	public int testColor(float[] color) {
		
		float[] results = new float[3];
		double[] endResults = new double[3];
		
		for (int i = 0; i < colors.length; i++) {
			for (int n = 0; n < colors[i].length; n++) {
				
				results[n] = Math.round(color[n]) - colors[i][n];
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
