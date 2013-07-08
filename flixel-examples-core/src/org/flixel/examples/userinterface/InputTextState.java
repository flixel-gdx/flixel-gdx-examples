package org.flixel.examples.userinterface;

import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;
import org.flixel.ui.FlxUISkin;

/**
 * 
 * @author Ka Wing Chin
 */
public class InputTextState extends Test
{
	private final String ImgTextField = "examples/userinterface/holo_dark/textfield.png";
	
	@Override
	public void create()
	{
		super.create();
		
		// Setup skin
		FlxUISkin skin = new FlxUISkin();
		skin.DISABLED = 1;
		skin.HIGHLIGHT = 2;
		skin.PRESSED = 2;
		skin.ACTIVE_NORMAL = 2;
		skin.labelPosition = FlxUISkin.LABEL_TOP;
		skin.setImage(ImgTextField, 328, 32);
		skin.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		FlxInputText inputText = createInputText(10, 60, skin, "INPUT TEXT");
		add(inputText);
		
		inputText = createInputText(10, 120, skin, "NUMERIC ONLY");
		inputText.setFilterMode(FlxInputText.ONLY_NUMERIC);
		add(inputText);
		
		inputText = createInputText(10, 180, skin, "UPPER CASE");
		inputText.setForceCase(FlxInputText.UPPER_CASE);
		add(inputText);
		
		inputText = createInputText(10, 240,  skin, "PASSWORD MODE");
		inputText.setPasswordMode(true);
		add(inputText);
		
		FlxDialogBox dialog = createDialogBox(10, 300, skin, "DIALOG BOX");
		add(dialog);
	}
}
