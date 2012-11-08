package org.flixel.examples.cameraeffects;

import org.flixel.FlxButton;
import org.flixel.FlxG;
import org.flixel.FlxState;
import org.flixel.event.IFlxButton;
import org.flixel.event.IFlxCamera;

public class PlayState extends FlxState
{

	@Override
	public void create()
	{		
		FlxG.setBgColor(0xff131c1b);
		
		add(new FlxButton(10, 10, "fade", onFade));
		add(new FlxButton(100, 10, "flash", onFlash));
		add(new FlxButton(190, 10, "shake", onShake));
	}

	IFlxButton onFade = new IFlxButton()
	{
		@Override
		public void callback()
		{
			FlxG.log("fade!");
			FlxG.fade(0xFF000000, 2, new IFlxCamera(){ @Override public void callback(){FlxG.camera.stopFX();}});
		}
	};
	
	IFlxButton onFlash = new IFlxButton()
	{
		@Override
		public void callback()
		{
			FlxG.log("flash!");
			FlxG.flash(0xFFFFFFFF, 2);
		}
	};
	
	IFlxButton onShake = new IFlxButton()
	{
		@Override
		public void callback()
		{
			FlxG.log("shake!");
			FlxG.shake(0.05f, 2);
		}
	};
}
