package pckApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import pckBusinessObjects.BusinessObject;
import pckGlobalVars.LayoutVars;
import pckGraphics.ChartAverage;
import pckGraphics.ChartSummary;
import pckMaths.Aggregation;
import pckMaths.Average;
import pckMaths.Deviation;
import pckUtilities.StringLocalizer;

/*
 * It provides to create the tab of the staistic / chart
 * and to call the 'chart' classes to create dinamically
 * all graphic elements.
 * Parent: Personality test panel
 * Child: all chart classes
 */
public class TabStatPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private PersonalityTestPanel personalityTestPanel;
	private static ChartSummary chartSummary;
	private static ChartAverage chartAverage;
	private static JPanel resultsPanel;
	private static JTextPane searchChartTitleArea;
	private static String[][] listResult;
	private static JButton btnChartReset;
	private static int openedChart;
	private static JPanel chartButtonPanel;
    
	public TabStatPanel(String tabName, PersonalityTestPanel personalityTestPanel) throws FileNotFoundException {
		
		this.personalityTestPanel = personalityTestPanel;
        this.setName(tabName);
        setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(), LayoutVars.getTabPanelHeight()));
        setLayout(new BorderLayout());
        
        openedChart = 0;
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(LayoutVars.getTabBackColor());
        
        resultsPanel = new JPanel();
        resultsPanel.setOpaque(false);
        resultsPanel.setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 1.6)));
        
        JPanel testChartTitlePanel = new JPanel();
        testChartTitlePanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelWidth() * 0.03)));
        testChartTitlePanel.setOpaque(false);
        searchChartTitleArea = new JTextPane();
        searchChartTitleArea.setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * 0.95),(int)(LayoutVars.getTabPanelWidth() * 0.03)));
        searchChartTitleArea.setFont(LayoutVars.getHeaderFont());
        searchChartTitleArea.setForeground(LayoutVars.getTabTextColor());
        SimpleAttributeSet centerAttrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttrs, StyleConstants.ALIGN_CENTER);
        searchChartTitleArea.setParagraphAttributes(centerAttrs, false);
        searchChartTitleArea.setEditable(false);
        testChartTitlePanel.add(searchChartTitleArea);
        
        resultsPanel.add(testChartTitlePanel);
        
        try {
			listResult = BusinessObject.ListResults();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        chartSummary = new ChartSummary(this);
        chartAverage = new ChartAverage(this);
		
		OpenChartSum(listResult);
        
        resultsPanel.add(chartSummary);
        
        //Create a button to switch from the sum to average panel and viceversa
        chartButtonPanel = new JPanel();
        chartButtonPanel.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),(int)(LayoutVars.getIntroWidth() / 6)));
        chartButtonPanel.setOpaque(false);
        btnChartReset = new JButton(StringLocalizer.getStatGoaverage().toUpperCase());
        btnChartReset.setFont(LayoutVars.getButtonFont());
        btnChartReset.setPreferredSize(LayoutVars.getButtonDimensions());
        btnChartReset.addActionListener((event)->{
        	
        		if (openedChart == 0) {
        			openedChart = 1;
        			resultsPanel.remove(chartSummary);
        			resultsPanel.remove(chartButtonPanel);
        			btnChartReset.setText(StringLocalizer.getStatGosum().toUpperCase());
        			resultsPanel.add(chartAverage);
        			resultsPanel.add(chartButtonPanel);
        			try {
						OpenChartAverage(listResult);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		else
        		{
        			openedChart = 0;
        			resultsPanel.remove(chartAverage);
        			resultsPanel.remove(chartButtonPanel);
        			btnChartReset.setText(StringLocalizer.getStatGoaverage().toUpperCase());
        			resultsPanel.add(chartSummary);
        			resultsPanel.add(chartButtonPanel);
        			try {
						OpenChartSum(listResult);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	});
        chartButtonPanel.add(btnChartReset);
        resultsPanel.add(chartButtonPanel);
        
        mainPanel.add(resultsPanel);
        add(mainPanel);	
	}
	
	//Open the sum chart
	public static void OpenChartSum(String[][] paramListSearch) throws FileNotFoundException {
		
		Float [][] paramSum= new Float[3][5];
		paramSum = Aggregation.AggregationMaker(paramListSearch);
		searchChartTitleArea.setText(StringLocalizer.getStatTitlesum());
		chartSummary.setConvertedProfile(paramSum);
		chartSummary.repaint();
	}
	
	//Open the average chart
	public void OpenChartAverage(String[][] paramListSearch) throws FileNotFoundException {
		
		Float [][] paramAverages= new Float[2][5];
		paramAverages = Average.AverageMaker(paramListSearch);
		paramAverages = Deviation.DeviationMaker(paramListSearch, paramAverages);
		searchChartTitleArea.setText(StringLocalizer.getStatTitleAverage());
		chartAverage.setConvertedProfile(paramAverages);
		chartAverage.repaint();
	}
	
	//For each data changing, re-calculate all data and re-create all charts
	public static void ListResultUpdate() throws FileNotFoundException {
		
		try {
			listResult = BusinessObject.ListResults();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultsPanel.remove(chartAverage);
		resultsPanel.remove(chartButtonPanel);
		btnChartReset.setText(StringLocalizer.getStatGoaverage().toUpperCase());
		resultsPanel.add(chartSummary);
		resultsPanel.add(chartButtonPanel);
		OpenChartSum(listResult);
	}
	
	public static void TextUpdater() {
    	
		if (openedChart == 1) {
			btnChartReset.setText(StringLocalizer.getStatGoaverage().toUpperCase());
			searchChartTitleArea.setText(StringLocalizer.getStatTitleAverage());
			chartAverage.repaint();	
		}
		else
		{
			btnChartReset.setText(StringLocalizer.getStatGosum().toUpperCase());
			searchChartTitleArea.setText(StringLocalizer.getStatTitlesum());
			chartSummary.repaint();
		}
	}
}
