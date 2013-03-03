package org.flixel.examples.analog;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxButton;

/**
 *
 * @author Ka Wing Chin
 */
public class Test extends FlxState
{
	@SuppressWarnings("rawtypes")
	private static Class[] tests;
	public static int currentTest = 0;
	
	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF131C1B);
		
		// Add some wall around the edges.
		FlxSprite s;
		add(s = new FlxSprite(0, 0).makeGraphic(FlxG.width, 2));
		s.immovable = true;
		add(s = new FlxSprite(0, FlxG.height-2).makeGraphic(FlxG.width, 2));
		s.immovable = true;
		add(s = new FlxSprite(0, 0).makeGraphic(2, FlxG.height));
		s.immovable = true;
		add(s = new FlxSprite(FlxG.width-2, 0).makeGraphic(2, FlxG.height));
		s.immovable = true;
		
		if(tests == null)
		{
			tests = new Class[]	
			{
				PlayState.class,		// Single Analog
				PlayState2.class,		// Dual Analog
				PlayState3.class		// Triple Analog
			};
		}

		// Mobile
		if(FlxG.mobile)
		{
			add(createButton(0, 0, "Previous", new IFlxButton(){@Override public void callback(){prev();}}));
			add(createButton(80, 0, "Next", new IFlxButton(){@Override public void callback(){next();}}));
		}
		else
		{
			add(new FlxText(0, 0, 300, "'Left/Right' arrows to go to previous/next example."));
		}
	}
	
	public FlxButton createButton(float x, float y, String label, IFlxButton callback)
	{
		FlxButton button = new FlxButton(x, y, label, callback);
		button.ignoreDrawDebug = true;
		button.scrollFactor.x = button.scrollFactor.y = 0;
		button.setSolid(false);
		button.moves = false;
		return button;
	}

	@Override
	public void update()
	{
		super.update();
		FlxG.collide();
		if(FlxG.keys.justPressed("RIGHT"))
			next();
		else if(FlxG.keys.justPressed("LEFT"))
			prev();
	}
	
	private void next()
	{
		if(tests.length <= ++currentTest)
			currentTest = 0;
		try
		{				
			FlxG.switchState((FlxState)tests[currentTest].newInstance());
		}
		catch(Exception e)
		{
			FlxG.log(e.getMessage());
			return;
		}
	}
	
	private void prev()
	{
		if(0 > --currentTest)
			currentTest = tests.length-1;
		try
		{				
			FlxG.switchState((FlxState)tests[currentTest].newInstance());
		}
		catch(Exception e)
		{
			FlxG.log(e.getMessage());
			return;
		}
	}
}

