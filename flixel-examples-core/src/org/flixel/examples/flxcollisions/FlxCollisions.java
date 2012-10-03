package org.flixel.examples.flxcollisions;

import org.flixel.FlxG;
import org.flixel.FlxGame;

/**
 * A test bed for Flixel's collision systems.
 * 
 * @author Adam Atomic
 * @author Thomas Weston
 * @author Ka Wing Chin
 */
public class FlxCollisions extends FlxGame
{
	public FlxCollisions()
	{
		super(320, 240, PlayState.class, 2, 40, 40);
		FlxG.debug = true;
	}
}
