package org.flixel.examples.userinterface;

import org.flixel.FlxText;
import org.flixel.ui.FlxRadioButton;
import org.flixel.ui.FlxRadioButtonGroup;
import org.flixel.ui.FlxUIGroup;
import org.flixel.ui.event.IFlxRadioButtonGroup;

/**
 * 
 * @author Ka Wing Chin
 */
public class RadioButtonState extends Test
{	
	@Override
	public void create()
	{
		super.create();
		
		// The choice you picked.
		final FlxText text = new FlxText(10, 300, 200)
		.setFormat(FntRobotoRegular, 30, 0x0099CC);
		add(text);
		
		// Just to vertical align the buttons.
		FlxUIGroup radioButtonGroup;
		add(radioButtonGroup = new FlxUIGroup(10, 20, "RADIOBUTTONS"));
		radioButtonGroup.label.setFormat(FntRobotoRegular, 14, 0x0099CC);
		
		// Radiobutton group. It's mandatory.
		final FlxRadioButtonGroup radioGroup = new FlxRadioButtonGroup();
		// Fire a callback when a radiobutton changes status.
		radioGroup.onChange = new IFlxRadioButtonGroup()
		{
			@Override
			public void callback()
			{			
				text.setText(radioGroup.getSelectedLabel().toString());
			}
		};
		
		// Create a bunch of radios. Default will be Linux.
		radioButtonGroup.add(new FlxRadioButton(0, 0, "A", radioGroup, null, "Android"));
		radioButtonGroup.add(new FlxRadioButton(0, 0, "B", radioGroup, null, "HTML5"));
		radioButtonGroup.add(new FlxRadioButton(0, 0, "C", radioGroup, null, "iOS"));
		radioButtonGroup.add(new FlxRadioButton(0, 0, "D", radioGroup, null, "Linux"));
		radioButtonGroup.add(new FlxRadioButton(0, 0, "E", radioGroup, null, "Windows"));
		radioGroup.setCheck(3);
	}
}
