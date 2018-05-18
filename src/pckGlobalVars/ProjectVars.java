package pckGlobalVars;

/*
 * Store the application variables, given in the 'about' panel
 */
public class ProjectVars {
	
	static final int CLASSES_NUMBER = 29;
	static final int METHODS_NUMBER = 230;
	static final int CODELINES_NUMBER = 4202;
	static final String DATA_FILE_PATH = "Res/Data/user-data.csv";
	
	public static int getClassesNumber() {
		
		return CLASSES_NUMBER;
	}
	
	public static int getMethodsNumber() {
		
		return METHODS_NUMBER;
	}

	public static int getCodelinesNumber() {
		
		return CODELINES_NUMBER;
	}
	
	public static String getDataFilePath() {
		
		return DATA_FILE_PATH;
	}
}
