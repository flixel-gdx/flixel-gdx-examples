package org.flixel.examples.userinterface;

import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;
import org.flixel.ui.FlxNinePatch;
import org.flixel.ui.FlxUISkin;

/**
 * 
 * @author Ka Wing Chin
 */
public class InputTextState extends Test
{
	private final String ImgTextField = "examples/userinterface/custom_skin/textfield.png";
	private final String ImgTextFieldLeft = "examples/userinterface/custom_skin/ninepatch_textfield_middleleft.png";
	private final String ImgTextFieldCenter = "examples/userinterface/custom_skin/ninepatch_textfield_middlecenter.png";
	private final String ImgTextFieldRight = "examples/userinterface/custom_skin/ninepatch_textfield_middleright.png";
	
	@Override
	public void create()
	{
		super.create();
		
		// Default skin
		FlxInputText inputText = new FlxInputText(10, 30, null, "INPUT TEXT", 250, 32);
		add(inputText);
		
		// Only numbers allowed.
		inputText = new FlxInputText(10, 90, null, "NUMERIC ONLY", 250, 32);
		inputText.setFilterMode(FlxInputText.ONLY_NUMERIC);
		add(inputText);
		
		// ONLY UPPER CASE ALLOWED.
		inputText = new FlxInputText(10, 150, null, "UPPER CASE", 250, 32);
		inputText.setForceCase(FlxInputText.UPPER_CASE);
		add(inputText);
		
		// You'll see asterisk when password mode is enabled.
		inputText = new FlxInputText(10, 210, null, "PASSWORD MODE", 55, 32);
		inputText.setPasswordMode(true);
		inputText.setMaxLength(4);
		add(inputText);
		
		// A dialog will prompted. 
		FlxDialogBox dialog = new FlxDialogBox(10, 370, null, "DIALOG BOX", 250, 32);
		add(dialog);
		
		
		// Our custom skin.
		FlxUISkin skin = new FlxUISkin();
		skin.DISABLED = 1;
		skin.HIGHLIGHT = 2;
		skin.PRESSED = 2;
		skin.ACTIVE_NORMAL = 2;
		skin.ACTIVE_HIGHTLIGHT = 2;
		skin.ACTIVE_PRESSED = 2;
		skin.labelPosition = FlxUISkin.LABEL_TOP;
		skin.labelOffset.y = -3;
		skin.labelWidth = 200;
		skin.setImage(ImgTextField, 328, 32);
		skin.setFormat(FntRobotoRegular, 12, 0x0099CC);
		
		// This use only one image for the skin.
		inputText = new FlxInputText(10, 270, skin, "SINGLE IMAGE", 200, 32);
		inputText.textfield.setFormat(FntRobotoRegular, 18);
		add(inputText);
		
		// Adjust the skin, only the middle of the ninepatch are used.
		skin.setNinePatch(FlxNinePatch.MIDDLE_LEFT, ImgTextFieldLeft, 4, 32);
		skin.setNinePatch(FlxNinePatch.MIDDLE_CENTER, ImgTextFieldCenter, 1, 32);
		skin.setNinePatch(FlxNinePatch.MIDDLE_RIGHT, ImgTextFieldRight, 4, 32);
		
		inputText = new FlxInputText(10, 320, skin, "ONLY NINEPATCH IN THE MIDDLE", 200, 32);
		inputText.textfield.setFormat(FntRobotoRegular, 18);
		add(inputText);
		
	}
}
