package org.flixel.examples.splashscreen;

import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Code extends FlxSprite
{
	public static final String Img = "examples/splashscreen/pack:code";
	
	public Code(float x, float y)
	{
		super(x, y);
		loadGraphic(Img, true, false, 37, 24);
		addAnimation("coding", new int[]{
				0,8,0,8,0,8,
				1,2,1,2,1,2,
				3,4,3,4,3,4,
				5,6,5,6,5,6,
				7
			}, 10, false);
		play("coding");
	}
}

