package org.flixel.examples.userinterface;

import org.flixel.ui.FlxCheckBox;
import org.flixel.ui.FlxUIGroup;
import org.flixel.ui.FlxUISkin;


/**
 *
 * @author Ka Wing Chin
 */
public class CheckBoxState extends Test
{
	private final String ImgCheckBox = "examples/userinterface/holo_dark/checkbox.png";

	@Override
	public void create()
	{
		super.create();
		
		FlxUIGroup checkBoxGroup;
		add(checkBoxGroup = new FlxUIGroup(10, 20, "CHECKBOXES"));
		checkBoxGroup.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		// Setup skin
		FlxUISkin skin = new FlxUISkin();
		skin.DISABLED = 3;
		skin.HIGHLIGHT_DISABLED = 4;
		skin.ACTIVE_NORMAL = 5;
		skin.ACTIVE_HIGHTLIGHT = 6;
		skin.ACTIVE_PRESSED = 7;
		skin.ACTIVE_DISABLED = 8;
		skin.ACTIVE_HIGHTLIGHT_DISABLED = 9;
		skin.labelPosition = FlxUISkin.LABEL_RIGHT;
		skin.setImage(ImgCheckBox, 32, 32);
		skin.setFormat(FntRobotoRegular, 18);
		
		checkBoxGroup.add(createCheckBox("A0", skin, "I like flixel-gdx"));
		checkBoxGroup.add(createCheckBox("A1", skin, "Unchecked"));
		
		FlxCheckBox box = createCheckBox("A2", skin, "Checked");
		box.setActive(true);
		checkBoxGroup.add(box);
		
		box = createCheckBox("A3", skin, "Disabled");
		box.setEnable(false);
		checkBoxGroup.add(box);
		
		box = createCheckBox("A4", skin, "Disabled Checked");
		box.setEnable(false);
		box.setActive(true);
		checkBoxGroup.add(box);
	}
}

