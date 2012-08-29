package org.flixel.examples.bomberplanet;

import org.flixel.*;

public class Flare extends FlxSprite
{
	static public String ImgFlare = "examples/bomberplanet/data/pack:flare"; 
	static public String SndFlare = "examples/bomberplanet/data/flare.mp3"; 

	public Flare(float X, float Y)
	{
		super(X, Y, ImgFlare);
		velocity.y = -150;
		angularVelocity = 800;
		FlxG.play(SndFlare,0.65f);
	}
	
	public Flare()
	{
		this(0, 0);
	}

	@Override
	public void update()
	{
		scale.y += FlxG.elapsed*2;
		scale.x = scale.y;
	}
}