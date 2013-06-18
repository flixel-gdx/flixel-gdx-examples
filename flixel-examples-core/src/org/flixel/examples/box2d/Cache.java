package org.flixel.examples.box2d;

import org.flixel.FlxGroup;
import org.flixel.plugin.flxbox2d.collision.shapes.B2FlxBox;

/**
 *
 * @author Ka Wing Chin
 */
public class Cache
{
	public static B2FlxBox a;
	public static B2FlxBox b;
	public static FlxGroup g = new FlxGroup();
	
	static
	{
		Cache.a = createBox(100,50, 50, 50);
		Cache.b = createBox(50,50, 50, 50);
		g.add(createBox(200,50, 50, 50));
		g.add(createBox(250,50, 50, 50));
	}
	
	public static B2FlxBox createBox(float x, float y, float width, float height)
	{
		return new B2FlxBox(x, y, width, height)
			.setFriction(.8f)
			.setRestitution(.3f)
			.setDensity(.7f)
			.setDraggable(true)
			.setSurvive(true)
			.create();
	}
}

