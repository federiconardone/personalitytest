package pckApplication;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import pckGlobalVars.LayoutVars;
import pckUtilities.StringLocalizer;

/*
 * It creates the main frame of the application.
 */
public class PersonalityTest {
	
	private static JFrame frame = new JFrame(StringLocalizer.getAppName());
	
	public PersonalityTest() throws FileNotFoundException {
		
		this.main(null);
	}
	
	public void main(String[] args) throws FileNotFoundException {
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            PersonalityTestPanel panel = new PersonalityTestPanel();
	    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	    	    frame.setPreferredSize(new Dimension (LayoutVars.getMainWidth(),LayoutVars.getMainHeight()));
	    	    frame.setLocation(dim.width/2-LayoutVars.getMainWidth()/2, dim.height/2-LayoutVars.getMainHeight()/2);
	    	    frame.getContentPane().add(panel);
	    	    PersonalityTestMenu.createMenu(frame);
	            
	        frame.pack();
            frame.setVisible(true);       
        }
    }
}
