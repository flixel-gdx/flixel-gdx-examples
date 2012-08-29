package org.flixel.examples.bomberplanet;

import org.flixel.FlxSprite;

public class Poof extends FlxSprite
{
	static public String ImgPoof = "examples/bomberplanet/data/pack:poof";

	public Poof()
	{
		super();
		loadGraphic(ImgPoof,true);
		addAnimation("poof",new int[]{0,1,2,3},12,false);
	}

	@Override
	public void update()
	{
		if(finished)
			kill();
	}

	@Override
	public void reset(float X, float Y)
	{
		super.reset(X,Y);
		play("poof",true);
	}
}