package org.flixel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Box2DDemo
{
	public static void main(String[] args)
	{
		new LwjglApplication(new org.flixel.examples.box2d.Box2DDemo(), "", 640, 360, false);
	}
}
