package pckBusinessObjects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import pckGlobalVars.ProjectVars;
import pckUtilities.HashMaker;
import pckUtilities.MessageBoxMaker;
import pckUtilities.StringLocalizer;

/*
 * This class contains all CRUD methods; Is not referred to a database
 * but I followed a similar formalism to make easy the application
 * structure organization. It contains also some specific method for
 * the Hash CRUD, directly connected to the 'hash maker' class. Some method
 * are override, according to the polymorphism, for the case referred to the
 * first operations (i.e. first application start) and to all others.
 * In particular, after the first start (and first data writing), all
 * CRUD ops are referred to all data lines except the first 4 (reserved
 * for the header and for the Hash code)
 */
public class BusinessObject {
	
	private static final String DATA_FILE_PATH = ProjectVars.getDataFilePath();
	private static MessageBoxMaker activeMsg;
	
	public static void WriteResult(String user, Float [] resultValues) throws IOException {
		
		String activeRow = "";
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
		
        activeRow += user + ";";
        for (int i = 0; i < 5; i ++) {
        		activeRow += resultValues[i];
        		if (i < 4) 
        			activeRow += ";";
        }
        
        fileWriter.write(activeRow);
        fileWriter.write(System.getProperty("line.separator"));
        fileWriter.close();
        
        HashUpdate();
	}
	
	public static void WriteResult(ArrayList<String> activeLine) throws IOException {
		
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
		
        for (int i = 0; i < activeLine.size(); i ++) {
        	
        		fileWriter.write(activeLine.get(i));
        		fileWriter.write(System.getProperty("line.separator"));    
        }
        fileWriter.close();
        
	}
	
	public static ArrayList<String> ReadLines(String pathString) throws FileNotFoundException {
		
		ArrayList<String> outputData = new ArrayList<String>();
		 
		Scanner fileReader = new Scanner(new File(pathString));
		while (fileReader.hasNextLine())
			outputData.add(fileReader.nextLine());
		fileReader.close();
		return outputData;	
	}
	
	public static int rowCounter() throws FileNotFoundException {
		
		int rows = 0;
		Scanner fileReader = new Scanner(new File(DATA_FILE_PATH));
		while (fileReader.hasNextLine()) {
			fileReader.nextLine();	
        		rows ++;
        	}
		fileReader.close();
		return rows;	
	}
	
	public static int rowCounter(String filePath) throws FileNotFoundException {
		
		int rows = 0;
		Scanner fileReader = new Scanner(new File(filePath));
		while (fileReader.hasNextLine()) {
			fileReader.nextLine();	
        		rows ++;
        	}
		fileReader.close();
		return rows;	
	}
	
	public static boolean HashCheck() throws FileNotFoundException {
		
		boolean existHash = false;
		Scanner fileReader = new Scanner(new File(DATA_FILE_PATH));
		if (fileReader.hasNextLine())
			existHash = true;
		fileReader.close();
		return existHash;	
	}
	
	public static void HashInitialize() throws IOException {
		
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
		fileWriter.write(StringLocalizer.getFileHead1() + System.getProperty("line.separator"));
		fileWriter.write(StringLocalizer.getFileHead2() + System.getProperty("line.separator"));
		fileWriter.write(HashMaker.getHash() + System.getProperty("line.separator"));		
		fileWriter.write(StringLocalizer.getFileHead2() + System.getProperty("line.separator"));
        fileWriter.close();
	}
	
	public static void HashUpdate() throws IOException {
		
		String HashArgument [][] = ListResults();
		
		DeleteResults();
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(DATA_FILE_PATH, true));
		fileWriter.write(StringLocalizer.getFileHead1() + System.getProperty("line.separator"));
		fileWriter.write(StringLocalizer.getFileHead2() + System.getProperty("line.separator"));
		fileWriter.write(HashMaker.getHash(HashArgument) + System.getProperty("line.separator"));		
		fileWriter.write(StringLocalizer.getFileHead2() + System.getProperty("line.separator"));
		
		String activeRow = "";
		for (int i = 0; i < HashArgument.length; i ++) {
			
			activeRow = "";
			for (int j = 0; j < 6; j ++) {
				
				activeRow += HashArgument[i][j];
		        if (j < 5) 
	        			activeRow += ";";
	        }
	        fileWriter.write(activeRow);
	        fileWriter.write(System.getProperty("line.separator"));
		}
		
