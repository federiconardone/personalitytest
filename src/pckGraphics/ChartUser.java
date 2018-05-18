package pckGraphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import pckApplication.TabTestPanel;
import pckGlobalVars.LayoutVars;
import pckUtilities.TestController;

/*
 * It creates the chart of each single-user
 * profile
 */
@SuppressWarnings("serial")
public class ChartUser extends JPanel {
	
	@SuppressWarnings("unused")
	private static TabTestPanel tabTestPanel;
	private static Graphics2D userChartGraphic;
	private static Float [] convertedProfile = null;
	@SuppressWarnings("unused")
	private static String userName = "";
	
	public ChartUser(TabTestPanel tabTestPanel) {
		
		ChartUser.tabTestPanel = tabTestPanel;	
		this.setPreferredSize(new Dimension(LayoutVars.getChartPanelWidth(),LayoutVars.getChartPanelHeight()));
	}
	
	public void paint(Graphics g)
	{
		userChartGraphic = (Graphics2D)g;
		
		//Graph background
		userChartGraphic.setColor(LayoutVars.getChartBackColor());
		userChartGraphic.fillRect(LayoutVars.getChartStartX(), LayoutVars.getChartStartY(), LayoutVars.getChartWidth(), LayoutVars.getChartHeight());
		userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
		userChartGraphic.drawRect(LayoutVars.getChartStartX(), LayoutVars.getChartStartY(), LayoutVars.getChartWidth(), LayoutVars.getChartHeight());
		userChartGraphic.setFont(LayoutVars.getChartAxisFont()); 
		
		//Graph grid with the axis indications
		for (int i = 0; i < 8; i ++) {
			
			userChartGraphic.drawLine(LayoutVars.getChartStartX() - (LayoutVars.getChartStartX() / 4), LayoutVars.getChartStartY() + (i * LayoutVars.getChartHeight() / 7), LayoutVars.getChartStartX() + LayoutVars.getChartWidth(), LayoutVars.getChartStartY() + (i * LayoutVars.getChartHeight() / 7));
			userChartGraphic.drawString((7 - i) + ".0", LayoutVars.getChartStartX() - (LayoutVars.getChartStartX()), LayoutVars.getChartStartY() + (i * LayoutVars.getChartHeight() / 7) + (LayoutVars.getChartStartY()/5));
		}
		
		//Graph legenda
		userChartGraphic.setColor(LayoutVars.getChartBackColor());
		userChartGraphic.fillRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 37), LayoutVars.getChartStartY(), LayoutVars.getChartWidth() / 35 * 12, LayoutVars.getChartHeight());
		userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
		userChartGraphic.drawRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 37), LayoutVars.getChartStartY(), LayoutVars.getChartWidth() / 35 * 12, LayoutVars.getChartHeight());
		
		//Graph bars indications
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.drawString(TestController.getFeature(i), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 50) + (i * (LayoutVars.getChartWidth() / 5)), LayoutVars.getChartStartY() + LayoutVars.getChartHeight() + (LayoutVars.getChartHeight() / 10));
		}
		
		//Graph bars and numeric values in the near of each bar
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.setColor(LayoutVars.getChartUserBarsColors()[i]);
			userChartGraphic.fillRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 30) + (int)(i * LayoutVars.getChartWidth() / 5.1), LayoutVars.getChartStartY() + LayoutVars.getChartHeight() - (int)(convertedProfile[i] * (LayoutVars.getChartHeight() / 7) * 1.03), (int)(LayoutVars.getChartWidth() / 7), (int)(convertedProfile[i] * (LayoutVars.getChartHeight() / 7) * 1.03));
			userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
			userChartGraphic.drawString(String.valueOf(convertedProfile[i]), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 12) + (int)(i * LayoutVars.getChartWidth() / 5.1), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartHeight() * 1.05) - (int)(convertedProfile[i] * (LayoutVars.getChartHeight() / 7)));
			
		}
		
		//Legenda indications
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
			userChartGraphic.drawString(TestController.getFeature(i) + ": ", LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 22), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartStartY() * 2) + (i * 50));
			userChartGraphic.drawString(String.valueOf(convertedProfile[i]), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 22), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartStartY() * 2) + (i * 50) + 20);
		}
		
		//Fix for a problem in case of a bar with maximum height
		userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
		userChartGraphic.drawLine(LayoutVars.getChartStartX(), LayoutVars.getChartStartY(), (LayoutVars.getChartStartX() + LayoutVars.getChartWidth()), LayoutVars.getChartStartY());	
	}
	
	public void setConvertedProfile(Float paramConvertedProfile[]) {
		
		convertedProfile = paramConvertedProfile;
	}
	
	public void setUserName(String paramUserName) {
		
		userName = paramUserName;
	}
}
