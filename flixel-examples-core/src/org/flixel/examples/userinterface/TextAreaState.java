package org.flixel.examples.userinterface;

import org.flixel.ui.FlxDialogBox;
import org.flixel.ui.FlxInputText;
import org.flixel.ui.FlxUISkin;
import org.flixel.ui.FlxUISkin.NinePatch;

/**
 * 
 * @author Ka Wing Chin
 */
public class TextAreaState extends Test
{	
	private final String ImgTextAreaTopLeft = "examples/userinterface/holo_dark/ninepatch_textarea_topleft.png";
	private final String ImgTextAreaTopCenter = "examples/userinterface/holo_dark/ninepatch_textarea_topcenter.png";
	private final String ImgTextAreaTopRight = "examples/userinterface/holo_dark/ninepatch_textarea_topright.png";
	private final String ImgTextAreaMiddleLeft = "examples/userinterface/holo_dark/ninepatch_textarea_middleleft.png";
	private final String ImgTextAreaMiddleCenter = "examples/userinterface/holo_dark/ninepatch_textarea_middlecenter.png";
	private final String ImgTextAreaMiddleRight = "examples/userinterface/holo_dark/ninepatch_textarea_middleright.png";
	private final String ImgTextAreaBottomLeft = "examples/userinterface/holo_dark/ninepatch_textarea_bottomleft.png";
	private final String ImgTextAreaBottomCenter = "examples/userinterface/holo_dark/ninepatch_textarea_bottomcenter.png";
	private final String ImgTextAreaBottomRight = "examples/userinterface/holo_dark/ninepatch_textarea_bottomright.png";

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
		skin.labelOffset.y = -7;
		skin.setNinePatch(NinePatch.TOP_LEFT, ImgTextAreaTopLeft, 4, 2);
		skin.setNinePatch(NinePatch.TOP_CENTER, ImgTextAreaTopCenter, 1, 2);
		skin.setNinePatch(NinePatch.TOP_RIGHT, ImgTextAreaTopRight, 4, 2);
		skin.setNinePatch(NinePatch.MIDDLE_LEFT, ImgTextAreaMiddleLeft, 4, 1);
		skin.setNinePatch(NinePatch.MIDDLE_CENTER, ImgTextAreaMiddleCenter, 1, 1);
		skin.setNinePatch(NinePatch.MIDDLE_RIGHT, ImgTextAreaMiddleRight, 4, 1);
		skin.setNinePatch(NinePatch.BOTTOM_LEFT, ImgTextAreaBottomLeft, 4, 2);
		skin.setNinePatch(NinePatch.BOTTOM_CENTER, ImgTextAreaBottomCenter, 1, 2);
		skin.setNinePatch(NinePatch.BOTTOM_RIGHT, ImgTextAreaBottomRight, 4, 2);
		skin.setFormat(FntRobotoRegular, 14, 0x0099CC);

		FlxInputText inputText = createInputText(10, 60, skin, "TEXT AREA - MAX. 3 LINES", 250, 70);
		inputText.getTextField().offset.y = -10;
		inputText.setMaxLength(0);
		inputText.setMaxLines(3);
		add(inputText);
		
		inputText = createInputText(10, 170, skin, "MAX. 2 LINES + MAX. 26 CHARS", 250, 49);
		inputText.getTextField().offset.y = -10;
		inputText.setMaxLength(26);
		inputText.setMaxLines(2);
		add(inputText);
		
		inputText = createInputText(10, 260, skin, "NINE PATCH", 200, 91);
		inputText.getTextField().offset.y = -10;
		inputText.setMaxLength(0);
		inputText.setMaxLines(4);
		add(inputText);
		
		inputText = createInputText(10, 390, skin, "DISABLED", 200, 91);
		inputText.getTextField().offset.y = -10;
		inputText.setMaxLength(0);
		inputText.setMaxLines(4);
		inputText.setEnable(false);
		inputText.setText("flixel-gdx's awesome UI components.");
		add(inputText);
		
		// TODO: weird bug dialog with multi lines / nine patch...
		FlxDialogBox dialog = createDialogBox(10, 550, skin, "DIALOG", 200, 91);
		dialog.getTextField().offset.y = -10;
		dialog.setMaxLength(0);
		dialog.setMaxLines(4);
		add(dialog);
	}
}
