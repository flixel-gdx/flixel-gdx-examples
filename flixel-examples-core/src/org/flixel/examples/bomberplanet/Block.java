package org.flixel.examples.bomberplanet;

import org.flixel.*;

public class Block extends FlxSprite
{
	static public String ImgBlock = "examples/bomberplanet/data/pack:block";

	public Block(float X, float Y)
	{
		super(X, Y);
		loadGraphic(ImgBlock,true);
		immovable = true;
	}

	public Block()
	{
		this(0,0);
	}
	
	@Override
	public void kill()
	{
		super.kill();
		exists = true;
		visible = true;
		setFrame(1);
		setSolid(false);

		FlxEmitter bb = ((PlayState)FlxG.getState()).blockbits;
		bb.at(this);
		bb.start(true,0.65f,0,8);
		bb.update(); //cheating!!

		if(FlxG.random()<0.35f)
			((PlayState) FlxG.getState()).spawnPowerup(x,y);
	}
}