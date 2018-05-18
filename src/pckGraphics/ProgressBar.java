package pckGraphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pckApplication.TabTestPanel;
import pckGlobalVars.LayoutVars;
import pckUtilities.StringLocalizer;

/*
 * It provides to create the progress bar, to indicate
 * to the user the current test phase. 
 */
public class ProgressBar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static TabTestPanel tabTestPanel;
	public static int currentStep;
	private static Graphics2D barGraphic;
	private static int refWidth;
	private static int refHeight;
	private static double progressWidth;
	
	private static JLabel [] titleLabel = new JLabel[12];
	
	public ProgressBar(TabTestPanel tabTestPanel){
		
		ProgressBar.tabTestPanel = tabTestPanel;
		ProgressBar.currentStep = 1;
		this.setPreferredSize(new Dimension(LayoutVars.getProgressPanelWidth(),LayoutVars.getProgressPanelHeight()));
		this.setOpaque(true);
		refWidth = (LayoutVars.getProgressBarWidth());
		refHeight = (LayoutVars.getProgressBarlHeight());	
	}
	
	//Draws a bar and change the color of a little portion of the bar for each completed step
	public void paint(Graphics g)
	{
		progressWidth = (currentStep - 1) * LayoutVars.getProgressStepWidth();
		barGraphic = (Graphics2D)g;
		barGraphic.setColor(LayoutVars.getProgressDoneColor());
		barGraphic.fillRect(LayoutVars.getProgressStepOffset(),0,(int)(refWidth * progressWidth),refHeight);
		barGraphic.setColor(LayoutVars.getProgressTodoColor());
		barGraphic.fillRect(LayoutVars.getProgressStepOffset() + (int)(refWidth * progressWidth) ,0,(int)(refWidth * (1-progressWidth)),refHeight);		
	}
	
	//Create the text of the bar for each step of the test
	public static JPanel ProgressPanel() {
		
		JPanel progressPanel = new JPanel();
		
		progressPanel.setPreferredSize(new Dimension ((int)(LayoutVars.getTabPanelWidth() * 0.95),LayoutVars.getTabPanelWidth() / 13));
        progressPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        progressPanel.setOpaque(false);
        for (int i = 0; i < 11; i ++) {
        	
        		String labelText = "";
        		switch (i) {
        		
        		case 0:
        			labelText = StringLocalizer.getProgressStart();
        			break;
        		case 11:
        			labelText = StringLocalizer.getProgressEnd();
        			break;
        		default:
        			labelText = StringLocalizer.getProgressTest() + " " + i;		
        		}      		
        		titleLabel[i] = new JLabel(labelText);
        		titleLabel[i].setFont(LayoutVars.getProgressFont());
        		titleLabel[i].setForeground(LayoutVars.getTabTextColor());
        		titleLabel[i].setPreferredSize(new Dimension(89,20));
        		titleLabel[i].setHorizontalAlignment(JLabel.CENTER);
        		progressPanel.add(titleLabel[i]);
        }
		return progressPanel;
	}
	
	public static void TextUpdater() {
		
		for (int i = 0; i < 11; i ++) {
        	
	    		String labelText = "";
	    		switch (i) {
	    		
		    		case 0:
		    			labelText = StringLocalizer.getProgressStart();
		    			break;
		    		case 11:
		    			labelText = StringLocalizer.getProgressEnd();
		    			break;
		    		default:
		    			labelText = StringLocalizer.getProgressTest() + " " + i;		
	    		}  
	    		titleLabel[i].setText(labelText);
		}
	}
}