        fileWriter.close();
	}
	
	public static boolean HashMatcher() throws FileNotFoundException {
		
		boolean isCorrect = true;
		String fileHash = "";
		
		try {
			
			fileHash = String.valueOf(HashMaker.getHash(ListResults()));
		} catch (Exception e) {
			isCorrect = false;
		}
		
		
		String rowHash = "";
		
		if (rowCounter() < 4)
			isCorrect = false;
		else
		{
			Scanner fileReader = new Scanner(new File(DATA_FILE_PATH));
			for (int i = 0; i < 2; i ++) {
				fileReader.nextLine();
			}
			rowHash = fileReader.nextLine();
			
			System.out.println("Indicated data hash:\t\t" + rowHash);
			System.out.println("Calculated data hash:\t\t" + fileHash);
			
			if (rowHash.equals(fileHash))
				isCorrect = true;
			else
				isCorrect = false;
			
			fileReader.close();
			
			if (isCorrect)
				System.out.println("\nCorrect hash match, data file is genuine");
			else
				System.out.println("\nHash error, data file is corrupted, reset data file");
		}
		return isCorrect;
	}
	
	public static boolean HashMatcher(String filePath) throws FileNotFoundException {
		
		boolean isCorrect = true;
		String fileHash = "";
		
		try {
			
			fileHash = String.valueOf(HashMaker.getHash(ListResults(filePath)));
		} catch (Exception e) {
			isCorrect = false;
		}
		
		
		String rowHash = "";
		
		if (rowCounter(filePath) < 4)
			isCorrect = false;
		else
		{
			Scanner fileReader = new Scanner(new File(filePath));
			for (int i = 0; i < 2; i ++) {
				fileReader.nextLine();
			}
			rowHash = fileReader.nextLine();
			
			System.out.println("Indicated data hash:\t\t" + rowHash);
			System.out.println("Calculated data hash:\t\t" + fileHash);
			
			if (rowHash.equals(fileHash))
				isCorrect = true;
			else
				isCorrect = false;
			
			fileReader.close();
			
			if (isCorrect)
				System.out.println("\nCorrect hash match, data file is genuine");
			else
				System.out.println("\nHash error, data file is corrupted, reset data file");
		}
		return isCorrect;
	}
	
	public static String [][] ListResults() throws FileNotFoundException {
		
		int rowIndex = 0;
		int colIndex = 6;
		int rowOffset = 0;
		Scanner fileReader = new Scanner(new File(DATA_FILE_PATH));
		if (rowCounter() > 3) {
			
			//If exist the header, the system skip it
			rowOffset = 4;
			for (int i = 0; i < 4; i ++) {
				fileReader.nextLine();
			}	
		}
		String [][] resultMatrix = new String[rowCounter() - rowOffset][colIndex];
		while (fileReader.hasNextLine()) {
    		
	    		String[] parts = fileReader.nextLine().split(";");
	    		for (int i = 0; i < colIndex; i ++) {
	    			
	    			try {
	    				resultMatrix[rowIndex][i] = parts[i];
	    			} catch (Exception e) {
	    				resultMatrix = null;
	    			}
	    			
	    		}
	    		rowIndex ++;
		}
		fileReader.close();
        	return resultMatrix;
	}
	
	public static String [][] ListResults(String filePath) throws FileNotFoundException {
		
		int rowIndex = 0;
		int colIndex = 6;
		int rowOffset = 0;
		Scanner fileReader = new Scanner(new File(filePath));
		if (rowCounter(filePath) > 3) {
			
			rowOffset = 4;
			for (int i = 0; i < 4; i ++) {
				fileReader.nextLine();
			}	
		}
		String [][] resultMatrix = new String[rowCounter(filePath) - rowOffset][colIndex];
		while (fileReader.hasNextLine()) {
    		
	    		String[] parts = fileReader.nextLine().split(";");
	    		for (int i = 0; i < colIndex; i ++) {
	    			
	    			try {
	    				resultMatrix[rowIndex][i] = parts[i];
	    			} catch (Exception e) {
	    				resultMatrix = null;
	    			}
	    			
	    		}
	    		rowIndex ++;
		}
		fileReader.close();
        	return resultMatrix;
	}
	
	//Delete results and create a new header
	public static void ResetResults() throws FileNotFoundException {
		
		PrintWriter writer = new PrintWriter(new File(DATA_FILE_PATH));
		writer.print("");
		writer.close();
		activeMsg = new MessageBoxMaker("DelDone");
		activeMsg.MessageCreator();
		
		try {
			BusinessObject.HashInitialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Simply delete result
	public static void DeleteResults() throws FileNotFoundException {
		
		PrintWriter writer = new PrintWriter(new File(DATA_FILE_PATH));
		writer.print("");
		writer.close();
	}
	
	public static boolean CheckUsername(String givenUsername) throws FileNotFoundException {
		
		boolean isValid = true;
		
		Scanner fileReader = new Scanner(new File(DATA_FILE_PATH));
		
		//If exist the header, skips it
		if (rowCounter() > 3) {
			
			for (int i = 0; i < 4; i ++) {
				fileReader.nextLine();
			}
		}
        
		while (fileReader.hasNextLine()) {
            String[] parts = fileReader.nextLine().split(";");
            String presentUser = parts[0];
            if (givenUsername.equals(presentUser))
	            	isValid = false;  
        }
        fileReader.close();
        
		return isValid;
	}
}
