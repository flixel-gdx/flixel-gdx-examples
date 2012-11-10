package org.flixel;

public class SensorDemo extends FlxAndroidApplication
{
	public SensorDemo()
	{
		super(new org.flixel.examples.sensor.SensorDemo());
		cfg.useAccelerometer = true;
	}
}
