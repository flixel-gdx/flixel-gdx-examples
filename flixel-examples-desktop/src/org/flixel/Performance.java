package org.flixel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Performance
{
	public static void main(String[] args)
	{
		new LwjglApplication(new org.flixel.examples.performance.Performance(), "", 480, 320, false);
	}
}
