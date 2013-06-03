package org.flixel.examples.splashscreen;

import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Gear extends FlxSprite
{
	private float rotationSpeed;
	
	public Gear(String img, float x, float y, float rotationSpeed)
	{
		super(x, y, img);
		this.rotationSpeed = rotationSpeed;
		
	}
	
	@Override
	public void update()
	{
		angle += rotationSpeed;
		super.update();
	}
}

