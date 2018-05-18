package pckApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import pckGlobalVars.LayoutVars;
import pckUtilities.StringLocalizer;
import pckUtilities.TestController;
import pckGraphics.ChartUser;
import pckGraphics.ProgressBar;

/*
 * It provides to create the tab of the test and some elements.
 * Parent: personality test panel
 * Child: tab test panel button, progress bar and single-user chart
 */
public class TabTestPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel startPanel;
	private static JPanel testPanel;
	private static JPanel resultsPanel;
	private static ProgressBar progressBar;
	private static int testStep;
	private TestController testController;
	private static JRadioButton[] answerButton = new JRadioButton[7];
	private static int tempAnswerValue = 0;
	private static JTextField nickField;
	@SuppressWarnings("unused")
	private static Float [] convertedProfile;
	private static ButtonGroup answerGroup = new ButtonGroup();
	private static ChartUser chartUser;
	private static String userName;
	private static JTextPane testChartTitleArea;
	private static JTextArea testIntroArea;
	private static JPanel progressPanel;
	private static JPanel mainPanel;
	private static JTextArea testLegendaArea;
    
	@SuppressWarnings("unused")
	private PersonalityTestPanel personalityTestPanel;
    
	@SuppressWarnings("static-access")
	public TabTestPanel(String tabName, PersonalityTestPanel personalityTestPanel) {
		
		this.testStep = 0;
		this.testController = new TestController();
		this.personalityTestPanel = personalityTestPanel;
		this.setName(tabName);
		this.convertedProfile = new Float [5];
        
        setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(), LayoutVars.getTabPanelHeight()));
        setLayout(new BorderLayout());
        
        mainPanel = new JPanel();
        mainPanel.setBackground(LayoutVars.getTabBackColor());
        mainPanel.setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(),LayoutVars.getTabPanelHeight()));
        
        //Start is for the username phase
        startPanel = new JPanel();
        startPanel.setOpaque(false);
        startPanel.setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 0.65)));
        
        //Test is for the question series
        testPanel = new JPanel();
        testPanel.setOpaque(false);
        testPanel.setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 0.65)));
        
        //Result is, after the question, for the final chart
        resultsPanel = new JPanel();
        resultsPanel.setOpaque(false);
        resultsPanel.setPreferredSize(new Dimension(LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 0.65)));
        
        //Progress bar, in the bottom of the page, shows the current step
        progressBar = new ProgressBar(this);
        
        //Chart user is referred to the chart at the end of the test for the current user
        chartUser = new ChartUser(this);
        
        //Test Intro Panel contains the welcome message
        JPanel testIntroPanel = new JPanel();
        testIntroPanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),LayoutVars.getTabPanelWidth() / 10));
        testIntroPanel.setOpaque(false);
        testIntroArea = new JTextArea(StringLocalizer.getTabTestIntro());
        testIntroArea.setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * 0.95),(int)(LayoutVars.getTabPanelWidth() * 0.5)));
        testIntroArea.setLineWrap(true);
        testIntroArea.setWrapStyleWord(true);
        testIntroArea.setEditable(false);
        testIntroArea.setFont(LayoutVars.getHeaderFont());
        testIntroArea.setForeground(LayoutVars.getTabTextColor());
        testIntroArea.setRows(3);
        testIntroPanel.add(testIntroArea);
        
        startPanel.add(testIntroPanel);
        
        //Nickname panel contains the element to insert the nickname and to check it
        JPanel testNicknamePanel = new JPanel();
        testNicknamePanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelHeight() * 4 / 14.1)));
        testNicknamePanel.setBorder(BorderFactory.createEmptyBorder(LayoutVars.getTabPanelWidth() / 10, 0, 0, 0));
        testNicknamePanel.setOpaque(isLightweight());        
        nickField = new JTextField();
        nickField.setHorizontalAlignment(JTextField.CENTER);
        nickField.setMinimumSize(new Dimension(400,40));;
        nickField.setPreferredSize(new Dimension (400,40));
        nickField.setFont(LayoutVars.getHeaderFont());
        testNicknamePanel.add(nickField);
        
        startPanel.add(testNicknamePanel);
        
        //Test question panels contains the questions of the test (it changes itself for each step)
        JPanel testQuestionPanel = new JPanel();
        testQuestionPanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),LayoutVars.getTabPanelWidth() / 12));
        testQuestionPanel.setOpaque(isLightweight());
        JTextArea testQuestionArea = new JTextArea(StringLocalizer.getQuestionIntro() + " " + StringLocalizer.getQuestion1().toLowerCase());
        testQuestionArea.setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * 0.95),(int)(LayoutVars.getTabPanelWidth() * 0.5)));
        testQuestionArea.setLineWrap(true);
        testQuestionArea.setWrapStyleWord(true);
        testQuestionArea.setFont(LayoutVars.getHeaderFont());
        testQuestionArea.setForeground(LayoutVars.getTabTextColor());
        testQuestionArea.setEditable(false);
        testQuestionArea.setRows(3);
        testQuestionPanel.add(testQuestionArea);
        
        testPanel.add(testQuestionPanel);
        
        //Radio panel contains the available answers
        JPanel[] radioPanel = new JPanel[7];
        Double sizeRatio = 0.09; 
        for (int i = 0; i < 7; i ++) {
        	
        		int answerCode = i;
        		radioPanel[i] = new JPanel();
        		radioPanel[i].setOpaque(false);
        		radioPanel[i].setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * sizeRatio), LayoutVars.getTabPanelHeight()/13));
        		answerButton[i] = new JRadioButton(TestController.getAnswer(i));
        		answerButton[i].setFont(LayoutVars.getHeaderFont());
        		answerButton[i].addActionListener((event) -> {this.tempAnswerValue = answerCode;});
        		radioPanel[i].add(answerButton[i]);
        		answerGroup.add(answerButton[i]);
        		testPanel.add(radioPanel[i]);
                
        }
        this.tempAnswerValue = -1;
        
        //Test question panels contains the description of the answer
        String Explanation = "";
        for (int i = 0; i < 7; i ++) {
        		Explanation += TestController.getAnswer(i) + ": " + TestController.getAnswerExplanation(i) + "\n";
        }
        JPanel testLegendaPanel = new JPanel();
        testLegendaPanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),LayoutVars.getTabPanelWidth() / 5));
        testLegendaPanel.setOpaque(false);
        testLegendaPanel.setBorder(BorderFactory.createEmptyBorder(LayoutVars.getTabPanelWidth() / 30, 0, 0, 0));
        testLegendaArea = new JTextArea(Explanation);
        testLegendaArea.setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * 0.90),(int)(LayoutVars.getTabPanelHeight() * 1.2)));
        testLegendaArea.setLineWrap(true);
        testLegendaArea.setWrapStyleWord(true);
        testLegendaArea.setFont(LayoutVars.getHeaderFont());
        testLegendaArea.setForeground(LayoutVars.getTabTextColor());
        testLegendaArea.setEditable(false);
        testLegendaArea.setRows(7);
        testLegendaPanel.add(testLegendaArea);
        
        testPanel.add(testLegendaPanel);
        
        //Chart Title panel contains the title of the chart
        JPanel testChartTitlePanel = new JPanel();
        testChartTitlePanel.setPreferredSize(new Dimension (LayoutVars.getTabPanelWidth(),(int)(LayoutVars.getTabPanelWidth() * 0.03)));
        testChartTitlePanel.setOpaque(false);
        testChartTitleArea = new JTextPane();
        testChartTitleArea.setPreferredSize(new Dimension((int)(LayoutVars.getTabPanelWidth() * 0.95),(int)(LayoutVars.getTabPanelWidth() * 0.03)));
        testChartTitleArea.setFont(LayoutVars.getHeaderFont());
        testChartTitleArea.setForeground(LayoutVars.getTabTextColor());
        SimpleAttributeSet centerAttrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttrs, StyleConstants.ALIGN_CENTER);
        testChartTitleArea.setParagraphAttributes(centerAttrs, false);
        testChartTitleArea.setEditable(false);
        testChartTitlePanel.add(testChartTitleArea);
        
        resultsPanel.add(testChartTitlePanel);
        
        resultsPanel.add(chartUser);
        
        testController.setQuestionArea(testQuestionArea);
        testController.setProgressBar(progressBar);
        testController.setStartPanel(startPanel);
        testController.setTestPanel(testPanel);
        testController.setResultsPanel(resultsPanel);
        
        TabTestPanelButtons buttonPanel = new TabTestPanelButtons(this, answerButton, resultsPanel, testPanel, startPanel);
        
        progressPanel = progressBar.ProgressPanel();
        progressPanel.add(progressBar);
        
        mainPanel.add(startPanel);	
        mainPanel.add(testPanel);
        mainPanel.add(resultsPanel);	
        mainPanel.add(buttonPanel);
        mainPanel.add(progressPanel);
        TestController.testSwitcher(this.testStep);
        
        add(mainPanel);	
	}
	
	public static String getUser () {
		
		return nickField.getText();
	}
	
	public static int getResultMax (int step) {
		
		//return tempAnswerValue;
		return Math.max(getSelectedValue(), tempAnswerValue);
	}
	
	public static int getSelectedValue() {
		
		int selectedValue = -1;
		
		for (int i = 0; i < 7; i ++) {
			
			if (answerButton[i].isSelected()) {
				selectedValue = i;
			}
		}
		return selectedValue;
	}
	
	//This method provide to convert the selection of the user to the TIPI profile
	public static void setConvertedProfile (Float [] ParamConvertedProfile, String paramUserName) {
		
		convertedProfile = ParamConvertedProfile;
		userName = paramUserName;
		testChartTitleArea.setText(StringLocalizer.getUserStatTitle() + " " + userName);
		chartUser.setConvertedProfile(ParamConvertedProfile);
		chartUser.setUserName(paramUserName);
		chartUser.repaint();
	}
	
	public static void resetSelection() {
		
		answerGroup.clearSelection();	
		tempAnswerValue = -1;
	}
	
	public static void resetNick() {
		
		nickField.setText("");
	}
	
	public static void setAnswer(int answerCode) {
		
		resetSelection();
		answerButton[answerCode].setSelected(true);
	}
	
	public static void TextUpdater() {
    	
		testIntroArea.setText(StringLocalizer.getTabTestIntro());
		
		String Explanation = "";
        for (int i = 0; i < 7; i ++) {
        		Explanation += TestController.getAnswer(i) + ": " + TestController.getAnswerExplanation(i) + "\n";
        }
        testLegendaArea.setText(Explanation);
        testChartTitleArea.setText(StringLocalizer.getUserStatTitle() + " " + userName);
		
        chartUser.repaint();
	}
}

