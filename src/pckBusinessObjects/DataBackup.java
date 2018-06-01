package pckBusinessObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import pckGlobalVars.ProjectVars;
import pckUtilities.MessageBoxMaker;

/*
 * This class provide to export a copy of the data file,
 * giving a name referred to the application and to the
 * current data-time-seconds so each backup has a different
 * file name.
 */
public class DataBackup extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DATA_FILE_PATH = ProjectVars.getDataFilePath();
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
	Date date = new Date();
	private static MessageBoxMaker activeMsg;
	
	@SuppressWarnings("resource")
	public void BackupMaker() throws IOException {
		
		JFileChooser fileChooser = new JFileChooser();
		//Only folder are allowed because the system create the file name itself
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int n = fileChooser.showOpenDialog(DataBackup.this);
		  
		if (n == JFileChooser.APPROVE_OPTION) {
	          File f = fileChooser.getSelectedFile();
	          System.out.println(f);      
	          
	          FileChannel source = new FileInputStream(DATA_FILE_PATH).getChannel();
	          FileChannel dest = new FileOutputStream(f + "/PersonalityTestBackup_" + String.valueOf(dateFormat.format(date)) + ".csv").getChannel();
	          source.transferTo(0, source.size(), dest);
	          source.close();
	          dest.close();
	  		  activeMsg = new MessageBoxMaker("BackupDone");
			  activeMsg.MessageCreator();
	    }
	}
}
