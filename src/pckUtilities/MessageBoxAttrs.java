package pckUtilities;

import javax.swing.JOptionPane;
import pckApplication.TabStatPanel;
import pckBusinessObjects.BusinessObject;
import pckBusinessObjects.DataRestore;

public class MessageBoxAttrs {

	protected String [] boxButtons;
	protected String boxTitle;
	protected String boxMessage;
	protected String boxType;
	protected int exitValue;
	protected int JOPane;
	
	public MessageBoxAttrs (String boxType) {
		
		this.boxType = boxType;
		
		switch (boxType) {
		
		case "Exit":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
			this.boxTitle = StringLocalizer.getExitBoxTitle();
			this.boxMessage = getMessage(StringLocalizer.getExitBoxMessage());
			this.JOPane = JOptionPane.YES_NO_CANCEL_OPTION;
			break;
			
		case "InvalidUsername":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm()};
			this.boxTitle = StringLocalizer.getUsernameBoxTitle();
			this.boxMessage = getMessage(StringLocalizer.getUsernameBoxInvalid());
			this.JOPane = JOptionPane.OK_OPTION;
			break;

		case "EmptyUsername":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm()};
			this.boxTitle = StringLocalizer.getUsernameBoxTitle();
			this.boxMessage = getMessage(StringLocalizer.getUsernameBoxEmpty());
			this.JOPane = JOptionPane.OK_OPTION;
			break;

		case "EmptyResult":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm()};
			this.boxTitle = StringLocalizer.getTestValueBoxTitle();
			this.boxMessage = getMessage(StringLocalizer.getTestValueBoxEmpty());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
			
		case "EmptySearch":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm()};
			this.boxTitle = StringLocalizer.getUsernameBoxTitle();
			this.boxMessage = getMessage(StringLocalizer.getSearchNoUser());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
		
		case "ToomuchSearch":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm()};
			this.boxTitle = StringLocalizer.getUsernameBoxTitle();
			this.boxMessage = getMessage(StringLocalizer.getSearchTooMuch());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
			
		case "DelConfirm":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
			this.boxTitle = StringLocalizer.getDelTitle();
			this.boxMessage = getMessage(StringLocalizer.getDelMessageConfirm());
			this.JOPane = JOptionPane.YES_NO_CANCEL_OPTION;
			break;
		case "ResetConfirm":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
			this.boxTitle = StringLocalizer.getDelTitle();
			this.boxMessage = getMessage(StringLocalizer.getFileResetWarning());
			this.JOPane = JOptionPane.YES_NO_CANCEL_OPTION;
			break;
			
		case "DelDone":
			this.boxButtons = new String []{StringLocalizer.getAboutClose()};
			this.boxTitle = StringLocalizer.getDelTitle();
			this.boxMessage = getMessage(StringLocalizer.getDelMessageDone());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
		
		case "BackupDone":
			this.boxButtons = new String []{StringLocalizer.getAboutClose()};
			this.boxTitle = StringLocalizer.getBckTitle();
			this.boxMessage = getMessage(StringLocalizer.getBckMessageDone());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
		
		case "RestoreConfirm":
			this.boxButtons = new String []{StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
			this.boxTitle = StringLocalizer.getImpTitle();
			this.boxMessage = getMessage(StringLocalizer.getImpMessageConfirm());
			this.JOPane = JOptionPane.YES_NO_CANCEL_OPTION;
			break;
			
		case "RestoreWrong":
			this.boxButtons = new String []{StringLocalizer.getAboutClose()};
			this.boxTitle = StringLocalizer.getImpTitle();
			this.boxMessage = getMessage(StringLocalizer.getImpMessageWrongfile());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
			
		case "RestoreDone":
			this.boxButtons = new String []{StringLocalizer.getAboutClose()};
			this.boxTitle = StringLocalizer.getImpTitle();
			this.boxMessage = getMessage(StringLocalizer.getImpDone());
			this.JOPane = JOptionPane.OK_OPTION;
			break;
		
		}
	}
	
	public final void getFunction(int btnPosition) {
		
		if (btnPosition == 0) {
			
			try {
			
				switch (boxType) {
				
				case "Exit":
					System.exit(0);
					break;
					
				case "DelConfirm":
					BusinessObject.ResetResults();
					TabStatPanel.ListResultUpdate();
					break;
					
				case "ResetConfirm":
					BusinessObject.ResetResults();
					break;
					
				case "RestoreConfirm":
					DataRestore dataRestore = new DataRestore();
					dataRestore.RestoreMaker();
					break;
				}
			} catch(Exception e) {e.printStackTrace();}		
		}
	}
	
	public final String getMessage (String boxMessage) {
		
		String outMessage = "";
		outMessage = "<html><body><p style='width: 300px;'>" + boxMessage + "</p></body></html>";
		return outMessage;
	}
	
	public final int getValue() {
		int messageValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, this.JOPane, JOptionPane.QUESTION_MESSAGE, null, this.boxButtons, this.boxButtons[this.boxButtons.length-1]);
		return messageValue;
	}
}
