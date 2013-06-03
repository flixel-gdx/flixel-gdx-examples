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
		add(new FlxText(10, 10, 200, "Hello flixel-gdx. Press R to restart the example"));		
	}
	
	@Override
	public void update() {
		if(FlxG.keys.R){
			FlxG.switchState(new PlayState());
		}
		super.update();
	}

}

