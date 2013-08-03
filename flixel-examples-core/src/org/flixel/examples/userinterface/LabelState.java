package org.flixel.examples.userinterface;

import org.flixel.ui.FlxLabel;

/**
 *
 * @author Ka Wing Chin
 */
public class LabelState extends Test
{
	 @Override
	public void create()
	{
		super.create();
		// Just a bunch of labels with auto and fixed size.
		add(new FlxLabel(10, 10, null, "flixel label", 0, 0));
		add(new FlxLabel(10, 50, null, "label", 200, 0));
		add(new FlxLabel(10, 100, null, "label", 50, 20));
		add(new FlxLabel(10, 150, null, "label", 100, 30));
	}
}

