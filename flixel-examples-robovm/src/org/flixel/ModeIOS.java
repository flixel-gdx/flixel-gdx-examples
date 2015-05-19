package org.flixel;

import org.flixel.examples.mode.Mode;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class ModeIOS extends IOSApplication.Delegate
{
	@Override
	protected IOSApplication createApplication()
	{
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		config.orientationLandscape = true;
		config.orientationPortrait = false;
		FlxGame game = new Mode();
		return new IOSApplication((ApplicationListener) game.stage, config);
	}

	public static void main(String[] argv)
	{
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(argv, null, ModeIOS.class);
		pool.drain();
	}
}
