package pckUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import pckGlobalVars.SessionVars;

/*
 * Manage the localization of all texts, picked from /Res/Texts folder
 * based from the chosen language at the application start. Without using
 * 'Resource' methods (not allowed), simply pick a row from the language  
 * file, split it and put in an element of array.
 * 
 * Important: I used the final formalism name with this vars but they are not
 * really final (the vars has all capitalized letter). The reason is that those
 * are changed only at the start of each session and after this point
 * the behavior is the same of a normal final variable.
 */
public class StringLocalizer{

	private static String LANGUAGE_QUESTION = "Please select your language \nBitte Wahlen Sie Seine Sprache \nSelezioni la Sua lingua";
	private static String LANGUAGE_TITLE = "Language | Sprache | Lingua";
	private static String LANGUAGE_EN = "English";
	private static String LANGUAGE_DE = "Deutsch";
	private static String LANGUAGE_IT = "Italiano";
	private static String APP_AUTHOR = "Federico Nardone Aggiutorio";
	private static String APP_AUTHOR_NUMBER = "10110861";		
	private static String APP_NAME="";
	private static String APP_VERSION = "";
	private static String APP_INTRO_TEXT = "";
	private static String APP_INTRO_BOTTOM = "";
	private static String INTRO_BTN_LOGIN = "";
	private static String INTRO_BTN_EXIT = "";
	private static String EXIT_BOX_TITLE = "";
	private static String EXIT_BOX_MESSAGE = "";
	private static String EXIT_BOX_CONFIRM = "";
	private static String EXIT_BOX_CANCEL = "";
	private static String MENU_NAME = "";
	private static String MENU_ABOUT = "";
	private static String MENU_EXIT = "";
	private static String ABOUT_TITLE = "";
	private static String ABOUT_DESCRIPTION = "";
	private static String ABOUT_NUMBER_CLASSES = "";
	private static String ABOUT_NUMBER_METHODS = "";
	private static String ABOUT_NUMBER_CODELINES = "";
	private static String ABOUT_CLOSE = "";
	private static String TAB_TEST_TITLE = "";
	private static String TAB_STAT_TITLE = "";
	private static String TAB_SEARCH_TITLE = "";
	private static String TAB_TEST_INTRO = "";
	private static String QUESTION_INTRO = "";
	private static String QUESTION_1 = "";
	private static String QUESTION_2 = "";
	private static String QUESTION_3 = "";
	private static String QUESTION_4 = "";
	private static String QUESTION_5 = "";
	private static String QUESTION_6 = "";
	private static String QUESTION_7 = "";
	private static String QUESTION_8 = "";
	private static String QUESTION_9 = "";
	private static String QUESTION_10 = "";
	private static String ANSWER_1 = "";
	private static String ANSWER_2 = "";
	private static String ANSWER_3 = "";
	private static String ANSWER_4 = "";
	private static String ANSWER_5 = "";
	private static String ANSWER_6 = "";
	private static String ANSWER_7 = "";
	private static String PROGRESS_START = "";
	private static String PROGRESS_TEST = "";
	private static String PROGRESS_END = "";
	private static String TEST_START = "";
	private static String TEST_NEXT = "";
	private static String TEST_PREV = "";
	private static String TEST_CANC = "";
	private static String USERNAME_BOX_TITLE = "";
	private static String USERNAME_BOX_EMPTY = "";
	private static String USERNAME_BOX_INVALID = "";
	private static String TEST_VALUE_BOX_TITLE = "";
	private static String TEST_VALUE_BOX_EMPTY = "";
	private static String FEATURE_1 = "";
	private static String FEATURE_2 = "";
	private static String FEATURE_3 = "";
	private static String FEATURE_4 = "";
	private static String FEATURE_5 = "";
	private static String USER_STAT_TITLE = "";
	private static String TEST_DONE = "";
	private static String SEARCH_INTRO = "";
	private static String SEARCH_NOUSER = "";
	private static String SEARCH_TOOMUCH = "";
	private static String SEARCH_BTN_START = "";
	private static String SEARCH_BTN_SELECT = "";
	private static String STAT_GOSUM = "";
	private static String STAT_GOAVERAGE = "";
	private static String STAT_TITLESUM = "";
	private static String STAT_TITLEAVERAGE = "";
	private static String STAT_DEV = "";
	private static String DEL_BUTTON = "";
	private static String DEL_TITLE = "";
	private static String DEL_MESSAGE_CONFIRM = "";
	private static String DEL_MESSAGE_DONE = "";
	private static String BCK_BUTTON = "";
	private static String BCK_TITLE = "";
	private static String BCK_MESSAGE_DONE = "";
	private static String IMP_BUTTON = "";
	private static String IMP_TITLE = "";
	private static String IMP_MESSAGE_CONFIRM = "";
	private static String IMP_MESSAGE_WRONGFILE = "";
	private static String IMP_DONE = "";
	private static String BTN_FILE = "";
	private static String FILE_HEAD_1 = "";
	private static String FILE_HEAD_2 = "";
	private static String FILE_RESET_WARNING = "";
	private static String LANGUAGE_MENU = "";
	private static String MENU_INFO = "";
	private static String MENU_SESSION = "";
	
