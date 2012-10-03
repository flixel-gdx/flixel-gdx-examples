package org.flixel.examples.analog;

import org.flixel.FlxAnalog;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;

/**
 * Just a simple test for dual analog.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	private FlxAnalog _analog1;
	private FlxAnalog _analog2;
	final float radiansToDegrees = (float) (180/Math.PI);	
	
	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF131C1B);
		
		// Add some wall around the edges.
		FlxSprite s;
		add(s = new FlxSprite(0, 0).makeGraphic(FlxG.width, 2));
		s.immovable = true;
		add(s = new FlxSprite(0, FlxG.height-2).makeGraphic(FlxG.width, 2));
		s.immovable = true;
		add(s = new FlxSprite(0, 0).makeGraphic(2, FlxG.height));
		s.immovable = true;
		add(s = new FlxSprite(FlxG.width-2, 0).makeGraphic(2, FlxG.height));
		s.immovable = true;
		
		_analog1 = new FlxAnalog(75, FlxG.height-75);
		_analog1.setAlpha(.75f);
		
		_analog2 = new FlxAnalog(FlxG.width - 75, FlxG.height-75);
		_analog2.setAlpha(.75f);
		
		add(new Player(10, 10, 0xFFFF0000, _analog1));		
		add(new Player(FlxG.width-30, 10, 0xFF00FF00, _analog2));		
		add(_analog1);
		add(_analog2);		
	}
	
	@Override
	public void update()
	{
		super.update();
		FlxG.collide();
	}
	
	@Override
	public void destroy()
	{		
		super.destroy();
		_analog1 = null;
		_analog2 = null;
	}
}
