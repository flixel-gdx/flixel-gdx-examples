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
		
		final Array<FlxText> texts = new Array<FlxText>();
		FlxText text;
		for(int i = 0; i < 6; i++)
		{
			text = new FlxText(10, 30 + i * 60, FlxG.width, "LIGHT")
			.setFormat(FntRobotoRegular, 18, 0x333333);
			add(text);
			texts.add(text);
		}		
		
		final FlxSwitch _switch;
		add(_switch = new FlxSwitch(130, 398, "Turn the lights"));
		_switch.labelOffset.x = -10;
		_switch.label.setFormat(FntRobotoRegular, 18);
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

