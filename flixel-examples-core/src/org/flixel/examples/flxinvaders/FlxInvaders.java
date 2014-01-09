package org.flixel.examples.flxinvaders;

import org.flixel.FlxGame;

/**
 * A shabby space invaders clone.
 * 
 * @author Adam Atomic
 * @author Ka Wing Chin
 */
public class FlxInvaders extends FlxGame
{
	public FlxInvaders()
	{
		super(320, 240, PlayState.class); //Create a new FlxGame object at 320x240 with 2x pixels, then load PlayState
		forceDebugger = true;
	}
}
