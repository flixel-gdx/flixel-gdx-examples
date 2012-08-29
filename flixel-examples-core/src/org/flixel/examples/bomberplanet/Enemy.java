package org.flixel.examples.bomberplanet;

import org.flixel.*;

public class Enemy extends FlxSprite
{
	 static public String ImgEnemy = "examples/bomberplanet/data/pack:enemy";

	public static FlxPoint midworld;

	public float timeout;
	public float speed;

	public Enemy(float X, float Y)
	{
		super(X, Y);
		loadGraphic(ImgEnemy,true);
		addAnimation("walk",new int[]{0,1,0,2},8);
		addAnimation("dead",new int[]{3},0,false);
		play("walk");
		width = height = 12;
		offset.x = offset.y = 2;

		if(midworld == null)
			midworld = new FlxPoint(256*8.5f,256*8.5f);
		float ratio = FlxU.getDistance(getMidpoint(),midworld)/(256*9);
		if(ratio > 1)
			ratio = 1;
		ratio *= ratio;

		timeout = 0;
		speed = 20 + (int)(ratio*120);
		health = 1 + (int)(ratio*5);

		if(health >= 4)
			setColor(0xdf7a92);
		else if(health == 3)
			setColor(0xbe3241);
		else if(health == 2)
			setColor(0xf7e176);

		elasticity = 1;
	}

	@Override 
	public void update()
	{
		if(!alive || !onScreen())
			return;

		timeout -= FlxG.elapsed;
		if(timeout <= 0)
		{
			timeout = 1 + FlxG.random();
			if(velocity.x == 0)
			{
				velocity.x = speed*((FlxG.random()>0.5f)?-1:1);
				velocity.y = 0;
			}
			else
			{
				velocity.x = 0;
				velocity.y = speed*((FlxG.random()>0.5f)?-1:1);
			}
		}
	}

	@Override 
	public void hurt(float Damage)
	{
		if(getFlickering())
			return;
		super.hurt(Damage);
		if(alive)
			flicker();
	}

	@Override
	public void kill()
	{
		super.kill();
		exists = true;
		visible = true;
		play("dead");
		setSolid(false);
		velocity.x = 0;
		velocity.y = 0;
		if(FlxG.random()<0.65f)
			((PlayState)FlxG.getState()).spawnPowerup(x,y);
	}
}