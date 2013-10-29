package org.flixel;

import android.os.Bundle;

/**
 *
 * @author Ka Wing Chin
 */
public class BlendDemo extends FlxAndroidApplication
{

	public BlendDemo()
	{
		super(new org.flixel.examples.blend.BlendDemo());		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		cfg.useGL20 = true;
		super.onCreate(savedInstanceState);
	}

}

