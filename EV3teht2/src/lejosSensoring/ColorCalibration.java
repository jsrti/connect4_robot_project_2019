package lejosSensoring;

public class ColorCalibration {
	private int[][] colors = new int[3][3];
	
	public void calibrateLine1(int[] line1) {
		colors[1] = line1;
	}
	
	public void calibrateLine2(int[] line2) {
		colors[2] = line2;
	}
	
	public void calibrateFloor(int[] floor) {
		colors[0] = floor;
	}
	
	public int testColor(int[] color) {
		
		int[] results = new int[3];
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
