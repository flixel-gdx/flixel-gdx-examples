package org.flixel.examples.splashscreen;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Lightbulb extends FlxSprite
{
	private static final String Img = "examples/splashscreen/pack:lightbulb";
	private float _offCounter;
	private float _blinkCounter;
	
	public Lightbulb(float x, float y)
	{
		super(x, y);
		loadGraphic(Img, true, false, 48, 71);
		addAnimation("off", new int[]{0}, 0, false);
		addAnimation("on", new int[]{1}, 0, false);
		addAnimation("blink", new int[]{0,1}, 20);
		play("off");
		
		_offCounter = 1f;
	}
	
	@Override
	public void update()
	{
		if(_offCounter > 0)
		{
			_offCounter -= FlxG.elapsed;
			if(_offCounter <= 0)
			{
				_blinkCounter = 1f;
				play("blink");
			}
		}
		
		if(_blinkCounter > 0)
		{
			_blinkCounter -= FlxG.elapsed;
			if(_blinkCounter <= 0)
				play("on");
		}		
		super.update();
	}
}

