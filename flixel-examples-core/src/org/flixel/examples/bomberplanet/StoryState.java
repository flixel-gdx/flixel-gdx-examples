package org.flixel.examples.bomberplanet;

import org.flixel.*;
import org.flixel.event.AFlxCamera;

public class StoryState extends FlxState
{
	static public String ImgStars ="examples/bomberplanet/data/pack:stars"; 

	public boolean ok;
	public FlxText text;

	@Override public void create()
	{
		ok = false;
		FlxG.flash(0xff000000,5);

		add(new FlxSprite(0,0,ImgStars));

		text = new FlxText(40,FlxG.height,FlxG.width-80,"Crashing on this island was about the best I could hope for, given the condition of the ship.  According to my records there should be an abandoned spacepad on a mountain nearby.  My best chance for escape lies in locating this spacepad...\n\n...if it even exists.\n\nThankfully, my suit's speed boosters still work (X) and I might be able to use these spent fuel cells to help get by obstacles (C).\n\nLet's see what's out there...");
		text.velocity.y = -10;
		add(text);
	}

	@Override
	public void update()
	{
		super.update();

		if((text.y < 0) || FlxG.keys.any())
			FlxG.fade(0xff000000,1,skip);
	}

	public AFlxCamera skip = new AFlxCamera()
	{
		@Override
		public void callback()
		{
			FlxG.switchState(new PlayState());
		}
	};
}