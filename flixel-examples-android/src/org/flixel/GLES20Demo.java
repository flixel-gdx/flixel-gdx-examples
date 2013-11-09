package org.flixel;

import android.os.Bundle;

/**
 *
 * @author Ka Wing Chin
 */
public class GLES20Demo extends FlxAndroidApplication
{

	public GLES20Demo()
	{
		super(new org.flixel.examples.gles20.GLES20Demo());
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		cfg.useGL20 = true;
		super.onCreate(savedInstanceState);
	}

}

