package org.flixel.examples.userinterface;

import org.flixel.FlxG;
import org.flixel.FlxText;
import org.flixel.ui.FlxSwitch;
import org.flixel.ui.event.IFlxUIListener;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Ka Wing Chin
 */
public class SwitchState extends Test
{	
	@Override
	public void create()
	{
		super.create();
		
		// Bunch of texts.
		final Array<FlxText> texts = new Array<FlxText>();
		FlxText text;
		for(int i = 0; i < 6; i++)
		{
			text = new FlxText(10, 30 + i * 60, FlxG.width, "LIGHT")
			.setFormat(FntRobotoRegular, 18, 0x333333);
			add(text);
			texts.add(text);
		}
		
		// Very simple, a switch and a callback when pressed.
		final FlxSwitch _switch;
		add(_switch = new FlxSwitch(156, 398, "0", null, "Turn the lights"));
		_switch.onUp = new IFlxUIListener()
		{
			@Override
			public void callback()
			{
				if(_switch.getActivated())
				{
					for(int i = 0; i < texts.size; i++)
						texts.get(i).setColor(0xFFFFFF);
				}
				else
				{
					for(int i = 0; i < texts.size; i++)
						texts.get(i).setColor(0x333333);
				}
			}
		};
	}
}

