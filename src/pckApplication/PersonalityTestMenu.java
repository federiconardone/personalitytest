package pckApplication;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pckBusinessObjects.DataBackup;
import pckGlobalVars.LayoutVars;
import pckGlobalVars.ProjectVars;
import pckGlobalVars.SessionVars;
import pckGraphics.ProgressBar;
import pckUtilities.MessageBoxMaker;
import pckUtilities.StringLocalizer;

/*
 * It provide to create the menu of the application.
 * Parent: Personality test
 * Child: nothing
 */
public class PersonalityTestMenu {
	
	private static JMenu subMenuFiles;
	private static JMenu subMenuLanguage;
	private static JMenu subMenuInfo;
	private static JMenu subMenuSession;
	
	private static JMenuItem delMenu;
	private static JMenuItem backupMenu;
	private static JMenuItem restoreMenu;
    
	private static JMenuItem EnMenu;
	private static JMenuItem DeMenu;
	private static JMenuItem ItMenu;
    
	private static JMenuItem exitMenu;
	private static JMenuItem aboutMenu;
	
	private static MessageBoxMaker activeMsg;
    
	
	public static void createMenu(JFrame frame) {
        
		MenuActionListener menuActList = new MenuActionListener();
        
		subMenuFiles = new JMenu(StringLocalizer.getBtnFile());
		subMenuLanguage = new JMenu(StringLocalizer.getLanguageMenu());
		subMenuInfo = new JMenu(StringLocalizer.getMenuInfo());
		subMenuSession = new JMenu(StringLocalizer.getMenuSession());
		
        delMenu = new JMenuItem(StringLocalizer.getDelButton());
        backupMenu = new JMenuItem(StringLocalizer.getBckButton());
        restoreMenu = new JMenuItem(StringLocalizer.getImpButton());
        
        EnMenu = new JMenuItem(StringLocalizer.getLanguageEN());
        DeMenu = new JMenuItem(StringLocalizer.getLanguageDE());
        ItMenu = new JMenuItem(StringLocalizer.getLanguageIT());
        
		exitMenu = new JMenuItem(StringLocalizer.getMenuExit());
		exitMenu.addActionListener(menuActList);
        aboutMenu = new JMenuItem(StringLocalizer.getMenuAbout());
        aboutMenu.addActionListener(menuActList);
        
		delMenu.addActionListener(menuActList);
		subMenuFiles.add(delMenu);
        backupMenu.addActionListener(menuActList);
        subMenuFiles.add(backupMenu);
        restoreMenu.addActionListener(menuActList);
        subMenuFiles.add(restoreMenu);
        
        EnMenu.addActionListener(menuActList);
		subMenuLanguage.add(EnMenu);
        DeMenu.addActionListener(menuActList);
		subMenuLanguage.add(DeMenu);
        ItMenu.addActionListener(menuActList);
		subMenuLanguage.add(ItMenu);
		
		subMenuInfo.add(aboutMenu);
		subMenuSession.add(exitMenu);
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(subMenuFiles);
        menuBar.add(subMenuLanguage);
        menuBar.add(subMenuInfo);
        menuBar.add(subMenuSession);
        
        frame.setJMenuBar(menuBar);
    }

    private static class MenuActionListener implements ActionListener {

