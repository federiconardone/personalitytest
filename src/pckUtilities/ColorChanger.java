package pckUtilities;

import java.awt.Color;

/*
 * In the 'sum' statistic chart, each personality info has three different values.
 * Each color (<3, >=3 and <=5, >5) depends from the number: if the number is big 
 * the color is more dark and viceversa. This class provide to make lighter / darker
 * the color basing on the default color for each personality info.
 */
public class ColorChanger {
	
	public static Color ColorFader(Color givenColor, int graphStep) {
		
		Color changedColor = null;
		
		int colorR = givenColor.getRed();
		int colorG = givenColor.getGreen();
		int colorB = givenColor.getBlue();
		
		int editedR = colorR;
		int editedG = colorG;
		int editedB = colorB;
		
		switch (graphStep) {
		case 0:
			editedR -= 50;
			editedG += 50;
			editedB += 50;
			break;
		case 1:
			editedR -= 25;
			editedG += 25;
			editedB += 25;
			break;
		case 2:
			editedR -= 0;
			editedG += 0;
			editedB += 0;
			break;
		}
		
		changedColor = new Color(editedR, editedG, editedB);
		
		return changedColor;
	}
}
