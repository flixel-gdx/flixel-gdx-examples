package org.flixel.examples.userinterface;

import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;
import org.flixel.ui.FlxUISkin;
import org.flixel.ui.FlxUISkin.NinePatch;

/**
 * 
 * @author Ka Wing Chin
 */
public class InputTextState extends Test
{
	private final String ImgTextField = "examples/userinterface/holo_dark/textfield.png";
	private final String ImgTextFieldLeft = "examples/userinterface/holo_dark/ninepatch_textfield_middleleft.png";
	private final String ImgTextFieldCenter = "examples/userinterface/holo_dark/ninepatch_textfield_middlecenter.png";
	private final String ImgTextFieldRight = "examples/userinterface/holo_dark/ninepatch_textfield_middleright.png";
	public static final String ImgMiddleCenter = "examples/userinterface/holo_dark/ninepatch_button_middlecenter.png";
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
		skin.ACTIVE_HIGHTLIGHT = 2;
		skin.ACTIVE_PRESSED = 2;
		skin.labelPosition = FlxUISkin.LABEL_TOP;
		skin.setImage(ImgTextField, 328, 32);		
		skin.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		FlxInputText inputText = createInputText(10, 60, skin, "INPUT TEXT", 300, 32);
		add(inputText);
		
		inputText = createInputText(10, 120, skin, "NUMERIC ONLY", 0, 32);
		inputText.setFilterMode(FlxInputText.ONLY_NUMERIC);
		add(inputText);
		
		inputText = createInputText(10, 180, skin, "UPPER CASE", 0, 32);
		inputText.setForceCase(FlxInputText.UPPER_CASE);
		add(inputText);
		
		inputText = createInputText(10, 240, skin, "PASSWORD MODE", 0, 32);
		inputText.setPasswordMode(true);
		add(inputText);
		
		FlxDialogBox dialog = createDialogBox(10, 360, skin, "DIALOG BOX", 0, 32);
		add(dialog);
		
		skin = new FlxUISkin();
		skin.DISABLED = 1;
		skin.HIGHLIGHT = 2;
		skin.PRESSED = 2;
		skin.ACTIVE_NORMAL = 2;
		skin.ACTIVE_HIGHTLIGHT = 2;
		skin.ACTIVE_PRESSED = 2;
		skin.labelPosition = FlxUISkin.LABEL_TOP;
		skin.labelOffset.y = -10;
		skin.setImage(ImgTextField, 328, 32);		
		skin.setFormat(FntRobotoRegular, 14, 0x0099CC);
		skin.setNinePatch(NinePatch.MIDDLE_LEFT, ImgTextFieldLeft, 4, 32);
		skin.setNinePatch(NinePatch.MIDDLE_CENTER, ImgTextFieldCenter, 1, 32);
		skin.setNinePatch(NinePatch.MIDDLE_RIGHT, ImgTextFieldRight, 4, 32);
		
		inputText = createInputText(10, 300, skin, "NINEPATCH", 200, 32);
		add(inputText);
		
	}
}
