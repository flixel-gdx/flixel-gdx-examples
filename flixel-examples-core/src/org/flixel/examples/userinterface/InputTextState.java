package org.flixel.examples.userinterface;

import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;

/**
 * 
 * @author Ka Wing Chin
 */
public class InputTextState extends Test
{
	@Override
	public void create()
	{
		super.create();
		FlxInputText inputText = new FlxInputText(10, 60, "INPUT TEXT");
		inputText.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		inputText.textField.setFormat(FntRobotoRegular, 18);
		inputText.setMaxLength(26);
		add(inputText);
		
		inputText = new FlxInputText(10, 120, "NUMERIC ONLY");
		inputText.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		inputText.textField.setFormat(FntRobotoRegular, 18);
		inputText.setFilterMode(FlxInputText.ONLY_NUMERIC);
		inputText.setMaxLength(26);
		add(inputText);
		
		inputText = new FlxInputText(10, 180, "UPPER CASE");
		inputText.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		inputText.textField.setFormat(FntRobotoRegular, 18);
		inputText.setForceCase(FlxInputText.UPPER_CASE);
		inputText.setMaxLength(26);
		add(inputText);
		
		inputText = new FlxInputText(10, 240, "PASSWORD MODE");
		inputText.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		inputText.textField.setFormat(FntRobotoRegular, 18);
		inputText.setPasswordMode(true);
		inputText.setMaxLength(26);
		add(inputText);
		
		FlxDialogBox dialog = new FlxDialogBox(10, 300, 300, "DIALOG BOX", "Enter your message");
		dialog.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		dialog.textField.setFormat(FntRobotoRegular, 18);
		dialog.setMaxLength(26);
		add(dialog);
	}
}
