package org.flixel;

import android.os.Bundle;

/**
 *
 * @author Ka Wing Chin
 */
public class BitmapFontDistance extends FlxAndroidApplication
{

	public BitmapFontDistance()
	{
		super(new org.flixel.examples.distancefieldfont.BitmapFontDistance());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		cfg.useGL20 = true;
		super.onCreate(savedInstanceState);
	}
}

