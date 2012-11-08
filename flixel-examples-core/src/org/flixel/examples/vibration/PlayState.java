package org.flixel.examples.vibration;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.event.IFlxButton;

/**
 * A simple demo how vibration works.
 * 
 * @author Ka Wing Chin
 */
public class PlayState extends FlxState
{
	@Override
	public void create()
	{
		FlxG.setBgColor(0xFF000000);
		add(new FlxButton(20, 20, "vibrate 1 sec", vibrate));
		add(new FlxButton(20, 60, "1,2,3 repeat", repeat));
		add(new FlxButton(120, 20, "stop vibrate", stop));
	}

	IFlxButton vibrate = new IFlxButton()
	{
		@Override
		public void callback()
		{
			FlxG.vibrate(1000);
		}
	};
	
	IFlxButton repeat = new IFlxButton()
	{
		@Override
		public void callback()
		{
			FlxG.vibrate(new long[]{0,500,1000,1000,1000,2000,1000}, 1);
		}
	};
	
	IFlxButton stop = new IFlxButton()
	{
		@Override
		public void callback()
		{
			FlxG.stopVibrate();
		}
	};
}