package org.flixel.examples.splashscreen;

import org.flixel.FlxCamera;
import org.flixel.FlxGame;

/**
 *
 * @author Ka Wing Chin
 */
public class SplashScreen extends FlxGame
{
	public SplashScreen()
	{
		super(480, 800, HelloState.class, 1, 30, 30, false, FlxCamera.FIT);
		forceDebugger = true;
		showSplashScreen = true;
	}
}

