package org.flixel.examples.bomberplanet;

import org.flixel.*;

import com.badlogic.gdx.utils.Array;

public class Powerup extends FlxSprite
{
	static public String ImgPowerups = "examples/bomberplanet/data/pack:powerups"; 
	static public String SndBomb = "examples/bomberplanet/data/bomb_up.mp3"; 
	static public String SndHealth = "examples/bomberplanet/data/health_up.mp3"; 
	static public String SndHealthRefill = "examples/bomberplanet/data/health_refill.mp3"; 

	public Array<String> sounds;
	public int index;

	public Powerup()
	{
		super();
		loadGraphic(ImgPowerups,true);
		sounds = new Array<String>(new String[]{SndBomb,SndHealth,SndHealthRefill});
		index = 0;
	}

	public void resetPowerup(float X, float Y, int Type, int Index)
	{
		reset(X,Y);
		setFrame(Type);
		index = Index;
	}

	public void resetPowerup(float X, float Y, int Type)
	{
		resetPowerup(X,Y,Type,0);
	}
	
	@Override
	public void kill()
	{
		super.kill();
		FlxG.play(sounds.get(getFrame()),0.65f);
		if(getFrame() == 1) //health increase
		{
			FlxG.levels.set(index, true);

			FlxSave save = new FlxSave();
			if(save.bind("escape"))
			{
				boolean[] health = save.data.get("health", boolean[].class);
				health[index] = true;
				save.data.put("health", health);
				save.close();
			}
		}
	}
}