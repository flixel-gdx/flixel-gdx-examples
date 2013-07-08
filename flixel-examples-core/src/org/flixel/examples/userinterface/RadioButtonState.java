package org.flixel.examples.userinterface;

import org.flixel.FlxText;
import org.flixel.ui.FlxRadioButtonGroup;
import org.flixel.ui.FlxUIGroup;
import org.flixel.ui.FlxUISkin;
import org.flixel.ui.event.IFlxRadioButtonGroup;

/**
 * 
 * @author Ka Wing Chin
 */
public class RadioButtonState extends Test
{
	private final String ImgRadioButton = "examples/userinterface/holo_dark/radiobutton.png";
	
	@Override
	public void create()
	{
		super.create();
		
		final FlxText text = new FlxText(10, 300, 200)
		.setFormat(FntRobotoRegular, 30, 0x0099CC);
		add(text);
		
		FlxUIGroup radioButtonGroup;
		add(radioButtonGroup = new FlxUIGroup(10, 20, "RADIOBUTTONS"));
		radioButtonGroup.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		
		final FlxRadioButtonGroup radioGroup = new FlxRadioButtonGroup();
		radioGroup.onChange = new IFlxRadioButtonGroup()
		{
			@Override
			public void callback()
			{			
				text.setText(radioGroup.getSelectedLabel());
			}
		};
		
		// Setup skin
		FlxUISkin skin = new FlxUISkin();
		skin.DISABLED = 3;
		skin.HIGHLIGHT_DISABLED = 4;
		skin.ACTIVE_NORMAL = 5;
		skin.ACTIVE_HIGHTLIGHT = 6;
		skin.ACTIVE_PRESSED = 7;
		skin.ACTIVE_DISABLED = 8;
		skin.ACTIVE_HIGHTLIGHT_DISABLED = 9;
		skin.setImage(ImgRadioButton, 32, 32);
		skin.labelPosition = FlxUISkin.LABEL_RIGHT;
		skin.setFormat(FntRobotoRegular, 18);
		
		radioButtonGroup.add(createRadioButton("A", skin, "Android", radioGroup));
		radioButtonGroup.add(createRadioButton("B", skin, "Linux", radioGroup));
		radioButtonGroup.add(createRadioButton("C", skin, "iOS", radioGroup));
		radioButtonGroup.add(createRadioButton("D", skin, "HTML5", radioGroup));
		radioButtonGroup.add(createRadioButton("E", skin, "Windows", radioGroup));
		radioGroup.setCheck(3);
	}
}
