package org.flixel.examples.analog;

import org.flixel.FlxSprite;
import org.flixel.event.IFlxAnalog;
import org.flixel.ui.FlxAnalog;

public class Player extends FlxSprite
{
	private FlxAnalog _analog;
	
	public Player(float X, float Y, int color, FlxAnalog analog)
	{
		super(X, Y);
		makeGraphic(20, 20, color);
		drag.x = drag.y = 100;
		maxVelocity.x = maxVelocity.y = 100;
		
		analog.onPressed = movePlayer;
		analog.onUp = stopPlayer;
		analog.setAlpha(.75f);
		_analog = analog;
	}
	
	@Override
	public void destroy()
	{
		super.destroy();
		_analog = null;
	}	

	IFlxAnalog stopPlayer = new IFlxAnalog()
	{		
		@Override
		public void callback()
		{
			acceleration.x = 0;
		}
	};
	
	IFlxAnalog movePlayer = new IFlxAnalog()
	{
		@Override
		public void callback()
		{
			angle = _analog.getAngle();
			velocity.x = 7 * _analog.acceleration.x;
			velocity.y = 7 * _analog.acceleration.y;
		}
	};
}
