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
		
		createRadioButton("A", "Android", radioGroup, radioButtonGroup);
		createRadioButton("B", "Linux", radioGroup, radioButtonGroup);
		createRadioButton("C", "iOS", radioGroup, radioButtonGroup);
		createRadioButton("D", "HTML5", radioGroup, radioButtonGroup);
		createRadioButton("E", "Windows", radioGroup, radioButtonGroup);
		radioGroup.setCheck(3);
	}
	
	public FlxRadioButton createRadioButton(String ID, String label, FlxRadioButtonGroup radioGroup, FlxUIGroup group)
	{
		FlxRadioButton radio = new FlxRadioButton(0, 0, ID, radioGroup, label);
		radio.label.setFormat(FntRobotoRegular, 18);
		group.add(radio);
		return radio;
	}
}
