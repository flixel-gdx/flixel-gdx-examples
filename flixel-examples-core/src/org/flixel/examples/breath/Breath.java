package org.flixel.examples.breath;

import org.flixel.FlxGame;

public class Breath extends FlxGame{

	//public static int bgcolor = 0xff303030;
    //public static int bgcolor = 0xffc0c0c0;
    //public static int bgcolor = 0x66161616;
    public static int bgcolor = 0xaa3de7c7;
	
	public Breath() {
		super(320, 160, PlayState.class, 2, 30, 30);
		//FlxG.setBgColor(bgcolor);
	}

}
