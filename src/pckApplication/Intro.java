package pckApplication;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pckGlobalVars.*;
import pckUtilities.StringLocalizer;

/*
 * It is the starting point of the application; it contains the method
 * for the language selection
 */
public class Intro {
	
	private static JFrame frame = new JFrame(StringLocalizer.getAppName());
	
	public static void main(String[] args) throws IOException {
		
		//LanguageSelector(); //Suppressed, due to new language system
		SessionVars.setLanguage("en");
		StringLocalizer.TextInitializer();
	    IntroPanel panel = new IntroPanel();
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocationRelativeTo(null);
	    frame.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),LayoutVars.getIntroHeight()));
	    frame.setLocation(dim.width/2-LayoutVars.getIntroWidth()/2, dim.height/2-LayoutVars.getIntroHeight()/2);
	    frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
	}
	
	//@SuppressWarnings("unused")
	public static void LanguageSelector() throws IOException {
		
		String[] boxButtons = {StringLocalizer.getLanguageIT(), StringLocalizer.getLanguageDE(), StringLocalizer.getLanguageEN()};
		String boxTitle = StringLocalizer.getLanguageTitle();
		String boxMessage = StringLocalizer.getLanguageQuestion();
		
		int languageValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[2]);
		switch (languageValue){
		
		case 0:
			SessionVars.setLanguage("it");
			break;
		case 1:
			SessionVars.setLanguage("de");
			break;
		case 2:
			SessionVars.setLanguage("en");
			break;
		}
		
		//This is the only point in the app in which the localized text are defined
		StringLocalizer.TextInitializer();
	}
	
	public static void FrameHider() {
		
		frame.setVisible(false);
	}
}
