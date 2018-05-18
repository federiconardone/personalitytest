package pckUtilities;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import pckApplication.TabStatPanel;
import pckBusinessObjects.BusinessObject;
import pckBusinessObjects.DataRestore;

/*
 * Manage all message box and relative behavior; manage also
 * the message layout using some HTML simple properties
 */
public class MessageBoxMaker {
	
	//Manage the text layout using HTML
	private static String MessageRender (String boxMessage) {
		
		String outMessage = "";
		outMessage = "<html><body><p style='width: 300px;'>" + boxMessage + "</p></body></html>";
		return outMessage;
	}
	
	public static void MessageBoxExit() {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
		String boxTitle = StringLocalizer.getExitBoxTitle();
		String boxMessage = MessageRender(StringLocalizer.getExitBoxMessage());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[1]);
		switch (exitValue){
		case 0:
			System.exit(0);
			break;
		case 1:
			break;
		}
	}
	
	public static void InvalidUsername () {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm()};
		String boxTitle = StringLocalizer.getUsernameBoxTitle();
		String boxMessage = MessageRender(StringLocalizer.getUsernameBoxInvalid());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void EmptyUsername () {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm()};
		String boxTitle = StringLocalizer.getUsernameBoxTitle();
		String boxMessage = MessageRender(StringLocalizer.getUsernameBoxEmpty());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void EmptyResult () {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm()};
		String boxTitle = StringLocalizer.getTestValueBoxTitle();
		String boxMessage = MessageRender(StringLocalizer.getTestValueBoxEmpty());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void EmptySearch () {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm()};
		String boxTitle = StringLocalizer.getUsernameBoxTitle();
		String boxMessage = MessageRender(StringLocalizer.getSearchNoUser());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void ToomuchSearch () {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm()};
		String boxTitle = StringLocalizer.getUsernameBoxTitle();
		String boxMessage = MessageRender(StringLocalizer.getSearchTooMuch());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void DelConfirm() throws FileNotFoundException {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
		String boxTitle = StringLocalizer.getDelTitle();
		String boxMessage = MessageRender(StringLocalizer.getDelMessageConfirm());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[1]);
		switch (exitValue){
		case 0:
			BusinessObject.ResetResults();
			TabStatPanel.ListResultUpdate();
			break;
		case 1:
			break;
		}
	}
	
	public static void ResetConfirm() throws FileNotFoundException {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
		String boxTitle = StringLocalizer.getDelTitle();
		String boxMessage = MessageRender(StringLocalizer.getFileResetWarning());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[1]);
		switch (exitValue){
		case 0:
			BusinessObject.ResetResults();
			break;
		case 1:
			System.exit(0);
			break;
		}
	}
	
	public static void DelDone () {
		
		String[] boxButtons = {StringLocalizer.getAboutClose()};
		String boxTitle = StringLocalizer.getDelTitle();
		String boxMessage = MessageRender(StringLocalizer.getDelMessageDone());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void BackupDone () {
		
		String[] boxButtons = {StringLocalizer.getAboutClose()};
		String boxTitle = StringLocalizer.getBckTitle();
		String boxMessage = MessageRender(StringLocalizer.getBckMessageDone());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void RestoreConfirm() throws IOException {
		
		String[] boxButtons = {StringLocalizer.getExitBoxConfirm(), StringLocalizer.getExitBoxCancel()};
		String boxTitle = StringLocalizer.getImpTitle();
		String boxMessage = MessageRender(StringLocalizer.getImpMessageConfirm());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[1]);
		DataRestore dataRestore = new DataRestore();
		switch (exitValue){
		case 0:
			dataRestore.RestoreMaker();
			break;
		case 1:
			break;
		}
	}
	
	public static void RestoreWrong () {
		
		String[] boxButtons = {StringLocalizer.getAboutClose()};
		String boxTitle = StringLocalizer.getImpTitle();
		String boxMessage = MessageRender(StringLocalizer.getImpMessageWrongfile());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
	
	public static void RestoreDone () {
		
		String[] boxButtons = {StringLocalizer.getAboutClose()};
		String boxTitle = StringLocalizer.getImpTitle();
		String boxMessage = MessageRender(StringLocalizer.getImpDone());
		int exitValue = JOptionPane.showOptionDialog(null, boxMessage, boxTitle, JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, boxButtons, boxButtons[0]);
		switch (exitValue){
		case 0:
			break;
		}
	}
}
