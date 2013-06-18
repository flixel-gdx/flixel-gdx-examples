package org.flixel.examples.quadtree;

import org.flixel.FlxSprite;

/**
 *
 * @author Ka Wing Chin
 */
public class Node extends FlxSprite
{
	public Node(float x, float y, String img)
	{
		super(x, y, img);		
		visible = false;
	}
	
	@Override
	public void update()
	{
		visible = false;
		super.update();
	}
	
	public void onOverlap()
	{
		visible = true;
	}
}

