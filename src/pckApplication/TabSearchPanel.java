package pckApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import pckBusinessObjects.BusinessObject;
import pckGlobalVars.LayoutVars;
import pckGraphics.ChartSearch;
import pckUtilities.MessageBoxMaker;
import pckUtilities.StringLocalizer;

/*
 * It provides to create the search panel.
 * Parent: personality test panel
 * Child: single-user chart class
 */
public class TabSearchPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private PersonalityTestPanel personalityTestPanel;
	private static JPanel searchPanel;
	private static JPanel resultsPanel;
	private static JTextField nickField;
	private static JButton btnConfirm;
	private static JButton btnReset;
	private static String listResult [][];
	private static String listSearch [][];
	private static JPanel testMessagePanel;
	private static JLabel[] resultLabel;
	private static JButton[] resultButton;
	private static ChartSearch chartSearch;
	private static JTextPane searchChartTitleArea;
	private static JButton btnChartReset;
	private static JTextArea testIntroArea;
	private static MessageBoxMaker activeMsg;
	//private static int graphStatus;
    
	public TabSearchPanel(String tabName, PersonalityTestPanel personalityTestPanel) {
		
		resultButton = new JButton[3];
		resultLabel = new JLabel[3];
		//graphStatus = 0;
		
		this.personalityTestPanel = personalityTestPanel;
        this.setName(tabName);
        setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(), LayoutVars.getTabPanelHeight()));
        setLayout(new BorderLayout());
        
        chartSearch = new ChartSearch(this);
        
        try {
        		listResult = new String [BusinessObject.rowCounter()][6];
			listResult = BusinessObject.ListResults();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(LayoutVars.getTabBackColor());
        
        searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        searchPanel.setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 1.6)));
        
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
        
        resultsPanel.add(chartSearch);
        
        //Creates the button to click after the search result to reset the panel
        JPanel chartButtonPanel = new JPanel();
        chartButtonPanel.setPreferredSize(new Dimension (LayoutVars.getIntroWidth(),(int)(LayoutVars.getIntroWidth() / 6)));
        chartButtonPanel.setOpaque(false);
        btnChartReset = new JButton(StringLocalizer.getTestDone().toUpperCase());
        btnChartReset.setFont(LayoutVars.getButtonFont());
        btnChartReset.setPreferredSize(LayoutVars.getButtonDimensions());
        btnChartReset.addActionListener((event)->{	
        		SearchReset();
        		SearchPanelSwitcher(1);
        	});
        chartButtonPanel.add(btnChartReset);
        
        resultsPanel.add(chartButtonPanel);
        
        JPanel testIntroPanel = new JPanel();
        testIntroPanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),LayoutVars.getTabPanelWidth() / 10));
        testIntroPanel.setOpaque(false);
        testIntroArea = new JTextArea(StringLocalizer.getSearchIntro());
        testIntroArea.setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * 0.95),(int)(LayoutVars.getTabPanelWidth() * 0.5)));
        testIntroArea.setLineWrap(true);
        testIntroArea.setWrapStyleWord(true);
        testIntroArea.setFont(LayoutVars.getHeaderFont());
        testIntroArea.setForeground(LayoutVars.getTabTextColor());
        testIntroArea.setRows(3);
        testIntroArea.setEditable(false);
        testIntroPanel.add(testIntroArea);
        
        searchPanel.add(testIntroPanel);
        
        //Create the panel with the search text field and relative buttons
        JPanel testNicknamePanel = new JPanel();
        testNicknamePanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 3 / 8 * 1.1)));
        testNicknamePanel.setBorder(BorderFactory.createEmptyBorder(LayoutVars.getTabPanelWidth() / 10, 0, 0, 0));
        testNicknamePanel.setOpaque(isLightweight());        
        nickField = new JTextField();
        nickField.setHorizontalAlignment(JTextField.CENTER);
        nickField.setMinimumSize(new Dimension(300,30));
        nickField.setPreferredSize(new Dimension (300,30));
        nickField.setFont(LayoutVars.getHeaderFont());
        testNicknamePanel.add(nickField);
        
        btnConfirm = new JButton(StringLocalizer.getSearchBtnStart().toUpperCase());
        btnConfirm.setFont(LayoutVars.getButtonFont());
        btnConfirm.setPreferredSize(LayoutVars.getButtonDimensions());
        btnConfirm.addActionListener((event)->{		
        	
        		String searchKey = nickField.getText();
        		int resultCounter = 0;
        		List<String> searchResult = new ArrayList<String>();
        		if (nickField.equals("")) {
        			activeMsg = new MessageBoxMaker("EmptyUsername");
        			activeMsg.MessageCreator();
        		}
        		else
        		{
        			//Create an array with the users corresponding to the choosen key
        			for (int i = 0; i < listResult.length; i ++) {
        				
        				if (listResult[i][0].contains(searchKey))
        				{
        					resultCounter++;
        					String rowValue = "";
        					for (int j = 0; j < 6; j ++) {
        						rowValue += listResult[i][j];
        						if (j < 5)
        							rowValue += ";";
        					}
        					searchResult.add(rowValue);
        				}
        			}
        			listSearch = new String [resultCounter][6];
        			
        			if (searchKey.equals("")) {
        				activeMsg = new MessageBoxMaker("EmptyUsername");
        				activeMsg.MessageCreator();
        			}
        			else
        				if(resultCounter == 0)
        				{
        					activeMsg = new MessageBoxMaker("EmptySearch");
        					activeMsg.MessageCreator();
        				}
        				else
        					//After 3 results, the system ask to the user a more accurate search
        					if (resultCounter > 3)
        					{
        						activeMsg = new MessageBoxMaker("ToomuchSearch");
        						activeMsg.MessageCreator();
        					}
            				else
            				{
            					//If the search is correct, it creates a row for each result
            					for (int k = 0; k < searchResult.size(); k ++) 
            					{
            						String[] parts = searchResult.get(k).split(";");
            		        			for (int z = 0; z < 6; z ++) {
            		        				listSearch[k][z] = parts [z];
            		        			}
            		        			int z = k;
            		        	        resultLabel[k] = new JLabel(listSearch[k][0]);
            		        	        resultLabel[k].setPreferredSize(new Dimension ((int)(LayoutVars.getTabPanelWidth() * 0.30), (int)(LayoutVars.getTabPanelWidth() * 0.03)));
            		        	        resultLabel[k].setFont(LayoutVars.getParagraphFont());
            		        	        resultButton[k] = new JButton(StringLocalizer.getSearchBtnSelect().toUpperCase());
            		        			resultButton[k].setFont(LayoutVars.getButtonFont());
            		        			resultButton[k].setPreferredSize(new Dimension(200,35));
            		        			resultButton[k].addActionListener((currEvent)->{
            		        				
            		        				testMessagePanel.setVisible(false);
            		        		        SearchPanelSwitcher(2);
                        				OpenChart(listSearch[z]);
            		        			});
            		        				
            		        			testMessagePanel.add(resultLabel[k]);
            		        			testMessagePanel.add(resultButton[k]);
            					}
            					testMessagePanel.setVisible(true);
            			        testMessagePanel.doLayout();		
            				}
        		}
        	});
        testNicknamePanel.add(btnConfirm);
        
        btnReset = new JButton(StringLocalizer.getTestCanc().toUpperCase());
        btnReset.setFont(LayoutVars.getButtonFont());
        btnReset.setPreferredSize(LayoutVars.getButtonDimensions());
        btnReset.addActionListener((event)->{		
        		SearchReset();
        		testMessagePanel.setVisible(false);
        	});
        testNicknamePanel.add(btnReset);
        
        searchPanel.add(testNicknamePanel);
        
        testMessagePanel = new JPanel();
        testMessagePanel.setPreferredSize(new Dimension ((int)(LayoutVars.getTabPanelWidth() * 0.575),(int)(LayoutVars.getTabPanelWidth() / 7.3)));
        testMessagePanel.setOpaque(true);
        testMessagePanel.setBorder(BorderFactory.createLineBorder(LayoutVars.getTabTextColor()));
        testMessagePanel.setBackground(LayoutVars.getChartBackColor());
        testMessagePanel.setVisible(false);
        searchPanel.add(testMessagePanel);
        mainPanel.add(searchPanel);
        mainPanel.add(resultsPanel);
        add(mainPanel);
	}
	
	//It switch from the search panel to the selected user graph panel
	public void SearchPanelSwitcher(int panelIndex) {
		
		if (panelIndex == 1) {
			searchPanel.setVisible(true);
			resultsPanel.setVisible(false);
		}
		else
		{
			searchPanel.setVisible(false);
			resultsPanel.setVisible(true);
		}
	}
	
	//After the search, it resets all elements
	public void SearchReset() {
		nickField.setText("");
		testMessagePanel.removeAll();
		testMessagePanel.doLayout();
		testMessagePanel.revalidate();
		testMessagePanel.repaint();
	}
	
	//Open the chart corresponds to the chosen user
	public void OpenChart(String[] paramListSearch) {
		
		String userName = paramListSearch[0];
		
		Float givenProfile[] = new Float[5];
		
		for (int z = 1; z < 6; z ++) {
			givenProfile[z-1] = Float.valueOf(paramListSearch[z]);
		}
		
		searchChartTitleArea.setText(StringLocalizer.getUserStatTitle() + " " + userName);
		chartSearch.setConvertedProfile(givenProfile);
		chartSearch.setUserName(userName);
		chartSearch.repaint();
		
		resultsPanel.doLayout();
		resultsPanel.revalidate();
		resultsPanel.repaint();
	}
	
	public static void ListResultUpdate() {
		
		try {
			listResult = BusinessObject.ListResults();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void TextUpdater() {
    	
		btnChartReset.setText(StringLocalizer.getTestDone().toUpperCase());
		testIntroArea.setText(StringLocalizer.getSearchIntro());
		btnConfirm.setText(StringLocalizer.getSearchBtnStart().toUpperCase());
		btnReset.setText(StringLocalizer.getTestCanc().toUpperCase());
		
		if (searchPanel.isVisible()) {
			
			if (testMessagePanel.isVisible()) {
				
				for (int i = 0; i < listSearch.length; i ++) 
				{
					resultButton[i].setText(StringLocalizer.getSearchBtnSelect().toUpperCase());
				}
			}
		}
		else
		{
			chartSearch.repaint();
			resultsPanel.doLayout();
			resultsPanel.revalidate();
			resultsPanel.repaint();
		}
        
	}
}
