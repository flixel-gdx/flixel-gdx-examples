package org.flixel.examples.userinterface;

import org.flixel.FlxG;
import org.flixel.ui.FlxCheckBox;
import org.flixel.ui.FlxRadioButton;
import org.flixel.ui.FlxRadioButtonGroup;
import org.flixel.ui.FlxUIGroup;
import org.flixel.ui.FlxUISkin;

/**
 *
 * @author Ka Wing Chin
 */
public class SkinState extends Test
{
	private static final String ImgCheckBox = "examples/userinterface/holo_light/checkboxes_light.png";
	private static final String ImgRadioButton = "examples/userinterface/holo_light/radiobuttons_light.png";
	
	@Override
	public void create()
	{
		super.create();
		FlxG.setBgColor(0xFFFFFFFF);
		
		// This example uses the HOLO_LIGHT theme from Android 4.2.
		
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
		skin.setImage(ImgCheckBox, 32, 32);
		skin.labelPosition = FlxUISkin.LABEL_RIGHT;
		skin.setFormat(FntRobotoRegular, 18, 0x111111);
		
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A0", skin, "I like flixel-gdx"));
		checkBoxGroup.add(new FlxCheckBox(0, 0, "A1", skin, "Unchecked"));
		
		FlxCheckBox box = new FlxCheckBox(0, 0, "A2", skin, "Checked");
		box.setActive(true);
		checkBoxGroup.add(box);
		
		box = new FlxCheckBox(0, 0, "A3", skin, "Disabled");
		box.setEnable(false);
		checkBoxGroup.add(box);
		
		box = new FlxCheckBox(0, 0, "A4", skin, "Disabled Checked");
		box.setEnable(false);
		box.setActive(true);
		checkBoxGroup.add(box);
		
		final FlxRadioButtonGroup radioGroup = new FlxRadioButtonGroup();
		FlxUIGroup radioButtonGroup;
		add(radioButtonGroup = new FlxUIGroup(10, 290, "RADIOBUTTONS"));
		radioButtonGroup.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		// Reuse the skin
		skin.setImage(ImgRadioButton, 32, 32);
		radioButtonGroup.add(new FlxRadioButton(0, 0, "A", radioGroup, skin, "Android"));
		radioButtonGroup.add(new FlxRadioButton(0, 0, "B", radioGroup, skin, "HTML5"));
		radioButtonGroup.add(new FlxRadioButton(0, 0, "C", radioGroup, skin, "iOS"));
		radioGroup.setCheck(3);
	}
}

