package org.flixel.examples.controller;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxText;

/**
 * Gamepad analog test.
 * 
 * @author Ka Wing Chin
 */
public class PlayState2 extends Test
{	
	@Override
	public void create()
	{
		super.create();
		FlxG.setBgColor(0xff333333);
		
		// Add some wall around the edges.
		FlxSprite s;
		add(s = new FlxSprite(0, 0).makeGraphic(FlxG.width, 2));
		s.immovable = true;
		add(s = new FlxSprite(0, FlxG.height-2).makeGraphic(FlxG.width, 2));
		s.immovable = true;
		add(s = new FlxSprite(0, 0).makeGraphic(2, FlxG.height));
		s.immovable = true;
		add(s = new FlxSprite(FlxG.width-2, 0).makeGraphic(2, FlxG.height));
		s.immovable = true;
		
		add(new FlxText(10, FlxG.height-20, FlxG.width, "Use analog to move"));
		
		add(new Block(10, 10, 0xFFFF0000, gamepad1));
		add(new Block(50, 10, 0xFF00FF00, gamepad2));
		add(new Block(100, 10, 0xFF0000FF, gamepad3));
	}
	
	@Override
	public void update()
	{
		super.update();
		FlxG.collide();
	}
}

