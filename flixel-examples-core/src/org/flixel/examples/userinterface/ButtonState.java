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
				
		// A button that stretch automatically to the bounding of the textfield.
		add(new FlxNinePatchButton(20, 10, null, "Auto Width", 0, 0, null));
		
		// This button got an extra line. 
		add(new FlxNinePatchButton(150, 10, null, "Extra\nline", 0, 0, null));
		
		// Just some fixed button by 50 x 50.
		add(new FlxNinePatchButton(20, 70, null, "1", 50, 50, null));
		add(new FlxNinePatchButton(20, 140, null, "2", 50, 50, null));
		add(new FlxNinePatchButton(20, 210, null, "3", 50, 50, null));
		
		// A big fixed button. The text will vertical align in the middle. 
		add(new FlxNinePatchButton(100, 70, null, "Fixed Width", 200, 100, null));
		
		// Even more new lines.
		add(new FlxNinePatchButton(100, 190, null, "flixel-gdx's\nNine\nPatch", 0, 0, null));
		
		// How cool is a nine patch button?
		add(new FlxNinePatchButton(10, 280, null, "Amazing Nine\nPatch Button", 250, 160, null));
	}
}