	private static final String[][] textArray = new String[100][2];
	
	//Select the text file based on the language user selection
	@SuppressWarnings("resource")
	public static void TextInitializer() throws IOException {
		
		Object sessionLanguage = SessionVars.getLanguage();
		File textFile = null;
		String rowText;
		int rowIndex, colIndex;
		Scanner fileScanner, rowScanner;
		
		switch ((SessionVars.Language)sessionLanguage) {
		
			case en:
				textFile = new File("Res/Texts/EN_Text.dat");
				break;
			case de:
				textFile = new File("Res/Texts/DE_Text.dat");
				break;
			case it:
				textFile = new File("Res/Texts/IT_Text.dat");
				break;
		}
		
		fileScanner = new Scanner(textFile);
		
		rowIndex = 0;
		
		//Pick the part of each language file after '='
		while (fileScanner.hasNext()) {
        	
        		colIndex = 0;
        		rowText = fileScanner.nextLine();
        		rowScanner = new Scanner(rowText);
        		rowScanner.useDelimiter("=");
        	
        		while (rowScanner.hasNext()){
        			textArray[rowIndex][colIndex] = rowScanner.next();
        			colIndex ++;
        		}
            rowIndex ++;
        }
        
		//Assign to each element the corresponding text
		LANGUAGE_QUESTION = textArray[0][1];
		LANGUAGE_TITLE = textArray[1][1];
		LANGUAGE_EN = textArray[2][1];
		LANGUAGE_DE = textArray[3][1];
		LANGUAGE_IT = textArray[4][1];
		APP_AUTHOR = textArray[5][1];
		APP_AUTHOR_NUMBER = textArray[6][1];	
		APP_NAME = textArray[7][1];
		APP_VERSION = textArray[8][1];
		APP_INTRO_TEXT = textArray[9][1];
		APP_INTRO_BOTTOM = textArray[10][1];
		INTRO_BTN_LOGIN = textArray[11][1];
		INTRO_BTN_EXIT = textArray[12][1];
		EXIT_BOX_TITLE = textArray[13][1];
		EXIT_BOX_MESSAGE = textArray[14][1];
		EXIT_BOX_CONFIRM = textArray[15][1];
		EXIT_BOX_CANCEL = textArray[16][1];
		MENU_NAME = textArray[17][1];
		MENU_ABOUT = textArray[18][1];
		MENU_EXIT = textArray[19][1];
		ABOUT_TITLE = textArray[20][1];
		ABOUT_DESCRIPTION = textArray[21][1];
		ABOUT_NUMBER_CLASSES = textArray[22][1];
		ABOUT_NUMBER_METHODS = textArray[23][1];
		ABOUT_NUMBER_CODELINES = textArray[24][1];
		ABOUT_CLOSE = textArray[25][1];
		TAB_TEST_TITLE = textArray[26][1];
		TAB_STAT_TITLE = textArray[27][1];
		TAB_SEARCH_TITLE = textArray[28][1];
		TAB_TEST_INTRO = textArray[29][1];
		QUESTION_INTRO = textArray[30][1];
		QUESTION_1 = textArray[31][1];
		QUESTION_2 = textArray[32][1];
		QUESTION_3 = textArray[33][1];
		QUESTION_4 = textArray[34][1];
		QUESTION_5 = textArray[35][1];
		QUESTION_6 = textArray[36][1];
		QUESTION_7 = textArray[37][1];
		QUESTION_8 = textArray[38][1];
		QUESTION_9 = textArray[39][1];
		QUESTION_10 = textArray[40][1];
		ANSWER_1 = textArray[41][1];
		ANSWER_2 = textArray[42][1];
		ANSWER_3 = textArray[43][1];
		ANSWER_4 = textArray[44][1];
		ANSWER_5 = textArray[45][1];
		ANSWER_6 = textArray[46][1];
		ANSWER_7 = textArray[47][1];
		PROGRESS_START = textArray[48][1];
		PROGRESS_TEST = textArray[49][1];
		PROGRESS_END = textArray[50][1];
		TEST_START = textArray[51][1];
		TEST_NEXT = textArray[52][1];
		TEST_PREV = textArray[53][1];
		TEST_CANC = textArray[54][1];
		USERNAME_BOX_TITLE = textArray[55][1];
		USERNAME_BOX_EMPTY = textArray[56][1];
		USERNAME_BOX_INVALID = textArray[57][1];
		TEST_VALUE_BOX_TITLE = textArray[58][1];
		TEST_VALUE_BOX_EMPTY = textArray[59][1];
		FEATURE_1 = textArray[60][1];
		FEATURE_2 = textArray[61][1];
		FEATURE_3 = textArray[62][1];
		FEATURE_4 = textArray[63][1];
		FEATURE_5 = textArray[64][1];
		USER_STAT_TITLE = textArray[65][1];
		TEST_DONE = textArray[66][1];
		SEARCH_INTRO = textArray[67][1];
		SEARCH_NOUSER = textArray[68][1];
		SEARCH_TOOMUCH = textArray[69][1];
		SEARCH_BTN_START = textArray[70][1];
		SEARCH_BTN_SELECT = textArray[71][1];
		STAT_GOSUM = textArray[72][1];
		STAT_GOAVERAGE = textArray[73][1];
		STAT_TITLESUM = textArray[74][1];
		STAT_TITLEAVERAGE = textArray[75][1];
		STAT_DEV = textArray[76][1];
		DEL_BUTTON = textArray[77][1];
		DEL_TITLE = textArray[78][1];
		DEL_MESSAGE_CONFIRM = textArray[79][1];
		DEL_MESSAGE_DONE = textArray[80][1];
		BCK_BUTTON = textArray[81][1];
		BCK_TITLE = textArray[82][1];
		BCK_MESSAGE_DONE = textArray[83][1];
		IMP_BUTTON = textArray[84][1];
		IMP_TITLE = textArray[85][1];
		IMP_MESSAGE_CONFIRM = textArray[86][1];
		IMP_MESSAGE_WRONGFILE = textArray[87][1];
		IMP_DONE = textArray[88][1];
		BTN_FILE = textArray[89][1];
		FILE_HEAD_1 = textArray[90][1];
		FILE_HEAD_2 = textArray[91][1];
		FILE_RESET_WARNING = textArray[92][1];
		LANGUAGE_MENU = textArray[93][1];
		MENU_INFO = textArray[94][1];
		MENU_SESSION = textArray[95][1];
	}
	
