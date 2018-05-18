package pckGlobalVars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/*
 * It stores all layout vars; nothing in the application ha an absolute
 * independent value but the vars are all depending from the first 'main
 * width value', such that is possible change a layout parameter in only
 * one point of the code and all elements related to this parameter
 * changed keeping the correct proportions. This is useful in particular
 * for colors and fonts. In the future, is possible easily implement
 * a version with bigger elements (for user with some physical problems)
 * only changing a very short number of values.
 */
public class LayoutVars {
	
	static final int MAIN_WIDTH = 1200;
	static final int MAIN_HEIGHT = (int)(MAIN_WIDTH * 0.6);
	static final int INTRO_WIDTH = (int)(MAIN_WIDTH * 0.5);
	static final int INTRO_HEIGHT = (int)(INTRO_WIDTH * 0.6);
	static final int ABOUT_WIDTH = (int)(MAIN_WIDTH * 0.4);
	static final int ABOUT_HEIGHT = (int)(ABOUT_WIDTH * 0.65);
	static final int TAB_PANEL_WIDTH = (int)(MAIN_WIDTH * 0.95);
	static final int TAB_PANEL_HEIGHT = (int)(TAB_PANEL_WIDTH * 0.53);	
	static final int PROGRESS_PANEL_WIDTH = (int)(TAB_PANEL_WIDTH * 0.95);
	static final int PROGRESS_PANEL_HEIGHT = (int)(TAB_PANEL_WIDTH * 0.0769);
	static final int PROGRESS_BAR_WIDTH = (int)(TAB_PANEL_WIDTH * 0.912);
	static final int PROGRESS_BAR_HEIGHT = (int)(TAB_PANEL_WIDTH * 0.01);
	static final Double PROGRESS_STEP_WIDTH = (PROGRESS_BAR_WIDTH * 0.000088);
	
	static final int PROGRESS_STEP_OFFSET = (int)(PROGRESS_BAR_WIDTH * 0.0217);
	static final int CHART_PANEL_WIDTH = (int)(TAB_PANEL_WIDTH * 0.90);
	static final int CHART_PANEL_HEIGHT = (int)(TAB_PANEL_HEIGHT * 0.60);
	static final int CHART_START_X = (int)(CHART_PANEL_WIDTH * 0.04);
	static final int CHART_START_Y = (int)(CHART_PANEL_HEIGHT * 0.05);
	static final int CHART_WIDTH = (int)(CHART_PANEL_WIDTH * 0.70);
	static final int CHART_HEIGHT = (int)(CHART_PANEL_HEIGHT * 0.75);
	
	static final Dimension BTN_SIZE = new Dimension (150,35);
	
	static final Font TITLE_FONT = new Font ("Serif", Font.ITALIC + Font.BOLD, INTRO_WIDTH/15);
	static final Font SUBTITLE_FONT = new Font ("Serif", Font.BOLD, INTRO_WIDTH/30);
	static final Font CREDIT_FONT = new Font ("Serif", Font.BOLD, INTRO_WIDTH/45 + 1);
	static final Font BUTTON_FONT = new Font ("Verdana", Font.BOLD, INTRO_WIDTH/45);
	static final Font HEADER_FONT = new Font ("Calibri", Font.PLAIN, INTRO_WIDTH/35);
	static final Font PARAGRAPH_FONT = new Font ("Calibri", Font.PLAIN, INTRO_WIDTH/35);
	static final Font GRAPH_FONT = new Font ("Calibri", Font.PLAIN, INTRO_WIDTH/45);
	static final Font PROGRESS_FONT = new Font ("Calibri", Font.BOLD, INTRO_WIDTH/48);
	static final Font CHART_AXIS_FONT = new Font ("Calibri", Font.BOLD, 12);
	
	static final Color INTRO_BACK_COLOR = new Color(27, 33, 43);
	static final Color INTRO_TEXT_COLOR = new Color(227,234,244);
	static final Color MAIN_BACK_COLOR = new Color(27, 33, 43);
	static final Color TAB_BACK_COLOR = new Color(255,255,255);
	static final Color TAB_TEXT_COLOR = new Color(0,0,0);
	static final Color PROGRESS_DONE_COLOR = new Color(71,79,72);
	static final Color PROGRESS_TODO_COLOR = new Color(181,186,189);
	static final Color CHART_LEGENDA_COLOR = new Color(58, 58, 58);
	static final Color[] CHART_USER_BARS_COLORS = {new Color (255,0,0), new Color(60,178,95), new Color(229,50,190), new Color(55,130,200), new Color(255,191,0)};
	static final Color CHART_BACK_COLOR = new Color(234, 237, 239);
	
