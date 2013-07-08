package org.flixel.examples.userinterface;

import org.flixel.ui.FlxUISkin;
import org.flixel.ui.FlxUISkin.NinePatch;


/**
 * 
 * @author Ka Wing Chin
 */
public class ButtonState extends Test
{
	public static final String ImgTopLeft = "examples/userinterface/holo_dark/ninepatch_button_topleft.png";
	public static final String ImgTopCenter = "examples/userinterface/holo_dark/ninepatch_button_topcenter.png";
	public static final String ImgTopRight = "examples/userinterface/holo_dark/ninepatch_button_topright.png";
	public static final String ImgBottomLeft = "examples/userinterface/holo_dark/ninepatch_button_bottomleft.png";
	public static final String ImgBottomCenter = "examples/userinterface/holo_dark/ninepatch_button_bottomcenter.png";
	public static final String ImgBottomRight= "examples/userinterface/holo_dark/ninepatch_button_bottomright.png";
	public static final String ImgMiddleLeft = "examples/userinterface/holo_dark/ninepatch_button_middleleft.png";
	public static final String ImgMiddleCenter = "examples/userinterface/holo_dark/ninepatch_button_middlecenter.png";
	public static final String ImgMiddleRight = "examples/userinterface/holo_dark/ninepatch_button_middleright.png";
	
	@Override
	public void create()
	{
		super.create();
		FlxUISkin skin = new FlxUISkin();
		skin.HIGHLIGHT_DISABLED = 3;
		skin.DISABLED = 4;
		skin.setImage(ImgMiddleCenter, 1, 1);
		skin.setFormat(FntRobotoRegular, 18);
		skin.setNinePatch(NinePatch.TOP_LEFT, ImgTopLeft, 8, 8);
		skin.setNinePatch(NinePatch.TOP_CENTER, ImgTopCenter, 1, 8);
		skin.setNinePatch(NinePatch.TOP_RIGHT, ImgTopRight, 8, 8);
		skin.setNinePatch(NinePatch.MIDDLE_LEFT, ImgMiddleLeft, 8, 1);
		skin.setNinePatch(NinePatch.MIDDLE_CENTER, ImgMiddleCenter, 1, 1);
		skin.setNinePatch(NinePatch.MIDDLE_RIGHT, ImgMiddleRight, 8, 1);
		skin.setNinePatch(NinePatch.BOTTOM_LEFT, ImgBottomLeft, 8, 8);
		skin.setNinePatch(NinePatch.BOTTOM_CENTER, ImgBottomCenter, 1, 8);
		skin.setNinePatch(NinePatch.BOTTOM_RIGHT, ImgBottomRight, 8, 8);
		skin.labelOffset.y = 0;
		
		
		add(createNinePatchButton(100, 50, skin, "Auto Width", null, 0, 0));
		add(createNinePatchButton(100, 150, skin, "Fixed Width", null, 200, 0));
		add(createNinePatchButton(100, 250, skin, "Extra\nline", null, 0, 0));
		add(createNinePatchButton(100, 350, skin, "Amazing\nNine\nPatch", null, 0, 0));
		add(createNinePatchButton(100, 450, skin, "Amazing Nine Patch", null, 0, 0));
	}
}