	public static String getAppName () {
		
		return APP_NAME;
	}
	
	public static String getAppVersion () {
		
		return APP_VERSION;
	}
	
	public static String getAppAuthor () {
		
		return APP_AUTHOR;
	}
	
	public static String getAppAuthorNumber () {
		
		return APP_AUTHOR_NUMBER;
	}
	
	public static String getLanguageQuestion () {
		
		return LANGUAGE_QUESTION;
	}
	
	public static String getLanguageTitle () {
		
		return LANGUAGE_TITLE;
	}
	
	public static String getLanguageIT () {
		
		return LANGUAGE_IT;
	}
	
	public static String getLanguageDE () {
		
		return LANGUAGE_DE;
	}
	
	public static String getLanguageEN () {
		
		return LANGUAGE_EN;
	}
	
	public static String getAppIntroText () {
		
		return APP_INTRO_TEXT;
	}
	
	public static String getAppIntroBottom () {
		
		return APP_INTRO_BOTTOM;
	}
	
	public static String getIntroBtnLogin () {
		
		return INTRO_BTN_LOGIN;
	}
	
	public static String getIntroBtnExit () {
		
		return INTRO_BTN_EXIT;
	}

	public static String getExitBoxTitle () {
	
		return EXIT_BOX_TITLE;
	}

