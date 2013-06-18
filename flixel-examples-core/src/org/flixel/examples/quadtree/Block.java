package org.flixel.examples.quadtree;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Block extends FlxSprite
{
	public Block(float x, float y, String img, int size)
	{
		super(x, y);
		loadGraphic(img, true, false, size, size);
		maxVelocity.x = maxVelocity.y = 70;
		int r = (int) (FlxG.random() * 2); 
		if(r == 0)
			velocity.x = velocity.y = maxVelocity.x;
		else
			velocity.x = velocity.y = -maxVelocity.x;
		elasticity = 1;
	}
	
	@Override
	public void update()
	{
		setFrame(0);
		super.update();
	}
	
	public void onOverlap()
	{
		setFrame(1);
	}
}

