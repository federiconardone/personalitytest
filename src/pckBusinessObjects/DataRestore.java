package pckBusinessObjects;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import pckUtilities.MessageBoxMaker;

/*
 * After a file backup, this class provide to restore. Choosing
 * the desired file, all rows are copied in the internal csv file.
 * This class is used in combination with all hash methods in the
 * application, such that in each data restore the hash is
 * checked.
 */
public class DataRestore extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void RestoreMaker() throws IOException {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int n = fileChooser.showOpenDialog(DataRestore.this);
		
		//Here is an array list because is not indicated the rows number
		ArrayList<String> inputData = new ArrayList<String>();
		
		if (n == JFileChooser.APPROVE_OPTION) {
	          File f = fileChooser.getSelectedFile();
	          System.out.println(f);      
	          
	          //Check the hash value
	          if (!BusinessObject.HashMatcher(f.toString()))
	        	  	MessageBoxMaker.RestoreWrong();
	          else
	          {
		        	  BusinessObject.DeleteResults();
		        	  inputData = BusinessObject.ReadLines(f.toString());
		        	  BusinessObject.DeleteResults();
		        	  BusinessObject.WriteResult(inputData);
		      }
	          System.out.println(BusinessObject.HashMatcher(f.toString()));
	    }
	}
}
