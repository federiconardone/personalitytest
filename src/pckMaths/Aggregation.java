package pckMaths;

import java.io.FileNotFoundException;

/*
 * It provides the sum of each personality
 * parameter for all users
 */
public class Aggregation {
	
	public static Float[][] AggregationMaker(String[][] paramListSearch) throws FileNotFoundException {
		
		Float[][] paramSum= new Float[3][5];
		
		for (int i = 0; i < 3; i ++) {	
			for (int j = 0; j < 5; j ++ ) 
				paramSum[i][j] = (float)(0.0);
		}
		
		for (int i = 0; i < paramListSearch.length; i ++) {
			
			for (int j = 1; j < 6; j ++ ) {
				
				if (Float.valueOf(paramListSearch[i][j]) < 3)
					paramSum[0][j-1]++;
				else
					if (Float.valueOf(paramListSearch[i][j]) >= 3 && Float.valueOf(paramListSearch[i][j]) <= 5)
						paramSum[1][j-1]++;
					else
						if (Float.valueOf(paramListSearch[i][j]) > 5)
							paramSum[2][j-1]++;
			}
		}
		
		for (int k = 0; k < 3; k ++) {	
			for (int x = 0; x < 5; x ++ ) 
				paramSum[k][x] = paramSum[k][x] / paramListSearch.length * 100;
		}
		
		return paramSum;
	}
}
