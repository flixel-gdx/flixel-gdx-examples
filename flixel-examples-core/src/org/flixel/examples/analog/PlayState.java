package org.flixel.examples.analog;

import org.flixel.FlxAnalog;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.event.AFlxAnalog;

public class PlayState extends FlxState
{
	private FlxSprite _player;
	private FlxAnalog _analog;
	final float radiansToDegrees = (float) (180/Math.PI);
	
	
	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF131C1B);
		_player = new FlxSprite(10, 10);
		add(_player);
		
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
		
		add(_analog = new FlxAnalog(75, FlxG.height-75));
		_analog.onPressed = movePlayer;
		_analog.onUp = stopPlayer;
		_analog.setAlpha(.75f);
	}
	
	AFlxAnalog stopPlayer = new AFlxAnalog()
	{		
		@Override
		public void callback()
		{
			_player.velocity.x = _player.velocity.y = 0;
		}
	};
	
	AFlxAnalog movePlayer = new AFlxAnalog()
	{		
		@Override
		public void callback()
		{
			_player.angle = _analog.getAngle();
			_player.velocity.x = 7 * _analog.acceleration.x;
			_player.velocity.y = 7 * _analog.acceleration.y;
		}
	};
	
	
	@Override
	public void update()
	{
		super.update();
		
		if(_analog.justPressed())
		{
//			trace("just pressed");
		}
		if(_analog.pressed())
		{
			//trace("pressed");
		}			
		if(_analog.justReleased())
		{
//			trace("released");				
		}
		FlxG.collide();
	}
}
