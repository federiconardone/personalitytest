package pckUtilities;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import pckGraphics.ProgressBar;

/*
 * Manages the action performed during the test progress, 
 * in particular referred to the graphic elements
 */
public class TestController {
	
	private static ProgressBar progressBar;
	private static JPanel startPanel;
	private static JPanel testPanel;
	private static JPanel resultsPanel;
	private static JTextArea testQuestionArea;
	
	public TestController() {
		
	}
	
	public static void setProgressBar(ProgressBar paramProgressBar) {
		
		progressBar = paramProgressBar;
	}
	
	public static void setStartPanel(JPanel paramStartPanel) {
			
		startPanel = paramStartPanel;
	}
	
	public static void setTestPanel(JPanel paramTestPanel) {
		
		testPanel = paramTestPanel;
	}
	
	public static void setResultsPanel(JPanel paramResultsPanel) {
		
		resultsPanel = paramResultsPanel;
	}
	
	public static void setQuestionArea(JTextArea paramQuestionArea) {
		
		testQuestionArea = paramQuestionArea;
	}
	
	//Main method, referred to others
	@SuppressWarnings("static-access")
	public static void testSwitcher(int currentStep) {
		
		PanelManager(currentStep);
		getQuestion(currentStep);
		progressBar.currentStep = currentStep + 1;
		progressBar.repaint();
	}
	
	//Manage the 3 phases of the test: username (0), questions (1-10), final result (11) 
	public static void PanelManager(int index) {
		
		if (startPanel.isVisible())
			startPanel.setVisible(false);
		if (testPanel.isVisible())
			testPanel.setVisible(false);
		if (resultsPanel.isVisible())
			resultsPanel.setVisible(false);
		
		switch (index) {
		
		case 0:
			startPanel.setVisible(true);
			break;
		case 11:
			resultsPanel.setVisible(true);
			break;
		default:
			testPanel.setVisible(true);
			break;
		}
	}
	
	//Select the question to present
	public static void getQuestion(int currentStep) {
		
		String activeQuestion = "";
		switch (currentStep) {
			
		case 1:
			activeQuestion = StringLocalizer.getQuestion1();
			break;
		case 2:
			activeQuestion = StringLocalizer.getQuestion2();
			break;
		case 3:
			activeQuestion = StringLocalizer.getQuestion3();
			break;
		case 4:
			activeQuestion = StringLocalizer.getQuestion4();
			break;
		case 5:
			activeQuestion = StringLocalizer.getQuestion5();
			break;
		case 6:
			activeQuestion = StringLocalizer.getQuestion6();
			break;
		case 7:
			activeQuestion = StringLocalizer.getQuestion7();
			break;
		case 8:
			activeQuestion = StringLocalizer.getQuestion8();
			break;
		case 9:
			activeQuestion = StringLocalizer.getQuestion9();
			break;
		case 10:
			activeQuestion = StringLocalizer.getQuestion10();
			break;
		}
		testQuestionArea.setText(StringLocalizer.getQuestionIntro() + " " + activeQuestion);
	}
	
	//Get the answer elements
	public static String getAnswer(int answerType) {
		
		String answerText = "";
		switch (answerType) {
		
		case 0:
			answerText = "1";
			break;
		case 1:
			answerText = "2";
			break;
		case 2:
			answerText = "3";
			break;
		case 3:
			answerText = "4";
			break;
		case 4:
			answerText = "5";
			break;
		case 5:
			answerText = "6";
			break;
		case 6:
			answerText = "7";
			break;
		
		}
		return answerText;
	}
	
	public static String getAnswerExplanation(int answerType) {
		
		String answerText = "";
		switch (answerType) {
		
		case 0:
			answerText = StringLocalizer.getAnswer1();
			break;
		case 1:
			answerText = StringLocalizer.getAnswer2();
			break;
		case 2:
			answerText = StringLocalizer.getAnswer3();
			break;
		case 3:
			answerText = StringLocalizer.getAnswer4();
			break;
		case 4:
			answerText = StringLocalizer.getAnswer5();
			break;
		case 5:
			answerText = StringLocalizer.getAnswer6();
			break;
		case 6:
			answerText = StringLocalizer.getAnswer7();
			break;
		}
		return answerText;
	}
	
	//Given a progressive number, get the personal feature
	public static String getFeature(int featureType) {
		
		String featureText = "";
		switch (featureType) {
		
		case 0:
			featureText = StringLocalizer.getFeature1();
			break;
		case 1:
			featureText = StringLocalizer.getFeature2();
			break;
		case 2:
			featureText = StringLocalizer.getFeature3();
			break;
		case 3:
			featureText = StringLocalizer.getFeature4();
			break;
		case 4:
			featureText = StringLocalizer.getFeature5();
			break;
		}
		return featureText;
	}
}
