package org.flixel.examples.bomberplanet;

import org.flixel.*;
import org.flixel.event.IFlxCamera;

public class VictoryState extends FlxState
{
	static public String ImgStars = "examples/bomberplanet/data/pack:stars"; 

	public boolean ok;
	public FlxText text;

	@Override 
	public void create()
	{
		ok = false;
		FlxG.flash(0xff000000,5,flashed);

		add(new FlxSprite(0,0,ImgStars));

		text = new FlxText(40,FlxG.height,FlxG.width-80,"And like that, it was over.  No more danger, no more uncertainty, no more confusion.\n\nI was back with people I knew and places I recognized.  By Monday I would be back in the office, with a mountain of paperwork to try to explain the lost ship to the insurance company.  No more beaches... no more mountains...\n\nno more excitement...");
		text.velocity.y = -10;
		add(text);
	}

	@Override
	public void update()
	{
		super.update();

		if((text.y < 0) || (ok && FlxG.keys.any()))
			FlxG.fade(0xff000000,2,faded);
	}

	protected IFlxCamera flashed = new IFlxCamera()
	{
		@Override	
		public void callback()
		{
			ok = true;
		}
	};
	
	protected IFlxCamera faded = new IFlxCamera()
	{
		@Override	
		public void callback()
		{
			FlxG.resetGame();
		}
	};
}