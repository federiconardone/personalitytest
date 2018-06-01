package pckApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pckBusinessObjects.BusinessObject;
import pckGlobalVars.LayoutVars;
import pckUtilities.MessageBoxMaker;
import pckUtilities.StringLocalizer;

/*
 * It provide to create the intro panel
 * Parent: intro
 * Child: nothing
 */
public class IntroPanel extends JPanel {
	
	/**
	 * 
	 */
	MessageBoxMaker activeMsg;
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	public IntroPanel() {
		
		BusinessObject businessObject = new BusinessObject();
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),LayoutVars.getIntroWidth() / 8));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(LayoutVars.getIntroWidth() / 30, 0, 0, 0));
        titlePanel.setOpaque(isLightweight());
        JLabel titleLabel = new JLabel(StringLocalizer.getAppName() + " " + StringLocalizer.getAppVersion());
        titleLabel.setFont(LayoutVars.getTitleFont());
        titleLabel.setForeground(LayoutVars.getIntroTextColor());
        
        titlePanel.add(titleLabel);
        
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),LayoutVars.getIntroWidth() / 20));
        subTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        subTitlePanel.setOpaque(isLightweight());
        JLabel subTitleLabel = new JLabel(StringLocalizer.getAppIntroText());
        subTitleLabel.setFont(LayoutVars.getSubTitleFont());
        subTitleLabel.setForeground(LayoutVars.getIntroTextColor());
        
        subTitlePanel.add(subTitleLabel);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),LayoutVars.getIntroWidth() / 4));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(LayoutVars.getIntroWidth() / 11, 0, 0, 0));
        buttonsPanel.setOpaque(isLightweight());
        JButton loginButton = new JButton(StringLocalizer.getIntroBtnLogin().toUpperCase());
        loginButton.setFont(LayoutVars.getButtonFont());
        loginButton.setPreferredSize(LayoutVars.getButtonDimensions());
        loginButton.addActionListener((event)->{
        		try {
				
        			//If the data file has no header / hash, it creates
        			if (BusinessObject.HashCheck() == false){
  
        				try {
    						BusinessObject.HashInitialize();
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
        			}
        			
        			//If the data file is corrupted, suggest to the user a data reset
        			if (BusinessObject.rowCounter() > 4 && !BusinessObject.HashMatcher())
        			{
        				activeMsg = new MessageBoxMaker("ResetConfirm");
            			activeMsg.MessageCreator();
        			}
        			
        			PersonalityTest personalityTest = new PersonalityTest();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Intro.FrameHider();
        	});
        JButton exitButton = new JButton(StringLocalizer.getIntroBtnExit().toUpperCase());
        exitButton.setFont(LayoutVars.getButtonFont());
        exitButton.setPreferredSize(LayoutVars.getButtonDimensions());
        exitButton.addActionListener((event)->{ 
        		activeMsg = new MessageBoxMaker("Exit");
        		activeMsg.MessageCreator();
        	});
        
        buttonsPanel.add(exitButton);
        buttonsPanel.add(loginButton);
        
        JPanel creditPanel = new JPanel();
        creditPanel.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),LayoutVars.getIntroWidth() / 10));
        creditPanel.setBorder(BorderFactory.createEmptyBorder(LayoutVars.getIntroWidth() / 28, 0, 0, 0));
        creditPanel.setOpaque(isLightweight());
        JLabel creditLabel = new JLabel("(" + StringLocalizer.getAppIntroBottom() + " " + StringLocalizer.getAppAuthor() + ")");
        creditLabel.setFont(LayoutVars.getCreditFont());
        creditLabel.setForeground(LayoutVars.getIntroTextColor());
        
        creditPanel.add(creditLabel);
        
        mainPanel.setBackground(LayoutVars.getIntroBackColor());
        mainPanel.add(titlePanel);
        mainPanel.add(subTitlePanel);
        mainPanel.add(buttonsPanel);
        mainPanel.add(creditPanel);
        
        add(mainPanel);
	}

}
