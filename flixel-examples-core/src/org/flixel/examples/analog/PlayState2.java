package org.flixel.examples.analog;

import org.flixel.FlxG;
import org.flixel.ui.FlxAnalog;

/**
 * Just a simple test for dual analog.
 * 
 * @author Ka Wing Chin
 */
public class PlayState2 extends Test
{	
	private FlxAnalog _analog1;
	private FlxAnalog _analog2;
	
	@Override
	public void create()
	{
		super.create();
		
		_analog1 = new FlxAnalog(75, FlxG.height-75);
		_analog1.setAlpha(.75f);
		add(_analog1);
		
		_analog2 = new FlxAnalog(FlxG.width - 75, FlxG.height-75);
		_analog2.setAlpha(.75f);
		add(_analog2);
		FlxG.width = 480;
		
		add(new Player(10, 10, 0xFFFF0000, _analog1));		
		add(new Player(FlxG.width-30, 10, 0xFF00FF00, _analog2));	
	}
		
	@Override
	public void destroy()
	{		
		super.destroy();
		_analog1 = null;
		_analog2 = null;
	}
}
