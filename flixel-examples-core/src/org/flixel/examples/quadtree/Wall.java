package org.flixel.examples.quadtree;

import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Wall extends FlxSprite
{
	public Wall(float x, float y, int width, int height)
	{
		super(x, y);
		this.width = width;
		this.height = height;
		moves = false;
		immovable = true;
		visible = false;
	}
}

