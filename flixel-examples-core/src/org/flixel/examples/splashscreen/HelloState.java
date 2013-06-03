package org.flixel.examples.splashscreen;

import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;

/**
 *
 * @author Ka Wing Chin
 */
public class HelloState extends FlxState
{

	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF000000);
		add(new FlxText(10, 10, 200, "Hello flixel-gdx"));
	}

}

