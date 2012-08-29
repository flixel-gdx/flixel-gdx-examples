package org.flixel.examples.bomberplanet;

import org.flixel.FlxSprite;

public class HealthBox extends FlxSprite
{
	static public String ImgHealthBox = "examples/bomberplanet/data/pack:healthbox"; 

	public HealthBox()
	{
		super();
		loadGraphic(ImgHealthBox,true);
		scrollFactor.x = scrollFactor.y = 0;
		y = 0;
		setSolid(false);
		moves = false;
	}
}