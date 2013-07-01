package org.flixel.examples.userinterface;

import org.flixel.ui.FlxNinePatchButton;

/**
 * 
 * @author Ka Wing Chin
 */
public class ButtonState extends Test
{
	@Override
	public void create()
	{
		super.create();
		add(createButton(100, 50, "Auto Width", 0, 0));
		add(createButton(100, 150, "Fixed Width", 200, 0));
		add(createButton(100, 250, "Extra\nline", 0, 0));
		add(createButton(100, 350, "Amazing\nNine \nPatch", 0, 0));
	}
	
	private FlxNinePatchButton createButton(float x, float y, String label, int width, int height)
	{
		FlxNinePatchButton button = new FlxNinePatchButton(x, y, label, width, height);
		button.label.setFormat(FntRobotoRegular, 18, 0xFFFFFF, "center");
//		button.positioning();
		return button;
	}
}
