package lejosSensoring;

public class ColorCalibration {
	private float[][] colors = new float[3][3];
	
	public void calibrateFloor(float[] floor) {
		colors[0] = floor;
	}
	
	public void calibrateLine1(float[] line1) {
		colors[1] = line1;
	}
	
	public void calibrateLine2(float[] line2) {
		colors[2] = line2;
	}
	
	public int testColor(float[] color) {
		
		System.out.println();
		System.out.println(colors[0][0]+" ");
		System.out.print(colors[0][1]+" ");
		System.out.print(colors[0][2]);
		System.out.println(colors[1][0]+" ");
		System.out.print(colors[1][1]+" ");
		System.out.print(colors[1][2]);
		System.out.println(colors[2][0]+" ");
		System.out.print(colors[2][1]+" ");
		System.out.print(colors[2][2]);
		System.out.println();
		
		float[] results = new float[3];
		double[] endResults = new double[3];
		
		for (int i = 0; i < colors.length; i++) {
			for (int n = 0; n < colors[i].length; n++) {
				//System.out.println(colors[i][n]);
				
				results[n] = color[n] - colors[i][n];
				
				//System.out.println(color[n]);
				//System.out.println(colors[i][n]);
				//System.out.println();	
				//System.out.println(results[n]);
				//System.out.println();	
				
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
		/*
		System.out.println(endResults[0]);
		System.out.println(endResults[1]);
		System.out.println(endResults[2]);
		System.out.println();
		*/
		
		return closest;		
	}
}
