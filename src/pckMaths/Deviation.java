package pckMaths;

/*
 * This class provide, starting from the value array and the average values,
 * to give the standard deviation of each personality value. This class
 * is called only after the average calculation.
 */
public class Deviation {
	
	public static Float[][] DeviationMaker(String[][] paramListSearch, Float[][] paramAverages) {
		
		//First step: creates the series of the sum
		for (int i = 0; i < paramListSearch.length; i ++)
		{
			for (int j = 1; j < 6; j ++) {
				
				Float tempAverage =  (float)( Math.pow((double)(Float.valueOf(paramListSearch[i][j]) - paramAverages[0][j-1]), 2));
				paramAverages[1][j-1] +=    tempAverage;
			}
		}
		
		//Second step: provides to the division and the square root
		for (int z = 0; z < 5; z++) {
			paramAverages[1][z] = (float) Math.sqrt((double)  (paramAverages[1][z] / paramListSearch.length));
		}
		
		return paramAverages;
	}
}
