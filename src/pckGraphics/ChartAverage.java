package pckGraphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import pckApplication.TabStatPanel;
import pckGlobalVars.LayoutVars;
import pckUtilities.TestController;

/*
 * It provides to give the chart with the average value
 * calculated from all user value
 */
@SuppressWarnings("serial")
public class ChartAverage extends JPanel {
	
	@SuppressWarnings("unused")
	private static TabStatPanel tabStatPanel;
	private static Graphics2D userChartGraphic;
	private static Float [][] convertedProfile = new Float [2][5];
	private DecimalFormat format = new DecimalFormat("0.00");
	private static boolean isEmpty = false;
	
	public ChartAverage(TabStatPanel tabStatPanel) {
		
		ChartAverage.tabStatPanel = tabStatPanel;	
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
		
		//Graph bars abscissa indications
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.drawString(TestController.getFeature(i), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 50) + (i * (LayoutVars.getChartWidth() / 5)), LayoutVars.getChartStartY() + LayoutVars.getChartHeight() + (LayoutVars.getChartHeight() / 10));
		}
		
		//Graph bars and numeric values in the near of each bar
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.setColor(LayoutVars.getChartUserBarsColors()[i]);
			userChartGraphic.fillRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 30) + (int)(i * LayoutVars.getChartWidth() / 5.1), LayoutVars.getChartStartY() + LayoutVars.getChartHeight() - (int)(convertedProfile[0][i] * (LayoutVars.getChartHeight() / 7) * 1.00), (int)(LayoutVars.getChartWidth() / 7), (int)(convertedProfile[0][i] * (LayoutVars.getChartHeight() / 7) * 1.00));
			
			if (ChartAverage.isEmpty == false) {
				
				userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
				userChartGraphic.drawString(String.valueOf(format.format(convertedProfile[0][i])), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 12) + (int)(i * LayoutVars.getChartWidth() / 5.07), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartHeight() * 1.05) - (int)(convertedProfile[0][i] * (LayoutVars.getChartHeight() / 7)));
			}			
			userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
			userChartGraphic.fillRect(LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 30) + (int)(i * LayoutVars.getChartWidth() / 5.1) - 3, LayoutVars.getChartStartY() + LayoutVars.getChartHeight() - (int)(convertedProfile[1][i] * (LayoutVars.getChartHeight() / 7) * 1.03), (int)(LayoutVars.getChartWidth() / 7) + 6, 3);
				
			userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
			userChartGraphic.drawString(String.valueOf(format.format(convertedProfile[1][i])), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth() / 12) + (int)(i * LayoutVars.getChartWidth() / 5.07), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartHeight() * 1.05) - (int)(convertedProfile[1][i] * (LayoutVars.getChartHeight() / 7)) - 18);
		}
		
		//Legenda indications
		for (int i = 0; i < 5; i ++) {
			
			userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
			userChartGraphic.drawString(TestController.getFeature(i) + ": ", LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 22), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartStartY() * 2) + (i * 50));
			userChartGraphic.drawString(format.format(convertedProfile[0][i]) + "-" + format.format(convertedProfile[1][i]), LayoutVars.getChartStartX() + (LayoutVars.getChartWidth()) + (LayoutVars.getChartWidth() / 22), LayoutVars.getChartStartY() + (int)(LayoutVars.getChartStartY() * 2) + (i * 50) + 20);
		}
		
		//Fix for a problem in case of a bar with maximum height
		userChartGraphic.setColor(LayoutVars.getChartLegendaColor());
		userChartGraphic.drawLine(LayoutVars.getChartStartX(), LayoutVars.getChartStartY(), (LayoutVars.getChartStartX() + LayoutVars.getChartWidth()), LayoutVars.getChartStartY());	
	}
	
	public void setConvertedProfile(Float paramConvertedProfile[][]) {
		
		convertedProfile = paramConvertedProfile;
		
		if (convertedProfile[0][0].toString().equals("NaN")) {
			
			ChartAverage.isEmpty = true;
			for (int i = 0; i < 2; i ++) {
				for (int j = 0; j < 5; j ++) {
					convertedProfile[i][j] = (float)(0.0);
				}
			}
		}
	}
}