	public static String getExitBoxMessage () {
	
		return EXIT_BOX_MESSAGE;
	}

	public static String getExitBoxConfirm () {
	
		return EXIT_BOX_CONFIRM;
	}

	public static String getExitBoxCancel () {
	
		return EXIT_BOX_CANCEL;
	}
	
	public static String getMenuName () {
		
		return MENU_NAME;
	}
	
	public static String getMenuAbout () {
		
		return MENU_ABOUT;
	}
	
	public static String getMenuExit () {
		
		return MENU_EXIT;
	}
	
	public static String getAboutTitle () {
		
		return ABOUT_TITLE;
	}
	
	public static String getAboutDescription () {
		
		return ABOUT_DESCRIPTION;
	}

	public static String getAboutClassesNumer () {
		
		return ABOUT_NUMBER_CLASSES;
	}
	
	public static String getAboutMethodsNumber () {
		
		return ABOUT_NUMBER_METHODS;
	}
	
	public static String getAboutLinesNumber () {
		
		return ABOUT_NUMBER_CODELINES;
	}
	
	public static String getAboutClose () {
		
		return ABOUT_CLOSE;
	}
	
	public static String getTabTestTitle () {
		
		return TAB_TEST_TITLE;
	}
	
	public static String getTabStatTitle () {
		
		return TAB_STAT_TITLE;
	}

	public static String getTabSearchTitle () {
	
		return TAB_SEARCH_TITLE;
	}
	
	public static String getTabTestIntro () {
		
		return TAB_TEST_INTRO;
	}
	
	
	public static String getQuestionIntro () {
		
		return QUESTION_INTRO;
	}
	
	public static String getQuestion1 () {
		
		return QUESTION_1;
	}
	
	public static String getQuestion2 () {
			
		return QUESTION_2;
	}
	
	public static String getQuestion3 () {
		
		return QUESTION_3;
	}
	
	public static String getQuestion4 () {
		
		return QUESTION_4;
	}
	
	public static String getQuestion5 () {
		
		return QUESTION_5;
	}
	
	public static String getQuestion6 () {
		
		return QUESTION_6;
	}
	
	public static String getQuestion7 () {
		
		return QUESTION_7;
	}
	
	public static String getQuestion8 () {
		
		return QUESTION_8;
	}
	
	public static String getQuestion9 () {
		
		return QUESTION_9;
	}
	
	public static String getQuestion10 () {
		
		return QUESTION_10;
	}
	
	public static String getAnswer1 () {
			
		return ANSWER_1;
	}
	
	public static String getAnswer2 () {
		
		return ANSWER_2;
	}
	
	public static String getAnswer3 () {
		
		return ANSWER_3;
	}
	
	public static String getAnswer4 () {
		
		return ANSWER_4;
	}
	
	public static String getAnswer5 () {
		
		return ANSWER_5;
	}
	
	public static String getAnswer6 () {
		
		return ANSWER_6;
	}
	
	public static String getAnswer7 () {
		
		return ANSWER_7;
	}
	
	public static String getProgressStart () {
			
		return PROGRESS_START;
	}
	
	public static String getProgressTest () {
		
		return PROGRESS_TEST;
	}
	
	public static String getProgressEnd () {
		
		return PROGRESS_END;
	}
	
