package org.flixel.examples.analog;

import org.flixel.FlxCamera;
import org.flixel.FlxGame;

public class AnalogDemo extends FlxGame
{
	public AnalogDemo()
	{
		super(480, 320, PlayState.class, 1, 50, 50, false, FlxCamera.FIT);
	}
}
