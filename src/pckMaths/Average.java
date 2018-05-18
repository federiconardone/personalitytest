package pckMaths;

import java.io.FileNotFoundException;

/*
 * To create the average chart, this class return the average
 * of the personality value for all users. Is also used to create
 * the deviation chart, passing the output valuye to 'Deviation'
 * class.
 */
public class Average {
	
	public static Float[][] AverageMaker(String[][] paramListSearch) throws FileNotFoundException {
		
		Float [][] paramAverages= new Float[2][5];
		
		for (int i = 0; i < 2; i ++) {	
			
			for (int j = 0; j < 5; j ++ ) 
				
				paramAverages[i][j] = (float)(0.0);
		}
		
		for (int i = 0; i < paramListSearch.length; i ++)
		{
			for (int j = 1; j < 6; j ++) {
				
				paramAverages[0][j-1] += Float.valueOf(paramListSearch[i][j]);	
			}
		}
		
		for (int k = 0; k < 5; k ++)
			paramAverages[0][k] = paramAverages[0][k] / paramListSearch.length;	
		
		return paramAverages;
	}
}