	public static String getTestStart () {
		
		return TEST_START;
	}
	
	public static String getTestNext () {
			
		return TEST_NEXT;
	}
	
	public static String getTestPrev () {
		
		return TEST_PREV;
	}
	
	public static String getTestCanc () {
		
		return TEST_CANC;
	}
	
	public static String getUsernameBoxTitle () {
		
		return USERNAME_BOX_TITLE;
	}
	
	public static String getUsernameBoxEmpty () {
			
		return USERNAME_BOX_EMPTY;
	}
	
	public static String getUsernameBoxInvalid () {
		
		return USERNAME_BOX_INVALID;
	}
	
	public static String getTestValueBoxTitle () {
			
		return TEST_VALUE_BOX_TITLE;
	}
	
	public static String getTestValueBoxEmpty () {
		
		return TEST_VALUE_BOX_EMPTY;
	}
	
	public static String getFeature1 () {
		
		return FEATURE_1;
	}
	
	public static String getFeature2 () {
			
		return FEATURE_2;
	}
	
	public static String getFeature3 () {
		
		return FEATURE_3;
	}
	
	public static String getFeature4 () {
		
		return FEATURE_4;
	}
	
	public static String getFeature5 () {
		
		return FEATURE_5;
	}
	
	public static String getUserStatTitle () {
		
		return USER_STAT_TITLE;
	}
	
	public static String getTestDone () {
		
		return TEST_DONE;
	}
	
	public static String getSearchIntro () {
			
		return SEARCH_INTRO;
	}
	
	public static String getSearchNoUser () {
		
		return SEARCH_NOUSER;
	}
	
	public static String getSearchTooMuch () {
		
		return SEARCH_TOOMUCH;
	}
	
	public static String getSearchBtnStart () {
		
		return SEARCH_BTN_START;
	}
	
	public static String getSearchBtnSelect () {
		
		return SEARCH_BTN_SELECT;
	}
	
	public static String getStatGosum () {
			
		return STAT_GOSUM;
	}
	
	public static String getStatGoaverage () {
		
		return STAT_GOAVERAGE;
	}
	
	public static String getStatTitlesum () {
		
		return STAT_TITLESUM;
	}
	
	public static String getStatTitleAverage () {
		
		return STAT_TITLEAVERAGE;
	}
	
	public static String getStatDev () {
		
		return STAT_DEV;
	}
	
	public static String getDelButton () {
			
		return DEL_BUTTON;
	}
	
	public static String getDelTitle () {
		
		return DEL_TITLE;
	}
	
	public static String getDelMessageConfirm () {
		
		return DEL_MESSAGE_CONFIRM;
	}
	
	public static String getDelMessageDone () {
		
		return DEL_MESSAGE_DONE;
	}
	
	public static String getBckButton () {
		
		return BCK_BUTTON;
	}
	
	public static String getBckTitle () {
		
		return BCK_TITLE;
	}
	
	public static String getBckMessageDone () {
		
		return BCK_MESSAGE_DONE;
	}
	
	public static String getImpButton () {
		
		return IMP_BUTTON;
	}
	
	public static String getImpTitle () {
		
		return IMP_TITLE;
	}
	
	public static String getImpMessageConfirm () {
		
		return IMP_MESSAGE_CONFIRM;
	}
	
	public static String getImpMessageWrongfile () {
		
		return IMP_MESSAGE_WRONGFILE;
	}
	
	public static String getImpDone () {
		
		return IMP_DONE;
	}
	
	public static String getBtnFile () {
		
		return BTN_FILE;
	}
	
	public static String getFileHead1 () {
			
		return FILE_HEAD_1;
	}
	
	public static String getFileHead2 () {
		
		return FILE_HEAD_2;
	}
	
	public static String getFileResetWarning () {
		
		return FILE_RESET_WARNING;
	}
	
	public static String getLanguageMenu () {
		
		return LANGUAGE_MENU;
	}
	
	public static String getMenuInfo () {
		
		return MENU_INFO;
	}
	
	public static String getMenuSession () {
		
		return MENU_SESSION;
	}
}