        @SuppressWarnings("unused")
		public void actionPerformed(ActionEvent event) {
            JMenuItem activeButton = (JMenuItem) event.getSource();
            String text = activeButton.getText();
            if (text.equals(StringLocalizer.getMenuExit())) {
            		//MessageBoxMaker.MessageBoxExit();
            }
            else
            {
            		if (text.equals(StringLocalizer.getDelButton())) {
            			activeMsg = new MessageBoxMaker("DelConfirm");
	        			activeMsg.MessageCreator();
                }
            		else
            		{
            			if (text.equals(StringLocalizer.getBckButton())) {
                        	
            				DataBackup dataBackup = new DataBackup();
            				try {
								dataBackup.BackupMaker();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                     }
            			else
            			{
            				if (text.equals(StringLocalizer.getImpButton())) {
                            	
            					activeMsg = new MessageBoxMaker("RestoreConfirm");
		        				activeMsg.MessageCreator();
                         }
            				else
            				{
            					if (text.equals(StringLocalizer.getMenuAbout())) {
            		            	
	            	            		String[] boxButtons = {StringLocalizer.getAboutClose()};
	            	            		String boxTitle = StringLocalizer.getAboutTitle();
	            	            		String boxText = "";
	            	            		JPanel creditsPanel = new JPanel();
	            	            	    JTextArea textArea = new JTextArea();
	            	            	    textArea.setPreferredSize(new Dimension(LayoutVars.getAboutWidth(),LayoutVars.getAboutHeight()));
	            	            	    textArea.setLineWrap(true);
	            	            	    textArea.setWrapStyleWord(true);
	            	            	    boxText += StringLocalizer.getAppName() + " " + StringLocalizer.getAppVersion() + "\n";
	            	            	    boxText += StringLocalizer.getAppIntroText() + "\n\n";
	            	            	    boxText += StringLocalizer.getAboutDescription() + "\n\n";
	            	            	    boxText += StringLocalizer.getAppAuthor() + " | " + StringLocalizer.getAppAuthorNumber() + "\n\n";
	            	            	    boxText += StringLocalizer.getAboutClassesNumer() + " " + ProjectVars.getClassesNumber() + "\n";
	            	            	    boxText += StringLocalizer.getAboutMethodsNumber() + " " + ProjectVars.getMethodsNumber() + "\n";
	            	            	    boxText += StringLocalizer.getAboutLinesNumber() + " " + ProjectVars.getCodelinesNumber() + "\n";         	    
	            	            	    textArea.setText(boxText);
	            	            	    textArea.setEditable(false);
	            	            	    creditsPanel.add(textArea);
	            	            	    JOptionPane.showOptionDialog(null, creditsPanel, boxTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
	            	            }
            					else
            					{
            						if (text.equals(StringLocalizer.getLanguageEN())){
            							SessionVars.setLanguage("en");
            						}
            						else
            						{
            							if (text.equals(StringLocalizer.getLanguageDE())){
            								SessionVars.setLanguage("de");
                						}
            							else
            								if (text.equals(StringLocalizer.getLanguageIT())){
            									SessionVars.setLanguage("it");
                    						}
            						}
            						
            						try {
										StringLocalizer.TextInitializer();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
            						
            						TextUpdater();
            						PersonalityTestPanel.TextUpdater();
            						TabStatPanel.TextUpdater();
            						TabSearchPanel.TextUpdater();
            						TabTestPanel.TextUpdater();
            						ProgressBar.TextUpdater();
            						TabTestPanelButtons.TextUpdater();
            					}
            				}
            			}     
            		} 
            } 
        }
        
        public static void TextUpdater() {
        	
        		subMenuFiles.setText(StringLocalizer.getBtnFile());
        		subMenuLanguage.setText(StringLocalizer.getLanguageMenu());
        		subMenuInfo .setText(StringLocalizer.getMenuInfo());
        		subMenuSession.setText(StringLocalizer.getMenuSession());
    		
            delMenu.setText(StringLocalizer.getDelButton());
            backupMenu.setText(StringLocalizer.getBckButton());
            restoreMenu.setText(StringLocalizer.getImpButton());
            
            EnMenu.setText(StringLocalizer.getLanguageEN());
            DeMenu.setText(StringLocalizer.getLanguageDE());
            ItMenu.setText(StringLocalizer.getLanguageIT());
            
    			exitMenu.setText(StringLocalizer.getMenuExit());
    			aboutMenu.setText(StringLocalizer.getMenuAbout());
        }
    }
}
