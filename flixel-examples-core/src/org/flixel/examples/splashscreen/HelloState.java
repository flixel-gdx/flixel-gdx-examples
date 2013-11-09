package org.flixel.examples.splashscreen;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxButton;

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
		
//		if(FlxG.mobile)
		{
			add(new FlxButton(100,100, "Reset Game", new IFlxButton()
			{				
				@Override
				public void callback()
				{
					FlxG.resetGame();
				}
			}));			
		}
	}
	
	@Override
	public void update() 
	{
		if(FlxG.keys.justPressed("R"))
		{
			FlxG.resetGame();
		}
		super.update();
	}

}

