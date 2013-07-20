package org.flixel.examples.userinterface;

import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;

/**
 * 
 * @author Ka Wing Chin
 */
public class TextAreaState extends Test
{
	
	@Override
	public void create()
	{
		super.create();
		FlxInputText inputText = new FlxInputText(10, 30, null, "TEXT AREA - MAX. 3 LINES", 250, 70);
		inputText.textfield.offset.y = -10;
		inputText.setMaxLength(0);
		inputText.setMaxLines(3);
		add(inputText);
		
		inputText = new FlxInputText(10, 130, null, "MAX. 2 LINES + MAX. 26 CHARS", 250, 49);
		inputText.textfield.offset.y = -10;
		inputText.setMaxLength(26);
		inputText.setMaxLines(2);
		add(inputText);
		
		inputText = new FlxInputText(10, 210, null, "NINE PATCH", 140, 91);
		inputText.textfield.offset.y = -10;
		inputText.setMaxLength(0);
		inputText.setMaxLines(4);
		inputText.textfield.setFormat(FntRobotoRegular, 18);
		inputText.setText("Now you can create text balloons in your games!");
		add(inputText);
		
		inputText = new FlxInputText(165, 210, null, "DISABLED", 140, 91);
		inputText.textfield.offset.y = -10;
		inputText.setMaxLength(0);
		inputText.setMaxLines(4);
		inputText.setEnable(false);
		inputText.setText("flixel-gdx's awesome UI components.");
		add(inputText);
		
		FlxDialogBox dialog = new FlxDialogBox(10, 330, null, "DIALOG", 200, 91);
		dialog.textfield.offset.y = -10;
		dialog.setMaxLength(0);
		dialog.setMaxLines(4);
		add(dialog);
	}
}
