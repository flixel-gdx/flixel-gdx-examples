package org.flixel.examples.bomberplanet;

import org.flixel.*;

public class Bomb extends FlxSprite
{
	static public String ImgBomb = "examples/bomberplanet/data/pack:bomb";
	static public String SndPlace = "examples/bomberplanet/data/bomb_place.mp3";
	static public String SndExplode = "examples/bomberplanet/data/bomb_explode.mp3";

	public float timeout;

	public Bomb()
	{
		super();
		loadGraphic(ImgBomb,true);
		addAnimation("tick",new int[]{0,1,2,3},2,false);
		immovable = true;
	}

	@Override
	public void update()
	{
		timeout -= FlxG.elapsed;
		if(timeout <= 0)
			kill();
	}

	@Override
	public void reset(float X, float Y)
	{
		super.reset(X,Y);
		play("tick");
		timeout = 2;
		FlxG.play(SndPlace,0.5f);
	}

	@Override
	public void kill()
	{
		super.kill();

		FlxG.play(SndExplode,0.35f);
		((Hitbox) ((PlayState)FlxG.getState()).hitboxes.recycle(Hitbox.class)).resetHitbox(x-12,y-12,40,40);
		((Poof) ((PlayState)FlxG.getState()).poofs.recycle(Poof.class)).reset(x-16,y-16);
		FlxG.camera.shake(0.01f,0.2f);

		free();
	}

	public void free()
	{
		((PlayState)FlxG.getState()).player.bombsInc();
	}
}