	public static int getIntroWidth() {
		
		return INTRO_WIDTH;
	}
	
	public static int getIntroHeight() {
		
		return INTRO_HEIGHT;
	}
	
	public static int getMainWidth() {
		
		return MAIN_WIDTH;
	}
	
	public static int getMainHeight() {
		
		return MAIN_HEIGHT;
	}
	
	public static int getAboutWidth() {
		
		return ABOUT_WIDTH;
	}

	public static int getAboutHeight() {
		
		return ABOUT_HEIGHT;
	}
	
	public static int getTabPanelWidth() {
			
		return TAB_PANEL_WIDTH;
	}
	
	public static int getTabPanelHeight() {
		
		return TAB_PANEL_HEIGHT;
	}
	
	public static int getProgressPanelWidth() {
			
		return PROGRESS_PANEL_WIDTH;
	}
	
	public static int getProgressPanelHeight() {
		
		return PROGRESS_PANEL_HEIGHT;
	}
	
	public static int getProgressBarWidth() {
		
		return PROGRESS_BAR_WIDTH;
	}
	
	public static int getProgressBarlHeight() {
		
		return PROGRESS_BAR_HEIGHT;
	}
	
	public static Double getProgressStepWidth() {
		
		return PROGRESS_STEP_WIDTH;
	}
	
	public static int getProgressStepOffset() {
		
		return PROGRESS_STEP_OFFSET;
	}
	
	public static int getChartPanelWidth() {
			
		return CHART_PANEL_WIDTH;
	}
	
	public static int getChartPanelHeight() {
		
		return CHART_PANEL_HEIGHT;
	}
	
	public static int getChartStartX() {
		
		return CHART_START_X;
	}
	
	public static int getChartStartY() {
	
	return CHART_START_Y;
	}
	
	public static int getChartWidth() {
		
		return CHART_WIDTH;
	}
	
	public static int getChartHeight() {
	
		return CHART_HEIGHT;
	}
	
	public static Dimension getButtonDimensions() {
		
		return BTN_SIZE;
	}
	
	public static Font getTitleFont() {
		
		return TITLE_FONT;
	}
	
	public static Font getSubTitleFont() {
		
		return SUBTITLE_FONT;
	}
	
	public static Font getCreditFont() {
		
		return CREDIT_FONT;
	}
	
	public static Font getButtonFont() {
		
		return BUTTON_FONT;
	}
	
	public static Font getHeaderFont() {
		
		return HEADER_FONT;
	}
	
	public static Font getParagraphFont() {
		
		return PARAGRAPH_FONT;
	}
	
	public static Font getGraphFont() {
		
		return GRAPH_FONT;
	}
	
	public static Font getProgressFont() {
		
		return PROGRESS_FONT;
	}
	
	public static Font getChartAxisFont() {
		
		return CHART_AXIS_FONT;
	}
	
	public static Color getIntroBackColor() {
		
		return INTRO_BACK_COLOR;
	}
	
	public static Color getIntroTextColor() {
		
		return INTRO_TEXT_COLOR;
	}
	
	public static Color getMainBackColor() {
		
		return MAIN_BACK_COLOR;
	}
	
	public static Color getTabBackColor() {
		
		return TAB_BACK_COLOR;
	}
	
	public static Color getTabTextColor() {
		
		return TAB_TEXT_COLOR;
	}
	
	public static Color getProgressDoneColor() {
			
		return PROGRESS_DONE_COLOR;
	}
	
	public static Color getProgressTodoColor() {
		
		return PROGRESS_TODO_COLOR;
	}
	
	public static Color getChartLegendaColor() {
		
		return CHART_LEGENDA_COLOR;
	}
	
	public static Color[] getChartUserBarsColors() {
		
		return CHART_USER_BARS_COLORS;
	}
	
	public static Color getChartBackColor() {
		
		return CHART_BACK_COLOR;
	}
}
