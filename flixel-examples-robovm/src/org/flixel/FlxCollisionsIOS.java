package org.flixel;

import org.flixel.examples.flxcollisions.FlxCollisions;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class FlxCollisionsIOS extends IOSApplication.Delegate
{
	public static void main(String[] args)
	{
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(args, null, FlxCollisionsIOS.class);
		pool.drain();		
	}

	@Override
	protected IOSApplication createApplication()
	{
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		config.orientationLandscape = true;
		config.orientationPortrait = false;
		FlxGame game = new FlxCollisions();
		return new IOSApplication((ApplicationListener) game.stage, config);
	}
}
