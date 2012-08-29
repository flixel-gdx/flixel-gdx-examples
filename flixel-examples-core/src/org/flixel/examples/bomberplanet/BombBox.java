package org.flixel.examples.bomberplanet;

import org.flixel.FlxSprite;

public class BombBox extends FlxSprite
{
	static public String ImgBombBox = "examples/bomberplanet/data/pack:bombbox"; 

	public BombBox()
	{
		super();
		loadGraphic(ImgBombBox,true);
		scrollFactor.x = scrollFactor.y = 0;
		y = 0;
		setSolid(false);
		moves = false;
	}
}
