package org.flixel.examples.controller;

import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.system.input.Gamepad;

/**
 *
 * @author Ka Wing Chin
 */
public class Block extends FlxSprite
{
	private Gamepad _gamepad;
	private FlxPoint _point;
	
	public Block(float x, float y, int color, Gamepad gamepad)
	{
		super(x, y);
		makeGraphic(12, 12, color);
		_gamepad = gamepad;
	}
	
	@Override
	public void update()
	{
		acceleration.x = acceleration.y = 0;
		_point = _gamepad.getAxisL();
		velocity.x = _point.x;
		velocity.y = _point.y;
		super.update();
	}
	
	@Override
	public void destroy()
	{
		super.destroy();		
		_gamepad = null;
		_point = null;
	}
}

