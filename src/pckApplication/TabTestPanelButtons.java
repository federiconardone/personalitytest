package pckApplication;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pckBusinessObjects.BusinessObject;
import pckGlobalVars.LayoutVars;
import pckMaths.Converter;
import pckUtilities.MessageBoxMaker;
import pckUtilities.StringLocalizer;
import pckUtilities.TestController;

/*
 * It provide to create the bottom 3-buttons area of the test panel,
 * with all test functions; the 3 buttons are enabled or not 
 * depending from the phase of the test.
 * Parent: Tab test panel
 */
public class TabTestPanelButtons extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private TabTestPanel tabTestPanel;
	private static int testStep;
	@SuppressWarnings("unused")
	private JRadioButton[] answerButton = new JRadioButton[7];
	private static String userName;
	private int[] answerValues = new int[12];
	private static JButton btnBack;
	private static JButton btnReset;
	private static JButton btnConfirm;
	private static boolean progressCheck;
	private static MessageBoxMaker activeMsg;
	
	public TabTestPanelButtons(TabTestPanel tabTestPanel, JRadioButton[] answerButton, JPanel resultsPanel, JPanel testPanel, JPanel startPanel) {
		
		this.tabTestPanel = tabTestPanel;
		this.answerButton = answerButton;
		testStep = 0;
		
		this.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),(int)(LayoutVars.getIntroWidth() / 6)));
        this.setOpaque(false);
        
        btnBack = new JButton(StringLocalizer.getTestPrev().toUpperCase());
        btnBack.setFont(LayoutVars.getButtonFont());
        btnBack.setPreferredSize(LayoutVars.getButtonDimensions());
        btnBack.setEnabled(false);
        btnBack.addActionListener((event)->{		
        		
        		if (testStep > 1)
	        		
	        	{
	        		TabTestPanel.setAnswer(this.answerValues[testStep-2] - 1);
	        	}
        			
        		TestController.testSwitcher(testStep - 1);
        		testStep--;
        		BtnManager(startPanel, testPanel, resultsPanel);
        	});
        btnReset = new JButton(StringLocalizer.getTestCanc().toUpperCase());
        btnReset.setFont(LayoutVars.getButtonFont());
        btnReset.setPreferredSize(LayoutVars.getButtonDimensions());
        btnReset.addActionListener((event)->{		
        		testStep = 0;
        		this.answerValues = new int[12];
        		TestController.testSwitcher(testStep);	
        		TabTestPanel.resetSelection();
        		TabTestPanel.resetNick();
        		BtnManager(startPanel, testPanel, resultsPanel);
        	});
        btnConfirm = new JButton(StringLocalizer.getTestNext().toUpperCase());
        btnConfirm.setFont(LayoutVars.getButtonFont());
        btnConfirm.setPreferredSize(LayoutVars.getButtonDimensions());
        btnConfirm.addActionListener((event)->{
			
        		progressCheck = false;
        		int testResult = 0;
        		
        		if (testStep == 0)
        		{
        			try {
        					if (TabTestPanel.getUser().equals("")) {
        						activeMsg = new MessageBoxMaker("EmptyUsername");
                				activeMsg.MessageCreator();
        					}
        					else
        						if (BusinessObject.CheckUsername(TabTestPanel.getUser()) == false) {
        							activeMsg = new MessageBoxMaker("InvalidUsername");
                    				activeMsg.MessageCreator();
        						}
        						else
        						{
        							userName = TabTestPanel.getUser();
        							progressCheck = true;
        						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
        		}
        		else
        		{
        			testResult = TabTestPanel.getResultMax(testStep - 1) + 1;
        			
        			if (testResult == 0 && this.answerValues[testStep - 1] == 0)
        			{
        				activeMsg = new MessageBoxMaker("EmptyResult");
        				activeMsg.MessageCreator();
        			}
        			else
        			{
        				if (this.answerValues[testStep] == 0)
        					TabTestPanel.resetSelection();
        				else
        					TabTestPanel.setAnswer(this.answerValues[testStep] - 1);
            			
        				this.answerValues[testStep-1] = testResult;
        				progressCheck = true;
            			if (testStep == 10)
            			
        					try {
        						Float [] convertedProfile = new Float [5];
        						convertedProfile = Converter.getConvertedProfile(this.answerValues);
        						BusinessObject.WriteResult(userName, convertedProfile);
        						TabTestPanel.setConvertedProfile(convertedProfile, userName);
        						TabTestPanel.resetNick();		
        						
        					} catch (IOException e) {
        						e.printStackTrace();
        				}
        			}  
        		}
        		
        		if (progressCheck == true) {
        			
        			TestController.testSwitcher(testStep + 1);
            		testStep++;
        		}
        		BtnManager(startPanel, testPanel, resultsPanel);
        	});
        add(btnBack);
        add(btnReset);
        add(btnConfirm);
	}
	
	//Provide to enable / disable each button, depends from the current phase
	private void BtnManager(JPanel startPanel, JPanel testPanel, JPanel resultsPanel) {
		
		if (btnBack.isEnabled())
			btnBack.setEnabled(false);
		if (btnReset.isEnabled())
			btnReset.setEnabled(false);
		if (btnConfirm.isEnabled())
			btnConfirm.setEnabled(false);
		btnReset.setText(StringLocalizer.getTestCanc().toUpperCase());
		
		if (startPanel.isVisible())
		{
			btnReset.setEnabled(true);
			btnConfirm.setEnabled(true);
		}
		else
		{
			if (resultsPanel.isVisible()) {
				btnReset.setEnabled(true);
				btnReset.setText(StringLocalizer.getTestDone().toUpperCase());
			}
			else
			{
				btnReset.setEnabled(true);
				btnConfirm.setEnabled(true);
				btnBack.setEnabled(true);
			}
		}	
	}
	
	public static void TextUpdater() {
		
		btnBack.setText(StringLocalizer.getTestPrev().toUpperCase());
        btnReset.setText(StringLocalizer.getTestCanc().toUpperCase());
        btnConfirm.setText(StringLocalizer.getTestNext().toUpperCase());
        
        if (progressCheck == true && testStep < 11) {
        		TestController.getQuestion(testStep);
    		}
        
        if (testStep == 11) {
        		btnReset.setText(StringLocalizer.getTestDone().toUpperCase());            
        }
	}
}
