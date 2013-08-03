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
	private final String ImgCheckBox = "examples/userinterface/custom_skin/checkbox.png";
	
	@Override
	public void create()
	{
		super.create();
		
		// Checkbox group which will hold our checkboxes. It's not mandatory, 
		// but it's handy group checkboxes that belongs together.
		FlxUIGroup checkBoxGroup;
		add(checkBoxGroup = new FlxUIGroup(10, 20, "CHECKBOXES"));
		checkBoxGroup.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		// Very easy, just adding checkbox to the group.
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A0", null, "I like flixel-gdx"));
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A1", null, "Unchecked"));
		
		// This one is already checked.
		FlxCheckBox box = new FlxCheckBox(0, 0, "A2", null, "Checked");
		box.setActive(true);
		checkBoxGroup.add(box);
		
		// This one can't be clicked.
		box = new FlxCheckBox(0, 0, "A3", null, "Disabled");
		box.setEnable(false);
		checkBoxGroup.add(box);
		
		// This one is disabled, but checked.
		box = new FlxCheckBox(0, 0, "A4", null, "Disabled Checked");
		box.setEnable(false);
		box.setActive(true);
		checkBoxGroup.add(box);
		
		
		// Our custom checkbox skin.
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
		
		// Apply the skin to our checkboxes.
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A5", skin, "Custom skin"));
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A6", skin, "Check us at flixel-gdx.com"));
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A7", skin, "It's very easy!"));
	}
}

