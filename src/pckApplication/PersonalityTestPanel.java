package pckApplication;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pckGlobalVars.LayoutVars;
import pckUtilities.StringLocalizer;

/*
 * It creates the main panel of the application, with all elements.
 * Parent: personality Test
 * Child: all panel and all it's child elements
 */
public class PersonalityTestPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String[] TABS = {StringLocalizer.getTabTestTitle(), StringLocalizer.getTabStatTitle(), StringLocalizer.getTabSearchTitle()};
	private static JPanel mainPanel = new JPanel();
	
	private static TabTestPanel tabTestPanel;
	private static TabStatPanel tabStatPanel;
	private static TabSearchPanel tabSearchPanel;
	private static JTabbedPane tabbedPane;
    
    
	@SuppressWarnings("unused")
	public PersonalityTestPanel() throws FileNotFoundException {
		
		setLayout(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setForeground(LayoutVars.getTabTextColor());
        
        tabTestPanel = new TabTestPanel(TABS[0], this);
        tabStatPanel = new TabStatPanel(TABS[1], this);
        tabSearchPanel = new TabSearchPanel(TABS[2], this);
        
        tabTestPanel.setName(TABS[0]);
        tabStatPanel.setName(TABS[1]);
        tabSearchPanel.setName(TABS[2]);
        
        ChangeListener tabSelectListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
              JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
              int index = sourceTabbedPane.getSelectedIndex();
              try {
				TabStatPanel.ListResultUpdate();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              TabSearchPanel.ListResultUpdate();
            }			
        };
        
        tabbedPane.addChangeListener(tabSelectListener);
        
        tabbedPane.add(tabTestPanel);
        tabbedPane.add(tabStatPanel);
        tabbedPane.add(tabSearchPanel);
        
        mainPanel.setBackground(LayoutVars.getMainBackColor());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
	}
	
	public static void TextUpdater() {
    	
		tabbedPane.setTitleAt(0, StringLocalizer.getTabTestTitle());
		tabbedPane.setTitleAt(1, StringLocalizer.getTabStatTitle());
		tabbedPane.setTitleAt(2, StringLocalizer.getTabSearchTitle());
	}
}
