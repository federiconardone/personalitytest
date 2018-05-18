package pckUtilities;

import java.io.FileNotFoundException;

/*
 * The program data are stored in a text file and not in a database; the user can 
 * easily modify and corrupt it. Due to this reason, at the beginning of the file is
 * a special header with an hash key calculated from all info under this heading.
 * The hash system is continuously used: each program start, data deletion, new
 * test addition, restore data and first application start. In each phase, the
 * hash is checked and if not correct the application suggest to the user
 * the complete restore of the data file. If the data will restored, automatically
 * the header is re-created. The hash is calculated from each char code of each
 * character in the file and with some mathematical operations on this code.
 */
public class HashMaker {
	
	//First version of the method, with a given param: the normal hash method
	public static long getHash(String [][] listResult) throws FileNotFoundException {
		
		long currentHash = 0;
		String tempOutput = "";
		
		int listLen = listResult.length;
		
		for (int i = 0; i < listLen; i ++) {
			for (int j = 0; j < 6; j ++) {
				
				tempOutput += listResult[i][j];
			}
		}
		for (int k = 0; k < tempOutput.length(); k ++) {
			currentHash += (int)tempOutput.charAt(k);
		}
		currentHash = (long)((Math.pow((int)currentHash, 3) + 7) * 15);
		
		return currentHash;
	}
	
	//Second version of the method, whitout param: the default hash creation if the file is empty
	public static long getHash() throws FileNotFoundException {
		
		long currentHash = 0;
		char currentChar = ' ';
		String StartWord = "--------------------Start_String_To_Hash_Without_Data-----------------";
		
		for (int k = 0; k < StartWord.length(); k ++) {
			
			currentChar = StartWord.charAt(k);
			currentHash += (Math.pow((int)currentChar, 10) + 7) * 80;
		}
		return currentHash;
	}
}
