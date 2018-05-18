package pckGlobalVars;

/*
 * Store the language chosen by the user at the applicaton start
 */
public class SessionVars {
	
	public enum Language {en, de, it};
	static Language ActiveLanguage = Language.en;
	
	public static void setLanguage(String LanguageCode) {
		
		switch (LanguageCode)
		{
			case "en":
				ActiveLanguage = Language.en;
				break;				
			case "it":
				ActiveLanguage = Language.it;
				break;				
			case "de":
				ActiveLanguage = Language.de;
				break;				
			default:
				break;
		}
	}
	
	public static Language getLanguage() {
		
		return ActiveLanguage;
	}
}
