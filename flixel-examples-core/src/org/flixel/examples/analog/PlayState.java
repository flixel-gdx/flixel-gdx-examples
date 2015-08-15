package org.flixel.examples.analog;

import org.flixel.FlxG;
import org.flixel.FlxPoint;
import org.flixel.ui.FlxAnalog;

/**
 * Just a simple test for single analog.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends Test
{
	private FlxAnalog _analog1;
	
	@Override
	public void create()
	{		
		super.create();
		
		_analog1 = new FlxAnalog(75, FlxG.height-75);
		_analog1.setAlpha(.75f);
		_analog1.setAll("scrollFactor", new FlxPoint(0, 0));
		add(_analog1);
		FlxG.width = 480;

		add(new Player(10, 10, 0xFFFF0000, _analog1));
	}
	
	@Override
	public void destroy()
	{		
		super.destroy();
		_analog1 = null;
	}
}
