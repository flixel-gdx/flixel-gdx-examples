package org.flixel.examples.bunnymark;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

/**
 * 
 * @author Ka Wing Chin
 */
public class Bunny extends FlxSprite
{
	private final String ImgBunny = "examples/bunnymark/pack:bunny";

	private boolean _complex;

	public Bunny(float x, float y)
	{
		super(x, y);
		loadGraphic(ImgBunny, false, false, 26, 37);
		float speedMultiplier = 50f;
		velocity.x = speedMultiplier * (FlxG.random() * 5f) * (Math.random() < 0.5f ? 1f : -1f);
		velocity.y = speedMultiplier * ((FlxG.random() * 5f) - 2.5f) * (Math.random() < 0.5f ? 1f : -1f);
		acceleration.y = 5;
		angle = 15 - FlxG.random() * 30;
		angularVelocity = 30f * (FlxG.random() * 5f) * (FlxG.random() < 0.5f ? 1f : -1f);
		elasticity = 1;
	}

	@Override
	public void update()
	{
		if(_complex)
		{
			setAlpha(.3f + .7f * y / FlxG.height);
		}

		if((x + width) >= FlxG.width)
		{
			velocity.x *= -1;
			x = FlxG.width - width;
		}
		else if(x <= 0)
		{
			velocity.x *= -1;
			x = 0;
		}

		if((y + height) >= FlxG.height)
		{
			velocity.y *= -0.8f;
			y = FlxG.height - height;

			if(FlxG.random() > 0.5f)
			{
				velocity.y -= 3 + FlxG.random() * 4;
			}
		}
		else if(y <= 0)
		{
			velocity.y *= -0.8f;
			y = 0;
		}
	}

	public void setComplex(boolean complex)
	{
		_complex = complex;
		if(_complex)
			scale.x = scale.y = .3f + FlxG.random();
		else
		{
			scale.x = scale.y = 1f;
			setAlpha(1f);
		}
	}
}
