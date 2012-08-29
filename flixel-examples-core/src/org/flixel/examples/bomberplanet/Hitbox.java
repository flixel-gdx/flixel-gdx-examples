package org.flixel.examples.bomberplanet;

import org.flixel.*;

public class Hitbox extends FlxObject
{
	public float timeout;

	public Hitbox()
	{
		super();
	}

	@Override
	public void update()
	{
		timeout -= FlxG.elapsed;
		if(timeout <= 0)
			kill();
	}

	public void resetHitbox(float X, float Y, float Width, float Height)
	{
		reset(X,Y);
		width = Width;
		height = Height;
		timeout = 0.25f;
	}
	
	public void resetHitbox(float X, float Y, float Width)
	{
		resetHitbox(X, Y, Width, 0);
	}
	
	public void resetHitbox(float X, float Y)
	{
		resetHitbox(X, Y, 0, 0);
	}
}