package org.flixel.examples.splashscreen;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class DPad extends FlxSprite
{
	private final static String Img = "examples/splashscreen/pack:dpad";
	
	public DPad(float x, float y)
	{
		super(x, y);
		loadGraphic(Img, true, false, 67, 67);
		
		int[] array = new int[20];
		for(int i = 0; i < 20; i++)
		{
			array[i] = (int) ((FlxG.random() * 4) + 1);
			i++;
			array[i] = 0;
		}
		
		addAnimation("gameOn", array, 4, true);
		play("gameOn");
	}
}

