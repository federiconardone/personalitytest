package pckGraphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import pckApplication.TabStatPanel;
import pckGlobalVars.LayoutVars;
import pckUtilities.ColorChanger;
import pckUtilities.TestController;

/*
 * It creates the chart of the sum of the personality
 * values of all users
 */
@SuppressWarnings("serial")
public class ChartSummary extends JPanel {
	
	@SuppressWarnings("unused")
	private static TabStatPanel tabStatPanel;
	private static Graphics2D userChartGraphic;
	private DecimalFormat format = new DecimalFormat("0.00");
	
	private static Float [][] convertedProfile = new Float[3][5];
	
	public ChartSummary(TabStatPanel tabStatPanel) {
		
		ChartSummary.tabStatPanel = tabStatPanel;	
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
		for (int i = 0; i < 11; i ++) {
			
			userChartGraphic.drawLine(LayoutVars.getChartStartX() - (LayoutVars.getChartStartX() / 4), LayoutVars.getChartStartY() + (i * LayoutVars.getChartHeight() / 10), LayoutVars.getChartStartX() + LayoutVars.getChartWidth(), LayoutVars.getChartStartY() + (i * LayoutVars.getChartHeight() / 10));
			userChartGraphic.drawString((10 - i) + "0%", LayoutVars.getChartStartX() - (LayoutVars.getChartStartX()) - 0, LayoutVars.getChartStartY() + (i * LayoutVars.getChartHeight() / 10) + (LayoutVars.getChartStartY()/5));
		}
		
		//Graph legenda
		userChartGraphic.setColor(LayoutVars.getChartBackColor());
		userChartGraphic.fillRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 37), LayoutVars.getChartStartY(), LayoutVars.getChartWidth() / 35 * 12, LayoutVars.getChartHeight());
		userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
		userChartGraphic.drawRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 37), LayoutVars.getChartStartY(), LayoutVars.getChartWidth() / 35 * 12, LayoutVars.getChartHeight());
		
		//Graph bars abscissa indications
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.drawString(TestController.getFeature(i), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 50) + (i * (LayoutVars.getChartWidth() / 5)), LayoutVars.getChartStartY() + LayoutVars.getChartHeight() + (LayoutVars.getChartHeight() / 10));
		}
		
		//Graph bars and numeric values in the near of each bar
		for (int i = 0; i < 5; i ++) {
			
			for (int j = 0; j < 3; j ++) {
				
				userChartGraphic.setColor(ColorChanger.ColorFader(LayoutVars.getChartUserBarsColors()[i], j));
				
				userChartGraphic.fillRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 30) + (int)(i * LayoutVars.getChartWidth() / 5.1)  + (j * 41), LayoutVars.getChartStartY() + LayoutVars.getChartHeight() - (int)(convertedProfile[j][i] * (LayoutVars.getChartHeight() / 100) * 1.35), (int)(LayoutVars.getChartWidth() / 18), (int)(convertedProfile[j][i] * (LayoutVars.getChartHeight() / 100) * 1.35));
				
				int correctionOffset = 0;
				if (convertedProfile[j][i] == (float)(0.0))
					correctionOffset += 16;
				userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
				userChartGraphic.drawString(String.valueOf(format.format(convertedProfile[j][i])), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 28) + (j * 41) + (int)(i * LayoutVars.getChartWidth() / 5.1), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartHeight() * 1.05) - (int)(convertedProfile[j][i] * (LayoutVars.getChartHeight() / 100) * 1.35) - correctionOffset);				
			}	
		}
		
		//Legenda indications
		String summaryData;
		for (int i = 0; i < 5; i ++) {
			
			summaryData = "";
			for (int j = 0; j < 3; j ++) {
				summaryData += String.valueOf(format.format(convertedProfile[j][i])) + "%";
				if (j<2)
					summaryData += "/";
			}
			
			userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
			userChartGraphic.drawString(TestController.getFeature(i) + ": ", LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 22), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartStartY() * 2) + (i * 50));
			userChartGraphic.drawString(summaryData /*convertedProfile[0][i]*/, LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 22), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartStartY() * 2) + (i * 50) + 20);
		}
		
		//Fix for a problem in case of a bar with maximum height
		userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
		userChartGraphic.drawLine(LayoutVars.getChartStartX(), LayoutVars.getChartStartY(), (LayoutVars.getChartStartX() + LayoutVars.getChartWidth()), LayoutVars.getChartStartY());	
	}
	
	public void setConvertedProfile(Float paramConvertedProfile[][]) {
		
		convertedProfile = paramConvertedProfile;
		
		if (convertedProfile[0][0].toString().equals("NaN")) {
			
			for (int i = 0; i < 3; i ++) {
				for (int j = 0; j < 5; j ++) {
					convertedProfile[i][j] = (float)(0.0);
				}
			}
		}
	}	
}