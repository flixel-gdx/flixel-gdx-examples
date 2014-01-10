package org.flixel.examples.userinterface;

import org.flixel.ui.FlxLabel;
import org.flixel.ui.FlxNinePatch;
import org.flixel.ui.FlxUISkin;

/**
 *
 * @author Ka Wing Chin
 */
public class LabelState extends Test
{
	private static final String ImgLabelCenter = "examples/userinterface/custom_skin/ninepatch_label_middlecenter.png";
	
	 @Override
	public void create()
	{
		super.create();
		// Just a bunch of labels with auto and fixed size.
		add(new FlxLabel(10, 10, null, "flixel label", 0, 0));
		add(new FlxLabel(10, 50, null, "label", 200, 0));
		add(new FlxLabel(10, 100, null, "label", 50, 20));
		add(new FlxLabel(10, 150, null, "label", 100, 50));
		
		
		FlxUISkin skin = new FlxUISkin();
		skin = new FlxUISkin();
		skin.HIGHLIGHT = -1;
		skin.PRESSED = -1;
		skin.DISABLED = -1;		
		skin.setFormat(null, 16, 0x333333, "center");
		skin.labelVerticalAlign = "middle";
		skin.setNinePatch(FlxNinePatch.MIDDLE_CENTER, ImgLabelCenter, 50, 50, true);
		skin.labelOffset.y = 0;
		skin.labelOffset.x = -2;
		
		FlxLabel label = new FlxLabel(5, 220, skin, "Repeated Background", 250, 50);
		add(label);
	}
}

