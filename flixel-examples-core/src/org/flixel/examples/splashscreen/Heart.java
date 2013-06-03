package org.flixel.examples.splashscreen;

import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Heart extends FlxSprite
{
	private final static String Img = "examples/splashscreen/pack:heart";
	
	public Heart(float x, float y)
	{
		super(x, y);
		loadGraphic(Img, true, false, 42, 38);
		addAnimation("bounce", new int[]{0,0,0,1,1,2,2,1,1}, 12);
		play("bounce");
	}
	
	@Override
	public void update()
	{
		
		super.update();
	}
}

